package ui;

import controllers.SystemInOut;
import controllers.use_cases.UserManager;
import entities.User;

public class RegistrationActivity implements Activity{

    String userType;

    @Override
    public void display() {
        sio.sendOutput("Enter name");
        String name = sio.getInput();

        int[] l = new int[2];
        sio.sendOutput("Enter x coordinate of location");
        l[0] = Integer.parseInt(sio.getInput());

        sio.sendOutput("Enter y coordinate of location");
        l[1] = Integer.parseInt(sio.getInput());

        sio.sendOutput("Enter number");
        String number = sio.getInput();

        sio.sendOutput("Create a Username");
        String username = sio.getInput();

        sio.sendOutput("Create a Password");
        String password = sio.getInput();

        long sin = 0;
        float rate = 0f;
        String transport = null;

        if(userType.equals("d")){
            sio.sendOutput("Enter SIN");
            sin = Long.parseLong(sio.getInput());

            sio.sendOutput("Enter transport");
            transport = sio.getInput();

            sio.sendOutput("Enter Rate");
            rate = Float.parseFloat(sio.getInput());
        }

        UserManager um;
        Activity userActivity;

        if(userType.equals("c")){
            um = UserManager.getUserManager("CUSTOMER");
            userActivity = new CustomerActivity();
        }
        else{
            um = UserManager.getUserManager("DELIVERYMAN");
            userActivity = new DeliveryManActivity();
        }

        User user =  um.registration(name, l, number, username, password, sin, transport, rate);

        if(user != null){
            um.save(user.getUname(), user);
            sio.intent(userActivity, user);
        }
        else{
            sio.sendOutput("You cannot register as your username is already used, or information is incorrect");
            sio.sendOutput("Try again!");
            display();
        }

    }

    @Override
    public void getData(Object transferredData) {
        userType = (String) transferredData;
    }
}
