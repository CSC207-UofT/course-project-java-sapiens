package controllers.use_cases;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import entities.Customer;
import entities.DeliveryMan;
import entities.Order;
import controllers.use_cases.Database.DBManager;
import controllers.use_cases.Database.OnDataReadListener;
import entities.ShoppingList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import entities.ShoppingList;


public class OrderManager extends DBManager<String, Order> {

      final String REF_PATH;

    /**
     * Creates an OrderManager on the basis of the current customer's username
     */
      public OrderManager() {
          REF_PATH = "Order";
          ref = database.getReference(REF_PATH);
      }


    /**
     * Creates an Order
     *
     * @param deliveryMan Delivery man of new order
     * @param customer Customer of new order
     * @param shoppingLists The shoppingLists that the customer has added
     * @return required order.
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
    public int getOrderInfo(int UID){
        return UID; // Get order from database which matches UID. DB connection not implemented so currently null.
    }

    /**
     * All Manager classes in controllers.use_cases have some transactions to save.
     *
     * @param customerUsername The username with which the database can be queried
     * @param order The corresponding Order
     */
    @Override
    public void save(String customerUsername, Order order) {
//        Map<String, Order> toSave = new HashMap<>();
//        toSave.put(customerUsername, (Order) order);
//
//        ref.setValueAsync(toSave);
    }

    /**
     * All Manager classes query the database for a specific Order type that it is managing.
     *
     * @param customerUsername The username with which the database can be queried
     */
    @Override
    public void get(String customerUsername, final OnDataReadListener onDataReadListener) {
        ref.child(customerUsername).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                onDataReadListener.setSavedObject(snapshot.getValue(Order.class));
                onDataReadListener.onSuccess();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                onDataReadListener.onFailure();
            }
        });
    }
}
