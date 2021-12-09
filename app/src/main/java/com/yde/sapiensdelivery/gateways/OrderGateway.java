package com.yde.sapiensdelivery.gateways;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.gateways.database.DBGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.use_cases.OrderManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OrderGateway extends DBGateway<String, Order> {

    final String REF_PATH = "Order";

    public OrderGateway(){
        ref = database.getReference(REF_PATH);
    }

    /**
     * Another getter function, although here,
     * @param delStr Name of delivery man
     * @param onDataReadListener Define onSuccess and onFailure actions
     */
    public void getByDeliveryman(String delStr, OnDataReadListener onDataReadListener) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    if((Objects.requireNonNull(childSnapshot.child("deliveryMan").child("uname").getValue(String.class)))
                            .equals(delStr)){
                        ArrayList<Object> doubleData =  new ArrayList<>();
                        OrderManager orderManager = new OrderManager(childSnapshot.getValue(Order.class));
                        if(orderManager.getStatus() == Order.OrderStatus.COMP){
                            continue;
                        }

                        doubleData.add(childSnapshot.getValue(Order.class));
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

    public void delete(String customerStr){
        ref.child(customerStr).removeValue();
    }

    @Override
    public void save(String obj, Order val) {
        Map<String, Object> toSave = new HashMap<>();
        toSave.put(obj, val);

        ref.updateChildren(toSave);
    }

    @Override
    public void get(String obj, OnDataReadListener onDataReadListener) {
        ref.child(obj).addListenerForSingleValueEvent(new ValueEventListener() {
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

    public void getPersist(String obj, OnDataReadListener onDataReadListener) {
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
