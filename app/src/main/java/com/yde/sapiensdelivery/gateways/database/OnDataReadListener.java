package com.yde.sapiensdelivery.gateways.database;

import java.util.ArrayList;

public abstract class OnDataReadListener {
    Object savedObject = new Object();
    final public ArrayList<Integer> ERROR_CODES = new ArrayList<>();

    public Object getSavedObject() {
        return savedObject;
    }

    public void setSavedObject(Object savedObject) {
        this.savedObject = savedObject;
    }

    abstract public void onSuccess();
    abstract public void onFailure();
}
