package com.yde.sapiensdelivery.controllers.delivery_man;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.gateways.OrderGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.use_cases.DeliveryManManager;
import com.yde.sapiensdelivery.use_cases.OrderManager;
import com.yde.sapiensdelivery.use_cases.ShoppingListManager;

import java.util.ArrayList;

public class OrderStatusDeliveryManActivity extends AppCompatActivity {

    OrderManager orderManager = new OrderManager();
    String customerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status_delivery_man);

        TextView nameTV = findViewById(R.id.deli_os_cus_name_tv);
        TextView phoneNumTV = findViewById(R.id.deli_os_cus_phone_number_tv);
        TextView addressTV = findViewById(R.id.deli_os_cus_address);
        TextView totalTV = findViewById(R.id.deli_os_total_tv);
        Button completeOrder = findViewById(R.id.complete_order_bt);
        Button otwOrderBT = findViewById(R.id.order_OTW_bt);
        ListView shoppingLists = findViewById(R.id.delivery_o_status_lv);

        DeliveryManManager dm = new DeliveryManManager((DeliveryMan) getIntent().getSerializableExtra("DELIVERYMAN"));
        OrderGateway orderGateway = new OrderGateway();

        orderGateway.getByDeliveryman(dm.getUsername(), new OnDataReadListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess() {
                ArrayList<Object> doubleData =  (ArrayList<Object>) getSavedObject();
                orderManager = new OrderManager((Order) doubleData.get(0));
                customerName = (String) doubleData.get(1);

                String cusName = orderManager.getCustomerName();
                String address = orderManager.getCustomerAddress();
                String phoneNum = orderManager.getCustomerPhoneNum();

                // Get the ShoppingLists and convert to String
                ArrayList<ShoppingList> shopLists = orderManager.getShoppingLists();

                ArrayList<String> slStrings = new ArrayList<>();
                for (ShoppingList shoppingList : shopLists) {
                    ShoppingListManager shoppingListManager = new ShoppingListManager(shoppingList);
                    slStrings.add(shoppingListManager.displayEntire());
                }

                // Set ListView adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        OrderStatusDeliveryManActivity.this, android.R.layout.simple_list_item_1, slStrings);
                shoppingLists.setAdapter(adapter);

                nameTV.setText(cusName);
                addressTV.setText(address);
                phoneNumTV.setText(phoneNum);
                totalTV.setText("Total: $ " + orderManager.getTotalPrice());
            }

            @Override
            public void onFailure() {
                Log.i("HAHA", "HAHA");
            }
        });

        completeOrder.setOnClickListener(view -> {
            orderManager.updateStatusComp(customerName);

            Intent intent = new Intent( OrderStatusDeliveryManActivity.this,
                                                     DeliveryRatingActivity.class);
            dm.passValue(intent);
            startActivity(intent);
        });

        otwOrderBT.setOnClickListener(v -> {
            orderManager.updateStatusOTW(customerName);

            Intent intent = new Intent( OrderStatusDeliveryManActivity.this,
                    DeliveryManActivity.class);
            dm.passValue(intent);
            startActivity(intent);
        });

    }
}