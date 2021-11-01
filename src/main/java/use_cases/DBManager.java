package use_cases;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;

public abstract class DBManager<K, V> {

    DatabaseReference ref;

    public DBManager(){
        try {
            FileInputStream serviceAccount =  new FileInputStream("../../resources/java-sapiens-10b7745de594.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://console.firebase.google.com/project/java-sapiens/database/java-sapiens-default-rtdb/data/~2F")
                    .build();
            FirebaseApp.initializeApp(options);

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * All Manager classes in use_cases have some transactions to save.
     * @param obj The 'key' with which the database can be queried
     * @param val The corresponding object
     */
    abstract void save(K obj, V val);

    /**
     * All Manager classes query the database for a specific object type that it is managing.
     * @param obj The 'key' with which the database can be queried.
     * @return The corresponding object
     */
    abstract V get(K obj);

}
