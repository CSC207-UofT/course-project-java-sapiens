package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.ShoppingList;

import java.util.ArrayList;

public class LISTShoppingListManager {
    // A list of ShoppingList Objects, with each ShoppingList representing an Outlet
    ArrayList<ShoppingList> shoppingLists;

    /**
     * Creates a ShoppingListManager given a ArrayList of ShoppingLists.
     *
     * @param shoppingLists a list of ShoppingList Objects
     */
    public LISTShoppingListManager(ArrayList<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    /**
     * Creates an empty ShoppingListManager
     */
    public LISTShoppingListManager() {
        this.shoppingLists = new ArrayList<>();
    }

    /**
     * Get the List of ShoppingLists that this Manager stores.
     *
     * @return the ShoppingLists that this Manager stores
     */
    public ArrayList<ShoppingList> getShoppingLists() {
        return this.shoppingLists;
    }

    /**
     * Create and add a new outlet ShoppingList to shoppingLists.
     *
     * @param storeName the name of the outlet to be added
     */
    public void newShoppingList(String storeName) {
        ShoppingList shoppingList = new ShoppingList(storeName, "NO ADDRESS, TO BE IMPLEMENTED IN OUTLET CLASS");
        shoppingLists.add(shoppingList);
    }

    /**
     * Delete the ShoppingList at index from shoppingLists.
     *
     * @param index index of the ShoppingList is at in shoppingLists.
     */
    public void deleteShoppingList(int index) {
        shoppingLists.remove(index);
    }

    /**
     * Set a new commodity to the ShoppingList in the index in shoppingLists.
     *
     * @param index          index of the Outlet's ShoppingList is at in shoppingLists
     * @param commodityName  name of Commodity
     * @param commodityPrice price of Commodity
     * @param quantity       the quantity of Commodity
     * // TODO @param commodity     a Commodity object
     *                       and then remove the above parameters except index
     */
    public void setCommodityAtIndex(int index, String commodityName, double commodityPrice, int quantity) {
        Commodity commodity = new Commodity(commodityName, commodityPrice, quantity);
        ShoppingList shoppingList = shoppingLists.get(index);
        shoppingList.setCommodity(commodity);

        // TODO after outlet keeps track of a list of Commodities it stores,
        //  have the User select and get a Commodity,
        //  then directly pass the Commodity object into this method.
    }

    /**
     * Add commodity quantity to the ShoppingList in the index in shoppingLists.
     *
     * @param index    index of the ShoppingList is at in shoppingLists
     * @param index2   index of the Commodity in ShoppingLIst at index of shoppingLists
     */
    public void add1CommodityAtIndex(int index, int index2) {
        shoppingLists.get(index).add1Commodity(index2);
    }

    /**
     * Remove commodity quantity to the ShoppingList in the index in shoppingLists.
     *
     * @param index    index of the ShoppingList is at in shoppingLists
     * @param commName name of commodity.
     */
    public void remove1Commodity(int index, String commName, int index2) {
        shoppingLists.get(index).remove1Commodity(index2);
    }

    /**
     * @param index a position of ShoppingLists
     * @return ShoppingList at index of ShoppingLists
     */
    public ShoppingList getShoppingList(int index) {
        return shoppingLists.get(index);
    }

    public String getOutletNameAtIndex(int index) {
        return shoppingLists.get(index).getOutletName();
    }
}
