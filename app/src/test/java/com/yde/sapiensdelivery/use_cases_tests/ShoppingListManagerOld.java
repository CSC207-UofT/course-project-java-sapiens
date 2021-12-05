package com.yde.sapiensdelivery.use_cases_tests;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities_tests.ShoppingListOld;

import java.util.ArrayList;

public class ShoppingListManagerOld {
    ArrayList<ShoppingListOld> shoppingListOlds;

    /**
     * Creates an ShoppingListManager given a ArrayList of ShoppingLists
     */
    public ShoppingListManagerOld(ArrayList<ShoppingListOld> shoppingListOlds){
        this.shoppingListOlds = shoppingListOlds;
    }

    /**
     * Creates an ShoppingListManager
     */
    public ShoppingListManagerOld(){
    }

    /**
     * Get the List of ShoppingLists that this Manager stores
     *
     * @return the ShoppingLists that this Manager stores
     */
    public ArrayList<ShoppingListOld> getShoppingLists() {
        return shoppingListOlds;
    }

    /**
     * Create and add a new store ShoppingList to shoppingLists.
     */
    public void newShoppingList(String storeName, ArrayList<ShoppingListOld> shoppingListOlds){
        ShoppingListOld shoppingListOld = new ShoppingListOld(storeName);
        shoppingListOlds.add(shoppingListOld);
    }

    /**
     * Create and add a new outlet ShoppingList to shoppingLists.
     */
    public void newShoppingList(String outletName, String outletAddress, ArrayList<ShoppingListOld> shoppingListOlds){
        ShoppingListOld shoppingListOld = new ShoppingListOld(outletName);
        shoppingListOld.setOutletAddress(outletAddress);
        shoppingListOlds.add(shoppingListOld);
    }

    /**
     * Remove the user's ShoppingList from shoppingLists.
     *
     * @param index index of the ShoppingList is at in shoppingLists.
     */
    public void deleteShoppingList(int index, ArrayList<ShoppingListOld> shoppingListOlds){
        shoppingListOlds.remove(index);
    }

    /**
     * Set a new commodity to the ShoppingList in the index in shoppingLists.
     *
     * @param index index of the ShoppingList is at in shoppingLists
     * @param commodityName name of commodity.
     * @param commodityPrice price of commodity.
     *
     */
    public void setCommodity(int index, String commodityName, double commodityPrice, int quantity, ArrayList<ShoppingListOld> shoppingListOlds){
        Commodity commodity = new Commodity(commodityName, commodityPrice, quantity);
        shoppingListOlds.get(index).setCommodity(commodity, quantity);

    }

    /**
     * Add commodity quantity to the ShoppingList in the index in shoppingLists.
     *
     * @param index index of the ShoppingList is at in shoppingLists
     * @param commName name of commodity.
     *
     * @return the ShoppingList after change
     */
    public ShoppingListOld addCommodity(int index, String commName, ArrayList<ShoppingListOld> shoppingListOlds){
        shoppingListOlds.get(index).addCommodity(commName);
        return shoppingListOlds.get(index);
    }

    /**
     * Remove commodity quantity to the ShoppingList in the index in shoppingLists.
     *
     * @param index index of the ShoppingList is at in shoppingLists
     * @param commName name of commodity.
     *
     * @return the ShoppingList after change
     */
    public ShoppingListOld removeCommodity(int index, String commName, ArrayList<ShoppingListOld> shoppingListOlds){
        shoppingListOlds.get(index).removeCommodity(commName);
        return shoppingListOlds.get(index);
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
