package com.yde.sapiensdelivery.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.controllers.adapters.CommodityListAdapter;
import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.use_cases.ShoppingListManager;

public class EditShoppingListActivity extends AppCompatActivity {

    private RecyclerView commRV;
    private CommodityListAdapter commodityListAdapter;

    private ShoppingListManager shoppingListManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shopping_list);

        // Get values that's been passed from the previous Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // TODO change this outletName to an Outlet OBJ
            String outletName = extras.getString("outlet");

            ShoppingList shoppingList = (ShoppingList) extras.getSerializable("shopping_list");
            shoppingListManager = new ShoppingListManager(shoppingList, outletName);
        }

        commRV = findViewById(R.id.comm_RV);
        commRV.setLayoutManager(new LinearLayoutManager(this));
        commodityListAdapter = new CommodityListAdapter(this, shoppingListManager);
        commRV.setAdapter(commodityListAdapter);


    }

    /**
     * User selects a Commodity stored in the Outlet through a Spinner
     *
     * @return
     */
    private Commodity selectCommodity(String outletName) {
        // TODO implement the Spinner and pass in an Outlet OBJ
        return
    }
}