package com.yde.sapiensdelivery.controllers.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yde.sapiensdelivery.R;

public class OrderCompletionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_completion);

        FloatingActionButton floatingActionButton = findViewById(R.id.rating_next);

        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent( OrderCompletionActivity.this, CustomerRatingActivity.class);
            startActivity(intent);
        });
    }
}