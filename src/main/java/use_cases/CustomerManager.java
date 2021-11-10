package use_cases;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import entities.Customer;
import entities.User;
import use_cases.Database.OnDataReadListener;

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
     * @param uname    Username of suer
     * @param password Password of user
     * @return The user if authenticated else null
     */
    @Override
    User authenticate(String uname, String password) {
        return null;
    }

    /**
     * All Manager classes in use_cases have some transactions to save.
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
