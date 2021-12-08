package com.yde.sapiensdelivery.gateways;

import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class OrderGatewayTest {
    OrderGateway og;

    @Before
    public void setUp() throws InterruptedException {
        og = new OrderGateway();

        DeliveryMan kevin = new DeliveryMan("Kevin", "ADDRESS", "4164449999", "kevin",
                DigestUtils.sha256Hex("ilikesparrows123"), (long) 1111, "MX-245");

        Customer ke = new Customer("Kevin", "ADDRESS", "4164449999", "kevin",
                DigestUtils.sha256Hex("ilikesparrows123"));

        og.save("jsjs", new Order(kevin, ke, new ArrayList<>()));
        Thread.sleep(5000);
    }

    @Test
    public void testRead() throws InterruptedException {
//        final String[] assertResult = {""};
//
//        cm.get("Kevin", new OnDataReadListener() {
//            @Override
//            public void onSuccess() {
//                Customer customer = (Customer) getSavedObject();
//                assertResult[0] = customer.getName();
//            }
//
//            @Override
//            public void onFailure() {
//                System.out.println("Failure of database.");
//            }
//        });
//        Thread.sleep(10000);
//        assertEquals("Kevin", assertResult[0]);
    }

}
