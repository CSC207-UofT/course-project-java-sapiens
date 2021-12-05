package com.yde.sapiensdelivery.controllers.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.gateways.OrderGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.use_cases.CustomerManager;

public class OrderStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        CustomerManager customerManager = new CustomerManager((Customer)
                getIntent().getSerializableExtra("CUSTOMER"));

        OrderGateway orderGateway = new OrderGateway();
        orderGateway.get(customerManager.getName(), new OnDataReadListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure() {
            }
        });
    }
}