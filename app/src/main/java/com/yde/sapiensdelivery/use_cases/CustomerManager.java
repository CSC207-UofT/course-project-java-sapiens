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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerManager extends UserManager {
    private Customer customer;

    /**
     * Creates an CustomerManager given an already existing Customer
     */
    public CustomerManager(Customer customer) {
        this.customer = customer;
    }

    /**
     * return the Costumer's name
     */
    public String getName(){
        return this.customer.getName();
    }

    /**
     * return the Costumer's Location
     */
    public ArrayList<Integer> getLocation(){
        return this.customer.getLocation();
    }

    /**
     * return the Costumer's PhoneNumber
     */
    public String getPhoneNumber(){
        return this.customer.getNumber();
    }

    /**
     * return the Costumer's user name
     */
    public String getUsername(){
        return this.customer.getUname();
    }

    /**
     * return the Costumer's password
     */
    public String getPassword(){
        return this.customer.getPassword();
    }


}