package ui;

import controllers.use_cases.OrderManager;
import controllers.use_cases.ShoppingListManager;
import entities.Customer;
import entities.DeliveryMan;
import entities.Order;
import entities.ShoppingList;

import java.util.ArrayList;

public class OrderCreationActivity implements Activity{
    private Customer customer;
    private final OrderManager orderManager = new OrderManager();
    private ShoppingList shoppingList;
    private ArrayList<DeliveryMan> dList;
    private Order order;
//    super(n, l, num, user, pass)
//     d1 = new DeliveryMan(String n, int[] l, String num, String user, String pass, long sin, "moto-bike", 4.5);

    DeliveryMan d1 = new DeliveryMan("Samuel", new int[] {10,20}, "GTS", "Samuel", "12", 1234, "moto-bike", 4.5);


    @Override
    public void display() {
        sio.sendOutput("Which delivery man would you like to select for your order?");
        sio.sendOutput("This step is skipped for phase1");
        sio.sendOutput("Delivery man is chosen successfully");
        order = orderManager.createOrder(d1,customer, shoppingList);
//
//        sio.sendOutput("Which delivery man would you like to select for your order:\n +" +
//                "1:" + dList.get(0).getName() + " "+ dList.get(0).getRate() +"\n"+
//                "1:" + dList.get(1).getName() + " "+ dList.get(1).getRate() +"\n"+
//                "1:" + dList.get(2).getName() + " "+ dList.get(2).getRate() +"\n");
//        String Deliveryman_number = sio.getInput();
//        switch (Deliveryman_number) {
//            case "1": // The customer places the order.
//                sio.sendOutput("You choose Delivery man: " + dList.get(0).getName());
//                order = orderManager.createOrder(dList.get(0),customer, shoppingList);
//                break;
//
//            case "2": // If the user wants to check order status, switch to the order status activity.
//                sio.sendOutput("You choose Delivery man: " + dList.get(1).getName());
//                order  = orderManager.createOrder(dList.get(1),customer, shoppingList);
//                break;
//
//            case "3": // If the user wants user info, prompt the info of current account.
//                sio.sendOutput("You choose Delivery man: " + dList.get(2).getName());
//                order = orderManager.createOrder(dList.get(2),customer, shoppingList);
//                break;
//        }
        // Pass the control to OrderStatus Activity
//        sio.intent(new OrderStatusActivity(), this.customer);
        sio.intent(new CustomerActivity(), new Object[] {customer, order});

    }


    @Override
    public void getData(Object transferredData) {
        this.customer = (Customer) transferredData;
        this.shoppingList = (ShoppingList) transferredData;
    }
}