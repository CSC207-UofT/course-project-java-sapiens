package com.yde.sapiensdelivery.entities;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class UserTest {
    User user1 = new DeliveryMan("Samuel", "new int[] {10,20}", "648", "Samuel", "12", 1234, "moto-bike", (float) 4.5);

    User user2 = new Customer("Kevin", "new int[] {4,20}", "647", "Kevin", "123");

    @Test
    public void DeliveryMan_Test1() {
        assertEquals("648", user1.getNumber());
    }
    @Test
    public void DeliveryMan_Test2() {
        assertEquals("Samuel", user1.getName());
    }    @Test
    public void DeliveryMan_Test3() {
        assertEquals("12", user1.getPassword());
    }
    @Test
    public void Customer_Test1() {
        assertEquals("647", user2.getNumber());
    }
    @Test
    public void Customer_Test2() {
        assertEquals("Kevin", user2.getName());
    }    @Test
    public void Customer_Test3() {
        assertEquals("123", user2.getPassword());
    }
}
