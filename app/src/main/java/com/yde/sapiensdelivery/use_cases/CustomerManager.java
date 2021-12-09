package com.yde.sapiensdelivery.use_cases;

import android.content.Intent;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.gateways.DeliveryManGateway;


public class CustomerManager implements Manager{
    private final Customer customer;

    @Override
    public void passValue(Intent intent){
        intent.putExtra("CUSTOMER", customer);
    }

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
    public String getLocation(){
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
     * update and save the Customer's rating and number of ratings
     */
    public void updateRating(float myRating){
        float rating = this.customer.getRating();
        int noOfRatings = this.customer.getNoOfRatings();
        rating = (rating * noOfRatings + myRating) / ++noOfRatings;
        this.customer.setRating(rating);
        this.customer.setNoOfRatings(noOfRatings);

        DeliveryManGateway deliveryManGateway = new DeliveryManGateway("DELIVERYMAN");
        deliveryManGateway.save(this.customer.getUname(), this.customer);
    }
}