package use_cases;

//import entities.Customer;
//import entities.DeliveryMan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserManager<DeliveryMan,Customer> {
    private HashMap<String, String> customers;
    private HashMap<String, String> deliverymans;

    /**
     * Creates a UserManager with lists of customer and delivery man that are empty
     */
    public UserManager() {
        customers = new HashMap<String, String>();
        deliverymans = new HashMap<String, String>();
    }


    /**
     * Stores the uname and password in this customers' class list.
     * @param uname and password to be stored
     */
    public void addC(String uname, String password) {
        customers.put(uname, password);
    }

    /**
     * Stores the uname  and password in this deliveryMan' class list.
     * @param uname and password to be stored
     */
    public void addD(String uname, String password) {
        deliverymans.put(uname, password);
    }

    /**
     * checking if the consumer's input match the password
     * @param
     */
    public boolean checkC(String uname, String password) {
        if (customers.get(uname).equals(password) ) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * checking if the deliveryMan's input match the password
     * @param uname and password
     */
    public boolean checkD(String uname, String password) {
        if (deliverymans.get(uname).equals(password) ) {
            return true;
        }
        else{
            return false;
        }
    }
}
