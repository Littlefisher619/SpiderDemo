package com.littlefisher.spider.xbiquge;

import com.littlefisher.spider.util.ConsoleUtil;
import com.littlefisher.spider.xbiquge.model.Novel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.littlefisher.spider.util.JobUtil.executeJobAndWaitForComplete;
import static com.littlefisher.spider.xbiquge.Settings.*;

public class XbiqugeManager {
    private static final Logger logger = LogManager.getLogger(XbiqugeManager.class);
    public static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");




    public static List<SummaryFetchJob> getSummaryFetchJobs(WebDriver driver){
        driver.get("http://www.xbiquge.la/xiaoshuodaquan/");

        List<SummaryFetchJob> jobs=new ArrayList<SummaryFetchJob>();
        Document document = Jsoup.parse(driver.getPageSource());
        List<Element> novelCatalogList=document.getElementsByClass("novellist");

        if(IGNORE_NOVEL_UPDATE_CHECK)
            logger.info("IGNORE_NOVEL_UPDATE_CHECK Toggle is ON!");
        for (Element novelcatalog:
                novelCatalogList) {
            List<Element> liTags=novelcatalog.getElementsByTag("li");
            //logger.info(novelcatalog.getElementsByTag("h2"));
            //logger.info(novels.size());
            for (Element liTag:
                    liTags) {
                String novelTitle=liTag.text(),novelURL=liTag.getElementsByTag("a").get(0).attr("href");
                try {
                    if (IGNORE_NOVEL_UPDATE_CHECK && DAOUtil.checkNovelExist(novelTitle)) {
                        logger.info("Skip "+ novelTitle);
                        continue;
                    }
                }catch (SQLException e){
                    logger.error("Cannot check novel exist or not due to a SQLERROR!",e);
                }
                logger.debug(String.join(" ","Add",novelURL,novelTitle,"to summary-fetch job list..." ) );
                jobs.add(new SummaryFetchJob(novelURL,novelTitle));
            }


        }
        return jobs;

    }

    private static List<ChapterDumpJob> getChapterDumpJobs(WebDriver driver, String novelName, String novelURL){
        driver.get(novelURL);
        List<ChapterDumpJob> jobs=new ArrayList<>();
        Document document = Jsoup.parse(driver.getPageSource());

        List<Element> aTags=document.getElementById("list").getElementsByTag("a");
        logger.info(aTags.size()+" chapters found!");
        if(OVERRIDE_EXISTING_CHAPTER)
            logger.info("OVERRIDE_EXISTING_CHAPTER Toggle is ON!");
        for(Element aTag:aTags) {
            String chapterTitle=aTag.text(),chapterURL="http://www.xbiquge.la"+ aTag.attr("href");
            try {

                if(DAOUtil.checkChapterExist(chapterTitle,novelName)){
                    if (OVERRIDE_EXISTING_CHAPTER) {
                        logger.info(String.format("Override chapter %s in novel %s",chapterTitle,novelName));
                        jobs.add(new ChapterDumpJob(novelName, chapterTitle,chapterURL));

                    }

                }else jobs.add(new ChapterDumpJob(novelName, chapterTitle,chapterURL));

            }catch (SQLException e){
                logger.error("Cannot check chapter exist or not due to a SQLERROR!",e);
            }


        }


        return jobs;

    }

    public static void resume(){

        List<SummaryFetchJob> jobs1=null;
        List<ChapterDumpJob> jobs2=null;
        while(true) {

            try {
                jobs1 = DAOUtil.getCachedSummaryFetchJobs();
                jobs2 = DAOUtil.getCachedChapterDumpJobs();
                if (jobs1.size() == 0 && jobs2.size() == 0) {
                    logger.info("There are no jobs need to resume!");
                    break;
                }
                logger.info(String.format( "There are %d summary-fetch jobs and %d chapter-dump jobs not completed fully",jobs1.size(),jobs2.size()));
                if(ConsoleUtil.yesOrNo("Would you want to skip them and then force continue",false)==1){
                    DAOUtil.clearCache("summary fetch");
                    DAOUtil.clearCache("chapter dump");
                    break;
                }
                executeJobAndWaitForComplete(jobs1, "summary fetch", SUMMARY_FETCH_TIMEOUT,THREADPOOL_SIZE);
                executeJobAndWaitForComplete(jobs2, "chapter dump", CHAPTER_DUMP_TIMEOUT,THREADPOOL_SIZE);
            }catch (SQLException e){
                logger.fatal("SQLERROR!",e);
                exit();
            }
        }
    }
    public static void start(){

        logger.info("Spider for xbiquge start.");

        logger.info("Connect to mysql...");
        try{
            DAOUtil.checkConnection();
        }catch (Exception e){
            logger.fatal("Mysql connection test failed.",e);
            exit();
        }

        resume();

        logger.info("Init WebDriver...");
        WebDriver driver=null;
        try{
            driver= WebDriverUtil.getNewWebDriver();
        }catch (Exception e){
            logger.fatal("An error occurred while create a webdriver.",e);
            exit();
        }

        logger.info("Spider is ready!");


        logger.info("Fetching novel list for summary fetch...");

        if(!SKIP_SUMMARY_FETCH) {
            logger.info("Generating summary fetch jobs...");
            List<SummaryFetchJob> jobs=null;


            try{
                jobs=getSummaryFetchJobs(driver);

            }catch (Exception e){
                logger.fatal("failed to read novel list.");
                exit();
            }

            HashMap<String, SummaryFetchJob> jobidTojob=new HashMap<>();
            for(SummaryFetchJob job:jobs)
                jobidTojob.put(job.getJobid(),job);

            logger.info(jobs.size()+" jobs generated for fetching summary.");
            logger.info("Waiting for summary fetching jobs.");
            while (true) {
                executeJobAndWaitForComplete(jobs, "summary fetch", SUMMARY_FETCH_TIMEOUT,THREADPOOL_SIZE);
                try {
                    List<String> failedJobIDList = DAOUtil.getCachedJobIDs("sfjob");
                    final int size=failedJobIDList.size();

                    if(size==0)break;
                    else {
                        logger.info(size + " summary-fetch jobs have not completed fully.");
                        if(ConsoleUtil.yesOrNo("Would you want to clear them and then force continue?",false)==1){
                            try {
                                DAOUtil.clearCache("sfjob");
                            }catch (SQLException e){
                                logger.error("fail to clear job cache due to a SQLERROR.",e);
                            }
                            break;
                        }
                    }
                    List<SummaryFetchJob> newJobList=new ArrayList<>();
                    for(String jobid:failedJobIDList)
                        newJobList.add( jobidTojob.get(jobid));

                    jobs.clear();
                    jobs=newJobList;
                }catch (SQLException e){
                    logger.error("fail to get job cache due to a SQLERROR.",e);
                    break;
                }
                logger.info("ready to re-execute failed jobs");
            }
            logger.info("Summary fetching job completed.");

        }else logger.info("SKIP_SUMMARY_FETCH Toggle is ON. Skip!");


        List<Novel> novelList=null;
        logger.info("Get novel-dump list from database...");
        try {
            novelList=DAOUtil.getNovelsWaitForDump();
        }catch (SQLException e){
            logger.error("fail to get novel-dump list",e);
            exit();
        }
        logger.info(String.format("Novel-dump list has %d in total",novelList.size()));


        final int dumpJobCount=novelList.size();
        for (int i=0;i<dumpJobCount;i++){

            Novel novel=novelList.get(i);
            logger.info( String.format("(%d/%d) dumping %s",i+1,dumpJobCount,novel.getNovelName()));
            logger.info("Generating chapter dump jobs...");
            List<ChapterDumpJob> jobs=null;
            try{
                jobs=getChapterDumpJobs(driver,novel.getNovelName(),novel.getNovelURL());
            }catch (Exception e){
                logger.fatal("failed to read chapter list.");
                exit();
            }



            logger.info(String.format("%d jobs generated for dumping %s",jobs.size(),novel.getNovelName()));
            logger.info("Waiting for chapter dump jobs.");
            if(OVERRIDE_EXISTING_CHAPTER)
                logger.info("OVERRIDE_EXISTING_CHAPTER Toggle is ON!");


            HashMap<String, ChapterDumpJob> jobidTojob=new HashMap<>();
            for(ChapterDumpJob job:jobs)
                jobidTojob.put(job.getJobid(),job);

            while (true) {

                executeJobAndWaitForComplete(jobs,"chapter dump",CHAPTER_DUMP_TIMEOUT,THREADPOOL_SIZE);
                try {
                    List<String> failedJobIDList = DAOUtil.getCachedJobIDs("cdjob");
                    final int size=failedJobIDList.size();

                    if(size==0)break;
                    else {
                        logger.info(size + " chapter-dump jobs have not completed fully.");
                        if(ConsoleUtil.yesOrNo("Would you want to clear them and then force continue?",false)==1){
                            try {
                                DAOUtil.clearCache("cdjob");
                            }catch (SQLException e){
                                logger.error("fail to clear job cache due to a SQLERROR.",e);
                            }
                            break;
                        }
                    }
                    List<ChapterDumpJob> newJobList=new ArrayList<>();
                    for(String jobid:failedJobIDList)
                        newJobList.add( jobidTojob.get(jobid));

                    jobs.clear();
                    jobs=newJobList;

                }catch (SQLException e){
                    logger.error("fail to get job cache due to a SQLERROR.",e);
                    break;
                }
                logger.info("ready to re-execute failed jobs");
            }

            logger.info("Chapter dump jobs completed.");


            try{
                DAOUtil.updateProgress(novel.getNovelName(),"done");
            }catch (SQLException e){

                logger.error("Cannot update progress to done due to a SQLERROR",e);
            }
        }

        logger.info("Xbiquge jobs done.");
    }
    private static void exit(){
        logger.info("Program exit now...");
        System.exit(0);
    }

}
