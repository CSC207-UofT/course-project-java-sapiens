package com.yde.sapiensdelivery.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.yde.sapiensdelivery.R;

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

        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        });


    }
}