package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.ShoppingList;

import java.util.ArrayList;

public class ShoppingListManager {
    ShoppingList shoppingList;

    /**
     * Creates a ShoppingListManager given a ShoppingList.
     *
     * @param shoppingList a ShoppingList Object
     */
    public ShoppingListManager(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    /**
     * Creates an empty ShoppingListManager
     *
     * @param outletName the Name of the Outlet object
     */
    public ShoppingListManager(String outletName) {
        // TODO pass in an Outlet Object and change the params
        String address = "NO ADDRESS, TO BE IMPLEMENTED IN OUTLET CLASS";
        // TODO change this address to use the Outlet's address
        shoppingList = new ShoppingList(outletName, address);
    }

    /**
     * @return the ShoppingList that this Manager stores
     */
    public ShoppingList getShoppingList() {
        return this.shoppingList;
    }

    /**
     * Set a new commodity to the ShoppingList
     *
     * @param commodityName  name of Commodity
     * @param commodityPrice price of Commodity
     * @param quantity       the quantity of Commodity
     * // TODO @param commodity     a Commodity object
     *                       and then remove the above parameters except index
     */
    public void setCommodity(String commodityName, double commodityPrice, int quantity) {
        Commodity commodity = new Commodity(commodityName, commodityPrice, quantity);
        this.shoppingList.setCommodity(commodity);

        // TODO after outlet keeps track of a list of Commodities it stores,
        //  have the User select and get a Commodity,
        //  then directly pass the Commodity object into this method.
    }

    /**
     * A method that allows the addition of one quantity of a commodity
     *
     * @param index the index the Commodity is at
     */
    public void add1Commodity(int index){
        this.shoppingList.add1Commodity(index);
    }

    /**
     * A method that allows the removal of one quantity of a commodity
     *
     * @param index the index the Commodity is at
     */
    public void remove1Commodity(int index){
        this.shoppingList.remove1Commodity(index);
    }

    /**
     * @return the name of the outlet
     */
    public String getOutletName() {
        return shoppingList.getOutletName();
    }

    /**
     * @return the address of the outlet
     */
    public String getOutletAddress() {
        return shoppingList.getOutletAddress();
    }

    public String getCommodityName(int index) {
        return shoppingList.getShoppingList().get(index).getName();
    }

    public double getCommodityPrice(int index) {
        return shoppingList.getShoppingList().get(index).getPrice();
    }

    public int getCommodityQuantity(int index) {
        return shoppingList.getShoppingList().get(index).getQuantity();
    }
}
