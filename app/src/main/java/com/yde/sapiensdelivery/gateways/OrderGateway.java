package com.yde.sapiensdelivery.gateways;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.gateways.database.DBGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderGateway extends DBGateway<String, Order> {

    final String REF_PATH = "Order";

    public OrderGateway(){
        ref = database.getReference(REF_PATH);
    }

    /**
     * Another getter function, although here,
     * @param obj Name of delivery man
     * @param onDataReadListener Define onSuccess and onFailure actions
     */
    public void getByDeliveryman(String obj, OnDataReadListener onDataReadListener) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    if(childSnapshot.hasChild(obj)){
                        ArrayList<Object> doubleData =  new ArrayList<>();
                        doubleData.add(snapshot.getValue(Order.class));
                        doubleData.add(childSnapshot.getKey());

                        onDataReadListener.setSavedObject(doubleData);
                        onDataReadListener.onSuccess();
                        return;
                    }
                }

                onDataReadListener.onFailure();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onDataReadListener.onFailure();
            }
        });
    }

    @Override
    public void save(String obj, Order val) {
        Map<String, Object> toSave = new HashMap<>();
        toSave.put(obj, val);

        ref.updateChildren(toSave);
    }

    @Override
    public void get(String obj, OnDataReadListener onDataReadListener) {
        ref.child(obj).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onDataReadListener.setSavedObject(snapshot.getValue(Order.class));
                onDataReadListener.onSuccess();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onDataReadListener.onFailure();
            }
        });
    }
}
