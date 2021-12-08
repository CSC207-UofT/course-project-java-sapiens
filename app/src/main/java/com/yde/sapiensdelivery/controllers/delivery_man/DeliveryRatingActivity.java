package com.yde.sapiensdelivery.controllers.delivery_man;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import com.yde.sapiensdelivery.R;

public class DeliveryRatingActivity extends AppCompatActivity {

    float myRating  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_rating);

        Button main = findViewById(R.id.mainD);
        RatingBar rating = findViewById(R.id.ratingBarD);

        /*
         * after click the main button, it goes back to DeliveryActivity page
         */
        main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DeliveryRatingActivity.this, DeliveryManActivity.class);
                startActivity(intent);
            }
        });

        /*
         * send different message based on a customer input
         */
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBarD, float rating, boolean fromUser) {
                int value = (int) rating;
                String message = null;
                myRating  = ratingBarD.getRating();

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

            }
        });

    }
}