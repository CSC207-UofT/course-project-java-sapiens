package entities;

public class Customer extends User{


    Customer(String n, int[] l, int num, String user, String pass){
        super(n, l, num, user, pass);

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

}
