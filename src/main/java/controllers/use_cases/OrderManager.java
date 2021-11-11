package controllers.use_cases;

import entities.Order;
import controllers.use_cases.Database.DBManager;
import controllers.use_cases.Database.OnDataReadListener;
//import entities.ShoppingList;


public class OrderManager extends DBManager<Integer, Order> {

    public int generateUID(){
        return 1546; //Assume there have been 1546 orders before (Need to sync with database) Hardcoded for now
    }

//    /**
//     * Creates an Order
//     *
//     * @param deliveryMan Delivery man of new order
//     * @param customer Customer of new order
//     * @param shoppingList The shoppingList that the customer has added
//     * @return required order.
//     */
//    public Order createOrder(DeliveryMan deliveryMan, Customer customer, ShoppingList shoppingList){
//        int currUID = generateUID();
//        return new Order(deliveryMan, customer, ++currUID ,shoppingList);
//    }

//    /**
//     * Provided the UID of the required order, get its information in the form of
//     * an order object.
//     * @param UID the required order's UID.
//     * @return The order corresponding to the UID.
//     */
////    public Order getOrderInfo(int UID){
////        return get(UID); // Get order from database which matches UID. DB connection not implemented so currently null.
////    }

    /**
     * All Manager classes in controllers.use_cases have some transactions to save.
     *
     * @param UID The UID with which the database can be queried
     * @param order The corresponding Order
     */
    @Override
    public void save(Integer UID, Order order) {

    }

    /**
     * All Manager classes query the database for a specific Order type that it is managing.
     *
     * @param UID The UID with which the database can be queried.
     */
    @Override
    public void get(Integer UID, final OnDataReadListener onDataReadListener) {
        return;
    }
}
