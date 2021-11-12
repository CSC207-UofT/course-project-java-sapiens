package ui;

import controllers.use_cases.ShoppingListManager;
import entities.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingListActivity implements Activity{
    private Customer customer;
    private final ShoppingListManager slManager = new ShoppingListManager();

    public String checkRegex(String reg, String str, String prompt){
        String string = str;
        while (!string.matches(reg)) {
            sio.sendOutput(prompt);
            string = sio.getInput();
        }
        return string;
    }
    public int checkInt(String str, String prompt){
        String string = str;
        String reg = "[0-9]*";
        while (!string.matches(reg)) {
            sio.sendOutput(prompt);
            string = sio.getInput();
        }
        return Integer.parseInt(string);
    }
    public double checkDouble(String str, String prompt){
        String string = str;
        String reg = "[0-9]*[\\.]?[0-9]*";
        while (!string.matches(reg)) {
            sio.sendOutput(prompt);
            string = sio.getInput();
        }
        return Double.parseDouble(string);
    }

    @Override
    public void display() {
        boolean addSL = true;
        int index = 0;

        while (addSL) {
            Scanner sc = new Scanner(System.in);

            sio.sendOutput("Store or Outlet?");
            if (checkRegex("[Ss]tore|[Oo]utlet", sio.getInput(), "Store or Outlet?")
                    .toLowerCase().equals("store")) {
                sio.sendOutput("Enter the store's name");
                String storeName = sio.getInput();
                slManager.newShoppingList(storeName);
            } else {
                sio.sendOutput("Enter the outlet's name");
                String outletName = sio.getInput();
                sio.sendOutput("Enter the outlet's address");
                String outletAddress = sio.getInput();
                slManager.newShoppingList(outletName, outletAddress);
            }

            boolean addCommodity = true;
            while (addCommodity) {
                sio.sendOutput("Enter the name of commodity");
                String commName = sio.getInput();

                String commPricePrompt = "Enter " + commName + "'s price";
                sio.sendOutput(commPricePrompt);
                double commPrice = checkDouble(sio.getInput(),commPricePrompt);

                String commQuantityPrompt = "Enter " + commName + "'s quantity";
                sio.sendOutput(commQuantityPrompt);
                int commQuantity = checkInt(sio.getInput(),commQuantityPrompt);
                slManager.setCommodity(index, commName, commPrice, commQuantity);

                sio.sendOutput("Type 'c' to add another commodity \n'n' to continue");
                String choice = sio.getInput().toLowerCase();

                if ("n".equals(choice)) {
                    addCommodity = false;
                }
            }

            sio.sendOutput("Type 'r' to remove a commodity by name \n" +
                    "'a' to add to a commodity by name \n'p' to check the total price for this store \n" +
                    "'n' to exit this store");
            String choiceContinue = sio.getInput().toLowerCase();
            boolean chooseContinue = true;
            while (chooseContinue) {
                switch (choiceContinue) {
                    case "r":
                        sio.sendOutput("Enter the name of commodity to remove(1)");
                        String commNameRemove = sio.getInput();
                        slManager.removeCommodity(index, commNameRemove);
                    case "a":
                        sio.sendOutput("Enter the name of commodity to add(1)");
                        String commNameAdd = sio.getInput();
                        slManager.addCommodity(index, commNameAdd);
                    case "p":
                        sio.sendOutput(slManager.getShoppingLists().get(index).getTotalPrice());
                    default:
                        chooseContinue = false;
                }
            }
            index += 1;

            sio.sendOutput("Type 'a' to add another store/outlet\n'n' to create an order with these shopping lists");
            String choice = sio.getInput().toLowerCase();
            if ("n".equals(choice)) {
                addSL = false;
            }

        }

        slManager.save(customer.getUname(), null);

        // ArrayList<Object> savedObjects = new ArrayList<>();
        // savedObjects.add(customer);
        // sio.intent(new OrderActivity(), savedObjects);

    }

    @Override
    public void getData(Object transferredData) {
        this.customer = (Customer) transferredData;
    }

}
