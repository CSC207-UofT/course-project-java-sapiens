package ui;

import controllers.use_cases.UserManager;

import java.util.ArrayList;

public class SignInActivity implements Activity{

    @Override
    public void display() {
        sio.sendOutput("Already Registered [Y/N]?");

        if(sio.getInput().equalsIgnoreCase("Y")){
            sio.sendOutput("Enter Username ");
            String username = sio.getInput();

            sio.sendOutput("Enter Password");
            String password = sio.getInput();

            sio.sendOutput("Enter 'c' if customer, 'd' if delivery-man");
            String usertype = sio.getInput();

            UserManager um;
            Activity userActivity;

            if(usertype.equals("c")){
               um = UserManager.getUserManager("CUSTOMER");
               userActivity = new CustomerActivity();
            }
            else{
                um = UserManager.getUserManager("DELIVERYMAN");
                userActivity = new DeliveryManActivity();
            }

            ArrayList<Object> toSave = new ArrayList<>();
            toSave.add(um.authenticate(username, um.createHash(password)));

            sio.intent(userActivity, toSave);
        }
        else{
            sio.intent(new RegistrationActivity(), null);
        }
    }

    @Override
    public void getData(ArrayList<Object> transferredData) {

    }
}
