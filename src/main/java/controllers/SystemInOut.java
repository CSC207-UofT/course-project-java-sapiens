package controllers;

import ui.Activity;

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

    /**
     * Replicate intent in Android
     *
     * @param activity Activity to which transfer of control is going to
     * @param object The data to be transferred to this activity
     */
    public void intent(Activity activity, Object object){
        activity.getData(object);
        activity.display();
    }
}
