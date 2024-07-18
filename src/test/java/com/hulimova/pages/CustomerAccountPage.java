package com.hulimova.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerAccountPage extends AbstractPage {

    @FindBy(xpath = "//span[@class='fontBig ng-binding']")
    private WebElement customerName;

    @FindBy(xpath = "//button[@class='btn logout']")
    private WebElement logoutButton;

    public CustomerAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getCustomerName() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(customerName))
                .getText();
    }

    public void logout() {
        logoutButton.click();
    }
}
