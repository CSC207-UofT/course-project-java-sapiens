package entities;

import java.util.ArrayList;

public class Order {
    private boolean isComplete;
    private DeliveryMan deliveryMan;
    private Customer customer;
    private ArrayList<ShoppingList> shoppingLists;


    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<ShoppingList> getShoppingLists(){
        return this.shoppingLists;
    }

//    public void setShoppingList(ShoppingList shoppingList){this.shoppingList = shoppingList; }
}

