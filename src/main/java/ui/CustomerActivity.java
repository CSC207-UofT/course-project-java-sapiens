package ui;

import controllers.use_cases.CustomerManager;
import controllers.use_cases.ShoppingListManager;
import entities.Customer;
import entities.ShoppingList;

import java.util.ArrayList;

public class CustomerActivity implements Activity{
    private Customer customer;
    @Override
    public void display() {
        ArrayList<Object> toSave = new ArrayList<>();
        toSave.add(customer);
        // TO-DO Create a shopping list
        sio.intent(new ShoppingListActivity(), toSave);
        // TO-DO Check order status

    }

    @Override
    public void getData(ArrayList<Object> transferredData) {
        this.customer = (Customer) transferredData.get(0);
    }
}
