package com.yde.sapiensdelivery.controllers.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.use_cases.CustomerManager;


public class CustomerActivity extends AppCompatActivity {
    public static final String fullName = "full";
    public static final String userName = "user";
    public static final String phone = "phone";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Button profile = findViewById(R.id.profile);
        Button status = findViewById(R.id.status);
        Button placeOrder = findViewById(R.id.placeOrder);
        TextView welcome = findViewById(R.id.welcome);

        CustomerManager cm = new CustomerManager((Customer) getIntent().getSerializableExtra("CUSTOMER"));
        String welcomeMessage = "Welcome " + cm.getName() + "!";
        welcome.setText(welcomeMessage);

        profile.setOnClickListener(v -> {
            Intent intent = new Intent( CustomerActivity.this, CustomerProfileActivity.class);
            intent.putExtra(fullName,cm.getName());
            intent.putExtra(userName,cm.getUsername());
            intent.putExtra(phone,cm.getPhoneNumber());
            startActivity(intent);
        });

        placeOrder.setOnClickListener(v -> {
            Intent intent = new Intent( CustomerActivity.this, ShoppingListCreationActivity.class);
            startActivity(intent);
        });

        status.setOnClickListener(v -> {
            Intent intent = new Intent( CustomerActivity.this, OrderStatusActivity.class);
            startActivity(intent);
        });
    }
}
