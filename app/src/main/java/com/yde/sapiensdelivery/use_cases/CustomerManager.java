package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Customer;

public class CustomerManager extends UserManager {
    private Customer customer;

    /**
     * Creates an OrderManager given an already existing Customer
     */
    public CustomerManager(Customer customer) {
        this.customer = customer;
    }

    /**
     * Creates an OrderManager
     */
    public CustomerManager() {
    }


}