package entities;

/**
 * An subclass of User that represents a customer.
 * Contains relative information about a deliveryMan.
 */
public class DeliveryMan extends User{

    private long sin;
    private String transport;
    private double rate;

    public DeliveryMan(String name, int[] location, String phoneNumber, String userName, String password, long sin, String transport, double rate) {
        super(name, location, phoneNumber, userName, password);
        this.transport = transport;
        this.rate = rate;
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

    public void setSin(long sin) {
        this.sin = sin;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public long getSin() {
        return sin;
    }

    public double getRate() {
        return rate;
    }
}
