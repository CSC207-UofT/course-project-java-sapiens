import ui.controllers.OrderSystem;
import ui.SystemInOut;

/**
 * This is main class of our program, please run this file to start the program
 * in the console.
 *
 * Sample Usage:
 * 1. IMPORTANT: to log in, please use the username "TestAccount" with password "CSC207".
 * It is the only existing account hardcoded for phase 0
 * 2. After log in, user can perform various operations by typing commands in
 * the console. Please type in "help" for a list of command available.
 * 3. To quit the program, please type in "quit".
 */
public class Main {
    public static void main(String[] args) {
        SystemInOut sio = new SystemInOut();
        OrderSystem os = new OrderSystem();
        os.run(sio);
    }
}
