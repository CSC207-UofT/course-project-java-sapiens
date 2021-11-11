package ui;

import controllers.use_cases.Database.OnDataReadListener;
import controllers.use_cases.UserManager;

import java.util.ArrayList;

public class SignInActivity implements Activity{

    boolean flag;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void display(){

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

            um.authenticate(username, password, new OnDataReadListener() {
                @Override
                public void onSuccess(){
                    ArrayList<Object> savedObjects = new ArrayList<>();
                    savedObjects.add(getSavedObject());
                    flag = true;
                    sio.sendOutput("Successful!");
                    sio.intent(userActivity, savedObjects);
                }

                @Override
                public void onFailure() {
                    flag = true;
                    sio.sendOutput("Wrong username/password");
                }
            });

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
