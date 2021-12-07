package com.yde.sapiensdelivery.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.controllers.customer.CustomerActivity;
import com.yde.sapiensdelivery.controllers.delivery_man.DeliveryManActivity;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.gateways.CurrentLocationFinder;
import com.yde.sapiensdelivery.gateways.DeliveryManGateway;
import com.yde.sapiensdelivery.gateways.GoogleMapGateway;
import com.yde.sapiensdelivery.gateways.UserGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.use_cases.CustomerManager;
import com.yde.sapiensdelivery.use_cases.DeliveryManManager;

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
                        CustomerManager cm = new CustomerManager((Customer) getSavedObject());
                        cm.passValue(intent);
                    }
                    else{
                        intent = new Intent(SignInActivity.this, DeliveryManActivity.class);
                        DeliveryManManager dm = new DeliveryManManager((DeliveryMan) getSavedObject());
                        GoogleMapGateway googleMapGateway = new GoogleMapGateway();
                        dm.setLocation(googleMapGateway.findCurrentLocation( SignInActivity.this));
                        dm.passValue(intent);
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