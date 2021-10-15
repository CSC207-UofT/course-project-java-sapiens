package use_cases;

public interface DBManager<K, V> {

    /**
     * All Manager classes in use_cases have some transactions to save.
     * @param obj The 'key' with which the database can be queried
     * @param val The corresponding object
     */
    void save(K obj, V val);

    /**
     * All Manager classes query the database for a specific object type that it is managing.
     * @param obj The 'key' with which the database can be queried.
     * @return The corresponding object
     */
    V get(K obj);

}
