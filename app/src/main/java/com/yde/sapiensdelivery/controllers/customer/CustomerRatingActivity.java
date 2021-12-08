package com.yde.sapiensdelivery.controllers.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.use_cases.CustomerManager;

public class CustomerRatingActivity extends AppCompatActivity {
    float myRating  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Button main = findViewById(R.id.main);
        RatingBar rating1 = findViewById(R.id.ratingBar);

        /*
         * after click the main button, it goes back to CustomerActivity page
         */
        main.setOnClickListener(v -> {
            Intent intent = new Intent( CustomerRatingActivity.this, CustomerActivity.class);
            CustomerManager customerManager = new CustomerManager((Customer)
                    getIntent().getSerializableExtra("CUSTOMER"));
            customerManager.passValue(intent);
            startActivity(intent);
        });

        /*
         * send different message based on a customer input
         */
        rating1.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            int value = (int) rating;
            String message = null;
            myRating  = ratingBar.getRating();

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
            Toast.makeText(CustomerRatingActivity.this, message, Toast.LENGTH_LONG).show();

        });

    }
}