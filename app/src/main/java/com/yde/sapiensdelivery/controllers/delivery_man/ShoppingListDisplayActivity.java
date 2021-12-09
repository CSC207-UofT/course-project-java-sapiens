package com.yde.sapiensdelivery.controllers.delivery_man;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.use_cases.ShoppingListManager;

import java.util.ArrayList;

public class ShoppingListDisplayActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_display);

        // TODO pass around the DeliveryMan
        Bundle extras = getIntent().getExtras();
        ArrayList<ShoppingList> shoppingLists = (ArrayList<ShoppingList>) extras.getSerializable("shopping_lists");

        ListView listView = findViewById(R.id.LV_deli_display);
        TextView totalTV = findViewById(R.id.deli_dis_total_top);

        ShoppingListManager slm = new ShoppingListManager();
        totalTV.setText("Total: $ "+ slm.calculateListsTotal(shoppingLists));

        ArrayList<String> slStrings = new ArrayList<>();
        for (ShoppingList shoppingList : shoppingLists) {
            ShoppingListManager shoppingListManager = new ShoppingListManager(shoppingList);
            slStrings.add(shoppingListManager.displayEntire());
        }

        // Set ListView adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, slStrings);
        listView.setAdapter(adapter);
    }
}