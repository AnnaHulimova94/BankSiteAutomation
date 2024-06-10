package com.hulimova.pages;

import com.hulimova.entity.Customer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ManagerAccountPage extends AbstractPage {

    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    private WebElement addCustomerButton;

    @FindBy(xpath = "//input[@ng-model='fName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@ng-model='lName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@ng-model='postCd']")
    private WebElement postCodeInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement addCustomerSubmitButton;

    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    private WebElement displayCustomerButton;

    @FindAll(@FindBy(xpath = "//tr[@class='ng-scope']"))
    private List<WebElement> customerDataList;

    public ManagerAccountPage(WebDriver driver) {
        super(driver);
    }

    public List<Customer> getCustommerList() {
        displayCustomerButton.click();

        String firstName;
        String lastName;
        String postCode;
        List<WebElement> accountNumberWebElementList;
        List<String> accountNumberList;
        List<Customer> customerList = new ArrayList<>();

        for (WebElement customerData : customerDataList) {
            accountNumberList = new ArrayList<>();

            firstName = customerData.findElements(By.xpath(".//td")).get(0).getText();
            lastName = customerData.findElements(By.xpath(".//td")).get(1).getText();
            postCode = customerData.findElements(By.xpath(".//td")).get(2).getText();

            accountNumberWebElementList = customerData.findElements(By.xpath(".//td//span"));

            for (WebElement accountNumberWebElement : accountNumberWebElementList) {
                accountNumberList.add(accountNumberWebElement.getText());
            }

            customerList.add(new Customer(firstName, lastName, postCode, accountNumberList));
        }

        return customerList;
    }

    public ManagerAccountPage addCustomer(Customer customer) {
        addCustomerButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("input")));

        firstNameInput.sendKeys(customer.getFirstname());
        lastNameInput.sendKeys(customer.getLastname());
        postCodeInput.sendKeys(customer.getPostCode());

        addCustomerSubmitButton.click();

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .ignoring(UnhandledAlertException.class)
                .until(ExpectedConditions.elementToBeClickable(addCustomerButton));

        return this;
    }
}
