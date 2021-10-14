package use_cases;

import entities.Commodity;
import entities.ShoppingList;

import java.util.HashMap;
import java.util.Objects;

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
     *
     * @param outlet name of the outlet.
     * @param userID user ID of the User.
     */
    public void newShoppingList(String outlet, String userID){
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
     * Find the Commodity in userToShoppingLists by commodityName
     * @param outlet name of the outlet.
     * @param userID user ID of the User.
     * @param commodityName name of commodity.
     *
     * @return the Commodity that has commodityName
     */
    public Commodity commodityByName(String outlet, String userID, String commodityName) {
        for (Commodity comm: userIDToShoppingList.get(userID).getOutletHashMap(outlet).keySet()){
            if (comm.getName().equals(commodityName)){
                return comm;
            }
        }
        return null;
    }

    /**
     * Add commodity to the user's ShoppingList under the outlet.
     *
     * @param outlet name of the outlet.
     * @param userID user ID of the User.
     * @param commodityName name of commodity.
     * @param commodityPrice price of commodity.
     *
     * @return the ShoppingList after change
     */
    public ShoppingList addCommodity(String outlet, String userID, String commodityName, float commodityPrice){
        Commodity comm = commodityByName(outlet, userID, commodityName);
        if (Objects.isNull(comm)){
            Commodity commodity = new Commodity(commodityName, commodityPrice);
            userIDToShoppingList.get(userID).addCommodity(outlet, commodity);
        }
        else {
            userIDToShoppingList.get(userID).addCommodity(outlet, comm);
        }
        return userIDToShoppingList.get(userID);
    }

    /**
     * Remove commodity from the user's ShoppingList under the outlet.
     *
     * @param outlet name of the outlet.
     * @param userID user ID of the User.
     * @param commodityName name of commodity.
     * @param commodityPrice price of commodity.
     *
     * @return the ShoppingList after change
     */
    public ShoppingList removeCommodity(String outlet, String userID, String commodityName, float commodityPrice){
        Commodity comm = commodityByName(outlet, userID, commodityName);
        userIDToShoppingList.get(userID).removeCommodity(outlet, comm);
        return userIDToShoppingList.get(userID);
    }
}
