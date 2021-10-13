package Java.entities;

public class DelieveryMan extends User{

    private final int sin;

    private final String transport;

    private final float rate;

    DelieveryMan(String n, int[] l, int num, String user, String pass, int id, String transport, float rate) {
        super(n, l, num, user, pass);
        this.sin = id;
        this.transport = transport;
        this.rate = rate;
    }

    @Override
    String getName() {
        return null;
    }

    @Override
    String getUname() {
        return null;
    }

    @Override
    String getPassword() {
        return null;
    }

    @Override
    int getNumber() {
        return 0;
    }

    @Override
    int[] getLocation() {
        return new int[0];
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
