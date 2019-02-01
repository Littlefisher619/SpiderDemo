package com.littlefisher.spider.xbiquge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverUtil {
    private static final Logger logger = LogManager.getLogger(WebDriverUtil.class);
    public static DesiredCapabilities capabilities=null;
    public static WebDriver getNewWebDriver() throws Exception{
        if(capabilities==null) {
            System.setProperty("webdriver.chrome.driver", Settings.CHROME_DRIVER_PATH);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("headless");
            chromeOptions.addArguments("disable-gpu");

            chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

            capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        WebDriver driver = null;
        driver = new RemoteWebDriver(new URL(Settings.REMOTE_DRIVER_URL),capabilities);
        driver.manage().timeouts().pageLoadTimeout(Settings.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
        return driver;
    }
}
