package controllers.use_cases;

import entities.Customer;
import org.junit.*;
import controllers.use_cases.Database.OnDataReadListener;

import static org.junit.Assert.assertEquals;

public class UserManagerTest {
    CustomerManager cm;

    @Before
    public void setUp() {
        cm = new CustomerManager("CUSTOMER");
    }

    @Test
    public void testRead() throws InterruptedException {
        final String[] assertResult = {""};

        cm.get("Nikhil", new OnDataReadListener() {
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
        assertEquals(assertResult[0], "Nikhil");
    }

}
