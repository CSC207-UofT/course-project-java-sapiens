package ui;

import controllers.use_cases.ShoppingListManager;
import entities.Customer;
import entities.ShoppingList;

import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingListActivity implements Activity{
    private Customer customer;
    private final ShoppingListManager slManager = new ShoppingListManager();

    private String checkRegex(String reg, String str, String prompt){
        String string = str;
        while (!string.matches(reg)) {
            sio.sendOutput(prompt);
            string = sio.getInput();
        }
        return string;
    }
    private int checkInt(String str, String prompt){
        String string = str;
        String reg = "[0-9]*";
        while (!string.matches(reg)) {
            sio.sendOutput(prompt);
            string = sio.getInput();
        }
        return Integer.parseInt(string);
    }
    private double checkDouble(String str, String prompt){
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

        ArrayList<ShoppingList> shoppingLists = new ArrayList<>();

        // Add ShoppingLists
        while (addSL) {
            sio.sendOutput("Store or Outlet?");
            if (checkRegex("[Ss]tore|[Oo]utlet", sio.getInput(), "Store or Outlet?").equals("store")) {
                sio.sendOutput("Enter the store's name");
                String storeName = sio.getInput();
                slManager.newShoppingList(storeName, shoppingLists);

            } else {
                sio.sendOutput("Enter the outlet's name");
                String outletName = sio.getInput();
                sio.sendOutput("Enter the outlet's address");
                String outletAddress = sio.getInput();
                slManager.newShoppingList(outletName, outletAddress, shoppingLists);
            }

            // addCommodities
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
                slManager.setCommodity(index, commName, commPrice, commQuantity, shoppingLists);

                String choiceCommPrompt = "Type 'c' to add another commodity \n'n' to continue";
                sio.sendOutput(choiceCommPrompt);
                String choiceComm= checkRegex("[c|n]", sio.getInput(), choiceCommPrompt);
                if ("n".equals(choiceComm)) {
                    addCommodity = false;
                }
            }

            // Modify a ShoppingList once it's been created
            String chooseContinuePrompt = "Type 'r' to remove a commodity by name \n" +
                    "'a' to add to a commodity by name \n'p' to check the total price for this store \n" +
                    "'n' to exit this store\n";

            boolean chooseContinue = true;
            while (chooseContinue) {
                sio.sendOutput(chooseContinuePrompt);
                String choiceContinue = checkRegex("[r|a|p|n]", sio.getInput(), chooseContinuePrompt);

                switch (choiceContinue) {
                    case "r":
                        sio.sendOutput("Enter the name of commodity to remove");
                        String commNameRemove = sio.getInput();
                        slManager.removeCommodity(index, commNameRemove, shoppingLists);
                        break;
                    case "a":
                        sio.sendOutput("Enter the name of commodity to add");
                        String commNameAdd = sio.getInput();
                        slManager.addCommodity(index, commNameAdd, shoppingLists);
                        break;
                    case "p":
                        sio.sendOutput(shoppingLists.get(index).getTotalPrice());
                        break;
                    default:
                        chooseContinue = false;
                }
            }
            index += 1;

            String choicePrompt = "Type 'a' to add another store/outlet" +
                    "\n'n' to create an order with these shopping lists";
            sio.sendOutput(choicePrompt);
            String choice = checkRegex("[a|n]", sio.getInput(), choicePrompt);
            if ("n".equals(choice)) {
                addSL = false;
            }
        }

        slManager.save(customer.getUname(), shoppingLists);

        sio.intent(new OrderCreationActivity(), new Object[] {customer, shoppingLists});
    }

    @Override
    public void getData(Object transferredData) {
        this.customer = (Customer) transferredData;
    }

}