package use_cases;
import entities.Customer;
import entities.DeliveryMan;
import entities.User;

public abstract class UserManager extends DBManager<String, User> {

    String userType;

    public UserManager(String userType){
        this.userType = userType;
    }

    /**
     * Takes in a password entered and uses SHA256 to hash it
     * when storing in Firebase.
     *
     * @param password The password of User.
     * @return hashed password
     */
    public String createHash(String password){
        return null;
    }

    /**
     * A Factory to create the appropriate UserManager
     *
     * @param userType Type of User being created
     * @return Required user manager
     */
    public static UserManager getUserManager(String userType){

        if(userType.equalsIgnoreCase("CUSTOMER")){
            return new CustomerManager(userType);
        } else if(userType.equalsIgnoreCase("DELIVERYMAN")){
            return new DeliveryManManager(userType);
        }

        return null;
    }

    /**
     * Creates a user as per the user type found
     *
     * All parameters are the arguments for creating DeliveryMan and Customer.
     * Any extraneous values entered not used by customer are ignored if userType
     * is customer.
     *
     * @return User created.
     */
    public User createUser(String n, int[] l, int num, String user, String pass, int sin, String transport, float rate){
        if(userType.equalsIgnoreCase("DELIVERYMAN")){
            return new DeliveryMan(n, l, num, user, pass, sin, transport, rate);
        }
        else{
            return new Customer(n, l, num, user, pass);
        }
    }

}
