package com.yde.sapiensdelivery;

import com.yde.sapiensdelivery.controllers.CustomerGateway;
import com.yde.sapiensdelivery.controllers.database.OnDataReadListener;
import com.yde.sapiensdelivery.entities.Customer;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserGatewayTest {
    CustomerGateway cm;

    @Before
    public void setUp() throws InterruptedException {
        cm = new CustomerGateway("CUSTOMER");

        Customer kevin = new Customer("Kevin", new int[] {100, 200}, "4164449999", "kevin",
                DigestUtils.sha256Hex("ilikesparrows123"));

        cm.save(kevin.getUname(), kevin);
        Thread.sleep(10000);
    }

    @Test
    public void testRead() throws InterruptedException {
        final String[] assertResult = {""};

        cm.get("Kevin", new OnDataReadListener() {
            @Override
            public void onSuccess() {
                Customer customer = (Customer) getSavedObject();
                assertResult[0] = customer.getName();
            }

            @Override
            public void onFailure() {
                System.out.println("Failure of database.");
            }
        });
        Thread.sleep(10000);
        assertEquals("Kevin", assertResult[0]);
    }

}
