package com.yde.sapiensdelivery.gateways;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.gateways.database.DBGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingListGateway extends DBGateway<String, ArrayList<ShoppingList>> {

    final String REF_PATH = "ShoppingLists";

    public ShoppingListGateway(){
        ref = database.getReference(REF_PATH);
    }

    public void delete(String customerStr){
        ref.child(customerStr).removeValue();
    }

    @Override
    public void save(String obj, ArrayList<ShoppingList> val) {
        Map<String, Object> toSave = new HashMap<>();
        toSave.put(obj, val);

        ref.updateChildren(toSave);
    }

    public void getAll(HashMap<String, ArrayList<ShoppingList>> customerToSL, OnDataReadListener onDataReadListener){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot childSnapshot : snapshot.getChildren()){
                    GenericTypeIndicator<ArrayList<ShoppingList>> t = new GenericTypeIndicator<ArrayList<ShoppingList>>() {};
                    customerToSL.put(childSnapshot.getKey(), childSnapshot.getValue(t));
                }
                onDataReadListener.onSuccess();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onDataReadListener.onFailure();
            }
        });
    }

    @Override
    public void get(String obj, OnDataReadListener onDataReadListener) {

    }
}
