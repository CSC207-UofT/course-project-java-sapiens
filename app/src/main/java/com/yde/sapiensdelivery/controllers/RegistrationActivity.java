package com.yde.sapiensdelivery.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.yde.sapiensdelivery.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        RadioGroup userType = findViewById(R.id.user_type);
        LinearLayout[] optionalDelivery = {findViewById(R.id.rate_ll), findViewById(R.id.sin_ll), findViewById(R.id.transport_ll)};
        LinearLayout optionalCustomer = findViewById(R.id.address_ll);

        EditText username = findViewById(R.id.username_register);
        EditText password = findViewById(R.id.password_register);
        EditText phoneNumber = findViewById(R.id.ph_num);
        EditText address = findViewById(R.id.address);
        EditText sin = findViewById(R.id.sin_sign_up);
        EditText rate = findViewById(R.id.rate_sign_up);
        EditText transport = findViewById(R.id.transport_sign_up);
        Button register = findViewById(R.id.register);

        userType.setOnCheckedChangeListener((radioGroup, i) -> {
            if(i == R.id.customer_sign_up){
                for(LinearLayout optionLL : optionalDelivery){
                    optionLL.setVisibility(View.GONE);
                }
                optionalCustomer.setVisibility(View.VISIBLE);
            }
            else{
                for(LinearLayout optionLL : optionalDelivery){
                    optionLL.setVisibility(View.VISIBLE);
                }
                optionalCustomer.setVisibility(View.GONE);
            }
        });

        register.setOnClickListener(view -> {

        });
    }
}