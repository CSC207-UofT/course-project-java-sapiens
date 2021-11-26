package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.ShoppingList;

import java.util.ArrayList;

public class ShoppingListManager {
    ArrayList<ShoppingList> shoppingLists;

    /**
     * Creates an ShoppingListManager given a ArrayList of ShoppingLists
     */
    public ShoppingListManager(ArrayList<ShoppingList> shoppingLists){
        this.shoppingLists = shoppingLists;
    }

    /**
     * Creates an ShoppingListManager
     */
    public ShoppingListManager(){
    }

    /**
     * Get the List of ShoppingLists that this Manager stores
     *
     * @return the ShoppingLists that this Manager stores
     */
    public ArrayList<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    /**
     * Create and add a new store ShoppingList to shoppingLists.
     */
    public void newShoppingList(String storeName){
        ShoppingList shoppingList = new ShoppingList(storeName);
        shoppingLists.add(shoppingList);
    }

    /**
     * Create and add a new outlet ShoppingList to shoppingLists.
     */
    public void newShoppingList(String outletName, String outletAddress){
        ShoppingList shoppingList = new ShoppingList(outletName);
        shoppingList.setOutletAddress(outletAddress);
        shoppingLists.add(shoppingList);
    }

    /**
     * Remove the user's ShoppingList from shoppingLists.
     *
     * @param index index of the ShoppingList is at in shoppingLists.
     */
    public void deleteShoppingList(int index){
        shoppingLists.remove(index);
    }

    /**
     * Set a new commodity to the ShoppingList in the index in shoppingLists.
     *
     * @param index index of the ShoppingList is at in shoppingLists
     * @param commodityName name of commodity.
     * @param commodityPrice price of commodity.
     *
     */
    public void setCommodity(int index, String commodityName, double commodityPrice, int quantity){
        Commodity commodity = new Commodity(commodityName, commodityPrice, quantity);
        shoppingLists.get(index).setCommodity(commodity, quantity);

    }

    /**
     * Add commodity quantity to the ShoppingList in the index in shoppingLists.
     *
     * @param index index of the ShoppingList is at in shoppingLists
     * @param commName name of commodity.
     *
     * @return the ShoppingList after change
     */
    public ShoppingList addCommodity(int index, String commName){
        shoppingLists.get(index).addCommodity(commName);
        return shoppingLists.get(index);
    }

    /**
     * Remove commodity quantity to the ShoppingList in the index in shoppingLists.
     *
     * @param index index of the ShoppingList is at in shoppingLists
     * @param commName name of commodity.
     *
     * @return the ShoppingList after change
     */
    public ShoppingList removeCommodity(int index, String commName){
        shoppingLists.get(index).removeCommodity(commName);
        return shoppingLists.get(index);
    }

    //    /**
//     * All Manager classes in controllers.use_cases have some transactions to save.
//     *
//     * @param obj The 'key' with which the database can be queried
//     * @param val The corresponding object
//     */
//    @Override
//    public void save(String obj, ArrayList<ShoppingList> val) {
//        Map<String, ArrayList<ShoppingList>> toSave = new HashMap<>();
//        toSave.put(obj, val);
//
//        ref.setValueAsync(toSave);
//    }
//
//    /**
//     * All Manager classes query the database for a specific object type that it is managing.
//     *
//     * @param obj The 'key' with which the database can be queried.
//     */
//    @Override
//    public void get(String obj, final OnDataReadListener onDataReadListener) {
//    }
}
