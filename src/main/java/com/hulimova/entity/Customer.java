package com.hulimova.entity;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Customer {

    private String firstname;

    private String lastname;

    private String postCode;

    private List<String> accountNumberList;

    public Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Customer(String firstname, String lastname, String postCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.postCode = postCode;
    }

    public Customer(String firstname, String lastname, String postCode, List<String> accountNumberList) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.postCode = postCode;
        this.accountNumberList = accountNumberList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstname, customer.firstname) && Objects.equals(lastname, customer.lastname) && Objects.equals(postCode, customer.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, postCode);
    }
}
