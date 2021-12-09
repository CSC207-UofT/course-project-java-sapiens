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

import java.util.ArrayList;

/**
 * An Adapter that connects a list of ShoppingList model to display on the UI
 * This Adapter doesn't depend on the Activity that's using it through dependency inversion using
 * an Interface.
 */
public class OutletListAdapter extends RecyclerView.Adapter<OutletListAdapter.ViewHolder>{
    private ArrayList<ShoppingListManager> shoppingListManagers;
    private final OutletListAdapter.OnOutletClickListener onOutletClickListener;

    public OutletListAdapter(ArrayList<ShoppingListManager> shoppingListManagers,
                             OnOutletClickListener onOutletClickListener){
        this.shoppingListManagers = shoppingListManagers;
        this.onOutletClickListener = onOutletClickListener;
    }

    /**
     * A class that holds the View for each individual index of the RecyclerView
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        Button removeBT;
        Button editBT;
        TextView outletName;
        TextView outletAddress;
        TextView commList;

        OnOutletClickListener onOutletClickListener;

        public ViewHolder(View view, OnOutletClickListener onOutletClickListener) {
            super(view);
            this.editBT = view.findViewById(R.id.edit_outlet_BT);
            this.removeBT = view.findViewById(R.id.remove_outlet_BT);
            this.outletName = view.findViewById(R.id.creation_outlet_name_TV);
            this.outletAddress = view.findViewById(R.id.outlet_address_TV);
            this.commList = view.findViewById(R.id.comm_list_TV);

            this.onOutletClickListener = onOutletClickListener;
        }
    }

    @NonNull
    @Override
    public OutletListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View commView = LayoutInflater.from((parent.getContext())).
                inflate(R.layout.shoppinglist_list_layout,parent,false);

        return new OutletListAdapter.ViewHolder(commView, onOutletClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OutletListAdapter.ViewHolder holder, int position) {
        // Binds the ViewHolder to data to reflect the item at the given position.
        // Use a ShoppingListManager to display info about ShoppingList at the position
        ShoppingListManager shoppingListManager = shoppingListManagers.get(position);

        holder.outletName.setText(shoppingListManager.getOutletName());
        holder.outletAddress.setText(shoppingListManager.getOutletAddress());
        holder.commList.setText(shoppingListManager.toString());

        // When buttons are clicked, modify data and update the ViewHolder
        holder.editBT.setOnClickListener(v -> {
            holder.getAdapterPosition();
            onOutletClickListener.onEditClick(position);
        });

        holder.removeBT.setOnClickListener(v -> {
            holder.getAdapterPosition();

            this.shoppingListManagers.remove(position);
            notifyItemRemoved(position);
            onOutletClickListener.onRemoveClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return this.shoppingListManagers.size();
    }

    public void setOutletListManager(ArrayList<ShoppingListManager> shoppingListManagers) {
        this.shoppingListManagers = shoppingListManagers;

        // Notify that a new item has been inserted
        notifyItemInserted(shoppingListManagers.size());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void initializeOutletListManager(ArrayList<ShoppingListManager> shoppingListManagers) {
        this.shoppingListManagers = shoppingListManagers;

        // Notify that a new item has been inserted
        notifyDataSetChanged();
    }

    /**
     * An interface that sends on click information back to the Activity
     */
    public interface OnOutletClickListener{
        void onEditClick(int position);

        void onRemoveClick(int position);
    }
}
