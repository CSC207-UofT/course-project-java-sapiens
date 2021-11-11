package controllers.use_cases;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import entities.Customer;
import entities.User;
import controllers.use_cases.Database.OnDataReadListener;

import java.util.HashMap;
import java.util.Map;

public class CustomerManager extends UserManager{

    final String REF_PATH = "Customer";

    public CustomerManager(String userType) {
        super(userType);
        ref = database.getReference(REF_PATH);
    }

    @Override
    protected boolean discrepancyCheck(User currUser) {
        return false;
    }

    /**
     * Returns the user if existing in database
     *
     * @param uname              Username of user
     * @param password           Password of user
     * @param onDataReadListener  Action to be performed on authentication
     */
    @Override
    public void authenticate(String uname, String password, final OnDataReadListener onDataReadListener) {
        ref.child(uname).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Customer customer = snapshot.getValue(Customer.class);
                if(customer.getPassword().equals(createHash(password))){
                    onDataReadListener.setSavedObject(snapshot.getValue(Customer.class));
                    onDataReadListener.onSuccess();
                }
                else{
                    onDataReadListener.onFailure();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                onDataReadListener.onFailure();
            }
        });
    }

    /**
     * All Manager classes in controllers.use_cases have some transactions to save.
     *
     * @param obj The username with which the database can be queried
     * @param val The corresponding Customer
     */
    @Override
    public void save(String obj, User val) {
        Map<String, Customer> toSave = new HashMap<>();
        toSave.put(obj, (Customer) val);

        ref.setValueAsync(toSave);
    }

    /**
     * All Manager classes query the database for a specific object type that it is managing.
     *
     * @param obj The username with which the database can be queried.
     */
    @Override
    public void get(String obj, final OnDataReadListener onDataReadListener){
        ref.child(obj).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                onDataReadListener.setSavedObject(snapshot.getValue(Customer.class));
                onDataReadListener.onSuccess();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                onDataReadListener.onFailure();
            }
        });
    }

}
