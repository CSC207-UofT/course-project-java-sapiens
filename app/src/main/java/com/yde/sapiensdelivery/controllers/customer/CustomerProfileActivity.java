package com.yde.sapiensdelivery.controllers.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yde.sapiensdelivery.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.gateways.UserGateway;
import com.yde.sapiensdelivery.use_cases.CustomerManager;

public class CustomerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        Button main = (Button) findViewById(R.id.ret);

        main.setOnClickListener(v -> {
            // go back to the customer Activity after click the main button
            Intent intent = new Intent( CustomerProfileActivity.this, CustomerActivity.class);
            startActivity(intent);
        });

        CustomerManager cm = new CustomerManager((Customer) getIntent().getSerializableExtra("CUSTOMER"));

        TextView name = (TextView) findViewById(R.id.name_view);
        TextView phoneView = (TextView) findViewById(R.id.ph_num_view);
        TextView address = (TextView) findViewById(R.id.address_view);

        // get information from use-cases
        String nameStr = cm.getName();
        String addressStr = cm.getLocation();
        String phoneStr  = cm.getPhoneNumber();

        // Update there variables of TextView
        name.setText(nameStr);
        address.setText(addressStr);
        phoneView.setText(phoneStr);


    }
}