package com.yde.sapiensdelivery.controllers.delivery_man;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.controllers.adapters.CustomersRVAdapter;
import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.use_cases.DeliveryManManager;
import com.yde.sapiensdelivery.use_cases.OrderManager;

import java.io.Serializable;
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

        // Get data from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            deliveryManManager = new DeliveryManManager((DeliveryMan) extras.getSerializable("DELIVERYMAN"));
        }


        // TODO get the Hashmap of username to ShoppingList from DB and remove the hardcoded
        ArrayList<ShoppingList> sls = new ArrayList<>();
        ShoppingList sl1 = new ShoppingList("UOFT", "AAA");
        sl1.setCommodity(new Commodity("TV", 1000, 1));
        sl1.setCommodity(new Commodity("Couch", 200, 1));

        ShoppingList sl2 = new ShoppingList("Apple store", "BBB");
        sl2.setCommodity(new Commodity("apple", 2, 1));
        sls.add(sl1);
        sls.add(sl2);

        HashMap<String, ArrayList<ShoppingList>> hashMap = new HashMap<>();
        hashMap.put("Patrick", sls);
        hashMap.put("Kevin", sls);
        // TODO remove above

        // Set up the environment
        customers = new ArrayList<>();
        customers.addAll(hashMap.keySet());
        customerToSL = hashMap;

        customersRV = findViewById(R.id.customers_rv);
        customersRV.setLayoutManager(new LinearLayoutManager(this));
        customersAdapter = new CustomersRVAdapter(customers, customerToSL, this);
        customersRV.setAdapter(customersAdapter);
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

        // TODO get Customer from DB to create an order
        String customer = customers.get(position);
        ArrayList<ShoppingList> shoppingLists = customerToSL.get(customer);
//        orderManager.createOrder(deliveryMan, customer, shoppingLists);

        // TODO pass the intent
    }
}