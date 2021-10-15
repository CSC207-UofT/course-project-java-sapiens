package ui;

import java.util.Scanner;

/**
 * This file contains the UI class that is in charge of collecting user
 * inputs and presenting program outputs.
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
