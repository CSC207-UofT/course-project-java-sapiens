package controllers;

import ui.SystemInOut;
import use_cases.OrderManager;
import use_cases.ShoppingListManager;
import use_cases.UserManager;

/**
 * This file contains the controller that is in charge of distinguishing the
 * command typed by the user, and call corresponding managers.
 *
 * Sample Usage:
 * 1. to log in, please use the username "TestAccount" with password "CSC207".
 * It is the only existing account hardcoded for phase 0
 * 2. After log in, user can perform various operations by typing commands in
 * the console. Please type in "help" for a list of command available.
 * 3. To quit the program, please type in "quit".
 */
public class OrderSystem {

    // This variable represents the current account. i.e. {username, accountType}.
    private String[] currentUser = {};

    // This variable contains all the available command.
    private final String commandList =
            "1. help: get the help for the available commands\n" +
            "2. quit: quit the program \n" +
            "3. user info: gets the current username and the type of the current account \n" +
            "4. sign out: sign out the current account \n" +
            "5. place order: start to place an order \n";

    public OrderSystem() {
    }

    // Initialize and starts the system
    public void run(SystemInOut inOut) {

        // load existing user data (hard coded in phase 0)
        UserManager userM = loadData();

        // Login process:
        if (login(inOut, userM).equals("quit")) {
            return;
        }

        // After login:
        // Create a ShoppingListManager for later order placement
        // Create a OrderManager for later order placement (not implemented for phase 0)
        ShoppingListManager slM = new ShoppingListManager();

        // Starts to receive commands
        while (true) {
            inOut.sendOutput("$ Please type in command (Type in \"help\" to get help) $");
            String command = inOut.getInput();
            switch (command) {
                case "quit": // If the user enters quit, quit the program.
                    return;

                case "help": // If the user enters help, prompt the commandList to help.
                    inOut.sendOutput(commandList);
                    break;

                case "user info": // If the user enters user info, prompt the info of current account.
                    inOut.sendOutput("Username: " + this.currentUser[0] +
                            "  Account type: " + this.currentUser[1]);
                    break;

                case "sign out": // If the user enters sign out, sign out the current account and
                                 // require login again
                    inOut.sendOutput("Sign out successful, please login");
                    if (login(inOut, userM).equals("quit")) {
                        return;
                    }
                    break;

                case "place order": // If the user enters place order, start the order placement procedure.

                    // collects all the information needed to form an Order.
                    ShoppingListManager result = orderPlacement(inOut, slM);

                    // should call OrderManager here to create Order, but hardcoded for now
                    if (result != null) {
                        inOut.sendOutput("................ \n " +
                                "................ \n " +
                                "................ \n " +
                                "................ \n " +
                                "................ \n " +
                                "................ \n " +
                                "Order complete! Thanks for using our service!");
                    }
                    break;
            }
        }
    }

    /**
     * This method is in charge of loading existing data
     */
    private UserManager loadData() {
        UserManager userM = new UserManager();
        userM.addC("TestAccount", "CSC207");
        return userM;
    }

    /**
     * This method is in charge of the login process
     */
    private String login(SystemInOut inOut, UserManager um) {
        while(true){
            // Get the username, if the user typed in "quit", then return "quit"
            inOut.sendOutput("Please type in your username:");
            String username = inOut.getInput();
            if (username.equals("quit")){
                return "quit";
            }

            // Get the password, if the user typed in "quit", then return "quit"
            inOut.sendOutput("Please type in your password:");
            String password = inOut.getInput();
            if (password.equals("quit")){
                return "quit";
            }

            // Check if the username and password matches to an existing account
            if (um.checkC(username, password)){
                inOut.sendOutput("Login Successful, dear " + username + ", welcome back!");
                this.currentUser = new String[]{username, "Customer"};
                return "Success";
            } else {
                inOut.sendOutput("Login failed, the password you entered is incorrect or the" +
                        " account does not exist.");
            }
        }
    }

    /**
     * This method is in charge of collection information for the order placement.
     */
    private ShoppingListManager orderPlacement(SystemInOut inOut, ShoppingListManager slM){

        // repeat the process until user enters finish or cancel
        while (true){

            // collect the location of store/outlet
            inOut.sendOutput("Please type in one location:");
            inOut.sendOutput("type in \"cancel\" to cancel placement");
            inOut.sendOutput("type in \"finish\" to finish placement");
            String command = inOut.getInput();
            String location;

            // if user enters cancel, then end the order placement
            if (command.equals("cancel")){
                return null;
            } else if (command.equals("finish")){
                // if user enters finish, prompt a list of delivery men for choice.
                // Should prompt real delivery man that wants the order, but hardcoded for now.
                inOut.sendOutput("Please select one delivery man by typing their numbers: \n" +
                        "Type in \"cancel\" to cancel the placement \n" +
                        "1. Ansh \n" +
                        "2. Junsong \n" +
                        "3. Kevin \n" +
                        "4. Nikhil \n" +
                        "5. Patrick \n" +
                        "6. Sid");
                command = inOut.getInput();

                // if user enters cancel, end the order placement.
                if (command.equals("cancel")){
                    return null;
                } else {
                    // else finish the order placement and return the information collected.
                    inOut.sendOutput("Order successfully set up!");
                    return slM;
                }
            } else {
                // if the user enters location, then ask the commodities they want in this location.
                location = command;
                slM = addComs(inOut, slM, location);
            }
        }


    }

    /**
     * This method is a helper for orderPlacement().
     * Manages the commodity addition to a certain location.
     */
    private ShoppingListManager addComs(SystemInOut inOut, ShoppingListManager slM, String location){
        String command;
        String comName;
        double comPrice;

        // repeat the process until user enter finish.
        while (true){

            // get the input
            inOut.sendOutput("Please type in the name of one commodity for this location:");
            inOut.sendOutput("(type in \"finish\" to finish adding commodities)");
            command = inOut.getInput();

            // if user enter finish, finish adding commodities and return the information collected.
            if (command.equals("finish")){
                return slM;
            } else {
                // if user enter a commodity name, record the name and
                // ask for the expected price.
                comName = command;

                // get the input
                inOut.sendOutput("Please type in the expected price of this commodity:");
                inOut.sendOutput("(type in \"back\" to go back)");
                command = inOut.getInput();

                // if user wants to go back, go back to adding another commodity
                if (command.equals("back")) {
                    continue;
                } else {
                    // if user enter price, record the price.
                    try {
                        comPrice = Double.parseDouble(command);
                    } catch (NumberFormatException e){
                        // if the price is not a double float, prompts warning and
                        // go back to adding another commodity
                        inOut.sendOutput("Please type in a number without unit!");
                        inOut.sendOutput("this commodity is canceled");
                        continue;
                    }

                }
                // add commodity using the ShoppingListManager with collected information.
                slM.addCommodity(currentUser[0] ,location, comName, comPrice);
            }
        }
    }

}