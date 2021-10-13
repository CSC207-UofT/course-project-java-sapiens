package use_cases;

import Java.entities.Commodity;
import Java.entities.ShoppingList;

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
     * @param commodityName name of commodity.
     * @param commodityPrice price of commodity.
     * @return a ShoppingList
     */
    public ShoppingList addCommodity(String outlet, String userID, String commodityName, float commodityPrice){
    }

    /**
     * Remove commodity from the user's ShoppingList under the outlet.
     * If user is not
     * @param outlet name of the outlet.
     * @param userID user ID of the User.
     * @param commodityName name of commodity.
     * @param commodityPrice price of commodity.
     * @return a ShoppingList
     */
    public ShoppingList removeCommodity(String outlet, String userID, String commodityName, float commodityPrice){
    }
}
