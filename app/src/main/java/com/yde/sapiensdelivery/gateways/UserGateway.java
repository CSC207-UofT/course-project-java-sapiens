package com.yde.sapiensdelivery.gateways;

import com.yde.sapiensdelivery.gateways.database.DBGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.entities.User;

import com.yde.sapiensdelivery.regex_checkers.InfoValidityChecker;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class UserGateway extends DBGateway<String, User> {

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
        return new String(Hex.encodeHex(DigestUtils.sha256(password)));
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
    public void registration(String num, String user, String sin, String transport, final OnDataReadListener onDataReadListener){

        HashMap<String, String> fieldToValue = new HashMap<>();
        fieldToValue.put("PHONE NUMBER", num);

        if(userType.equals("DELIVERYMAN")){
            fieldToValue.put("SIN", sin);
            fieldToValue.put("TRANSPORT", transport);
        }

        if(isRegexInvalid(fieldToValue, onDataReadListener.ERROR_CODES)){ // Template of regex checks.
            onDataReadListener.onFailure();
            return;
        }

        usernameRepetitionChecker(user, onDataReadListener); // Template of username repeat checks.
    }

    /**
     * Checks if the username is already registered in the server.
     *
     * @param user The username to check
     * @param onDataReadListener Describes what to do on success/failure
     */
    protected abstract void usernameRepetitionChecker(String user, OnDataReadListener onDataReadListener);

    /**
     * Checks if the value for a certain field is a valid entry.
     * The Base UserGateway (used by CustomerGateway) is only concerned with the field Phone Number.
     *
     * @param fieldToValue Hashmap of one KV pair: PHONE NUMBER -> input
     * @return if the phone number is legal.
     */
    protected boolean isRegexInvalid(HashMap<String, String> fieldToValue, ArrayList<Integer> errorCodes){
        String phoneNum = fieldToValue.get("PHONE NUMBER");
        boolean isPhoneValid = InfoValidityChecker.isPhoneNumValid(phoneNum);

        if(!isPhoneValid){
            errorCodes.add(2); // Error Code for phone num
        }
        return !isPhoneValid;
    }

    /**
     * Returns the user if existing in database
     *
     * @param uname Username of user
     * @param password Password of user
     */
    public abstract void authenticate(String uname, String password, final OnDataReadListener onDataReadListener);

}
