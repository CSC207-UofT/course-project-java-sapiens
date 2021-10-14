package presenters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Running this class starts the program in the console.
 * User then can enter corresponding command into the console.
 * This class will keep running until the user type in "quit".
 */
public class SystemInOut {

    Scanner input;

    public SystemInOut(){
        this.input = new Scanner(System.in);
    }

    public String getInput(){
        return this.input.nextLine();
    }
    public void sendOutput(Object s) {
        System.out.println(s);
    }
}
