package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.ShoppingList;

import java.util.ArrayList;

public class ShoppingListManager {
    final String REF_PATH = "ShoppingList";

    public ShoppingListManager(){
//        super();
//        ref = database.getReference(REF_PATH);
    }

    /**
     * Create and add a new store ShoppingList to shoppingLists.
     */
    public void newShoppingList(String storeName, ArrayList<ShoppingList> shoppingLists){
        ShoppingList shoppingList = new ShoppingList(storeName);
        shoppingLists.add(shoppingList);
    }

    /**
     * Create and add a new outlet ShoppingList to shoppingLists.
     */
    public void newShoppingList(String outletName, String outletAddress, ArrayList<ShoppingList> shoppingLists){
        ShoppingList shoppingList = new ShoppingList(outletName);
        shoppingList.setOutletAddress(outletAddress);
        shoppingLists.add(shoppingList);
    }

    /**
     * Remove the user's ShoppingList from shoppingLists.
     *
     * @param index index of the ShoppingList is at in shoppingLists.
     */
    public void deleteShoppingList(int index, ArrayList<ShoppingList> shoppingLists){
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
    public void setCommodity(int index, String commodityName, double commodityPrice, int quantity
            , ArrayList<ShoppingList> shoppingLists){
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
    public ShoppingList addCommodity(int index, String commName, ArrayList<ShoppingList> shoppingLists){
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
    public ShoppingList removeCommodity(int index, String commName, ArrayList<ShoppingList> shoppingLists){
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
