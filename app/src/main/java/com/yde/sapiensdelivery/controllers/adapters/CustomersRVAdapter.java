package com.yde.sapiensdelivery.controllers.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.entities.ShoppingList;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomersRVAdapter extends RecyclerView.Adapter<CustomersRVAdapter.ViewHolder> {

    private final ArrayList<String> customers;

    private final OnCustomerClickListener onCustomerClickListener;

    public CustomersRVAdapter(ArrayList<String> customers, HashMap<String, ArrayList<ShoppingList>> shoppingLists, OnCustomerClickListener onCustomerClickListener) {
        // Use this to get additional info about that username
        this.customers = customers;

        this.onCustomerClickListener = onCustomerClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView distanceTV;
        TextView addressTV;
        TextView nameTV;
        Button detailsBT;
        Button acceptBT;

        OnCustomerClickListener onCustomerClickListener;

        public ViewHolder(View view, OnCustomerClickListener onCustomerClickListener) {
            super(view);
            distanceTV = view.findViewById(R.id.distance_rv_TV);
            addressTV = view.findViewById(R.id.address_rv_TV);
            nameTV = view.findViewById(R.id.customer_name_rv_TV);
            detailsBT = view.findViewById(R.id.detail_BT);
            acceptBT = view.findViewById(R.id.accept_cus_BT);

            this.onCustomerClickListener = onCustomerClickListener;
        }
    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_layout, parent, false);

        return new ViewHolder(itemView, onCustomerClickListener);
    }

    @SuppressLint("SetTextI18n")
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position){
        holder.nameTV.setText(customers.get(position));
        // TODO put in the address and distance
        holder.addressTV.setText("ADDRESS");
        holder.distanceTV.setText("DISTANCE");

        holder.detailsBT.setOnClickListener(v -> onCustomerClickListener.onDetailsClick(position));


        holder.acceptBT.setOnClickListener(v -> onCustomerClickListener.onAcceptClick(position));
    }

    @Override
    public int getItemCount(){
        return customers.size();
    }

    public interface OnCustomerClickListener{
        void onDetailsClick(int position);

        void onAcceptClick(int position);
    }
}
