package ui;

import controllers.use_cases.CustomerManager;
import controllers.use_cases.UserManager;
import entities.Customer;
import entities.Order;

import java.lang.reflect.Array;

public class CustomerActivity implements Activity{
    // This variable contains the current user.
    private Customer cus;
    private CustomerManager cm;
    private Order order;

    @Override
    public void display() {

        cm = (CustomerManager) UserManager.getUserManager("CUSTOMER");
        boolean activityShift = false;

        while (!activityShift) {
            sio.sendOutput("$ Please type in command (Type in \"help\" to get help) $");
            String command = sio.getInput();

            // This variable contains all the available command.
            String commandList = "Please type in the corresponding number of the following command\n" +
                    "1. place order: go to order placement page \n" +
                    "2. order status: go to order status page \n" +
                    "3. user profile: go to user profile page \n" +
                    "4. sign out: sign out from the current account \n" +
                    "5. quit: quit the program \n";

            switch (command) {
                case "1": // The customer places the order.
                    if (this.order == null){
                        this.save();
                        sio.sendOutput("Starting order placement");
                        sio.intent(new ShoppingListActivity(), this.cus);
                        activityShift = true;
                    }else {
                        sio.sendOutput("You already have an Order in progress, " +
                                "cannot place order now!");
                    }
                    break;


                case "2": // If the user wants to check order status, switch to the order status activity.
                    if (this.order == null){
                        sio.sendOutput("You have not placed an order yet!");
                    }else {
                        this.save();
                        sio.sendOutput("Status of the order:");
                        sio.intent(new OrderStatusActivity(), new Object[]{this.cus, this.order});
                    }
                    break;


                case "3": // If the user wants user info, prompt the info of current account.
                    sio.sendOutput("Username: " + this.cus.getName() +
                            "  Account type: Customer");
                    break;

                case "4": // If the user wants to sign out, sign out the current account and
                    // require login again
                    this.save();
                    sio.sendOutput("Sign out successful, please login");
                    sio.intent(new SignInActivity(), null);
                    activityShift = true;
                    break;

                case "5": // If the user wants to quit, quit the program.
                    this.save();
                    System.exit(0);
                    return;

                case "help": // If the user enters help, give them help.
                    sio.sendOutput(commandList);
                    break;
            }
        }
    }

    // A private method that saves the data to database using customer manager.
    private void save(){
        this.cm.save(this.cus.getName(), this.cus);
    }

    @Override
    public void getData(Object transferredData) {
        this.cus = (Customer) Array.get(transferredData, 0);
        this.order = (Order) Array.get(transferredData, 1);
    }

}
