package com.yde.sapiensdelivery.controllers.customer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.gateways.GoogleMapGateway;
import com.yde.sapiensdelivery.gateways.OrderGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.use_cases.CustomerManager;
import com.yde.sapiensdelivery.use_cases.DeliveryManManager;
import com.yde.sapiensdelivery.use_cases.OrderManager;
import com.yde.sapiensdelivery.use_cases.ShoppingListManager;

import java.util.ArrayList;

public class OrderCompletionActivity extends AppCompatActivity {
    private OrderManager orderManager;
    OrderGateway orderGateway = new OrderGateway();
    GoogleMapGateway googleMapGateway = new GoogleMapGateway();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_order_completion);

        FloatingActionButton floatingActionButton = findViewById(R.id.rating_next_BT);
        TextView totalTV = findViewById(R.id.customer_total_TV);
        ListView shoppingLists = findViewById(R.id.customer_o_status_lv);

        CustomerManager customerManager = new CustomerManager((Customer)
                getIntent().getSerializableExtra("CUSTOMER"));

        orderGateway.getPersist(customerManager.getUsername(), new OnDataReadListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess() {
                if(getSavedObject() != null) {
                    orderManager = new OrderManager((Order) getSavedObject());

                    // Get the ShoppingLists and convert to String
                    ArrayList<ShoppingList> shopLists = orderManager.getShoppingLists();

                    ArrayList<String> slStrings = new ArrayList<>();
                    for (ShoppingList shoppingList : shopLists) {
                        ShoppingListManager shoppingListManager = new ShoppingListManager(shoppingList);
                        slStrings.add(shoppingListManager.displayEntire());
                    }

                    // Set ListView adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            OrderCompletionActivity.this, android.R.layout.simple_list_item_1, slStrings);
                    shoppingLists.setAdapter(adapter);

                    double travel_cost = orderManager.calculateJourney(googleMapGateway);
                    totalTV.setText("Total: $ " + (orderManager.getTotalPrice() + travel_cost));
                    }
                }

            @Override
            public void onFailure() {
            }
        });


        floatingActionButton.setOnClickListener(view -> {

            orderGateway.delete(customerManager.getUsername());
            DeliveryManManager deliveryManManager = new DeliveryManManager(orderManager.getDeliveryMan());

            Intent intent = new Intent( OrderCompletionActivity.this, CustomerRatingActivity.class);
            deliveryManManager.passValue(intent);
            customerManager.passValue(intent);
            startActivity(intent);
        });
    }
}