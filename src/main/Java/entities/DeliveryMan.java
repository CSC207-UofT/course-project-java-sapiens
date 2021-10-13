package entities;

public class DeliveryMan extends User{

    private final int sin;

    private final String transport;

    private final float rate;

    DeliveryMan(String n, int[] l, int num, String user, String pass, int id, String transport, float rate) {
        super(n, l, num, user, pass);
        this.sin = id;
        this.transport = transport;
        this.rate = rate;
    }

    String getTransport(){
        return this.transport;
    }

    int getSin(){
        return this.sin;
    }

    float getRate(){
        return this.rate;
    }
}
