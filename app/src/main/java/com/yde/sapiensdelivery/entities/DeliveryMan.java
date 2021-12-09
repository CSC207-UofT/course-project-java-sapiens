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

    // A set of getters and setters.

    public DeliveryMan(){
        super();
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
