package entities;
import java.util.ArrayList;

public class Order {
    private OrderStatus status;

    private DeliveryMan deliveryMan;
    private int UID;

    private Customer customer;
    private ArrayList<ShoppingList> shoppingLists;

    private double totalPrice;

    // Order stores a list of ShoppingLists, one ShoppingList for each store/outlet.
    public Order(DeliveryMan deliveryMan, Customer customer, int UID, ArrayList<ShoppingList> shoppingLists) {
        this.deliveryMan  = deliveryMan;
        this.customer = customer;
        this.UID = UID;
        this.shoppingLists = shoppingLists;

        for (ShoppingList shoppingList : shoppingLists) {
            this.totalPrice += shoppingList.getTotalPrice();
        }
    }

    public Order(DeliveryMan deliveryMan, Customer customer, int UID, ShoppingList shoppingList) {
        this.deliveryMan  = deliveryMan;
        this.customer = customer;
        this.UID = UID;
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

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public int getUID(){
        return this.UID;
    }

    public String getDeliverManDisplay() {
        String start = "The Information of the Delivery Person completing your order is: \n";
        String delName = this.deliveryMan.getName();
        String delContact = this.deliveryMan.getNumber();
        float delRate = this.deliveryMan.getRate();
        String displayName = "Name: " + delName + "\n";
        String displayContact = "Phone Number: " + "\n";
        String displayRate = "Rating: "  + "\n";
        return (start + displayName + displayContact + displayRate);
    }
}

