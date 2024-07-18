package com.hulimova;

import com.hulimova.entity.Customer;
import com.hulimova.pages.CustomerAccountPage;
import com.hulimova.pages.LoginPage;
import com.hulimova.util.ConfigProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AuthorizationTest extends AbstractTest {

    @Test
    public void test_customer_authorization() {
        LoginPage loginPage = new LoginPage(driver);
        List<Customer> customerList = loginPage
                .openPage()
                .loginAsCustomer()
                .getCustomerList();

        CustomerAccountPage customerAccountPage;
        Customer customer;

        for (int i = 0; i < customerList.size() - 1 && i < 10; i++) {
            customerAccountPage = loginPage.loginCustomer(i);
            customer = customerList.get(i);

            Assert.assertEquals(customer.getFirstname() + " " + customer.getLastname(),
                    customerAccountPage.getCustomerName());

            customerAccountPage.logout();
        }
    }

    @Test
    public void test_manager_authorization() {
        new LoginPage(driver)
                .openPage()
                .loginAsManager();

        Assert.assertEquals(ConfigProvider.URL_MAIN_MANAGER, driver.getCurrentUrl());
    }
}
