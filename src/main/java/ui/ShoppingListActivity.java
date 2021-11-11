package ui;

import controllers.use_cases.ShoppingListManager;
import entities.Customer;

import java.util.ArrayList;
import java.util.Locale;

public class ShoppingListActivity implements Activity{
    private Customer customer;
    private final ShoppingListManager slManager = new ShoppingListManager();
    @Override
    public void display() {
        boolean addSL = true;
        int index = 0;

        while (addSL) {
            sio.sendOutput("Store or Outlet?");

            if (sio.getInput().toLowerCase(Locale.ROOT).equals("store")) {
                sio.sendOutput("Enter store name");
                String storeName = sio.getInput();
                slManager.newShoppingList(storeName);
            }
            else {
                sio.sendOutput("Enter outlet name");
                String outletName = sio.getInput();
                sio.sendOutput("Enter outlet address");
                String outletAddress = sio.getInput();
                slManager.newShoppingList(outletName, outletAddress);
            }
            boolean add_commodity = true;
            while (add_commodity) {
                sio.sendOutput("Name of Commodity");
                String outletName = sio.getInput();
            }
        }



        slManager.save(customer.getUname(), null);
    }

    @Override
    public void getData(ArrayList<Object> transferredData) {
        this.customer = (Customer) transferredData.get(0);
    }
}
