package com.yde.sapiensdelivery.controllers.delivery_man;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.controllers.customer.OrderCompletionActivity;
import com.yde.sapiensdelivery.controllers.customer.OrderStatusActivity;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.gateways.OrderGateway;
import com.yde.sapiensdelivery.gateways.database.OnDataReadListener;
import com.yde.sapiensdelivery.use_cases.CustomerManager;
import com.yde.sapiensdelivery.use_cases.DeliveryManManager;
import com.yde.sapiensdelivery.use_cases.OrderManager;

public class OrderStatusDeliveryManActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status_delivery_man);

        TextView title = findViewById(R.id.titleOSD);
        TextView cContact = findViewById(R.id.ccontact);
        TextView oStatus = findViewById(R.id.ostatus);
        Button completeOrder = findViewById(R.id.dcompleteOrder);

        DeliveryManManager dm = new DeliveryManManager((DeliveryMan)
                getIntent().getSerializableExtra("DELIVERYMAN"));

        OrderGateway orderGateway = new OrderGateway();
        orderGateway.get(dm.getName(), new OnDataReadListener() {
            @Override
            public void onSuccess() {
                OrderManager orderManager = new OrderManager((Order) getSavedObject());

                String orderName = orderManager.getName();
                String contact = orderManager.getCustomerContact();
                String status = orderManager.getStatus().toString();

                title.setText(orderName);
                cContact.setText(contact);
                oStatus.setText(status);

            }

            @Override
            public void onFailure() {
            }
        });

//        completeOrder.setOnClickListener(view -> {
//            Intent intent = new Intent( OrderStatusActivity.this, OrderCompletionActivity.class);
//            startActivity(intent);
//        });

//        ProgressBar progressBar = findViewById(R.id.progressBar);
//        Button completeOrder = findViewById(R.id.button);
//        TextView orderName = findViewById(R.id.order_name);
//        TextView statusOrder = findViewById(R.id.status_order);
//        TextView contact = findViewById(R.id.contact);

//        CustomerManager customerManager = new CustomerManager((Customer)
//                getIntent().getSerializableExtra("CUSTOMER"));
//
//        OrderGateway orderGateway = new OrderGateway();
//        orderGateway.get(customerManager.getName(), new OnDataReadListener() {
//
//            @Override
//            public void onSuccess() {
//                OrderManager orderManager = new OrderManager((Order) getSavedObject());
//
//                if(orderManager.getStatus() == Order.OrderStatus.COMP){
//                    progressBar.setVisibility(View.INVISIBLE);
//                    completeOrder.setVisibility(View.VISIBLE);
//                }
//                else if(orderManager.getStatus() == Order.OrderStatus.REC){
//                    String name = orderManager.getName();
//                    orderName.setText(name);
//                    String contactInfo = orderManager.getContact();
//                    contact.setText(contactInfo);
//                    String statusInfo = orderManager.getStatus().toString();
//                    statusOrder.setText(statusInfo);
//                }
//            }
//
//            @Override
//            public void onFailure() {
//            }
//        });

//        completeOrder.setOnClickListener(view -> {
//            Intent intent = new Intent( OrderStatusActivity.this, OrderCompletionActivity.class);
//            startActivity(intent);
//        });
    }
}