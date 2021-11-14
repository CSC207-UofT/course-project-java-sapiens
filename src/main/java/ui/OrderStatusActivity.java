package ui;

import entities.Customer;
import entities.Order;
import entities.ShoppingList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrderStatusActivity implements Activity{

    private Customer customer;
    private Order order;

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
        sio.sendOutput("Your current order status is:");
        sio.sendOutput(this.order.getDeliverManDisplay());
        displayShoppingLists(this.order);
        displayStatus(this.order);

        boolean activityShift = false;
        while (!activityShift) {
            sio.sendOutput("$ Please type in the corresponding number for the commands$ \n " +
                    "1: refresh your order status \n " +
                    "2: back to main menu.");
            String command = sio.getInput();
            switch (command) {
                case "1": // If the user wants to check order status.
                    sio.sendOutput("Your current order status is:");
                    sio.sendOutput(this.order.getDeliverManDisplay());
                    displayShoppingLists(this.order);
                    displayStatus(this.order);
                    break;

                case "2": // If the user wants to go back, switch to the CustomerActivity.
                    // Originally this line should go back to CustomerActivity.
                    //sio.intent(new CustomerActivity(), new Object[] {this.customer, this.order});
                    // For phase 1, we automatically complete the order since we don't have delivery man.
                    sio.intent(new OrderCompletionActivity(), new Object[] {this.customer, this.order});
                    activityShift = true;
                    break;
            }
        }

    }

    @Override
    public void getData(Object transferredData) {
        this.customer = (Customer) Array.get(transferredData, 0);
        this.order = (Order) Array.get(transferredData, 1);
    }

}
