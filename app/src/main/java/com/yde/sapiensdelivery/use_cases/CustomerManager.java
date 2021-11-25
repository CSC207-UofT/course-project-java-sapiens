package com.yde.sapiensdelivery.use_cases;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.yde.sapiensdelivery.controllers.database.OnDataReadListener;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.entities.User;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.User;
import com.yde.sapiensdelivery.controllers.database.OnDataReadListener;
import com.yde.sapiensdelivery.use_cases.UserManager;

import java.util.HashMap;
import java.util.Map;

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