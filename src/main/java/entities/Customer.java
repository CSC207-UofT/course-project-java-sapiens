package entities;

public class Customer extends User{

    public Customer(String n, int[] l, String num, String user, String pass){
        super(n, l, num, user, pass);
    }

    public Customer(){
        super();
    }

    public Customer(String n, int[] l, String num, String user, String pass, float rating) {
        super(n, l, num, user, pass, rating);
    }
}
