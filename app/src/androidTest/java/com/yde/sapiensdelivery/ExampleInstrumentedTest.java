package com.yde.sapiensdelivery;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.gateways.OrderGateway;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.yde.sapiensdelivery", appContext.getPackageName());

        OrderGateway cm;
        cm = new OrderGateway();

        Customer kevin = new Customer("Kevin", "ADDRESS", "4164449999", "hai",
                "yo");
        DeliveryMan tyner = new DeliveryMan("Kevin", "ADDRESS", "4164449999", "hai",
                "yo", 1111111, "MKW111");

        Order order = new Order(tyner, kevin, new ArrayList<>());
        cm.save("jsjs", order);
    }
}