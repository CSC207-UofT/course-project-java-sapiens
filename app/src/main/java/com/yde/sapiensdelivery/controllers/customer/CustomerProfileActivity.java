package com.yde.sapiensdelivery.controllers.customer;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.use_cases.CustomerManager;

public class CustomerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        CustomerManager cm = new CustomerManager((Customer) getIntent().getSerializableExtra("CUSTOMER"));

        TextView name = findViewById(R.id.name_view);
        TextView phoneView = findViewById(R.id.ph_num_view);
        TextView address = findViewById(R.id.address_view);

        // get information from use-cases
        String nameStr = cm.getName();
        String addressStr = cm.getLocation();
        String phoneStr  = cm.getPhoneNumber();

        // Update there variables of TextView
        name.setText(nameStr);
        address.setText(addressStr);
        phoneView.setText(phoneStr);

    }
}