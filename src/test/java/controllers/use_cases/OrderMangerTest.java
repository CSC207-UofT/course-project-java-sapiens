package controllers.use_cases;

import entities.Customer;
import entities.DeliveryMan;
import entities.Order;
import entities.ShoppingList;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class OrderMangerTest {
    OrderManager orderManager = new OrderManager();

    @Test(timeout = 50)
    public void testCreateOrder1() {
        DeliveryMan deliveryMan1 = new DeliveryMan("Samuel", new int[] {10,20}, "648", "Samuel", "12", 1234, "moto-bike", 4.5);
        Customer customer1 = new Customer("Patrick", new int[] {4,20}, "647", "Pat", "123");
        ShoppingList shoppingList = new ShoppingList("KFC");
        ArrayList<ShoppingList> s = new ArrayList<>();
        s.add(shoppingList);

        Order order = orderManager.createOrder(deliveryMan1, customer1, s);
        assertEquals("Patrick", order.getCustomer().getName());
    }

    @Test(timeout = 50)
    public void testCreateOrder2() {
        DeliveryMan deliveryMan1 = new DeliveryMan("Samuel", new int[] {10,20}, "648", "Samuel", "12", 1234, "moto-bike", 4.5);
        Customer customer1 = new Customer("Patrick", new int[] {4,20}, "647", "Pat", "123");
        ShoppingList shoppingList = new ShoppingList("KFC");
        ArrayList<ShoppingList> s = new ArrayList<>();
        s.add(shoppingList);
        Order order = orderManager.createOrder(deliveryMan1, customer1, s);

        assertEquals("648", order.getDeliveryMan().getNumber());
    }
}