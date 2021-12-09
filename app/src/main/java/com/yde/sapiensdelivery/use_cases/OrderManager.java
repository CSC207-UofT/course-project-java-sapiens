package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.gateways.OrderGateway;

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
        return new Order(deliveryMan, customer ,shoppingLists);
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

    public String getCustomerName(){
        return order.getCustomer().getName();
    }

    public String getCustomerAddress(){
        return order.getCustomer().getLocation();
    }

    public String getCustomerPhoneNum(){
        return order.getCustomer().getNumber();
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

    public ArrayList<ShoppingList> getShoppingLists(){
        return order.getShoppingLists();
    }

    public Order.OrderStatus getStatus() {
        return this.order.getStatus();
    }

    public boolean isStatusCOMP(Order.OrderStatus orderStatus){
        return order.isStatusCOMP(orderStatus);
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

            }

        }

        HashMap<String, Float> journey= new HashMap<>();

        journey.put("Total Distance", total_distance);
        journey.put("Total Duration", total_duration);

        return journey;
    }

    public void updateStatusComp(String customerName) {
        order.setStatusCOMP();
        OrderGateway orderGateway = new OrderGateway();
        orderGateway.save(customerName, order);
    }

    public void updateStatusOTW(String customerName) {
        order.setStatusOTW();
        OrderGateway orderGateway = new OrderGateway();
        orderGateway.save(customerName, order);
    }
}
