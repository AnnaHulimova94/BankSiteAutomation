package com.hulimova.pages;

import com.hulimova.entity.Customer;
import com.hulimova.util.ConfigProvider;
import com.hulimova.util.CustomConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//button[text()='Customer Login']")
    private WebElement loginCustomerButton;

    @FindBy(xpath = "//button[text()='Bank Manager Login']")
    private WebElement loginManagerButton;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;

    @FindBy(id = "userSelect")
    private WebElement userSelect;

    @FindAll(@FindBy(xpath = "//option[@ng-repeat='cust in Customers']"))
    private List<WebElement> customerWebElementList;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openPage() {
        driver.get(ConfigProvider.URL_LOGIN);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(CustomConditions.documentStateIsReady());

        return this;
    }

    public LoginPage loginAsCustomer() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(loginCustomerButton))
                .click();

        return this;
    }

    public ManagerAccountPage loginAsManager() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(loginManagerButton))
                .click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(ConfigProvider.URL_MAIN_MANAGER));

        return new ManagerAccountPage(driver);
    }

    public List<Customer> getCustomerList() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(userSelect))
                .click();

        List<Customer> customerList = new ArrayList<>();
        String[] fullName;

        for (int i = 0; i < customerWebElementList.size(); i++) {
            fullName = customerWebElementList.get(i).getText().split(" ");
            customerList.add(new Customer(fullName[0], fullName[1]));
        }

        userSelect.click();

        return customerList;
    }

    public CustomerAccountPage loginCustomer(int id) {
        userSelect.click();
        customerWebElementList.get(id).click();
        loginButton.click();

        return new CustomerAccountPage(driver);
    }
}
