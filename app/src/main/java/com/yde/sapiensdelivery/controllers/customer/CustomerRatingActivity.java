package com.yde.sapiensdelivery.controllers.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.yde.sapiensdelivery.R;

public class CustomerRatingActivity extends AppCompatActivity {
    private Button main;
    private RatingBar Rating;
    float myRating  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        main = (Button) findViewById(R.id.main);
        Rating = findViewById(R.id.ratingBar);

        /*
         * after click the main button, it goes back to CustomerActivity page
         */
        main.setOnClickListener(v -> {
            Intent intent = new Intent( CustomerRatingActivity.this, CustomerActivity.class);
            startActivity(intent);
        });

        /*
         * send different message based on a customer input
         */
        Rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
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

            }
        });

    }
}