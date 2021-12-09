package com.yde.sapiensdelivery.controllers.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
        Button completeOrder = findViewById(R.id.complete_order);
        TextView statusOrder = findViewById(R.id.status_order);
        TextView contact = findViewById(R.id.contact);
        CardView cardView = findViewById(R.id.cardView);

        CustomerManager customerManager = new CustomerManager((Customer)
                getIntent().getSerializableExtra("CUSTOMER"));

        OrderGateway orderGateway = new OrderGateway();
        orderGateway.getPersist(customerManager.getUsername(), new OnDataReadListener() {

            @Override
            public void onSuccess() {

                if(getSavedObject() != null) {
                    OrderManager orderManager = new OrderManager((Order) getSavedObject());
                    progressBar.setVisibility(View.INVISIBLE);
                    cardView.setVisibility(View.VISIBLE);

                    String contactInfo = orderManager.getDeliveryManContact();
                    contact.setText(contactInfo);
                    String statusInfo = orderManager.getStatus().toString();
                    statusOrder.setText(statusInfo);

                    if (orderManager.isStatusCOMP(orderManager.getStatus())) {
                        completeOrder.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure() {
            }
        });

        completeOrder.setOnClickListener(view -> {
            Intent intent = new Intent( OrderStatusActivity.this, OrderCompletionActivity.class);
            customerManager.passValue(intent);
            startActivity(intent);
        });
    }
}