package com.yde.sapiensdelivery.entities;

import java.io.Serializable;

/**
 * An abstract class that represents a user,
 * extended by Customer and DeliveryMan.
 */
public abstract class User implements Serializable {

    private String name;
    private String location;
    private String number;
    private float rating;
    private int noOfRatings;
    private String uname;
    private String password;

    public User(String n, String l, String num, String user, String pass){
        this.name = n;
        this.location = l;
        this.number = num;
        this.uname = user;
        this.password = pass;
    }

    public User(){
        name = "";
        location = "";
        number = "";
        rating = 0.0f;
        noOfRatings = 0;
        uname = "";
        password= "";
    }

    public User(String name, String location, String phoneNum, String user, String pass, float rating, int noOfRatings){
        this(name, location, phoneNum, user, pass);
        this.rating = rating;
        this.noOfRatings = noOfRatings;
    }

    // A set of getters and setters.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNoOfRatings() {
        return noOfRatings;
    }

    public void setNoOfRatings(int noOfRatings) {
        this.noOfRatings = noOfRatings;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
