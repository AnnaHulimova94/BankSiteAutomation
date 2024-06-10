package com.hulimova.tests;

import com.hulimova.pages.LoginPage;
import com.hulimova.pages.ManagerAccountPage;
import com.hulimova.util.ConfigProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ManagerFunctionalityTest extends AbstractTest {

    @Test
    public void test_adding_new_customer() {
        ConfigProvider.CUSTOMER_LIST.forEach(customer -> {
            ManagerAccountPage managerAccountPage = new LoginPage(driver)
                    .openPage()
                    .loginAsManager();

            Assert.assertFalse(managerAccountPage.getCustommerList().contains(customer));

            managerAccountPage.addCustomer(customer);

            Assert.assertTrue(managerAccountPage.getCustommerList().contains(customer));
        });
    }
}
