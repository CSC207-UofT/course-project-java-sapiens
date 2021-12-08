package com.yde.sapiensdelivery.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * An entity class that represents a shopping list.
 * Contains relative information about a shopping list and
 * a list of Commodities.
 */
public class ShoppingList implements Serializable {
    private final String outletName;
    private final String outletAddress;
    private ArrayList<Commodity> shoppingList;
    // a Hashmap of commodity name to Commodity object
    private double totalPrice;

    public ShoppingList(String outletName, String outletAddress){
        this.outletName = outletName;
        this.shoppingList = new ArrayList<>();
        this.totalPrice = 0.0;
        this.outletAddress = outletAddress;
    }

    public ShoppingList(){
        outletAddress = "";
        outletName = "";
    }

    /**
     * @return the name of the outlet
     */
    public String getOutletName() {
        return this.outletName;
    }

    /**
     * @return the address of the outlet
     */
    public String getOutletAddress() {
        return this.outletAddress;
    }

    /**
     * @return the total price of all the Commodities in this ShoppingList
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * @return this ShoppingList
     */
    public ArrayList<Commodity> getShoppingList(){
        return this.shoppingList;
    }

    /**
     * @param commodity the Commodity to be added
     */
    public void setCommodity(Commodity commodity){
        this.shoppingList.add(commodity);
        this.totalPrice += commodity.getPrice() * commodity.getQuantity();
    }


    /**
     * A method that allows the addition of one quantity of a commodity
     *
     * @param index the index the Commodity is at
     */
    public void add1Commodity(int index){
        Commodity commodity = shoppingList.get(index);
        commodity.addQuantity();
        this.totalPrice += commodity.getPrice();
    }

    /**
     * A method that allows the removal of one quantity of a commodity
     *
     * @param index the index the Commodity is at
     */
    public void remove1Commodity(int index){
        Commodity commodity = this.shoppingList.get(index);
        commodity.removeQuantity();
        if (commodity.getQuantity() == 0) {
            this.shoppingList.remove(index);
        }
        this.totalPrice -= commodity.getPrice();
    }

    /**
     * @return size of this ShoppingList
     */
    public int size(){
        return this.shoppingList.size();
    }

    @NonNull
    @Override
    public String toString(){
        StringBuilder toDisplay = new StringBuilder();
        for (Commodity commodity: this.shoppingList){
            String toAdd = String.format("%30s", commodity.getName() + "  $" +
                    commodity.getPrice() + " x " + commodity.getQuantity() + "\n");
            toDisplay.append(toAdd);
        }
        return toDisplay.toString();
    }

    public String displayEntire(){
        return getOutletName() + "\n Address: " + getOutletAddress()  + "\n" + toString();
    }
}
