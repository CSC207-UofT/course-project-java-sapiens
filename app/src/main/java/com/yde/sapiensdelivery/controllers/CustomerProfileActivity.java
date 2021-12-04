package com.yde.sapiensdelivery.controllers;

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
    private Button main;
    private FirebaseDatabase user;
    private DatabaseReference ref;

    UserGateway userGateway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        main = (Button) findViewById(R.id.main);

        main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( CustomerProfileActivity.this, CustomerActivity.class);
                startActivity(intent);
            }
        });

        CustomerManager cm = new CustomerManager((Customer) getIntent().getSerializableExtra("CUSTOMER"));

        TextView fullNameView = (TextView) findViewById(R.id.FullName);
        TextView userNameView = (TextView) findViewById(R.id.UserName);
        TextView phoneView = (TextView) findViewById(R.id.phone);


        String Fullname = cm.getName();
        String Username = cm.getUsername();
        String Phone  = cm.getPhoneNumber();

        fullNameView.setText(Fullname);
        userNameView.setText(Username);
        phoneView.setText(Integer.parseInt(Phone));


    }
}