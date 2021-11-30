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

public class ProfileActivity extends AppCompatActivity {
    private Button main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



    }
}