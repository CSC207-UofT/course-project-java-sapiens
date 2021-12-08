package com.yde.sapiensdelivery.controllers.delivery_man;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.controllers.customer.CustomerActivity;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.use_cases.DeliveryManManager;


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
            Intent intent = new Intent( DeliveryManActivity.this, ChooseCustomerActivity.class);
            dm.passValue(intent);
            startActivity(intent);
        });

        status.setOnClickListener(v -> {
            String message = "You have no active orders right now.";
            Toast.makeText(DeliveryManActivity.this, message, Toast.LENGTH_LONG).show();
        });
    }
}
