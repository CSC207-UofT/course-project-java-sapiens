package com.yde.sapiensdelivery.entities;

/**
 * An entity class that represents a commodity.
 * Contains relative information about a commodity.
 */
public class Commodity {
    private final String name;
    private final double price;
    private int quantity;

    public Commodity(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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
}
