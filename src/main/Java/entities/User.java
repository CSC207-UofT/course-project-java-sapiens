package entities;

public abstract class User {

private String name;
private int[] location;
private int number;
private float rating;
private String comment;
private String uname;
private String password;

User(String n, int[] l, int num, String user, String pass){
    this.name = n;
    this.location = l;
    this.number = num;
    this.uname = user;
    this.password = pass;
}

abstract String getName();

abstract String getUname();

abstract String getPassword();

abstract int getNumber();

abstract int[] getLocation();
}
