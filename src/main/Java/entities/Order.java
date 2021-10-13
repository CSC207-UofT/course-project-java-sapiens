package entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private boolean isComplete;
    private DeliveryMan deliveryMan;
    private Customer customer;
    private final int UID;
    private double price;

    private HashMap<Outlet, ArrayList<Entity>> shoppingList;

    public Order(DeliveryMan deliveryMan, Customer customer, int UID){
        this.deliveryMan = deliveryMan;
        this.customer = customer;
        this.UID = UID;
        isComplete = false;
        shoppingList = new HashMap<>();
    }

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

    public int getUID() {
        return UID;
    }

    public void addEntity(Outlet outlet, Entity entity){
        if(shoppingList.get(outlet) == null){
            ArrayList<Entity> outletList = new ArrayList<>();
            shoppingList.put(outlet, outletList);
        }
        else {
            shoppingList.get(outlet).add(entity);
        }
    }
}

