package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;

public class DeliveryManManager {
    private DeliveryMan deliveryMan;

    /**
     * Creates an OrderManager given an already existing Customer
     */
    public DeliveryManManager(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    /**
     * Creates an OrderManager
     */
    public DeliveryManManager() {
    }
}
