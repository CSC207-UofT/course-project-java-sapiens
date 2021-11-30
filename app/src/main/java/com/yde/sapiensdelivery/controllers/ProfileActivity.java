package com.yde.sapiensdelivery.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.gateways.UserGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class ProfileActivity extends AppCompatActivity {
    private Button main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        main = (Button) findViewById(R.id.main);

        main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ProfileActivity.this, CustomerActivity.class);
                startActivity(intent);
            }
        });

        TextView fullName = (TextView) findViewById(R.id.FullName);
        TextView userName = (TextView) findViewById(R.id.UserName);
        TextView phone = (TextView) findViewById(R.id.phone);



        String Fullname = "";
        String Username = "";
        String Phone  = "";
        fullName.setText(Fullname);
        userName.setText(Username);
        phone.setText(Integer.parseInt(Phone));


    }
}