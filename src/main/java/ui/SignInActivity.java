package ui;

import controllers.use_cases.Database.OnDataReadListener;
import controllers.use_cases.UserManager;

public class SignInActivity implements Activity{

    @Override
    public void display(){

        UserManager um;

        sio.sendOutput("Already Registered [Y/N]?");
        boolean registered = sio.getInput().equalsIgnoreCase("Y");

        sio.sendOutput("Enter 'c' if customer, 'd' if delivery-man");
        String usertype = sio.getInput();

        if(registered){
            sio.sendOutput("Enter Username ");
            String username = sio.getInput();

            sio.sendOutput("Enter Password");
            String password = sio.getInput();

            Activity userActivity;

            if(usertype.equals("c")){
               um = UserManager.getUserManager("CUSTOMER");
               userActivity = new CustomerActivity();
            }
            else{
                um = UserManager.getUserManager("DELIVERYMAN");
                userActivity = new DeliveryManActivity();
            }

            um.authenticate(username, password, new OnDataReadListener() {
                @Override
                public void onSuccess(){
                    sio.sendOutput("Successful!");
                    sio.intent(userActivity, new Object[] {getSavedObject(), null});
                }

                @Override
                public void onFailure() {
                    sio.sendOutput("Wrong username/password");
                    display();
                }
            });
        }
        else{
            sio.intent(new RegistrationActivity(), usertype);
        }
    }

    @Override
    public void getData(Object transferredData) {

    }
}
