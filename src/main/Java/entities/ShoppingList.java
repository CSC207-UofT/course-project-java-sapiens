package entities;

import entities.Commodity;
import entities.Outlet;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingList {

    private HashMap<Outlet, HashMap<Commodity, Integer>> shoppingList;
    private double totalPrice;

    public ShoppingList(){
        shoppingList = null;
        totalPrice = 0.0;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public HashMap<Outlet, HashMap<Commodity, Integer>> getShoppingList() {
        return shoppingList;
    }

    public void addCommodity(Outlet outlet, Commodity commodity){
        if(shoppingList.get(outlet) == null){
            // if the outlet is not there
            HashMap<Commodity, Integer> outletList = new HashMap<>();
            outletList.put(commodity, 1);
            shoppingList.put(outlet, outletList);
        }
        else if(shoppingList.get(outlet).get(commodity) == null) {
            //if the commodity is not there
            shoppingList.get(outlet).put(commodity, 1);
        }
        else {
            //if both are there
            Integer currentQuantity = shoppingList.get(outlet).get(commodity);
            shoppingList.get(outlet).put(commodity, currentQuantity + 1);
        }
        totalPrice += commodity.getPrice();
    }

    public void removeCommodity(Outlet outlet, Commodity commodity){
        if(shoppingList.get(outlet) == null || shoppingList.get(outlet).get(commodity) == null){
            //TODO: handle the exception
            System.out.println("No such commodity found");
        }
        else {
            Integer currentQuantity = shoppingList.get(outlet).get(commodity);
            if(currentQuantity == 1)
                shoppingList.get(outlet).remove(commodity);
            else
                shoppingList.get(outlet).put(commodity, currentQuantity - 1);
            totalPrice -= commodity.getPrice();
        }
    }
}
