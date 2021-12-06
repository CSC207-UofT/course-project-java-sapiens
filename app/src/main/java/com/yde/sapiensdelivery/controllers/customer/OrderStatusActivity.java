package com.yde.sapiensdelivery.controllers.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.gateways.OrderGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.use_cases.CustomerManager;
import com.yde.sapiensdelivery.use_cases.OrderManager;

public class OrderStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button completeOrder = findViewById(R.id.button);
        TextView orderName = findViewById(R.id.order_name);
        TextView statusOrder = findViewById(R.id.status_order);
        TextView contact = findViewById(R.id.contact);

        CustomerManager customerManager = new CustomerManager((Customer)
                getIntent().getSerializableExtra("CUSTOMER"));

        OrderGateway orderGateway = new OrderGateway();
        orderGateway.get(customerManager.getName(), new OnDataReadListener() {

            @Override
            public void onSuccess() {
                OrderManager orderManager = new OrderManager((Order) getSavedObject());

                if(orderManager.getStatus() == Order.OrderStatus.COMP){
                    progressBar.setVisibility(View.INVISIBLE);
                    completeOrder.setVisibility(View.VISIBLE);
                }
                else if(orderManager.getStatus() == Order.OrderStatus.REC){
                    String name = orderManager.getName();
                    orderName.setText(name);
                    String contactInfo = orderManager.getDeliveryManContact();
                    contact.setText(contactInfo);
                    String statusInfo = orderManager.getStatus().toString();
                    statusOrder.setText(statusInfo);
                }
            }

            @Override
            public void onFailure() {
            }
        });

        completeOrder.setOnClickListener(view -> {
            Intent intent = new Intent( OrderStatusActivity.this, OrderCompletionActivity.class);
            startActivity(intent);
        });
    }
}