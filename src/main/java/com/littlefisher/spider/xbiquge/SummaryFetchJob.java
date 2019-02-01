package com.littlefisher.spider.xbiquge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class SummaryFetchJob extends Thread {
    private final Logger logger = LogManager.getLogger(SummaryFetchJob.class);
    private String novelTitle;
    private String url;
    private String prefix;
    private String jobid;
    SummaryFetchJob(String url, String novelTitle, String jobid){
        this.novelTitle=novelTitle;
        this.url=url;
        this.jobid= jobid;
    }
    SummaryFetchJob(String url, String novelTitle){
        this.novelTitle=novelTitle;
        this.url=url;
        this.jobid=UUID.randomUUID().toString();
    }

    public static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    @Override
    public void run() {

        boolean ERROR=false;
        this.prefix=String.format("Thread %d(%s): fetch-summary %s",Thread.currentThread().getId(), this.jobid,novelTitle);
        logger.info(prefix +"has been activated!");

        WebDriver webDriver=null;
        try {
            webDriver = WebDriverUtil.getNewWebDriver();
        }catch (Exception e){
            logger.fatal(prefix+ "failed to init webdriver!",e);
            logger.info(prefix+ "Interrupted due to an error!");
            return;
        }
        try{
            DAOUtil.finishJob(jobid);
            DAOUtil.cacheSummaryFetchJob(novelTitle,url,jobid);
        }catch (SQLException e){
            logger.info(prefix+"failed to cache job into database due to a SQLERROR.",e);
        }
        try {
            logger.info(prefix+"opening page...");
            webDriver.get(url);
            Document document = Jsoup.parse(webDriver.getPageSource());
            Element info = document.getElementById("info");
            List<Element> ptags = info.getElementsByTag("p");
            String author = ptags.get(0).text().substring(4);

            String lastupdate = ptags.get(2).text().substring(5);
            String summary = document.getElementById("intro").getElementsByTag("p").get(1).text();
            String imgsrc = document.getElementById("fmimg").getElementsByTag("img").attr("src");

            logger.info(prefix + String.format("%s - %s - %s (%s)",  author, lastupdate,summary, imgsrc));
            try {
                if (!DAOUtil.checkNovelExist(novelTitle)) {
                    DAOUtil.insertNewNovel(novelTitle, author, summary, imgsrc, url, "no", lastupdate);
                    logger.info(prefix+" - New novel found!");
                } else {

                    if(DAOUtil.checkNovelHasUpdate(novelTitle,lastupdate)){
                        logger.info(prefix+" - Novel update detected!");
                        DAOUtil.updateProgress(novelTitle,"no");
                        DAOUtil.updateLastUpdate(novelTitle,lastupdate);
                    }

                }
            }catch (SQLException e){
                logger.error(prefix + "SQLERROR!",e);
                webDriver.quit();
                return;
            }
        }catch (Exception e){
            logger.error(prefix+"failed to fetch data.",e);
            webDriver.quit();
            return;
        }

        webDriver.quit();

        try{
            DAOUtil.finishJob(jobid);
        }catch (SQLException e){
            logger.info(prefix+"failed to clear job cache from database due to a SQLERROR.",e);
        }

        logger.info(prefix+"finished!");
    }

}
