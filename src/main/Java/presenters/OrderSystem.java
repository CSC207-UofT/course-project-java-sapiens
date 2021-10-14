package presenters;

import java.util.Scanner;

public class OrderSystem {

    private static final String commandList = "1. place order: start to place an order \n" +
            "2. quit: quit the program \n" +
            "3. user info: gets the current username and the type of the current account\n";

    public OrderSystem() {
    }

//    public String commandParser(String command){
//        if (command.equals("quit")){
//            return "quit";
//        } else if (command.equals("help")){
//            return commandList;
//        } else if (command.equals("place order")){
////                TODO
//        }
//    }

    public void run(SystemInOut inOut) {

        // Login:
        //login(inOut);

        // After login:
        while (true) {
            System.out.println("$ Please type in command (Type in \"help\" to get help) $");
            String command = inOut.getInput();
            if (command.equals("quit")) {
                return;
            } else if (command.equals("help")) {
                System.out.println(commandList);
            } else if (command.equals("user info")) {
                System.out.println();//TODO: use the manager to get the user info
            } else if (command.equals("place order")) {
                //TODO
            }
        }
    }

    private void login(SystemInOut inOut) {
        // Login TODO: check the correctness of user info
        while(true){
            System.out.println("Please type in your username:");
            String username = inOut.getInput();
            System.out.println("Please type in your password:");
            String password = inOut.getInput();
        }
    }
}