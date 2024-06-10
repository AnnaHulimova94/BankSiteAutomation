package com.hulimova.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;

public class DriverSingleton {

    private static WebDriver driver;

    public static WebDriver getDriver(ITestContext context) {
        if (driver == null) {
            driver = context.getCurrentXmlTest().getParameter("environment").equals("firefox") ?
                    new FirefoxDriver() : new ChromeDriver();
        }

        driver.manage().window().maximize();

        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
