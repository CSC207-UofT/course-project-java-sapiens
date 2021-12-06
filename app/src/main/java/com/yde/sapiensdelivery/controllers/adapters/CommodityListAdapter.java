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
import com.yde.sapiensdelivery.use_cases.ShoppingListManager;

/**
 * An Adapter that connects ShoppingList model to display on the UI.
 * This Adapter doesn't depend on the Activity that's using it through dependency inversion using
 * an Interface.
 */
public class CommodityListAdapter extends RecyclerView.Adapter<CommodityListAdapter.ViewHolder> {

    private ShoppingListManager slManager;
    private final OnCommClickListener onCommClickListener;

    public CommodityListAdapter (ShoppingListManager slManager, OnCommClickListener onCommClickListener){
        this.slManager = slManager;
        this.onCommClickListener = onCommClickListener;
    }

    /**
     * A class that holds the View for each individual index of the RecyclerView
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements CustomersRVAdapter.OnCustomerClickListener {
        Button add1BT;
        Button remove1BT;
        TextView commName;
        TextView commPrice;
        TextView commQuantity;

        OnCommClickListener onCommClickListener;

        public ViewHolder(View view, OnCommClickListener onCommClickListener) {
            super(view);
            this.add1BT = view.findViewById(R.id.add_comm_BT);
            this.remove1BT = view.findViewById(R.id.remove_comm_BT);
            this.commName = view.findViewById(R.id.comm_name_TV);
            this.commPrice = view.findViewById(R.id.comm_price_TV);
            this.commQuantity = view.findViewById(R.id.comm_quant_TV);

            this.onCommClickListener = onCommClickListener;
        }

        @Override
        public void onDetailsClick(int position) {

        }

        @Override
        public void onAcceptClick(int position) {

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View commView = LayoutInflater.from((parent.getContext())).
                inflate(R.layout.commodity_list,parent,false);

        return new ViewHolder(commView, onCommClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CommodityListAdapter.ViewHolder holder, int position) {
        // Binds the ViewHolder to data to reflect the item at the given position.
        holder.commName.setText(slManager.getCommodityName(position));
        holder.commPrice.setText("$ " + slManager.getCommodityTotalPrice(position));
        holder.commQuantity.setText("x " + slManager.getCommodityQuantity(position));

        // When buttons are clicked, modify data and update the ViewHolder
        holder.remove1BT.setOnClickListener(v -> {
            int position1 = holder.getAdapterPosition();

            // Update RecyclerView
            if (slManager.getCommodityQuantity(position1) > 1) {
                // If Commodity has more than 1,
                // then we only need to update View
                slManager.remove1Commodity(position1);
                notifyItemChanged(position1);

                // Update Activity
                onCommClickListener.onRemove1Click(position1);
            }
            else {
                // Else when the Commodity count hits zero,
                // it needs to be deleted from View as well
                slManager.remove1Commodity(position1);
                notifyItemRemoved(position1);

                // Update Activity
                onCommClickListener.onRemove1Click(position1);
            }

        });

        holder.add1BT.setOnClickListener(v -> {
            int position12 = holder.getAdapterPosition();
            slManager.add1Commodity(position12);
            notifyItemChanged(position12);

            onCommClickListener.onAdd1Click(position12);
        });
    }

    @Override
    public int getItemCount() {
        return slManager.getSize();
    }

    public void setCommList(ShoppingListManager slManager) {
        this.slManager = slManager;

        // Notify that a new item has been inserted
        notifyItemInserted(slManager.getSize());
    }

    /**
     * An interface that sends on click information back to the Activity
     */
    public interface OnCommClickListener{
        void onAdd1Click(int position);

        void onRemove1Click(int position);
    }
}
