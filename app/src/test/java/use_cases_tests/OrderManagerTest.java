package use_cases_tests;

import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.use_cases.OrderManager;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class OrderManagerTest {
    OrderManager orderManager = new OrderManager();

    @Test(timeout = 50)
    public void testCreateOrder1() {
        DeliveryMan deliveryMan1 = new DeliveryMan("Samuel", new int[] {10,20}, "648", "Samuel", "12", 1234, "moto-bike", (float)4.5);
        Customer customer1 = new Customer("Patrick", new int[] {4,29}, "647", "Pat", "123");
        ShoppingList shoppingList = new ShoppingList("KFC");
        ArrayList<ShoppingList> s = new ArrayList<>();
        s.add(shoppingList);

        Order order = orderManager.createOrder(deliveryMan1, customer1, s);
        assertEquals("Patrick", order.getCustomer().getName());
    }

    @Test(timeout = 50)
    public void testCreateOrder2() {
        DeliveryMan deliveryMan1 = new DeliveryMan("Samuel", new int[] {10,20}, "648", "Samuel", "12", 1234, "moto-bike", (float)4.5);
        Customer customer1 = new Customer("Patrick", new int[] {4,20}, "647", "Pat", "123");
        ShoppingList shoppingList = new ShoppingList("KFC");
        ArrayList<ShoppingList> s = new ArrayList<>();
        s.add(shoppingList);
        Order order = orderManager.createOrder(deliveryMan1, customer1, s);

        assertEquals("647", order.getDeliveryMan().getNumber());
    }
}
