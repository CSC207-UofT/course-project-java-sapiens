package com.yde.sapiensdelivery.controllers.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.gateways.OrderGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.use_cases.CustomerManager;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Button profile = findViewById(R.id.profile);
        Button status = findViewById(R.id.status);
        Button placeOrder = findViewById(R.id.placeOrder);
        TextView welcome = findViewById(R.id.welcome);

        CustomerManager cm = new CustomerManager((Customer) getIntent().getSerializableExtra("CUSTOMER"));
        String welcomeMessage = "Welcome " + cm.getName() + "!";
        welcome.setText(welcomeMessage);

        profile.setOnClickListener(v -> {
                Intent intent = new Intent( CustomerActivity.this, CustomerProfileActivity.class);
                cm.passValue(intent);
                startActivity(intent);
        });

        placeOrder.setOnClickListener(v -> {
            OrderGateway orderGateway = new OrderGateway();
            
            orderGateway.get(cm.getUsername(), new OnDataReadListener() {
                @Override
                public void onSuccess() {

                    if(getSavedObject() != null){
                        String message = "You have already created an active order";
                        Toast.makeText(CustomerActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent intent = new Intent( CustomerActivity.this, ShoppingListCreationActivity.class);

                        cm.passValue(intent);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure() {
                    Intent intent = new Intent( CustomerActivity.this, ShoppingListCreationActivity.class);

                    cm.passValue(intent);
                    startActivity(intent);
                }
            });
        });

        status.setOnClickListener(v -> {
            OrderGateway orderGateway = new OrderGateway();

            orderGateway.get(cm.getUsername(), new OnDataReadListener() {
                @Override
                public void onSuccess() {
                    if(getSavedObject() == null){
                        String message = "You have no active orders right now.";
                        Toast.makeText(CustomerActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent intent = new Intent( CustomerActivity.this, OrderStatusActivity.class);
                        cm.passValue(intent);
                        startActivity(intent);// Order is available to display
                    }
                }

                @Override
                public void onFailure() {
                    String message = "Error in database Connection. Please check connection";
                    Toast.makeText(CustomerActivity.this, message, Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}