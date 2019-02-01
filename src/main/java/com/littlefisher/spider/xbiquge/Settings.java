package com.littlefisher.spider.xbiquge;

public class Settings {
    public static int SUMMARY_FETCH_TIMEOUT=3600,CHAPTER_DUMP_TIMEOUT=3600,PAGELOAD_TIMEOUT=15;//seconds
    public static boolean IGNORE_NOVEL_UPDATE_CHECK=false;
    //program will ignore updates of novel.
    public static boolean OVERRIDE_EXISTING_CHAPTER=false;
    //by default, when this toggle is off, program will skip it when dump a existing chapter.
    public static boolean SKIP_SUMMARY_FETCH=false;

    public static int THREADPOOL_SIZE=4;

    public static String CHROME_DRIVER_PATH="D:\\selenium\\chromedriver.exe";
    public static String REMOTE_DRIVER_URL="http://localhost:4444/wd/hub";
    public static void parseSettingsFromParams(String []args)
    {
        //spider.jar CHROME_DRIVER_PATH REMOTE_DRIVER_URL
        //spider.jar D:\selenium\chromedriver.exe http://localhost:4444/wd/hub --skipsf --th16
        if(args.length<3) {
            System.out.println("Illegal arguments! Usage example: ");
            System.out.println("java -jar spider.jar xbiquge D:\\selenium\\chromedriver.exe http://localhost:4444/wd/hub --skipsf --th16");
            System.exit(0);
        }
        CHROME_DRIVER_PATH = args[1];
        REMOTE_DRIVER_URL = args[2];
        for (String str:
             args) {
            if(str.contains("--th"))
                THREADPOOL_SIZE=Integer.parseInt(str.substring(4));
            if(str.contains("--sftimeout"))
                SUMMARY_FETCH_TIMEOUT=Integer.parseInt(str.substring(11));
            if(str.contains("--cdtimeout"))
                CHAPTER_DUMP_TIMEOUT=Integer.parseInt(str.substring(11));
            if(str.contains("--pltimeout"))
                PAGELOAD_TIMEOUT=Integer.parseInt(str.substring(11));
            if(str.contains("--skipsf"))
                SKIP_SUMMARY_FETCH=true;
            if(str.contains("--override"))
                OVERRIDE_EXISTING_CHAPTER=true;
            if(str.contains("--ignoreupdate"))
                IGNORE_NOVEL_UPDATE_CHECK=true;

        }
    }




}
