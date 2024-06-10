package com.hulimova.tests;

import com.hulimova.util.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class AbstractTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp(ITestContext context) {
        driver = DriverSingleton.getDriver(context);
    }

    @AfterMethod
    public void shutDown() {
        DriverSingleton.closeDriver();
    }
}
