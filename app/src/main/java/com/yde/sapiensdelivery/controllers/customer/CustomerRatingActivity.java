package com.yde.sapiensdelivery.controllers.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.use_cases.CustomerManager;
import com.yde.sapiensdelivery.use_cases.DeliveryManManager;

public class CustomerRatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Button main = findViewById(R.id.main);
        RatingBar rating1 = findViewById(R.id.ratingBar);

        DeliveryManManager deliveryManManager = new DeliveryManManager((DeliveryMan)
                getIntent().getSerializableExtra("DELIVERYMAN"));

        CustomerManager customerManager = new CustomerManager((Customer) getIntent().getSerializableExtra("CUSTOMER"));

        /*
         * after click the main button, it goes back to CustomerActivity page
         */
        main.setOnClickListener(v -> {
            float myRating = rating1.getRating();
            deliveryManManager.updateRating(myRating);

            Intent intent = new Intent( CustomerRatingActivity.this, CustomerActivity.class);
            customerManager.passValue(intent);
            startActivity(intent);
        });

        /*
         * send different message based on a customer input
         */
        rating1.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            int value = (int) rating;
            String message = null;

            switch(value){
                case 1:
                    message = "sorry to hear that :(";
                    break;
                case 2:
                    message = "sorry to hear that :<";
                    break;
                case 3:
                    message = "we always accept suggestions :|";
                    break;
                case 4:
                    message = "good to hear that :>";
                    break;
                case 5:
                    message = "great enough :)";
                    break;
            }
            Toast.makeText(CustomerRatingActivity.this, message, Toast.LENGTH_SHORT).show();

        });

    }
}