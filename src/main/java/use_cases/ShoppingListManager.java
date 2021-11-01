package use_cases;

import entities.Commodity;
import entities.ShoppingList;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;

/**
 * Represents the entire system of Customers creating ShoppingLists.
 */
public class ShoppingListManager extends DBManager<String, ShoppingList> {

    private HashMap<String, ShoppingList> userIDToShoppingList;

    /**
     * Create a ShoppingListManager with an empty userIDToShoppingList.
     */
    public ShoppingListManager(){
        super();
        userIDToShoppingList = new HashMap<String, ShoppingList>();
    }

    /**
     * Get ShoppingList given a userID.
     *
     * @param userID user ID of the User.
     *
     * @return the ShoppingList of the userID.
     */
    public ShoppingList getShoppingList(String userID){
        return this.userIDToShoppingList.get(userID);
    }

    /**
     * Create and add a new ShoppingList and maps it to the User in userIDToShoppingList.
     *
     * @param userID user ID of the User.
     */
    public void newShoppingList(String userID){
        ShoppingList shoppingList = new ShoppingList();
        userIDToShoppingList.put(userID, shoppingList);
    }

    /**
     * Remove the user's ShoppingList from userToShoppingLists.
     *
     * @param userID user ID of the User.
     */
    public void deleteShoppingList(String userID){
        userIDToShoppingList.put(userID, null);
    }

    /**
     * Add commodity to the user's ShoppingList under the outlet.
     *
     * @param userID user ID of the User.
     * @param outlet name of the outlet.
     * @param commodityName name of commodity.
     * @param commodityPrice price of commodity.
     *
     * @return the ShoppingList after change
     */
    public ShoppingList addCommodity(String userID, String outlet, String commodityName, double commodityPrice){
        if (userIDToShoppingList.get(userID) == null){
            newShoppingList(userID);
        }
//        for (Commodity comm : userIDToShoppingList.get(userID).getOutletHashMap(outlet).keySet()) {
//            if (comm.getName().equals(commodityName)) {
//                userIDToShoppingList.get(userID).addCommodity(outlet, comm);
//                return userIDToShoppingList.get(userID);
//            }
//        }
        Commodity commodity = new Commodity(commodityName, commodityPrice);
        userIDToShoppingList.get(userID).addCommodity(outlet, commodity);
        return userIDToShoppingList.get(userID);
    }

    /**
     * Remove commodity from the user's ShoppingList under the outlet.
     *
     * @param userID user ID of the User.
     * @param outlet name of the outlet.
     * @param commodityName name of commodity.
     *
     * @return the ShoppingList after change
     */
    public ShoppingList removeCommodity(String userID, String outlet, String commodityName) {
        for (Commodity comm : userIDToShoppingList.get(userID).getOutletHashMap(outlet).keySet()) {
            if (comm.getName().equals(commodityName)) {
                userIDToShoppingList.get(userID).removeCommodity(outlet, comm);
                return userIDToShoppingList.get(userID);
            }
        }
        return null;
    }

    /**
     * All Manager classes in use_cases have some transactions to save.
     *
     * @param obj The 'key' with which the database can be queried
     * @param val The corresponding object
     */
    @Override
    public void save(String obj, ShoppingList val) {

    }

    /**
     * All Manager classes query the database for a specific object type that it is managing.
     *
     * @param obj The 'key' with which the database can be queried.
     * @return The corresponding object
     */
    @Override
    public ShoppingList get(String obj) {
        return null;
    }
}
