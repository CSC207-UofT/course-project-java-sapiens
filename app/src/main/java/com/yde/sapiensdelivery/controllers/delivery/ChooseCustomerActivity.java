package com.yde.sapiensdelivery.controllers.delivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.yde.sapiensdelivery.R;

public class ChooseCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_delivery_man);

        RecyclerView deliverymanRV = findViewById(R.id.delivery_men_rv);
        deliverymanRV.setLayoutManager(new LinearLayoutManager(this));
    }
}