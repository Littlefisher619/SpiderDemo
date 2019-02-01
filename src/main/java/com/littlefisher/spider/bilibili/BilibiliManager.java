package com.littlefisher.spider.bilibili;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.littlefisher.spider.bilibili.Settings.THREADPOOL_SIZE;
import static com.littlefisher.spider.util.JobUtil.executeJobAndWaitForComplete;


public class BilibiliManager {
    private final static Logger logger = LogManager.getLogger(BilibiliManager.class);

    public static String doGetAV(int avid) {

        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL("http://api.bilibili.com/x/web-interface/view?aid="+avid);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(Settings.CONN_TIMEOUT);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(Settings.READ_TIMEOUT);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }
    public static void start(){

        List<BilibiliJob> jobs=getJobs();
        //for (BilibiliJob job:jobs)
        //    logger.debug(job.getAvIdFrom()+" "+job.getAvIdTo());
        executeJobAndWaitForComplete(jobs,"Bilibili AV Info dump",3600,THREADPOOL_SIZE);
        logger.info("Bilibili jobs done.");
    }
    private static List<BilibiliJob> getJobs(){
        List<BilibiliJob> jobs=new ArrayList<>();
        //10 divided into 4
        //1-4 5-6 7-8 9-10
        int div=Settings.BI_MAX/Settings.BI_DIVIDE_COUNT;//2
        int left=Settings.BI_MAX%Settings.BI_DIVIDE_COUNT;//2
        jobs.add(new BilibiliJob(1,div+left));
        for(int i=div+left+1;i<=Settings.BI_MAX;i+=div){
            jobs.add(new BilibiliJob(i,i+div-1));
        }
        return  jobs;


    }



}
