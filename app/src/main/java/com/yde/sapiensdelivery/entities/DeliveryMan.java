package com.yde.sapiensdelivery.entities;

/**
 * An subclass of User that represents a customer.
 * Contains relative information about a deliveryMan.
 */
public class DeliveryMan extends User{

    private long sin;
    private String transport;

    public DeliveryMan(String name, String location, String phoneNumber, String userName, String password, long sin, String transport) {
        super(name, location, phoneNumber, userName, password);
        this.transport = transport;
        this.sin = sin;
    }

//    public DeliveryMan(String n, int[] l, String num, String user, String pass, long sin, String transport, float rate, float rating) {
//        super(n, l, num, user, pass, rating);
//        this.transport = transport;
//        this.rate = rate;
//        this.sin = sin;
//    }

    // A set of getters and setters.

    public DeliveryMan(){
        super();
    }

    public DeliveryMan(String samuel, int[] ints, String phoneNumber, String samuel1, String password, int sin, String transport, float rate) {
    }

    public void setSin(long sin) {
        this.sin = sin;
    }

    public String getTransport() {
        return transport;
    }

    public long getSin() {
        return sin;
    }
}
