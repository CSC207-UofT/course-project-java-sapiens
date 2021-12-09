package com.yde.sapiensdelivery.controllers.customer;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.controllers.adapters.OutletListAdapter;
import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.Outlet;
import com.yde.sapiensdelivery.gateways.ShoppingListGateway;
import com.yde.sapiensdelivery.use_cases.CustomerManager;
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
    CustomerManager customerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_creation);
        customerManager = new CustomerManager((Customer) getIntent().getSerializableExtra("CUSTOMER"));

        // Get what's passed through Intent
        useIntent();

        // Get Outlets from the Database
        getOutletsFromDB();

        // Initialize the environment
        setLayout();
        setOnClick();
        setSpinner();

        // Display ShoppingListManagers through Adapter
        displayManagers();
    }

    /**
     * User selects a Outlet stored in through a Spinner
     */
    @SuppressLint("SetTextI18n")
    private void newShoppingList(Outlet outlet) {
        // Add an empty ShoppingList at the end and pass it to be edited

        ShoppingListManager shoppingListManager = new ShoppingListManager(outlet);

        shoppingListManagers.add(shoppingListManager);
        outletListAdapter.setOutletListManager(shoppingListManagers);

        Intent i = new Intent(ShoppingListCreationActivity.this, EditShoppingListActivity.class);
        i.putExtra("sl_managers", shoppingListManagers);
        i.putExtra("sl_position", shoppingListManagers.size() - 1);
        customerManager.passValue(i);
        startActivity(i);
    }

    private void useIntent() {
        Bundle extras = getIntent().getExtras();

        // Get the ShoppingLists needed to be displayed
        if (extras != null) {
            if (extras.getSerializable("sl_managers") != null) {
                this.shoppingListManagers =
                        (ArrayList<ShoppingListManager>) extras.getSerializable("sl_managers");
            } else {
                // Create a new ShoppingList
                this.shoppingListManagers = new ArrayList<>();
            }
        } else {
            this.shoppingListManagers = new ArrayList<>();
        }
    }

    private void getOutletsFromDB() {
        ArrayList<Outlet> outlets = new ArrayList<>();

        ArrayList<Commodity> list = new ArrayList<>();
        list.add(new Commodity("Apple", 2.5, 1));
        list.add(new Commodity("Banana", 3, 1));
        Outlet walmart = new Outlet("Walmart", "Dufferin Mall Supercentre, 900 Dufferin St, Toronto, ON M6H 4A9", list);

        ArrayList<Commodity> house = new ArrayList<>();
        house.add(new Commodity("TV", 1000, 1));
        house.add(new Commodity("Couch", 200, 1));
        Outlet friend = new Outlet("Friend's House", "253 College St, Toronto, ON M5T 1R5", house);

        outlets.add(friend);
        outlets.add(walmart);

        this.outlets = outlets;
    }

    @SuppressLint("SetTextI18n")
    private void setLayout() {
        topPriceTV = findViewById(R.id.total_outlet_top_TV);
        RecyclerView outletRV = findViewById(R.id.outlets_RV);
        outletSpinnerTV = findViewById(R.id.outlet_spinner_TV);
        createOrderBT = findViewById(R.id.create_order_BT);


        outletRV.setLayoutManager(new LinearLayoutManager(this));

        // new Adapter with a custom onClickListener Interface
        outletListAdapter = new OutletListAdapter(shoppingListManagers, this);
        outletRV.setAdapter(outletListAdapter);

        TextView outletTV = findViewById(R.id.shopL_top_TV);
        outletTV.setText("Your Order");
    }

    private void setOnClick() {
        createOrderBT.setOnClickListener(v -> {

            ShoppingListGateway shoppingListGateway = new ShoppingListGateway();
            shoppingListGateway.save(customerManager.getUsername(), ShoppingListManager.getShoppingLists(shoppingListManagers));

            Intent intent = new Intent( ShoppingListCreationActivity.this, OrderStatusActivity.class);
            customerManager.passValue(intent);
            startActivity(intent);
        });
    }

    @SuppressLint("SetTextI18n")
    private void setSpinner() {
        outletSpinnerTV.setOnClickListener(v -> {
            // Set dialog
            dialog = new Dialog(ShoppingListCreationActivity.this);
            dialog.setContentView(R.layout.outlet_dialog_searchable_spinner);
            dialog.getWindow().setLayout(650, 800);
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
                String outletAddress = outletManager.getOutletAddress();
                outletSpinnerTV.setText(outletName + " at " + outletAddress);

                // Get the Outlet from this position
                Outlet outlet = outlets.get(position);
                newShoppingList(outlet);

                // Dismiss dialog
                dialog.dismiss();
            });
        });
    }

    @SuppressLint("SetTextI18n")
    public void displayManagers() {
        outletListAdapter.initializeOutletListManager(shoppingListManagers);

        ShoppingListManager slManager = new ShoppingListManager();
        topPriceTV.setText("Total: $ " + slManager.calculateManagersTotal(shoppingListManagers));
    }

    // Models all the on clicks that could happen to the Adapter
    @Override
    public void onEditClick(int position) {
        // Pass shoppingLists and the index that it needs to be edited on and Intent to be edited
        Intent i = new Intent(ShoppingListCreationActivity.this, EditShoppingListActivity.class);
        i.putExtra("sl_managers", shoppingListManagers);
        i.putExtra("sl_position", position);
        customerManager.passValue(i);
        startActivity(i);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onRemoveClick(int position) {
        // Update total price
        ShoppingListManager slManager = new ShoppingListManager();
        topPriceTV.setText("Total: $ " + slManager.calculateManagersTotal(shoppingListManagers));
    }
}