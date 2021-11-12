package controllers.use_cases;

import entities.Commodity;
import entities.DeliveryMan;
import entities.ShoppingList;
import controllers.use_cases.Database.DBManager;
import controllers.use_cases.Database.OnDataReadListener;
import entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the entire system of Customers creating ShoppingLists.
 */
public class ShoppingListManager extends DBManager<String, ShoppingList> {
    private ArrayList<ShoppingList> shoppingLists;

    /**
     * Create a ShoppingListManager with an empty shoppingLists.
     */
    public ShoppingListManager(){
        super();
        this.shoppingLists = new ArrayList<ShoppingList>();
    }

    /**
     * Get shoppingLists.
     */
    public ArrayList<ShoppingList> getShoppingLists(){
        return this.shoppingLists;
    }

    /**
     * Create and add a new store ShoppingList to shoppingLists.
     */
    public void newShoppingList(String storeName){
        ShoppingList shoppingList = new ShoppingList(storeName);
        this.shoppingLists.add(shoppingList);
    }

    /**
     * Create and add a new outlet ShoppingList to shoppingLists.
     */
    public void newShoppingList(String outletName, String outletAddress){
        ShoppingList shoppingList = new ShoppingList(outletName);
        shoppingList.setOutletAddress(outletAddress);
        this.shoppingLists.add(shoppingList);
    }

    /**
     * Remove the user's ShoppingList from shoppingLists.
     *
     * @param index index of the ShoppingList is at in shoppingLists.
     */
    public void deleteShoppingList(int index){
        this.shoppingLists.remove(index);
    }

    /**
     * Set a new commodity to the ShoppingList in the index in shoppingLists.
     *
     * @param index index of the ShoppingList is at in shoppingLists
     * @param commodityName name of commodity.
     * @param commodityPrice price of commodity.
     *
     * @return the ShoppingList after change
     */
    public ShoppingList setCommodity(int index, String commodityName, double commodityPrice, int quantity){
        Commodity commodity = new Commodity(commodityName, commodityPrice, quantity);
        this.shoppingLists.get(index).setCommodity(commodity, quantity);

        return this.shoppingLists.get(index);
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
        this.shoppingLists.get(index).addCommodity(commName);
        return this.shoppingLists.get(index);
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
        this.shoppingLists.get(index).removeCommodity(commName);
        return this.shoppingLists.get(index);
    }

    /**
     * All Manager classes in controllers.use_cases have some transactions to save.
     *
     * @param obj The 'key' with which the database can be queried
     * @param val The corresponding object
     */
    @Override
    public void save(String obj, ShoppingList val) {
        Map<String, ArrayList<ShoppingList>> toSave = new HashMap<>();
        toSave.put(obj, this.shoppingLists);

        ref.setValueAsync(toSave);
    }

    /**
     * All Manager classes query the database for a specific object type that it is managing.
     *
     * @param obj The 'key' with which the database can be queried.
     */
    @Override
    public void get(String obj, final OnDataReadListener onDataReadListener) {
        return;
    }
}

