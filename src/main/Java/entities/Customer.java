package entities;

public class Customer extends User{

    public Customer(String n, int[] l, int num, String user, String pass){
        super(n, l, num, user, pass);

    }

    @Override
    public String getUname() {
        return super.getUname();
    }
    @Override
    public String getPassword() {
        return super.getPassword();
    }
}
