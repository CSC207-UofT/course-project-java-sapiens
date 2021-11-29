package use_cases_tests;

import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.use_cases.CustomerManager;
import com.yde.sapiensdelivery.use_cases.UserManager;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class UserManagerTest {
    String userType;

    @Test
    public void DeliveryManTest() {
        userType = "deliveryman";

        User d1 = UserManager.createUser(userType, "Samuel P", new int[]{10, 20}, "648", "Samuel", "password", 1234, "moto-bike", (float) 4.5);

        DeliveryMan deliveryMan1 = new DeliveryMan("Samuel P", new int[]{10, 20}, "648", "Samuel", "password", 1234, "moto-bike", (float) 4.5);

        assertEquals(deliveryMan1.getName(), d1.getName());
    }

    @Test
    public void CustomerTest() {
        userType = "customer";

        User c1 = UserManager.createUser(userType, "Patrick", new int[]{10, 20}, "647", "pz", "password1", 1234, "moto-bike", (float) 4.5);
        
        Customer customer1 = new Customer("Patrick", new int[] {4,20}, "647", "Pat", "password1");

        assertEquals(customer1.getPassword(), c1.getName());
    }
}
