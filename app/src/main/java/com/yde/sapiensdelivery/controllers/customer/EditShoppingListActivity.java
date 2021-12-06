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
import com.yde.sapiensdelivery.controllers.adapters.CommodityListAdapter;
import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.use_cases.CustomerManager;
import com.yde.sapiensdelivery.use_cases.OutletManager;
import com.yde.sapiensdelivery.use_cases.ShoppingListManager;

import java.util.ArrayList;

public class EditShoppingListActivity extends AppCompatActivity implements CommodityListAdapter.OnCommClickListener {

    private TextView topPriceTV;
    private CommodityListAdapter commodityListAdapter;
    private TextView commSpinnerTV;
    private Dialog dialog;
    private Button doneEditingBT;

    // Since this Activity only needs one Outlet and ShoppingList so initialize with instances
    // of both managers
    private ShoppingListManager shoppingListManager;
    private OutletManager outletManager;
    private CustomerManager customerManager;

    // Info that need to be passed back
    private ArrayList<ShoppingListManager> shoppingListManagers;
    private int positionOfThis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shopping_list);

        // Get what's passed through Intent
        useIntent();

        // Initialize the environment
        setLayout();
        setOnClick();
        setSpinner();
    }

    /**
     * User selects a Commodity stored in the Outlet through a Spinner
     */
    @SuppressLint("SetTextI18n")
    private void selectCommodity(int position) {
        Commodity commodity = outletManager.newCommodityAtIndex(position);
        shoppingListManager.setCommodity(commodity);

        // Display the Commodity selected through Adapter
        commodityListAdapter.setCommList(shoppingListManager);

        // Update the total Price TextView
        topPriceTV.setText("Total: $ " + shoppingListManager.getTotalPrice());
    }

    private void useIntent() {
        Bundle extras = getIntent().getExtras();
        customerManager = new CustomerManager((Customer) extras.getSerializable("CUSTOMER"));

        ArrayList<ShoppingListManager> shoppingListManagers =
                (ArrayList<ShoppingListManager>) extras.getSerializable("sl_managers");
        this.positionOfThis = (int) extras.get("sl_position");
        this.shoppingListManagers = shoppingListManagers;
        this.shoppingListManager = shoppingListManagers.get(this.positionOfThis);
        this.outletManager = new OutletManager(this.shoppingListManager.getOutlet());
    }

    @SuppressLint("SetTextI18n")
    private void setLayout() {
        topPriceTV = findViewById(R.id.total_price_top_TV);
        RecyclerView commRV = findViewById(R.id.comm_RV);
        commSpinnerTV = findViewById(R.id.comm_spinner_TV);
        doneEditingBT = findViewById(R.id.done_editing_BT);


        commRV.setLayoutManager(new LinearLayoutManager(this));

        commodityListAdapter = new CommodityListAdapter(shoppingListManager, this);
        commRV.setAdapter(commodityListAdapter);

        TextView outletTV = findViewById(R.id.outlet_name_top_TV);
        outletTV.setText(shoppingListManager.getOutletName());
        topPriceTV.setText("Total: $ " + shoppingListManager.getTotalPrice());
    }

    private void setOnClick() {
        doneEditingBT.setOnClickListener(v -> {
            Intent i = new Intent(EditShoppingListActivity.this, ShoppingListCreationActivity.class);

            // Update the ShoppingListManager that's edited through this Activity before passing back
            shoppingListManagers.set(positionOfThis, shoppingListManager);

            i.putExtra("sl_managers", shoppingListManagers);
            customerManager.passValue(i);
            startActivity(i);
        });
    }

    private void setSpinner() {
        commSpinnerTV.setOnClickListener(v -> {
            // Set dialog
            dialog = new Dialog(EditShoppingListActivity.this);
            dialog.setContentView(R.layout.dialog_searchable_spinner);
            dialog.getWindow().setLayout(650, 800);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            EditText editText = dialog.findViewById(R.id.comm_search_ET);
            ListView listView = dialog.findViewById(R.id.comm_select_LV);

            // Set an ArrayAdapter to display Commodity catalog
            ArrayList<Commodity> listToDisplay = outletManager.getCommList();
            ArrayAdapter<Commodity> arrayAdapter =
                    new ArrayAdapter<>(EditShoppingListActivity.this,
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

            listView.setOnItemClickListener((parent, view, position, id) -> {
                // When item is selected from list
                // Set selected item on TextView

                // Use OutletManager to get the selected Commodity's name then display it
                String commName = outletManager.getCommNameAtIndex(position);
                commSpinnerTV.setText(commName);

                // Display selected Commodity in RecyclerView
                selectCommodity(position);

                // Dismiss dialog
                dialog.dismiss();
            });
        });
    }

    // Models all the on clicks that could happen to the Adapter
    @SuppressLint("SetTextI18n")
    @Override
    public void onAdd1Click(int position) {
        //Update the total price TextView
        topPriceTV.setText("Total: $ " + shoppingListManager.getTotalPrice());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onRemove1Click(int position) {
        //Update the total price TextView
        topPriceTV.setText("Total: $ " + shoppingListManager.getTotalPrice());
    }
}