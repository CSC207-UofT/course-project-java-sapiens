package com.yde.sapiensdelivery.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.controllers.adapters.CommodityListAdapter;
import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.Outlet;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.use_cases.OutletManager;
import com.yde.sapiensdelivery.use_cases.ShoppingListManager;

import java.util.ArrayList;

public class EditShoppingListActivity extends AppCompatActivity {

    private RecyclerView commRV;
    private CommodityListAdapter commodityListAdapter;
    private TextView commSpinnerTV;
    private Dialog dialog;

    private ShoppingListManager shoppingListManager;
    private OutletManager outletManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shopping_list);

        // Get values that's been passed from the previous Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Outlet outlet = (Outlet) extras.getSerializable("outlet");

            ShoppingList shoppingList = (ShoppingList) extras.getSerializable("shopping_list");
            shoppingListManager = new ShoppingListManager(shoppingList, outlet);
        }
        else {
            // TODO get outlet from previous Activity and remove this else statement
            ArrayList<Commodity> list = new ArrayList<>();
            list.add(new Commodity("Apple", 2.5, 1) );
            list.add(new Commodity("Banana", 3, 1) );
            Outlet outlet = new Outlet("Walmart", "NO ADDRESS", list);

            ShoppingList shoppingList = new ShoppingList(outlet.getName(), outlet.getAddress());
            shoppingListManager = new ShoppingListManager(shoppingList, outlet);
            outletManager = new OutletManager(outlet);
        }

        // Initialize the environment
        setup();
        setSpinner();
        // Display Commodity

    }

    /**
     * User selects a Commodity stored in the Outlet through a Spinner
     *
     */
    private void selectCommodity(int position) {
        Commodity commodity = outletManager.newCommodityAtIndex(position);
        shoppingListManager.setCommodity(commodity);

        // Display the Commodity selected
        commodityListAdapter.setCommList(shoppingListManager, commodityListAdapter.getItemCount());
    }

    private void setup() {
        commRV = findViewById(R.id.comm_RV);
        commSpinnerTV = findViewById(R.id.comm_spinner_TV);

        commRV.setLayoutManager(new LinearLayoutManager(this));
        commodityListAdapter = new CommodityListAdapter(this, shoppingListManager);
        commRV.setAdapter(commodityListAdapter);

        TextView outletTV = findViewById(R.id.outlet_name_top_TV);
        outletTV.setText(shoppingListManager.getOutletName());
    }

    private void setSpinner() {
        commSpinnerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set dialog
                dialog = new Dialog(EditShoppingListActivity.this);
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(650,800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText = dialog.findViewById(R.id.comm_search_ET);
                ListView listView = dialog.findViewById(R.id.comm_select_LV);

                // Set an ArrayAdapter to display Commodity catalog
                ArrayList<Commodity> listToDisplay = outletManager.getCommList();
                ArrayAdapter<Commodity> arrayAdapter =
                        new ArrayAdapter<Commodity>(EditShoppingListActivity.this,
                        android.R.layout.simple_list_item_1, listToDisplay);

                listView.setAdapter(arrayAdapter);

                // Filter and search Commodities based on input
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        // Filter ArrayList
                        arrayAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // When item is selected from list
                        // Set selected item on TextView

                        // Use OutletManager to get the selected Commodity's name then display it
                        String commName = outletManager.getCommNameAtIndex(position);
                        commSpinnerTV.setText(commName);

                        // Display selected Commodity in RecyclerView
                        selectCommodity(position);

                        // Dismiss dialog
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}