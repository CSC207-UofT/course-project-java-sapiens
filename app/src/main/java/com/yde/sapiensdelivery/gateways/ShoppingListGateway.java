package com.yde.sapiensdelivery.gateways;

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

    @Override
    public void save(String obj, ArrayList<ShoppingList> val) {
        Map<String, Object> toSave = new HashMap<>();
        toSave.put(obj, val);

        ref.updateChildren(toSave);
    }

    @Override
    public void get(String obj, OnDataReadListener onDataReadListener) {

    }
}
