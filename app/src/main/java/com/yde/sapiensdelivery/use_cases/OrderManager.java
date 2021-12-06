package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.entities.ShoppingList;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderManager{
    private Order order;

    /**
     * Creates an OrderManager given an already existing Order
     */
    public OrderManager(Order order) {
        this.order = order;
    }

    /**
     * Creates an OrderManager
     */
    public OrderManager() {
    }

    /**
     * Creates an Order
     *
     * @param deliveryMan Delivery man of new order
     * @param customer Customer of new order
     * @param shoppingLists The shoppingLists that the customer has added
     */
    public Order createOrder(DeliveryMan deliveryMan, Customer customer, ArrayList<ShoppingList> shoppingLists){
        int currUID = generateUID(shoppingLists);
        return new Order(deliveryMan, customer, ++currUID ,shoppingLists);
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

    /**
     * Name of Order
     * @return Order name using UID
     */
    public String getName(){
        return "Order#" + order.getUID();
    }

    /**
     * Get contact info for order
     * @return Contact information of deliveryman
     */
    public String getDeliveryManContact(){
        return order.getDeliveryMan().getName() + " : " + order.getDeliveryMan().getNumber();
    }

    /**
     * Get contact info for order
     * @return Contact information of customer
     */
    public String getCustomerContact(){
        return order.getCustomer().getName() + " : " + order.getDeliveryMan().getNumber();
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

    public HashMap<String, Float> calculateJourney(Locator locator){
        float total_distance = 0;
        float total_duration = 0;

        String start = this.order.getDeliveryMan().getLocation();
        String end = this.order.getCustomer().getLocation();

        ArrayList<String> stops = new ArrayList<>();
        stops.add(start);

        for(ShoppingList s: this.order.getShoppingLists()){
            stops.add(s.getOutletAddress());
        }
        stops.add(end);

        String transport = order.getDeliveryMan().getTransport();


        for(int i = 0; i < stops.size(); i++){
            try {
                HashMap<String, Double> info = locator.findRouteInfo
                        (stops.get(i), stops.get(i + 1), Locator.transportation.valueOf(transport));
                double distance = info.get("Distance");
                double duration = info.get("Duration");

                total_distance += distance;
                total_duration += duration;
            } catch (Exception e){
                // TODO: Error cuz either wrong address or internet issues.
            }

        }

        HashMap<String, Float> journey= new HashMap<>();

        journey.put("Total Distance", total_distance);
        journey.put("Total Duration", total_duration);

        return journey;
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
//        for(ShoppingList o: this.order.getShoppingLists()){
//            stops.add(o.getOutletAddress());
//        }
//        stops.add(end);
//
//        String transport = order.getDeliveryMan().getTransport();
//
//
//        for(int i = 0; i < stops.size(); i++){
//            try {
//                HashMap<String, Double> info = locator.findRouteInfo
//                        (stops.get(i), stops.get(i + 1), Locator.transportation.valueOf(transport));
//                double distance = info.get("Distance");
//                double duration = info.get("Duration");
//
//                total_distance += distance;
//                total_duration += duration;
//            } catch (Exception e){
//                // TODO: Error cuz either wrong address or internet issues.
//            }
//
//        }
//
//        HashMap<String, Float> journey= new HashMap<>();
//
//        journey.put("Total Distance", total_distance);
//        journey.put("Total Duration", total_duration);
//
//        return journey;
//    }
}
