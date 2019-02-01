package com.littlefisher.spider.bilibili;

import com.google.gson.Gson;
import com.littlefisher.spider.bilibili.beans.ResponseBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class BilibiliJob extends Thread{
    public int getAvIdFrom() {
        return avIdFrom;
    }

    public void setAvIdFrom(int avIdFrom) {
        this.avIdFrom = avIdFrom;
    }

    public int getAvIdTo() {
        return avIdTo;
    }

    public void setAvIdTo(int avIdTo) {
        this.avIdTo = avIdTo;
    }

    private int avIdFrom,avIdTo;
    private final Logger logger = LogManager.getLogger(BilibiliManager.class);
    private String prefix;
    BilibiliJob(int avIdFrom,int avIdTo)
    {
        this.avIdFrom=avIdFrom;
        this.avIdTo=avIdTo;
    }


    @Override
    public void run() {


        for(int i=avIdFrom;i<=avIdTo;i++) {
            this.prefix = String.format("Thread %d(%d~%d) - AV %d - ", Thread.currentThread().getId(), avIdFrom,avIdTo,i);
            try{
                if(DAOUtil.checkAVExist(i)){
                    logger.info(prefix + "AV already exist! Skip...");
                    continue;
                }
            }catch (SQLException e){
                logger.error(prefix+"Cannot check av exist or not dut to a SQLERROR!",e);
                continue;
            }


            String jsonStr = BilibiliManager.doGetAV(i);

            if(jsonStr==null) {
                logger.error(prefix + "Cannot get response!");
                continue;
            }
            logger.debug(prefix+jsonStr);

            Gson gson = new Gson();
            ResponseBean responseBean = gson.fromJson(jsonStr, ResponseBean.class);
            final int code=responseBean.getCode();
            if(code!=0) {
                logger.info(prefix+"Get AV Info failed! Message:"+responseBean.getMessage());
                if(code==-404 || code==62002 || code==-403){
                    logger.info(prefix+"AV not found");
                    try{
                        DAOUtil.insertNewAV(i,code,responseBean.getMessage());
                    }catch (SQLException e){
                        logger.error(prefix+"Cannot save AV to DB.",e);
                    }
                }else logger.info(prefix+"Unknown response code!");
                continue;
            }
            logger.info(prefix+"AV Info dumped.");
            try {
                String title = responseBean.getData().getTitle();
                String desc = responseBean.getData().getDesc();
                String author = responseBean.getData().getOwner().getName();
                String pages = gson.toJson(responseBean.getData().getPages());
                //List<ResponseBean.DataBean.PagesBean> pagesBeanList=gson.fromJson(pages, new TypeToken<List<ResponseBean.DataBean.PagesBean>>(){}.getType());
                //logger.debug(pagesBeanList.size());
                int view = responseBean.getData().getStat().getView();
                int danmu = responseBean.getData().getStat().getDanmaku();

                int pubdate = responseBean.getData().getPubdate();
                String pic = responseBean.getData().getPic();
                int videos = responseBean.getData().getVideos();//pagescount

                //logger.debug(String.format("%s %s %s %s %d %d %d %s %d", title, desc, author, pages, view, danmu, pubdate, pic, videos));
                DAOUtil.insertNewAV(i,title, desc, author, pages, view, danmu, pubdate, pic, videos);
            }catch (NullPointerException e){
                logger.error(prefix+"Cannot get AV Info!",e);


            }catch (SQLException e){
                logger.error(prefix+"Cannot save AV to DB.",e);
            }

        }
    }
}
