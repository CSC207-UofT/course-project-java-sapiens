package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.Outlet;
import com.yde.sapiensdelivery.entities.ShoppingList;

import java.util.ArrayList;

public class ShoppingListManager {

    private Outlet outlet;
    private ShoppingList shoppingList;

    /**
     * Creates a ShoppingListManager given a ShoppingList.
     *
     * @param shoppingList a ShoppingList Object
     */
    public ShoppingListManager(ShoppingList shoppingList, Outlet outlet) {
        this.outlet = outlet;
        this.shoppingList = shoppingList;
    }

    /**
     * Creates an empty ShoppingListManager, used to create an empty SL in ShoppingListCreationActivity
     * to be edited in EditShoppingListActivity
     *
     * @param outlet the Outlet user selects
     */
    public ShoppingListManager(Outlet outlet) {
        shoppingList = new ShoppingList(outlet.getName(), outlet.getAddress());
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

    public String getCommodityName(int index) {
        return shoppingList.getShoppingList().get(index).getName();
    }

    public double getCommodityPrice(int index) {
        return shoppingList.getShoppingList().get(index).getPrice();
    }

    public int getCommodityQuantity(int index) {
        return shoppingList.getShoppingList().get(index).getQuantity();
    }

    public Outlet getOutlet() {
        return outlet;
    }

    public int getSize(){
        return shoppingList.size();
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
}
