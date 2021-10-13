package entities;

public class Customer extends User{

    Customer(String n, int[] l, int num, String user, String pass){
        super(n, l, num, user, pass);

    }

    @Override
    public String getName() {
        return null;
    }


    @Override
    public String getUname() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public int[] getLocation() {
        return new int[0];
    }

}
