package com.hulimova.util;

import com.hulimova.entity.Customer;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;

import java.util.ArrayList;
import java.util.List;

public interface ConfigProvider {

    Config config = readConfig();

    String URL_LOGIN = config.getString("url_login");

    String URL_MAIN_MANAGER = config.getString("url_main_manager");

    List<Customer> CUSTOMER_LIST = getCustomerList();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("application.conf");
    }

    static List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        String firstname;
        String lastname;
        String postCode;

        for (ConfigValue configValue : config.getList("customer_list")) {
            firstname = configValue.atKey("firstname").getObject("firstname").toConfig().getString("firstname");
            lastname = configValue.atKey("lastname").getObject("lastname").toConfig().getString("lastname");
            postCode = configValue.atKey("postCode").getObject("postCode").toConfig().getString("postCode");

            customerList.add(new Customer(firstname, lastname, postCode));
        }

        return customerList;
    }
}
