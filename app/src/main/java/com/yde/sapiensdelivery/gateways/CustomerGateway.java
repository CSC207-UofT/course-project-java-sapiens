package com.yde.sapiensdelivery.gateways;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.User;

import java.util.HashMap;
import java.util.Map;

public class CustomerGateway extends UserGateway {

    final String REF_PATH = "Customer";

    public CustomerGateway(String userType) {
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
                Customer customer = snapshot.getValue(Customer.class);

                if(customer == null){
                    onDataReadListener.onSuccess();
                }
                else{
                    onDataReadListener.ERROR_CODES.add(0); // Username Error
                    onDataReadListener.onFailure();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onDataReadListener.ERROR_CODES.add(1); // Database Error
                onDataReadListener.onFailure();
            }
        });
    }

    /**
     * Returns the user if existing in database
     *
     * @param uname              Username of user
     * @param password           Password of user
     * @param onDataReadListener  Action to be performed on authentication
     */
    @Override
    public void authenticate(String uname, String password, final OnDataReadListener onDataReadListener) {

        ref.child(uname).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Customer customer = snapshot.getValue(Customer.class);

                if(customer == null){
                    onDataReadListener.onFailure();
                }
                else if(customer.getPassword().equals(createHash(password))){
                    onDataReadListener.setSavedObject(snapshot.getValue(Customer.class));
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
     * @param val The corresponding Customer
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
    public void get(String obj, final OnDataReadListener onDataReadListener){
        ref.child(obj).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onDataReadListener.setSavedObject(snapshot.getValue(Customer.class));
                onDataReadListener.onSuccess();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onDataReadListener.onFailure();
            }
        });
    }

}

