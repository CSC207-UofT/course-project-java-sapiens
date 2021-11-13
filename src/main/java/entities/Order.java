package entities;

import java.util.ArrayList;

public class Order {
    private OrderStatus status;

    private DeliveryMan deliveryMan;

    private Customer customer;
    private ArrayList<ShoppingList> shoppingLists;

    private enum OrderStatus {
        OTW {
            @Override
            public String toString() {
                return "On the Way to get your order.";
            }
        },

        REC {
            @Override
            public String toString() {
                return "Order Received, Out for Delivery.";
            }
        },

        COMP {
            @Override
            public String toString() {
                return "Order Delivered and Complete.";
            }
        }
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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

    public void setShoppingLists(ArrayList<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }
}

