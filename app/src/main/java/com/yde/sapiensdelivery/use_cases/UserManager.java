package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.ArrayList;

public abstract class UserManager{
    String userType;

    /**
     * Creates a user as per the user type found
     *
     * All parameters are the arguments for creating DeliveryMan and Customer.
     * Any extraneous values entered not used by customer are ignored if userType
     * is customer.
     *
     * @return User created.
     */
    public User createUser(String n, int[] l, String num, String user, String pass, long sin, String transport, float rate){
        if(userType.equalsIgnoreCase("DELIVERYMAN")){
            return new DeliveryMan(n, l, num, user, pass, sin, transport, rate);
        }
        else{
            return new Customer(n, l, num, user, pass);
        }
    }
}
