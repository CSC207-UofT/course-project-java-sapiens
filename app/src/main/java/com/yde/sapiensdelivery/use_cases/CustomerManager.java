package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Customer;
import java.util.ArrayList;


public class CustomerManager {
    private final Customer customer;

    /**
     * Creates an CustomerManager given an already existing Customer
     */
    public CustomerManager(Customer customer) {
        this.customer = customer;
    }

    /**
     * return the Customer's name
     */
    public String getName(){
        return this.customer.getName();
    }

    /**
     * return the Customer's Location
     */
    public ArrayList<Integer> getLocation(){
        return this.customer.getLocation();
    }

    /**
     * return the Customer's PhoneNumber
     */
    public String getPhoneNumber(){
        return this.customer.getNumber();
    }

    /**
     * return the Customer's user name
     */
    public String getUsername(){
        return this.customer.getUname();
    }

    /**
     * return the Customer's password
     */
    public String getPassword(){
        return this.customer.getPassword();
    }

    /**
     * set the Customer's name
     */
    public void setName(String name){
        this.customer.setName(name);
    }

    /**
     * set the Customer's location in the following format
     * {longitude, latitude}
     */
    public void setLocation(ArrayList<Integer> location){
        this.customer.setLocation(location);
    }

    /**
     * set the Customer's phone number
     */
    public void setPhoneNumber(String number){
        this.customer.setNumber(number);
    }

    /**
     * set the Customer's username
     */
    public void setUsername(String username){
        this.customer.setUname(username);
    }

    /**
     * set the Customer's password
     */
    public void setPassword(String password){
        this.customer.setPassword(password);
    }
}