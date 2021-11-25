package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.entities.ShoppingList;

import java.util.ArrayList;

public class OrderManager {
    private Order order;

//    final String REF_PATH;

    /**
     * Creates an OrderManager on the basis of the current customer's username
     */
    public OrderManager(Order order) {
//        this.order = order;
    }


    /**
     * Creates an Order
     *
     * @param deliveryMan Delivery man of new order
     * @param customer Customer of new order
     * @param shoppingLists The shoppingLists that the customer has added
     */
    public void createOrder(DeliveryMan deliveryMan, Customer customer, ArrayList<ShoppingList> shoppingLists){
        int currUID = generateUID(shoppingLists);
        this.order = new Order(deliveryMan, customer, ++currUID ,shoppingLists);
    }

    /**
     * Creates an Unique ID for an order
     *
     * @param shoppingLists The shoppingLists that the customer has added
     * @return unique ID based on.
     */
    private int generateUID(ArrayList<ShoppingList> shoppingLists) {
        double totalPrice = 0;
        for (ShoppingList shoppingList : shoppingLists) {
            totalPrice += shoppingList.getTotalPrice();
        }
        return (int) (totalPrice + Math.floor(Math.random()*(Math.pow(10,(int) (Math.log10(totalPrice) + 1)))));
    }

    /**
     * Provided the UID of the required order, get its information in the form of
     * an order object.
     * @param UID the required order's UID.
     * @return The order corresponding to the UID.
     */
    public int getUID (int UID){
        return UID; // Get order from database which matches UID. DB connection not implemented so currently null.
    }

    public void setStatusOTW() {
        this.order.setStatusOTW();
    }

    public void setStatusREC() {
        this.order.setStatusREC();
    }

    public void setStatusCOMP() {
        this.order.setStatusCOMP();
    }

    public Order.OrderStatus getStatus() {
        return this.order.getStatus();
    }

    public String getDeliverManDisplay(){
        return this.order.getDeliverManDisplay();
    }

    public void setDeliveryMan(DeliveryMan deliveryMan){
        this.order.setDeliveryMan(deliveryMan);
    }

    public DeliveryMan getDeliveryMan() {
        return this.order.getDeliveryMan();
    }

    public double getTotalPrice() {
        return this.order.getTotalPrice();
    }

//    /**
//     * All Manager classes in controllers.use_cases have some transactions to save.
//     *
//     * @param customerUsername The username with which the database can be queried
//     * @param order The corresponding Order
//     */
//    @Override
//    public void save(String customerUsername, Order order) {
//        Map<String, Order> toSave = new HashMap<>();
//        toSave.put(customerUsername, (Order) order);
//
//        ref.setValueAsync(toSave);
//    }
//
//    /**
//     * All Manager classes query the database for a specific Order type that it is managing.
//     *
//     * @param customerUsername The username with which the database can be queried
//     */
//    @Override
//    public void get(String customerUsername, final OnDataReadListener onDataReadListener) {
//        ref.child(customerUsername).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                onDataReadListener.setSavedObject(snapshot.getValue(Order.class));
//                onDataReadListener.onSuccess();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                onDataReadListener.onFailure();
//            }
//        });
//    }

}
