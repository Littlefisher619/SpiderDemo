package com.littlefisher.spider.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class JobUtil {
    private final static Logger logger = LogManager.getLogger(JobUtil.class);

    public static void executeJobAndWaitForComplete(List<? extends Thread> jobs, String description, int timeout,int thread){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(thread);
        final int jobCount = jobs.size();

        for (int i = 0; i < jobCount; i++) {
            final int index = i;

            fixedThreadPool.execute(jobs.get(index));
        }

        try {
            fixedThreadPool.shutdown();
            if (!fixedThreadPool.awaitTermination(timeout, TimeUnit.SECONDS)) {
                logger.error("Timeout for "+description);
                fixedThreadPool.shutdownNow();
            }
        } catch (Throwable e) {
            fixedThreadPool.shutdownNow();

            logger.error("Unknown error occurred: ",e);
        }
    }
}
