package entities;

public class DeliveryMan extends User{

    private int sin;
    private String transport;
    private float rate;

    public void setSin(int sin) {
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

    public DeliveryMan(String n, int[] l, int num, String user, String pass, int sin, String transport, float rate) {
        super(n, l, num, user, pass);
        this.transport = transport;
        this.rate = rate;
        this.sin = sin;
    }

    public DeliveryMan(String n, int[] l, int num, String user, String pass, int sin, String transport, float rate, float rating) {
        super(n, l, num, user, pass, rating);
        this.transport = transport;
        this.rate = rate;
        this.sin = sin;
    }

    public int getSin() {
        return sin;
    }

    public float getRate() {
        return rate;
    }
}
