package entities;

import java.util.HashMap;

/**
 * An entity class that represents a shopping list.
 * Contains relative information about a shopping list and
 * a list of Commodities.
 */
public class ShoppingList {
    private final String outletName;
    private String outletAddress; // optional
    private HashMap<String, Commodity> shoppingList;
    // a Hashmap of commodity name to Commodity object
    private double totalPrice;

    public ShoppingList(String outletName){
        this.outletName = outletName;
        this.shoppingList = new HashMap<>();
        this.totalPrice = 0.0;
    }

    //A set of getters and setters.

    public String getOutletName() {
        return this.outletName;
    }

    public String getOutletAddress() {
        return this.outletAddress;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public HashMap<String, Commodity> getShoppingList(){
        return this.shoppingList;
    }

    public void setOutletAddress(String address) {
        this.outletAddress = address;
    }

    public int setCommodity(Commodity commodity, int quantity){
        this.shoppingList.put(commodity.getName(), commodity);
        this.totalPrice += commodity.getPrice()*quantity;
        return 1;
    }

    // A method that allows the addition of a commodity
    public int addCommodity(String commName){
        //if the commodity is already in shoppingList
        Commodity commodity = this.shoppingList.get(commName);
        commodity.addQuantity();
        this.totalPrice += commodity.getPrice();
        return commodity.getQuantity();
    }

    // A method that allows the deletion of a commodity
    public int removeCommodity(String commName){
        Commodity commodity = this.shoppingList.get(commName);
        if(commodity.getQuantity() == 1){
            this.totalPrice -= commodity.getPrice();
            this.shoppingList.remove(commName);
            return 0; // Also remove the Commodity from UI
        }
        else {
            commodity.removeQuantity();
            this.totalPrice -= commodity.getPrice();
            return commodity.getQuantity();
        }
    }
}
