package use_cases;

import entities.Customer;

public class CustomerManager extends DBManager<String, Customer> {

    /**
     * All Manager classes in use_cases have some transactions to save.
     *
     * @param obj The username with which the database can be queried
     * @param val The corresponding Customer
     */
    @Override
    void save(String obj, Customer val) {

    }

    /**
     * All Manager classes query the database for a specific object type that it is managing.
     *
     * @param obj The username with which the database can be queried.
     * @return The corresponding customer
     */
    @Override
    Customer get(String obj) {
        return null;
    }
}
