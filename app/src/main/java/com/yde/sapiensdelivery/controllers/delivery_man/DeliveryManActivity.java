package com.yde.sapiensdelivery.controllers.delivery_man;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.use_cases.DeliveryManManager;


public class DeliveryManActivity extends AppCompatActivity {

    //Commented portion belongs to DeliveryManProfileActivity, which may or may not be added

//    public static final String fullName = "full";
    public static final String userName = "user";
    public static final String phone = "phone";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_man);

//        Button profile = findViewById(R.id.profileD);
        Button status = findViewById(R.id.statusD);
        Button takeOrder = findViewById(R.id.takeOrderD);
        TextView welcome = findViewById(R.id.welcomeD);

        DeliveryManManager dm = new DeliveryManManager((DeliveryMan)
                getIntent().getSerializableExtra("DELIVERYMAN"));
        String welcomeMessage = "Welcome " + dm.getName() + "!";
        welcome.setText(welcomeMessage);

//        profile.setOnClickListener(v -> {
////            Intent intent = new Intent( DeliveryManActivity.this, CustomerProfileActivity.class);
////            intent.putExtra(fullName,dm.getName());
////            intent.putExtra(userName,dm.getUsername());
////            intent.putExtra(phone,dm.getPhoneNumber());
////            startActivity(intent);
//        });

        takeOrder.setOnClickListener(v -> {
            Intent intent = new Intent( DeliveryManActivity.this, ChooseCustomerActivity.class);
            startActivity(intent);
        });

        status.setOnClickListener(v -> {
            Intent intent = new Intent( DeliveryManActivity.this, OrderStatusDeliveryManActivity.class);
            startActivity(intent);
        });
    }
}
