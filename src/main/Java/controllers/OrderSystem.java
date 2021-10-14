package controllers;

import ui.SystemInOut;
import use_cases.ShoppingListManager;
import use_cases.UserManager;

/**
 * This file contains the controller that is in charge of distinguishing the
 * command typed by the user, and call corresponding managers.
 */
public class OrderSystem {

    // This variable represents the current account.
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
        ShoppingListManager slM = new ShoppingListManager();

        // Starts to receive commands
        while (true) {
            System.out.println("$ Please type in command (Type in \"help\" to get help) $");
            String command = inOut.getInput();
            if (command.equals("quit")) {
                return;
            } else if (command.equals("help")) {
                System.out.println(commandList);
            } else if (command.equals("user info")) {
                System.out.println("Username: " + this.currentUser[0] +
                                   "  Account type: " + this.currentUser[1]);
            } else if (command.equals("sign out")) {
                System.out.println("Sign out successful, please login");
                if (login(inOut, userM).equals("quit")) {
                    return;
                }
            } else if (command.equals("place order")) {

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
            System.out.println("Please type in your username:");
            String username = inOut.getInput();
            if (username.equals("quit")){
                return "quit";
            }

            // Get the password, if the user typed in "quit", then return "quit"
            System.out.println("Please type in your password:");
            String password = inOut.getInput();
            if (password.equals("quit")){
                return "quit";
            }

            // Check if the username and password matches to an existing account
            if (um.checkC(username, password)){
                System.out.println("Login Successful, dear " + username + ", welcome back!");
                this.currentUser = new String[]{username, "Customer"};
                return "Success";
            } else {
                System.out.println("Login failed, the password you entered is incorrect or the" +
                        " account does not exist.");
            }
        }
    }


}