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

                String choiceCommPrompt = "Type 'c' to add another commodity \n'n' to continue";
                sio.sendOutput(choiceCommPrompt);
                String choiceComm= checkRegex("[c|n]", sio.getInput(), choiceCommPrompt);
                if ("n".equals(choiceComm)) {
                    addCommodity = false;
                }
            }

            String chooseContinuePrompt = "Type 'r' to remove a commodity by name \n" +
                    "'a' to add to a commodity by name \n'p' to check the total price for this store \n" +
                    "'n' to exit this store";
            sio.sendOutput(chooseContinuePrompt);
            String choiceContinue = checkRegex("[r|a|p|n]", sio.getInput(), chooseContinuePrompt);
            boolean chooseContinue = true;
            while (chooseContinue) {
                switch (choiceContinue) {
                    case "r":
                        sio.sendOutput("Enter index of outlet you want to delete");
                        int removeIndex = Integer.parseInt(sio.getInput());

                        sio.sendOutput("Enter the name of commodity to remove(1)");
                        String commNameRemove = sio.getInput();
                        slManager.removeCommodity(removeIndex, commNameRemove);
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

            String choicePrompt = "Type 'a' to add another store/outlet" +
                    "\n'n' to create an order with these shopping lists";
            sio.sendOutput(choicePrompt);
            String choice = checkRegex("[a|n]", sio.getInput(), choicePrompt);
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

//class RunSLA {
//    public static void main(String[] args){
//        ShoppingListActivity sla = new ShoppingListActivity();
//        sla.display();
//    }
//}