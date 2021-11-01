package use_cases;

import entities.DeliveryMan;

public class DeliveryManManager extends DBManager<String, DeliveryMan>{

    /**
     * All Manager classes in use_cases have some transactions to save.
     *
     * @param obj The username with which the database can be queried
     * @param val The corresponding delivery man
     */
    @Override
    void save(String obj, DeliveryMan val) {

    }

    /**
     * All Manager classes query the database for a specific object type that it is managing.
     *
     * @param obj The username with which the database can be queried.
     * @return The corresponding delivery man
     */
    @Override
    DeliveryMan get(String obj) {
        return null;
    }
}
