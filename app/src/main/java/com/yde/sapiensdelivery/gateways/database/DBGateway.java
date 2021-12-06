package com.yde.sapiensdelivery.gateways.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class DBGateway<K, V> {

    public FirebaseDatabase database;
    public DatabaseReference ref;

    public DBGateway(){
        try {
            database = FirebaseDatabase.getInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * All Manager classes in controllers.use_cases have some transactions to save.
     * @param obj The 'key' with which the database can be queried
     * @param val The corresponding object
     */
    abstract public void save(K obj, V val);

    /**
     * All Manager classes query the database for a specific object type that it is managing.
     * @param obj The 'key' with which the database can be queried.
     */
    abstract public void get(K obj, final OnDataReadListener onDataReadListener);

}
