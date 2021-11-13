package ui;

import controllers.use_cases.UserManager;

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

        um.registration(name, l, number, username, password, sin, transport, rate);
    }

    @Override
    public void getData(Object transferredData) {
        userType = (String) transferredData;
    }
}
