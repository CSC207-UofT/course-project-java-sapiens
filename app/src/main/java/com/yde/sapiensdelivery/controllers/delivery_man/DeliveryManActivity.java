package com.yde.sapiensdelivery.controllers.delivery_man;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.gateways.OrderGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.use_cases.DeliveryManManager;
import com.yde.sapiensdelivery.use_cases.OrderManager;

import java.util.ArrayList;

public class DeliveryManActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_man);

        Button profile = findViewById(R.id.profileD);
        Button status = findViewById(R.id.statusD);
        Button takeOrder = findViewById(R.id.takeOrderD);
        TextView welcome = findViewById(R.id.welcomeD);

        DeliveryManManager dm = new DeliveryManManager((DeliveryMan)
                getIntent().getSerializableExtra("DELIVERYMAN"));
        String welcomeMessage = "Welcome " + dm.getName() + "!";
        welcome.setText(welcomeMessage);

        profile.setOnClickListener(v -> {
            Intent intent = new Intent( DeliveryManActivity.this, DeliveryManProfileActivity.class);
            dm.passValue(intent);
            startActivity(intent);
        });

        takeOrder.setOnClickListener(v -> {
            OrderGateway orderGateway = new OrderGateway();

            orderGateway.getByDeliveryman(dm.getUsername(), new OnDataReadListener() {
                @Override
                public void onSuccess() {
                    ArrayList<Object> doubleData = (ArrayList<Object>) getSavedObject();
                    OrderManager orderManager = new OrderManager((Order) doubleData.get(0));

                    if(orderManager.getStatus() != Order.OrderStatus.COMP){
                        String message = "You already have an order to deliver";
                        Toast.makeText(DeliveryManActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent intent = new Intent( DeliveryManActivity.this, ChooseCustomerActivity.class);

                        dm.passValue(intent);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure() {
                    Intent intent = new Intent( DeliveryManActivity.this, ChooseCustomerActivity.class);

                    dm.passValue(intent);
                    startActivity(intent);
                }
            });
        });

        status.setOnClickListener(v -> {
            OrderGateway orderGateway = new OrderGateway();

            orderGateway.getByDeliveryman(dm.getUsername(), new OnDataReadListener() {
                @Override
                public void onSuccess() {
                    Intent intent = new Intent( DeliveryManActivity.this, OrderStatusDeliveryManActivity.class);
                    ArrayList<Object> doubleData = (ArrayList<Object>) getSavedObject();
                    OrderManager orderManager = new OrderManager((Order) doubleData.get(0));

                    if(orderManager.getStatus() == Order.OrderStatus.COMP){
                        String message = "You have no active orders right now.";
                        Toast.makeText(DeliveryManActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                    else{
                        dm.passValue(intent);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure() {
                    String message = "You have no active orders right now.";
                    Toast.makeText(DeliveryManActivity.this, message, Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
