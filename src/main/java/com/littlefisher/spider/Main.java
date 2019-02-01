package com.littlefisher.spider;

import com.littlefisher.spider.bilibili.BilibiliManager;
import com.littlefisher.spider.xbiquge.XbiqugeManager;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        if(args.length<1) {
            System.out.println("Illegal arguments! Usage example: ");
            System.out.println("java -jar spider.jar xbiquge D:\\selenium\\chromedriver.exe http://localhost:4444/wd/hub --skipsf --th16");
            System.out.println("java -jar spider.jar bilibili --to500000 --divide1000 --th16");
            System.exit(0);
        }
        switch (args[0]){
            case "xbiquge":
                com.littlefisher.spider.xbiquge.Settings.parseSettingsFromParams(args);
                XbiqugeManager.start();
                break;
            case "bilibili":
                com.littlefisher.spider.bilibili.Settings.parseSettingsFromParams(args);
                BilibiliManager.start();
                break;
            case "help":
                System.out.println(String.join("\n",
                        "For bilibili:",
                        "[Required Arguments]",
                        "None",
                        "[Optional Arguments]",
                        "--th: Threads",
                        "--divide: Jobs to divided into",
                        "--to: Max id to dump",
                        "--conntimeout: Connection Timeout (ms)",
                        "--readtimeout: Timeout for reading response (ms)"
                        ));
                System.out.println();
                System.out.println(String.join("\n",
                        "For xbiquge",
                        "[Required Arguments]",
                        "args[1]: CHROME_DRIVER_PATH",
                        "args[2]: Selenium's REMOTE_DRIVER_URL",
                        "[Optional Arguments]",
                        "--th: Threads",
                        "--sftimeout: Summary Fetch Job Timeout In total (s)",
                        "--cdtimeout: Chapter Dump Job Timeout In total (s)",
                        "--pltimeout: Selenium Page Load Timeout (s)",
                        "--skipsf: Skip Summary Fetch Jobs",
                        "--override: Override existing chapters",
                        "--ignoreupdate: Skip novels update check"

                ));
                System.out.println();
                System.out.println("Usage example:");
                System.out.println("java -jar spider.jar xbiquge D:\\selenium\\chromedriver.exe http://localhost:4444/wd/hub --skipsf --th16");
                System.out.println("java -jar spider.jar bilibili --to500000 --divide1000 --th16");
                break;

            default:
                System.out.println("Unknown command!use 'help' to get help.");

        }
    }
}
