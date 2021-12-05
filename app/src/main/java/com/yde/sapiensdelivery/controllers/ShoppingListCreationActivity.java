package com.yde.sapiensdelivery.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.Edits;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.controllers.adapters.CommodityListAdapter;
import com.yde.sapiensdelivery.controllers.adapters.OutletListAdapter;
import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.Outlet;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.use_cases.OutletManager;
import com.yde.sapiensdelivery.use_cases.ShoppingListManager;

import java.util.ArrayList;

public class ShoppingListCreationActivity extends AppCompatActivity implements OutletListAdapter.OnOutletClickListener {

    private TextView topPriceTV;
    private OutletListAdapter outletListAdapter;
    private TextView outletSpinnerTV;
    private Dialog dialog;
    private Button createOrderBT;

    // Stores a list of ShoppingListManagers that keeps
    // track of which Outlet belongs to which ShoppingList.
    private ArrayList<ShoppingListManager> shoppingListManagers;
    private ArrayList<Outlet> outlets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shopping_list);

        // Get what's passed through Intent
        useIntent();

        // Initialize the environment
        setup();
        setSpinner();

        // Display ShoppingListManagers through Adapter
        displayManagers();
    }

    /**
     * User selects a Outlet stored in through a Spinner
     */
    @SuppressLint("SetTextI18n")
    private void newShoppingList(Outlet outlet, int position) {
        // Add an empty ShoppingList at the end and pass it to be edited

        ShoppingListManager shoppingListManager = new ShoppingListManager(outlet);
        shoppingListManagers.add(shoppingListManager);
        outletListAdapter.setOutletListManager(shoppingListManagers);

        Intent i = new Intent(ShoppingListCreationActivity.this, EditShoppingListActivity.class);
        i.putExtra("sl_managers", shoppingListManagers);
        i.putExtra("sl_position", position);
        startActivity(i);
    }

    private void useIntent(){
        Bundle extras = getIntent().getExtras();

        // Get the ShoppingList needed to be displayed
        if (extras.getSerializable("sl_managers") != null) {

        }
        ArrayList<ShoppingListManager> shoppingListsManagers =
                (ArrayList<ShoppingListManager>) extras.getSerializable("sl_managers");
        this.shoppingListManagers = shoppingListsManagers;

        // TODO get a List of Outlets from the Database and remove the hardcoded ones below
        ArrayList<Outlet> outlets = new ArrayList<>();

        ArrayList<Commodity> list = new ArrayList<>();
        list.add(new Commodity("Apple", 2.5, 1) );
        list.add(new Commodity("Banana", 3, 1) );
        Outlet walmart = new Outlet("Walmart", "NO ADDRESS", list);

        ArrayList<Commodity> house = new ArrayList<>();
        house.add(new Commodity("TV", 1000, 1) );
        house.add(new Commodity("Couch", 200, 1) );
        Outlet friend = new Outlet("Friend's House", "NO ADDRESS", house);

        outlets.add(friend);
        outlets.add(walmart);
        // TODO remove above

        this.outlets = outlets;
    }

    private void setup() {
        topPriceTV = findViewById(R.id.total_outlet_top_TV);
        RecyclerView outletRV = findViewById(R.id.outlets_RV);
        outletSpinnerTV = findViewById(R.id.comm_spinner_TV);
        createOrderBT = findViewById(R.id.done_editing_BT);


        outletRV.setLayoutManager(new LinearLayoutManager(this));

        outletListAdapter = new OutletListAdapter(this, shoppingListManagers, this);
        outletRV.setAdapter(outletListAdapter);

        TextView outletTV = findViewById(R.id.outlet_name_top_TV);
        outletTV.setText(shoppingListManagers.size());

        createOrderBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO pass to create order and pass around the Customer
            }
        });
    }

    private void setSpinner() {
        outletSpinnerTV.setOnClickListener(v -> {
            // Set dialog
            dialog = new Dialog(ShoppingListCreationActivity.this);
            dialog.setContentView(R.layout.dialog_searchable_spinner);
            dialog.getWindow().setLayout(650,800);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            EditText editText = dialog.findViewById(R.id.outlet_search_ET);
            ListView listView = dialog.findViewById(R.id.outlet_select_LV);

            // Set an ArrayAdapter to display Outlets
            ArrayList<Outlet> listToDisplay = this.outlets;
            ArrayAdapter<Outlet> arrayAdapter =
                    new ArrayAdapter<>(ShoppingListCreationActivity.this,
                            android.R.layout.simple_list_item_1, listToDisplay);

            listView.setAdapter(arrayAdapter);

            // Filter and search Outlets based on input
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

            listView.setOnItemClickListener((parent, view, position, id) -> {
                // When item is selected from list
                // Set selected item on TextView

                // Use OutletManager to the name of the Outlet at the position
                OutletManager outletManager = new OutletManager(this.outlets.get(position));
                String outletName = outletManager.getOutletName();
                outletSpinnerTV.setText(outletName);

                // Get the Outlet from this position
                Outlet outlet = outlets.get(position);
                newShoppingList(outlet, position);

                // Dismiss dialog
                dialog.dismiss();
            });
        });
    }

    public void displayManagers() {
        outletListAdapter.initializeOutletListManager(shoppingListManagers);
    }

    // Models all the on clicks that could happen to the Adapter
    @Override
    public void onEditClick(int position) {
        // Pass shoppingLists and the index that it needs to be edited on and Intent to be edited
        Intent i = new Intent(ShoppingListCreationActivity.this, EditShoppingListActivity.class);
        i.putExtra("sl_managers", shoppingListManagers);
        i.putExtra("sl_position", position);
        startActivity(i);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onRemoveClick(int position) {
        // TODO add a confirmation prompt
        // Update total price
        ShoppingListManager slManager = new ShoppingListManager();
        topPriceTV.setText("Total: $ "+ slManager.calculateManagersTotal(shoppingListManagers));
    }
}