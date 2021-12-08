package com.yde.sapiensdelivery.controllers.delivery_man;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.use_cases.CustomerManager;
import com.yde.sapiensdelivery.use_cases.DeliveryManManager;

public class DeliveryManProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_man_profile);

        DeliveryManManager dm = new DeliveryManManager((DeliveryMan) getIntent().getSerializableExtra("DELIVERYMAN"));

        TextView name = findViewById(R.id.name_view);
        TextView phoneView = findViewById(R.id.ph_num_view);
        TextView sin = findViewById(R.id.sin_view);
        TextView transport = findViewById(R.id.license_view);

        // get information from use-cases
        String nameStr = dm.getName();
        String sinStr = String.valueOf(dm.getSin());
        String phoneStr  = dm.getPhoneNumber();
        String transportStr = dm.getTransport();

        // Update there variables of TextView
        name.setText(nameStr);
        sin.setText(sinStr);
        phoneView.setText(phoneStr);
        transport.setText(transportStr);

    }
}