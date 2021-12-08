package com.yde.sapiensdelivery.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * An entity class that represents a commodity.
 * Contains relative information about a commodity.
 */
public class Commodity implements Serializable {
    private final String name;
    private final double price;
    private int quantity;

    public Commodity(String name, double price, int quantity){
        this.name = name;
        this.price = price;

        // Initial quantity can't be 0
        if (quantity ==  0) {
            this.quantity = 1;
        } else {
            this.quantity = quantity;
        }
    }

    public Commodity(){
        name = "";
        price = 0;
    }

    // A set of getters and setters.

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void addQuantity(){
        this.quantity += 1;
    }

    public void removeQuantity(){
        this.quantity -= 1;
    }


    @NonNull
    @Override
    public String toString() {
        return this.getName() + ": $ " + this.getPrice();
    }
}
