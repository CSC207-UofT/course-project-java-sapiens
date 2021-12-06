package com.yde.sapiensdelivery.controllers.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // call the information from previous activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        Button main = (Button) findViewById(R.id.ret);

        main.setOnClickListener(v -> {
            // go back to the customer Activity after click the main button
            Intent intent = new Intent( CustomerProfileActivity.this, CustomerActivity.class);
            startActivity(intent);
        });
        
        Intent intent = getIntent();
        
        // set TextView of three variables
        TextView fullNameView = (TextView) findViewById(R.id.FullNameTitle);
        TextView userNameView = (TextView) findViewById(R.id.UserNameTitle);
        TextView phoneView = (TextView) findViewById(R.id.phoneTitle);

        // Update there variables of TextView
        fullNameView.setText(" full name: " +Fullname);
        userNameView.setText(" user name: " + Username);
        phoneView.setText(" phone number: "+ Phone);
    }
}
