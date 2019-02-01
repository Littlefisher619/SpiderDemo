package com.littlefisher.spider.bilibili;

public class Settings {
    public static int BI_DIVIDE_COUNT=5000;
    public static int BI_MAX=1000000;
    public static int THREADPOOL_SIZE=4;
    public static int CONN_TIMEOUT=15000;//ms
    public static int READ_TIMEOUT=60000;//ms
    public static void parseSettingsFromParams(String []args)
    {
        for (String str:
                args) {
            if(str.contains("--th"))
                THREADPOOL_SIZE=Integer.parseInt(str.substring(4));
            if(str.contains("--divide"))
                BI_DIVIDE_COUNT=Integer.parseInt(str.substring(8));
            if(str.contains("--to"))
                BI_MAX=Integer.parseInt(str.substring(4));
            if(str.contains("--conntimeout"))
                CONN_TIMEOUT=Integer.parseInt(str.substring(13));
            if(str.contains("--readtimeout"))
                READ_TIMEOUT=Integer.parseInt(str.substring(13));
        }
    }
}
