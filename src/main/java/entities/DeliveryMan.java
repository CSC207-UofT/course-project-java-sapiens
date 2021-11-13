package entities;

public class DeliveryMan extends User{

    private long sin;
    private String transport;
    private float rate;

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

    public DeliveryMan(String n, int[] l, String num, String user, String pass, long sin, String transport, float rate) {
        super(n, l, num, user, pass);
        this.transport = transport;
        this.rate = rate;
        this.sin = sin;
    }

    public DeliveryMan(String n, int[] l, String num, String user, String pass, long sin, String transport, float rate, float rating) {
        super(n, l, num, user, pass, rating);
        this.transport = transport;
        this.rate = rate;
        this.sin = sin;
    }

    public long getSin() {
        return sin;
    }

    public float getRate() {
        return rate;
    }
}
