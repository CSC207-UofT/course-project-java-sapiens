package com.yde.sapiensdelivery.use_cases;

import androidx.annotation.NonNull;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.Outlet;
import com.yde.sapiensdelivery.entities.ShoppingList;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingListManager implements Serializable{

    private ShoppingList shoppingList;
    private Outlet outlet;

    /**
     * Creates a ShoppingListManager given an Outlet.
     *
     * @param outlet the outlet of this ShoppingList
     */
    public ShoppingListManager(Outlet outlet) {
        this.shoppingList = newShoppingList(outlet);
        this.outlet = outlet;
    }

    /**
     * Creates an empty ShoppingListManager
     */
    public ShoppingListManager(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    /**
     * Creates an empty ShoppingListManager
     */
    public ShoppingListManager() {
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
     * @param commodity a Commodity that the user selected
     */
    public void setCommodity(Commodity commodity) {
        this.shoppingList.setCommodity(commodity);
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

    /**
     * @param index position of the Commodity
     * @return Commodity's name
     */
    public String getCommodityName(int index) {
        return shoppingList.getShoppingList().get(index).getName();
    }

    public ShoppingList newShoppingList(Outlet outlet) {
        return new ShoppingList(outlet.getName(), outlet.getAddress());
    }

    public int getCommodityQuantity(int index) {
        return shoppingList.getShoppingList().get(index).getQuantity();
    }

    public int getSize(){
        return shoppingList.size();
    }

    public Outlet getOutlet(){
        return this.outlet;
    }

    public double getTotalPrice(){
        return this.shoppingList.getTotalPrice();
    }

    /**
     * Get the total price of the Commodity, for displaying purposes
     * Total price = quantity * price
     *
     * @param index index of the Commodity
     * @return the total price of the Commodity
     */
    public double getCommodityTotalPrice(int index) {
        Commodity commodity = shoppingList.getShoppingList().get(index);
        return commodity.getQuantity() * commodity.getPrice();
    }

    @NonNull
    @Override
    public String toString() {
        return shoppingList.toString();
    }

    public String displayEntire(){
        return shoppingList.displayEntire();
    }

    /**
     * Get the total price from this to be created order
     *
     * @param shoppingListManagers a list of ShoppingListManager objects
     * @return the total price
     */
    public double calculateManagersTotal(ArrayList<ShoppingListManager> shoppingListManagers){
        double totalPrice = 0;
        for (ShoppingListManager shoppingListManager : shoppingListManagers) {
            totalPrice += shoppingListManager.getTotalPrice();
        }
        return totalPrice;
    }

    /**
     * Get the shopping lists being monitored by managers
     *
     * @param shoppingListManagers  a list of ShoppingListManager objects
     * @return the shopping list arraylist
     */
    public static ArrayList<ShoppingList> getShoppingLists(ArrayList<ShoppingListManager> shoppingListManagers){
        ArrayList<ShoppingList> shoppingLists = new ArrayList<>();
        for (ShoppingListManager shoppingListManager: shoppingListManagers) {
            shoppingLists.add(shoppingListManager.getShoppingList());
        }
        return shoppingLists;
    }

    /**
     * Get the total price from this to be created order
     *
     * @param shoppingLists a list of ShoppingList objects
     * @return the total price
     */
    public double calculateListsTotal(ArrayList<ShoppingList> shoppingLists){
        double totalPrice = 0;
        for (ShoppingList shoppingList : shoppingLists) {
            totalPrice += shoppingList.getTotalPrice();
        }
        return totalPrice;
    }
}
