import ui.SignInActivity;
import org.apache.commons.codec.digest.DigestUtils;

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
    public static void main(String[] args) throws InterruptedException {
        SignInActivity signInActivity = new SignInActivity();
        signInActivity.display();

        Thread.sleep(3600000); // Timeout after an hour.
    }
}
