package com.yde.sapiensdelivery.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.gateways.UserGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.use_cases.UserManager;

public class RegistrationActivity extends AppCompatActivity {

    EditText name;
    EditText username;
    EditText password;
    EditText phoneNumber;
    EditText address;
    EditText sin;
    EditText transport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        RadioGroup userType = findViewById(R.id.user_type);
        LinearLayout[] optionalDelivery = {findViewById(R.id.sin_ll), findViewById(R.id.transport_ll)};
        LinearLayout optionalCustomer = findViewById(R.id.address_ll);
        ((RadioButton) userType.getChildAt(0)).setChecked(true);

        name = findViewById(R.id.name_register);
        username = findViewById(R.id.username_register);
        password = findViewById(R.id.password_register);
        phoneNumber = findViewById(R.id.ph_num_register);
        address = findViewById(R.id.address_register);
        sin = findViewById(R.id.sin_sign_up);
        transport = findViewById(R.id.transport_sign_up);
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

            UserGateway userGateway;

            if(userType.getCheckedRadioButtonId() == R.id.customer_sign_up){
                userGateway = UserGateway.getUserGateway("CUSTOMER");
                if(checkEmptyCustomer()){
                    return;
                }
            }
            else{
                userGateway = UserGateway.getUserGateway("DELIVERYMAN");
                if(checkEmptyDeliveryMan()){
                    return;
                }
            }

            String nameStr = name.getText().toString();
            String phNumStr = phoneNumber.getText().toString();
            String usernameStr = username.getText().toString();
            String location = address.getText().toString();

            long sinVal = 0;
            if(!sin.getText().toString().equals("")){
                sinVal = Long.parseLong(sin.getText().toString());
            }

            String passwordStr = password.getText().toString();
            String transportStr = transport.getText().toString();

            long finalSinVal = sinVal;

            userGateway.registration(phoneNumber.getText().toString(), username.getText().toString(), sin.getText().toString(),
                    transport.getText().toString(), new OnDataReadListener() {
                        @Override
                        public void onSuccess() {
                            Intent intent;
                            boolean isCustomer = userType.getCheckedRadioButtonId() == R.id.customer_sign_up;
                            String userChosen = isCustomer ? "CUSTOMER" : "DELIVERYMAN";

                            intent = new Intent(RegistrationActivity.this, SignInActivity.class);

                            userGateway.save(usernameStr, UserManager.createUser(userChosen, nameStr, location, phNumStr, usernameStr,
                                    userGateway.createHash(passwordStr), finalSinVal, transportStr));
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure() {
                            for(int errorCode: ERROR_CODES){
                                switch (errorCode){
                                    case 0:
                                        username.setError("Username is already taken");
                                        break;
                                    case 2:
                                        phoneNumber.setError("Not a phone number");
                                        break;
                                    case 3:
                                        sin.setError("Not a SIN");
                                        break;
                                    case 4:
                                        transport.setError("Not a License Plate");
                                }
                            }
                        }
                    });
        });
    }

    private boolean checkEmptyDeliveryMan() {
        boolean isEmpty = false;

        if(name.getText().toString().equals("")){
            name.setError("This field is missing");
            isEmpty = true;
        }
        if(username.getText().toString().equals("")){
            username.setError("This field is missing");
            isEmpty = true;
        }
        if(password.getText().toString().equals("")){
            password.setError("This field is missing");
            isEmpty = true;
        }
        if(phoneNumber.getText().toString().equals("")){
            phoneNumber.setError("This field is missing");
            isEmpty = true;
        }
        if(sin.getText().toString().equals("")){
            sin.setError("This field is missing");
            isEmpty = true;
        }
        if(transport.getText().toString().equals("")){
            transport.setError("This field is missing");
            isEmpty = true;
        }

        return isEmpty;
    }

    private boolean checkEmptyCustomer() {
        boolean isEmpty = false;

        if(name.getText().toString().equals("")){
            name.setError("This field is missing");
            isEmpty = true;
        }
        if(username.getText().toString().equals("")){
            username.setError("This field is missing");
            isEmpty = true;
        }
        if(password.getText().toString().equals("")){
            password.setError("This field is missing");
            isEmpty = true;
        }
        if(phoneNumber.getText().toString().equals("")){
            phoneNumber.setError("This field is missing");
            isEmpty = true;
        }
        if(address.getText().toString().equals("")){
            address.setError("This field is missing");
            isEmpty = true;
        }
        return isEmpty;
    }
}