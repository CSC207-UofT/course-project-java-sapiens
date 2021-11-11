package controllers.use_cases.Database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.util.List;

public abstract class DBManager<K, V> {

    public FirebaseDatabase database;
    public DatabaseReference ref;

    public DBManager(){
        try {
            FileInputStream serviceAccount =  new FileInputStream("/home/vic/Sapiens/src/main/resources/java-sapiens-10b7745de594.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://java-sapiens-default-rtdb.firebaseio.com/")
                    .build();

            List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
            if(firebaseApps == null || firebaseApps.isEmpty()){
                FirebaseApp.initializeApp(options);
            }

            database = FirebaseDatabase.getInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * All Manager classes in controllers.use_cases have some transactions to save.
     * @param obj The 'key' with which the database can be queried
     * @param val The corresponding object
     */
    abstract public void save(K obj, V val);

    /**
     * All Manager classes query the database for a specific object type that it is managing.
     * @param obj The 'key' with which the database can be queried.
     */
    abstract public void get(K obj, final OnDataReadListener onDataReadListener);

}
