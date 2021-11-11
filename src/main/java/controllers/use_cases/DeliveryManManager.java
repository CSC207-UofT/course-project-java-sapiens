package controllers.use_cases;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import entities.DeliveryMan;
import entities.User;
import controllers.use_cases.Database.OnDataReadListener;

import java.util.HashMap;
import java.util.Map;

public class DeliveryManManager extends UserManager{

    final String REF_PATH = "DeliveryMan";

    public DeliveryManManager(String userType) {
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
    public void authenticate(String uname, String password, OnDataReadListener onDataReadListener) {

    }

    /**
     * All Manager classes in controllers.use_cases have some transactions to save.
     *
     * @param obj The username with which the database can be queried
     * @param val The corresponding delivery man
     */
    @Override
    public void save(String obj, User val) {
        Map<String, DeliveryMan> toSave = new HashMap<>();
        toSave.put(obj, (DeliveryMan) val);

        ref.setValueAsync(toSave);
    }

    /**
     * All Manager classes query the database for a specific object type that it is managing.
     *
     * @param obj The username with which the database can be queried.
     */
    @Override
    public void get(String obj, final OnDataReadListener onDataReadListener) {

        ref.child(obj).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                onDataReadListener.setSavedObject(snapshot.getValue(DeliveryMan.class));
                onDataReadListener.onSuccess();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                onDataReadListener.onFailure();
            }
        });

    }
}
