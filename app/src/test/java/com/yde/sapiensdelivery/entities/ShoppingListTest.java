package com.yde.sapiensdelivery.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ShoppingListTest {
    ShoppingList shoppingList;

    @Before
    public void setUp() {
        shoppingList = new ShoppingList("Walmart", "NO ADDRESS, TO BE IMPLEMENTED IN OUTLET CLASS");
    }


    @Test
    public void getTotalPrice() {
        Commodity apple = new Commodity("Apple", 3.5, 1);
        shoppingList.setCommodity(apple);
        Commodity peach = new Commodity("Peach", 4.5, 10);
        shoppingList.setCommodity(peach);
        double totalPrice = 1*3.5 + 10*4.5;
        assertEquals(totalPrice, shoppingList.getTotalPrice(), 0);
    }

    @Test
    public void getShoppingList() {
        Commodity apple = new Commodity("Apple", 3.5, 1);
        shoppingList.setCommodity(apple);
        Commodity peach = new Commodity("Peach", 3.5, 10);
        shoppingList.setCommodity(peach);
        ArrayList<Commodity> list = new ArrayList<>();
        list.add(apple);
        list.add(peach);
        assertEquals(list, shoppingList.getShoppingList());
    }

    @Test
    public void setCommodity() {
        Commodity apple = new Commodity("Apple", 3.5, 1);
        shoppingList.setCommodity(apple);
        Commodity peach = new Commodity("Peach", 3.5, 10);
        shoppingList.setCommodity(peach);
        assertEquals(peach, shoppingList.getShoppingList().get(1));
    }

    @Test
    public void add1Commodity() {
        Commodity apple = new Commodity("Apple", 3.5, 1);
        shoppingList.setCommodity(apple);
        shoppingList.add1Commodity(0);
        assertEquals(2, shoppingList.getShoppingList().get(0).getQuantity());
    }

    @Test
    public void remove1Commodity() {
        Commodity apple = new Commodity("Apple", 3.5, 1);
        shoppingList.setCommodity(apple);
        shoppingList.remove1Commodity(0);
        // After the quantity of a Commodity is 0, the Commodity is removed
        // So the size of this shoppingList is now 0
        assertEquals(0, shoppingList.size());
    }
}