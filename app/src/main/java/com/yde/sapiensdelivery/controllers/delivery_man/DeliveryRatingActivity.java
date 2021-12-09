package com.yde.sapiensdelivery.controllers.delivery_man;

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

public class DeliveryRatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_rating);

        Button main = findViewById(R.id.mainD);
        RatingBar rating = findViewById(R.id.ratingBarD);

        CustomerManager customerManager = new CustomerManager((Customer)
                getIntent().getSerializableExtra("CUSTOMER"));

        DeliveryManManager deliveryManManager = new DeliveryManManager((DeliveryMan)
                getIntent().getSerializableExtra("DELIVERYMAN"));

        /*
         * after click the main button, it goes back to DeliveryActivity page
         */
        main.setOnClickListener(v -> {
            float myRating = rating.getRating();
            customerManager.updateRating(myRating);

            Intent intent = new Intent( DeliveryRatingActivity.this, DeliveryManActivity.class);
            deliveryManManager.passValue(intent);
            startActivity(intent);
        });

        /*
         * send different message based on a customer input
         */
        rating.setOnRatingBarChangeListener((ratingBarD, rating1, fromUser) -> {
            int value = (int) rating1;
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
            Toast.makeText(DeliveryRatingActivity.this, message, Toast.LENGTH_LONG).show();

        });

    }
}