package com.yde.sapiensdelivery.gateways;
import com.yde.sapiensdelivery.gateways.database.DBController;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.entities.User;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;

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
    public void registration(String n, int[] l, String num, String user, String pass, String sin, String transport, float rate,
                             final OnDataReadListener onDataReadListener){

        HashMap<String, String> fieldToValue = new HashMap<>();
        fieldToValue.put("PHONE NUMBER", num);

        if(userType.equals("DELIVERYMAN")){
            fieldToValue.put("SIN", sin);
            fieldToValue.put("TRANSPORT", transport);
        }

        if(IsRegexInvalid(fieldToValue)){ // Template of regex checks.
            onDataReadListener.onFailure();
        }

        usernameRepetitionChecker(user, onDataReadListener); // Template of username repeat checks.
    }

    protected abstract void usernameRepetitionChecker(String user, OnDataReadListener onDataReadListener);

    protected boolean IsRegexInvalid(HashMap<String, String> fieldToValue){
        return false;
    }

    /**
     * Returns the user if existing in database
     *
     * @param uname Username of user
     * @param password Password of user
     */
    public abstract void authenticate(String uname, String password, final OnDataReadListener onDataReadListener);

}
