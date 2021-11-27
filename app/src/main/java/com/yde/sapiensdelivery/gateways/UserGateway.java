package com.yde.sapiensdelivery.gateways;
import com.yde.sapiensdelivery.gateways.database.DBController;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.User;
import com.yde.sapiensdelivery.use_cases.UserManager;

import org.apache.commons.codec.digest.DigestUtils;

public abstract class UserGateway extends DBController<String, User> {

    String userType;

    public UserGateway(String userType){
        this.userType = userType;
    }

    /**
     * Takes in a password entered and uses SHA256 to hash it
     * when storing in Firebase.
     *
     * @param password The password of User.
     * @return hashed password
     */
    public String createHash(String password){
        return DigestUtils.sha256Hex(password);
    }

    /**
     * A Factory to create the appropriate UserManager
     *
     * @param userType Type of User being created
     * @return Required user manager
     */
    public static UserGateway getUserGateway(String userType){

        if(userType.equalsIgnoreCase("CUSTOMER")){
            return new CustomerGateway(userType);
        } else {
            return new DeliveryManGateway(userType);
        }
    }

    /**
     * Register the user into database if possible
     */
    public User registration(String n, int[] l, String num, String user, String pass, long sin, String transport, float rate){

        User currUser = UserManager.createUser(userType, n, l, num, user, pass, sin, transport, rate);
        return discrepancyCheck(currUser) ? currUser : null; // Template of creating user and discrepancy check
    }

    protected abstract boolean discrepancyCheck(User currUser);

    /**
     * Returns the user if existing in database
     *
     * @param uname Username of user
     * @param password Password of user
     */
    public abstract void authenticate(String uname, String password, final OnDataReadListener onDataReadListener);

}
