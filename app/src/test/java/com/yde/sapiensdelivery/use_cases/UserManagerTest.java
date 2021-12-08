package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.User;
import com.yde.sapiensdelivery.use_cases.UserManager;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class UserManagerTest {
    String userType;

    @Test
    public void DeliveryManTest() {
        userType = "deliveryman";

        User d1 = UserManager.createUser(userType, "Samuel P", "location1", "648", "Samuel", "password", 1234, "moto-bike");

        DeliveryMan deliveryMan1 = new DeliveryMan("Samuel P", "location1", "648", "Samuel", "password", 1234, "moto-bike");

        assertEquals(deliveryMan1.getName(), d1.getName());
    }

    @Test
    public void CustomerTest() {
        userType = "customer";

        User c1 = UserManager.createUser(userType, "Patrick", "location1", "647", "pz", "password1", 1234, "moto-bike");

        Customer customer1 = new Customer("Patrick", "location1", "647", "Pat", "password1");

        assertEquals(customer1.getPassword(), c1.getName());
    }
}
