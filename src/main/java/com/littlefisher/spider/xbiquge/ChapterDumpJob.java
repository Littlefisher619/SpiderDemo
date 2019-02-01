package com.littlefisher.spider.xbiquge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;
import java.util.UUID;

public class ChapterDumpJob extends Thread {
    private final Logger logger = LogManager.getLogger(ChapterDumpJob.class);
    private String novelName;
    private String chapter;
    private String chapterURL;
    private String jobid;
    private String prefix;
    ChapterDumpJob(String novelName, String chapter, String chapterURL, String jobid){
        this.novelName=novelName;
        this.chapter=chapter;
        this.chapterURL=chapterURL;
        this.jobid=jobid;
    }
    ChapterDumpJob(String novelName, String chapter, String chapterURL){
        this.novelName=novelName;
        this.chapter=chapter;
        this.chapterURL=chapterURL;
        this.jobid=UUID.randomUUID().toString();
    }
    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    @Override
    public void run() {


        boolean overrideflag= false,existflag=false;
        this.prefix = String.format("Thread %d(%s): novel-dump %s - %s ", Thread.currentThread().getId(), this.jobid, chapter,novelName);
        logger.info(prefix +"has been activated!");
        WebDriver driver=null;
        try {
            driver = WebDriverUtil.getNewWebDriver();
        }catch (Exception e){
            logger.error(prefix+ "failed to init webdriver!");
            logger.info(prefix+ "Interrupted due to an error!");
            return;
        }


        try{
            DAOUtil.finishJob(jobid);
            DAOUtil.cacheChapterDumpJob(novelName,chapter,chapterURL,jobid);
        }catch (SQLException e){
            logger.info(prefix+"failed to cache job into database due to a SQLERROR.",e);
        }


        try{
            existflag= DAOUtil.checkChapterExist(chapter,novelName) ;
        }catch (SQLException e) {
            logger.error(prefix+"cannot check chapter exist or not due to SQLERROR",e);
            logger.info(prefix+ "Interrupted due to an error!");
            driver.quit();
            return;
        }
        overrideflag=Settings.OVERRIDE_EXISTING_CHAPTER && existflag;
        if(!existflag || overrideflag) {
            try {
                logger.info(prefix+"opening page...");
                driver.get(chapterURL);
                Document document = Jsoup.parse(driver.getPageSource());

                String content = document.getElementById("content").wholeText().replaceAll("亲,点击进去.*http://m\\.xbiquge\\.la.*无广告清新阅读！", "");
                try {
                    if (overrideflag) {
                        logger.info(prefix+"update content...");
                        DAOUtil.updateChapter(novelName,chapter, content);
                    } else {
                        logger.info(prefix+"import content...");
                        DAOUtil.insertNewChapter(novelName, chapter, content, chapterURL);
                    }
                }catch (SQLException e){
                    logger.error(prefix+"failed to save data due to a SQLERROR.",e);
                    driver.quit();
                    return;
                }
            } catch (Exception e) {
                logger.error(prefix + "failed to dump chapter.",e);
                driver.quit();
                return;
            }
        }else logger.info("Chapter already exist! Skip...");


        driver.quit();

        try{
            DAOUtil.finishJob(jobid);
        }catch (SQLException e){
            logger.info(prefix+"failed to clear job cache from database due to a SQLERROR.",e);
        }
        logger.info(prefix+"finished!");

    }
}
