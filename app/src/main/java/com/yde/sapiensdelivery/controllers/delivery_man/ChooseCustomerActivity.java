package com.yde.sapiensdelivery.controllers.delivery_man;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.controllers.adapters.CustomersRVAdapter;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.gateways.CustomerGateway;
import com.yde.sapiensdelivery.gateways.ShoppingListGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.use_cases.DeliveryManManager;
import com.yde.sapiensdelivery.use_cases.OrderManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ChooseCustomerActivity extends AppCompatActivity implements CustomersRVAdapter.OnCustomerClickListener {
    RecyclerView customersRV;
    ArrayList<String> customers;

    CustomersRVAdapter customersAdapter;
    HashMap<String, ArrayList<ShoppingList>> customerToSL;

    DeliveryManManager deliveryManManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_customer);

        customerToSL = new HashMap<>();

        // Get data from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            deliveryManManager = new DeliveryManManager((DeliveryMan) extras.getSerializable("DELIVERYMAN"));
        }


        customersRV = findViewById(R.id.customers_rv);
        customersRV.setLayoutManager(new LinearLayoutManager(this));

        ShoppingListGateway shoppingListGateway = new ShoppingListGateway();
        shoppingListGateway.getAll(customerToSL, new OnDataReadListener() {
            @Override
            public void onSuccess() {
                customers = new ArrayList<>();
                customers.addAll(customerToSL.keySet());

                for (String c: customers){
                    Log.d("Checking error", "Values in array list -> " + c);
                }

                customersAdapter = new CustomersRVAdapter(customers, customerToSL, ChooseCustomerActivity.this);
                customersRV.setAdapter(customersAdapter);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void onDetailsClick(int position) {
        Intent intent = new Intent(ChooseCustomerActivity.this, ShoppingListDisplayActivity.class);

        String customer = customers.get(position);
        ArrayList<ShoppingList> shoppingLists = customerToSL.get(customer);

        intent.putExtra("shopping_lists", shoppingLists);
        startActivity(intent);
    }

    @Override
    public void onAcceptClick(int position) {
        OrderManager orderManager = new OrderManager();

        String customer = customers.get(position);
        ArrayList<ShoppingList> shoppingLists = customerToSL.get(customer);

        CustomerGateway customerGateway = new CustomerGateway("CUSTOMER");
        customerGateway.get(customer, new OnDataReadListener() {

            @Override
            public void onSuccess() {
                ShoppingListGateway shoppingListGateway = new ShoppingListGateway();

                deliveryManManager.createOrder(orderManager, (Customer) getSavedObject(), shoppingLists);
                shoppingListGateway.delete(customer);
                Intent intent = new Intent( ChooseCustomerActivity.this, OrderStatusDeliveryManActivity.class);
                deliveryManManager.passValue(intent);
                startActivity(intent);// Order is available to display
            }

            @Override
            public void onFailure() {

            }
        });
    }
}