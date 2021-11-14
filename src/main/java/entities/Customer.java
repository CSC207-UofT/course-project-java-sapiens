package entities;

/**
 * An subclass of User that represents a customer.
 * Contains relative information about a commodity.
 */
public class Customer extends User{

    public Customer(String name, int[] location, String phoneNumber, String userName, String password){
        super(name, location, phoneNumber, userName, password);
    }

    public Customer(){
        super();
    }

//    public Customer(String name, int[] l, String num, String user, String pass, float rating) {
//        super(name, l, num, user, pass, rating);
//    }
}
