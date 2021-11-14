package entities;
import java.util.ArrayList;

public class Order {
    private OrderStatus status;

    private DeliveryMan deliveryMan;
    private int UID;

    private Customer customer;
    private ArrayList<ShoppingList> shoppingLists;

    public Order(DeliveryMan deliveryMan, Customer customer, int UID, ShoppingList shoppingList) {
        this.deliveryMan  = deliveryMan;
        this.customer = customer;
        this.UID = UID;
//        this.shoppingList = shoppingList;
    }

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

    private double totalPrice;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatusOTW() {
        this.status = OrderStatus.OTW;
    }

    public void setStatusREC() {
        this.status = OrderStatus.REC;
    }

    public void setStatusCOMP() {
        this.status = OrderStatus.COMP;
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

    public void setTotalPrice() {
        double total = 0;
        for (ShoppingList shoppingList : this.getShoppingLists()) {
            total += shoppingList.getTotalPrice();
        }
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public int getUID(){
        return this.UID;
    }
}

