package ui;

import controllers.use_cases.CustomerManager;
import controllers.use_cases.ShoppingListManager;
import entities.Customer;
import entities.ShoppingList;

import java.util.ArrayList;

public class CustomerActivity implements Activity{
    private Customer cus;
    @Override
    public void display() {
        // TO-DO Create a shopping list
        sio.intent(new ShoppingListActivity(), null);
        // TO-DO Check order status

    }

    @Override
    public void getData(ArrayList<Object> transferredData) {
        this.cus = (Customer) transferredData.get(0);
    }
}
