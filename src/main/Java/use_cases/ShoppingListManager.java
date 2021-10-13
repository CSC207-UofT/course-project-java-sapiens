package Java.use_cases;

import Java.entities.Commodity;
import Java.entities.ShoppingList;
import Java.entities.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the entire system of Customers creating ShoppingLists.
 */
public class ShoppingListManager {

    private HashMap<String, ShoppingList> userIDToShoppingList;

    /**
     * Create a ShoppingListManager with an empty userIDToShoppingList.
     */
    public ShoppingListManager(){
        userIDToShoppingList = new HashMap<String, ShoppingList>();
    }

    /**
     * Create and add a new ShoppingList and maps it to the User in userIDToShoppingList.
     * If user is not
     * @param outlet name of the outlet.
     * @param userID user ID of the User.
     */
    public void newShoppingList(String outlet, String userID){
        ShoppingList shoppingList = new ShoppingList();
        userIDToShoppingList.put(userID, shoppingList);
    }

    /**
     * remove the user's ShoppingList from userToShoppingLists.
     * If user is not
     * @param userID user ID of the User.
     */
    public void deleteShoppingList(String userID){
        userIDToShoppingList.put(userID, null);
    }

    /**
     * Add commodity to the user's ShoppingList under the outlet.
     * If user is not
     * @param outlet name of the outlet.
     * @param userID user ID of the User.
     * @param commodity a Commodity.
     */
    public void addCommodity(String outlet, String userID, Commodity commodity){
    }

    /**
     * Remove commodity from the user's ShoppingList under the outlet.
     * If user is not
     * @param outlet name of the outlet.
     * @param userID user ID of the User.
     * @param commodity a Commodity.
     */
    public void removeCommodity(String outlet, String userID, Commodity commodity){
    }
}
