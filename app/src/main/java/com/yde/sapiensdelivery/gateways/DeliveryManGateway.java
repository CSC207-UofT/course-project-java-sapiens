package com.yde.sapiensdelivery.gateways;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.User;

import java.util.HashMap;
import java.util.Map;

public class DeliveryManGateway extends UserGateway {

    final String REF_PATH = "DeliveryMan";

    public DeliveryManGateway(String userType) {
        super(userType);
        ref = database.getReference(REF_PATH);
    }

    /**
     * Checks if the username is already registered in the server.
     *
     * @param user The username to check
     * @param onDataReadListener Describes what to do on success/failure
     */
    @Override
    protected void usernameRepetitionChecker(String user, OnDataReadListener onDataReadListener) {

        ref.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DeliveryMan deliveryMan = snapshot.getValue(DeliveryMan.class);

                if(deliveryMan == null){
                    onDataReadListener.onSuccess();
                    onDataReadListener.ERROR_CODES.add(0); // Username Error
                }
                else{
                    onDataReadListener.onFailure();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onDataReadListener.onFailure();
                onDataReadListener.ERROR_CODES.add(1); // Database Error
            }
        });
    }

    /**
     * Checks if the value for a certain field is a valid entry.
     * Concerned with fields: Phone Number, SIN and License Plate (transport)
     *
     * @param fieldToValue Hashmap containing the above fields.
     * @return if all input fields are legal.
     */
    @Override
    protected boolean isRegexInvalid(HashMap<String, String> fieldToValue) {
        String phoneNum = fieldToValue.get("PHONE NUMBER");
        if (phoneNum == null) {
            return false;
        }
        else if (!phoneNum.matches("[0-9]{10}")) {
            return false;
        }

        String sinNum = fieldToValue.get("SIN");
        if (sinNum == null) {
            return false;
        }
        else if (!sinNum.matches("[0-9]{9}")) {
            return false;
        }

        String plateVal = fieldToValue.get("TRANSPORT");
        // Canadian plates
        if (plateVal == null) {
            return false;
        }
        // Canadian license plates can be 2-8 characters in capital letters and numbers
        else return plateVal.matches("[A-Z|0-9]{2,8}");
    }

    /**
     * Returns the user if existing in database
     *
     * @param uname              Username of user
     * @param password           Password of user
     * @param onDataReadListener  Action to be performed on authentication
     */
    @Override
    public void authenticate(String uname, String password, OnDataReadListener onDataReadListener) {

        ref.child(uname).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DeliveryMan deliveryMan = snapshot.getValue(DeliveryMan.class);

                if(deliveryMan == null){
                    onDataReadListener.onFailure();
                }
                else if(deliveryMan.getPassword().equals(createHash(password))){
                    onDataReadListener.setSavedObject(snapshot.getValue(DeliveryMan.class));
                    onDataReadListener.onSuccess();
                }
                else{
                    onDataReadListener.onFailure();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onDataReadListener.onFailure();
            }
        });

    }

    /**
     * All Manager classes in controllers.use_cases have some transactions to save.
     *
     * @param obj The username with which the database can be queried
     * @param val The corresponding delivery man
     */
    @Override
    public void save(String obj, User val) {
        Map<String, Object> toSave = new HashMap<>();
        toSave.put(obj, val);

        ref.updateChildren(toSave);
    }

    /**
     * All Manager classes query the database for a specific object type that it is managing.
     *
     * @param obj The username with which the database can be queried.
     */
    @Override
    public void get(String obj, final OnDataReadListener onDataReadListener) {

        ref.child(obj).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onDataReadListener.setSavedObject(snapshot.getValue(DeliveryMan.class));
                onDataReadListener.onSuccess();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onDataReadListener.onFailure();
            }
        });

    }
}
