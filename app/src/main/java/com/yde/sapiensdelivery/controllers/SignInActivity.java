package com.yde.sapiensdelivery.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.gateways.UserGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        CheckBox isCustomer = findViewById(R.id.is_customer);
        Button signIn = findViewById(R.id.button);
        TextView signUp = findViewById(R.id.sign_up);
        TextView error = findViewById(R.id.error);

        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        });

        signIn.setOnClickListener(view -> {

            UserGateway userGateway;

            if(isCustomer.isChecked()){
                userGateway = UserGateway.getUserGateway("CUSTOMER");
            }
            else{
                userGateway = UserGateway.getUserGateway("DELIVERYMAN");
            }

            String usernameStr = username.getText().toString();
            String passwordStr = password.getText().toString();

            userGateway.authenticate(usernameStr, passwordStr, new OnDataReadListener() {
                @Override
                public void onSuccess(){

                    error.setText(R.string.empty);
                    Intent intent;

                    if(isCustomer.isChecked()){
                        intent = new Intent(SignInActivity.this, CustomerActivity.class);
                        intent.putExtra("CUSTOMER", (Customer) getSavedObject());
                    }
                    else{
                        intent = new Intent(SignInActivity.this, DeliveryManActivity.class);
                    }

                    startActivity(intent);
                }

                @Override
                public void onFailure() {
                    error.setText(R.string.wrong_auth);
                }
            });
        });
    }
}