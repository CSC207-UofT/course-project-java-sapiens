package com.yde.sapiensdelivery.entities;
import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * An entity class that represents an Order.
 * Order relates a Customer, a deliveryMan, and a ShoppingList
 */

public class Order implements Serializable {
    private OrderStatus status;

    private DeliveryMan deliveryMan;
    private int UID;

    private Customer customer;
    private ArrayList<ShoppingListOld> shoppingLists;

    private double totalPrice;

    // Order stores a list of ShoppingLists, one ShoppingList for each store/outlet.
    public Order(DeliveryMan deliveryMan, Customer customer, int UID, ArrayList<ShoppingListOld> shoppingLists) {
        this.deliveryMan  = deliveryMan;
        this.customer = customer;
        this.UID = UID;
        this.shoppingLists = shoppingLists;
        this.totalPrice = 0;

        for (ShoppingListOld shoppingList : shoppingLists) {
            this.totalPrice += shoppingList.getTotalPrice();
        }
    }


    public String getStatusOTW() {
        return "On the Way to get your order.";
    }

    // An enum for different status of an Order
    public enum OrderStatus {
        OTW {
            @NonNull
            @Override
            public String toString() {
                return "On the Way to get your order.";
            }
        },

        REC {
            @NonNull
            @Override
            public String toString() {
                return "Order Received, Out for Delivery.";
            }
        },

        COMP {
            @NonNull
            @Override
            public String toString() {
                return "Order Delivered and Complete.";
            }
        }
    }

    // A set of getters and setters.

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatusOTW() {
        this.status = OrderStatus.OTW;
    }

    public void setStatusREC() {
        this.status = OrderStatus.REC;
    }

    public void setStatusCOMP() {
        this.status = OrderStatus.COMP;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<ShoppingListOld> getShoppingLists(){
        return this.shoppingLists;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public int getUID(){
        return this.UID;
    }

    public String getDeliverManDisplay() {
        String start = "The Information of the Delivery Person completing your order is: \n";
        String delName = this.deliveryMan.getName();
        String delContact = this.deliveryMan.getNumber();
        double delRate = this.deliveryMan.getRate();
        String displayName = "Name: " + delName + "\n";
        String displayContact = "Phone Number: " + delContact + "\n";
        String displayRate = "Rating: " + delRate  + "\n";
        return (start + displayName + displayContact + displayRate);
    }
}

