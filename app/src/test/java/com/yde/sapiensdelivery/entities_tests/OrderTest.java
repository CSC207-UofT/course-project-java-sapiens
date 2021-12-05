//TODO: Fix for ShoppingList
//package com.yde.sapiensdelivery.entities_tests;
//
//import com.yde.sapiensdelivery.entities.Customer;
//import com.yde.sapiensdelivery.entities.DeliveryMan;
//import com.yde.sapiensdelivery.entities.Order;
//import com.yde.sapiensdelivery.entities_tests.ShoppingListOld;
//import com.yde.sapiensdelivery.use_cases_tests.ShoppingListManagerOld;
//import org.junit.*;
//import java.util.ArrayList;
//import static org.junit.Assert.assertEquals;
//
//public class OrderTest {
//    Order order;
//    ShoppingListManagerOld shoppingListManagerOld;
//    ArrayList<ShoppingListOld> shoppingLists;
//
//    @Before
//    public void setUp() {
//        shoppingListManagerOld = new ShoppingListManagerOld();
//        shoppingLists = new ArrayList<>();
//        shoppingListManagerOld.newShoppingList("Best Buy", shoppingLists);
//        shoppingListManagerOld.newShoppingList("KFC", shoppingLists);
//    }
//
//    @Test
//    public void UID_Test() {
//        ShoppingListOld shoppingList = new ShoppingListOld("KFC");
//        ArrayList<ShoppingListOld> s = new ArrayList<>();
//        s.add(shoppingList);
//        order = new Order(null,null,17,s);
//
//        assertEquals(17, order.getUID());
//    }
//
//
//    @Test
//    public void OrderStatusTest() {
//        DeliveryMan deliveryMan1 = new DeliveryMan("Samuel", new int[] {10,20}, "648", "Samuel", "12", 1234, "moto-bike", (float)4.5);
//        Customer customer1 = new Customer("Patrick", new int[] {4,20}, "647", "Pat", "123");
//        ShoppingListOld shoppingList = new ShoppingListOld("KFC");
//        ArrayList<ShoppingListOld> s = new ArrayList<>();
//        s.add(shoppingList);
//        order = new Order(deliveryMan1,customer1,17,s);
//        order.setStatusOTW();
//        assertEquals("On the Way to get your order.", order.getStatusOTW());
//    }
//
//    @Test
//    public void TotalPriceTest() {
//        DeliveryMan deliveryMan1 = new DeliveryMan("Samuel", new int[] {10,20}, "648", "Samuel", "12", 1234, "moto-bike", (float)4.5);
//        Customer customer1 = new Customer("Patrick", new int[] {4,20}, "647", "Pat", "123");
//
//
//        shoppingListManagerOld.setCommodity(0, "10x Wings",20,1, shoppingLists);
//        ShoppingListOld shoppingList = shoppingListManagerOld.addCommodity(0, "10x Wings", shoppingLists);
//        ArrayList<ShoppingListOld> s = new ArrayList<>();
//        s.add(shoppingList);
//
//        order = new Order(deliveryMan1,customer1,17,s);
//
//        assertEquals(40.0, order.getTotalPrice(),0);
//    }
//
//}
