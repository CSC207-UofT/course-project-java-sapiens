package com.yde.sapiensdelivery.controllers.database;

public abstract class OnDataReadListener {
    Object savedObject = new Object();

    public Object getSavedObject() {
        return savedObject;
    }

    public void setSavedObject(Object savedObject) {
        this.savedObject = savedObject;
    }

    abstract public void onSuccess();
    abstract public void onFailure();
}