package entities;

import java.util.ArrayList;

/**
 * An abstract class that represents a user,
 * extended by Customer and DeliveryMan.
 */
public abstract class User {

    private String name;
    private ArrayList<Integer> location;
    private String number;
    private float rating;
    private String comment;
    private String uname;
    private String password;

    public User(String n, int[] l, String num, String user, String pass){
        location = new ArrayList<>();
        this.name = n;
        this.location.add(l[0]);
        this.location.add(l[1]);
        this.number = num;
        this.uname = user;
        this.password = pass;
    }

    public User(){
        name = "";
        location = new ArrayList<>();
        number = "";
        rating = 0;
        comment = "";
        uname = "";
        password= "";
    }

    public User(String name, int[] location, String phoneNum, String user, String pass, float rating){
        this(name, location, phoneNum, user, pass);
        this.rating = rating;
    }

    // A set of getters and setters.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Integer> location) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
