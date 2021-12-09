package com.yde.sapiensdelivery.use_cases;

import android.content.Intent;

import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.gateways.DeliveryManGateway;
import com.yde.sapiensdelivery.gateways.OrderGateway;

import java.util.ArrayList;

public class DeliveryManManager implements Manager{
    private final DeliveryMan deliveryMan;

    /**
     * Creates an OrderManager given an already existing deliveryMan
     */
    public DeliveryManManager(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    /**
     * return the Delivery man's name
     */
    public String getName(){
        return this.deliveryMan.getName();
    }

    /**
     * return the Delivery man's PhoneNumber
     */
    public String getPhoneNumber(){
        return this.deliveryMan.getNumber();
    }

    /**
     * return the Delivery man's user name
     */
    public String getUsername(){
        return this.deliveryMan.getUname();
    }

    /**
     * return the Delivery man's password
     */
    public String getPassword(){
        return this.deliveryMan.getPassword();
    }

    /**
     * return the Delivery man's Social Insurance Number
     */
    public long getSin(){
        return this.deliveryMan.getSin();
    }

    /**
     * return the Delivery man's mode of Transportation
     */
    public String getTransport(){
        return this.deliveryMan.getTransport();
    }

    /**
     * update and save the Delivery man's rating and number of ratings
     */
    public void updateRating(float myRating){
        float rating = this.deliveryMan.getRating();
        int noOfRatings = this.deliveryMan.getNoOfRatings();
        rating = (rating * noOfRatings + myRating) / ++noOfRatings;
        this.deliveryMan.setRating(rating);
        this.deliveryMan.setNoOfRatings(noOfRatings);

        DeliveryManGateway deliveryManGateway = new DeliveryManGateway("DELIVERYMAN");
        deliveryManGateway.save(this.deliveryMan.getUname(), this.deliveryMan);
    }

    /**
     * set the Delivery man's location.
     */
    public void setLocation(String location){
        DeliveryManGateway deliveryManGateway = new DeliveryManGateway("DELIVERYMAN");
        this.deliveryMan.setLocation(location);
        deliveryManGateway.save(getUsername(), deliveryMan);
    }

    @Override
    public void passValue(Intent intent) {
        intent.putExtra("DELIVERYMAN", deliveryMan);
    }

    public void createOrder(OrderManager orderManager, Customer customer, ArrayList<ShoppingList> shoppingLists) {
        OrderGateway orderGateway = new OrderGateway();
        orderGateway.save(customer.getUname(), orderManager.createOrder(deliveryMan, customer, shoppingLists));
    }
}
