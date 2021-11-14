package ui;

import entities.Customer;
import entities.Order;
import entities.ShoppingList;

import java.util.ArrayList;

public class OrderStatusActivity implements Activity{

    private Customer customer;

    ArrayList<Order> order = new ArrayList<>();

//    private void displayOrder(Order order){
//        sio.sendOutput("The Information of the Delivery Person completing your order is:");
//        String delName = order.getDeliveryMan().getName();
//        long delId = order.getDeliveryMan().getSin();
//        String delContact = order.getDeliveryMan().getNumber();
//        float delRate = order.getDeliveryMan().getRate();
//        String displayName = "Name: " + delName;
//        String displayId = "ID: " + delId;
//        String displayContact = "Phone Number: " + delContact;
//        String displayRate = "Rating: " + delRate;
//
//        sio.sendOutput(displayName);
//        sio.sendOutput(displayId);
//        sio.sendOutput(displayContact);
//        sio.sendOutput(displayRate);
//    }

    private void displayShoppingLists(Order order){
        sio.sendOutput("The commodities you asked for are:");

        ArrayList<ShoppingList> shoppingLists = order.getShoppingLists();

        for (ShoppingList shoppingList : shoppingLists) {

            String displayOutlet = shoppingList.getOutletName() + ": ";
            StringBuilder displayContents = new StringBuilder();
            for (String item: shoppingList.getShoppingList().keySet()){
                displayContents.insert(0, item + " ");

            sio.sendOutput(displayOutlet + displayContents);
            }
        }
    }

    private void displayStatus(Order order){
        sio.sendOutput("Order Status: " + order.getStatus());
    }

    @Override
    public void display(){
        for(Order order: this.order){

            sio.sendOutput(order.getDeliverManDisplay());
            displayShoppingLists(order);
            displayStatus(order);
        }
    }

    @Override
    public void getData(Object transferredData) {
        this.customer = (Customer) transferredData;
    }

}
