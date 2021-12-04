package com.yde.sapiensdelivery.controllers.delivery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.yde.sapiensdelivery.R;
import com.yde.sapiensdelivery.controllers.delivery.ChooseCustomerActivity;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.use_cases.DeliveryManManager;

import java.util.List;

public class DeliveryManRVAdapter extends RecyclerView.Adapter<DeliveryManRVAdapter.ViewHolder> {

    private List<DeliveryMan> deliveryManList;
    private ChooseCustomerActivity cdActivity;

    public DeliveryManRVAdapter(ChooseCustomerActivity activity) {
        this.cdActivity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_man_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        // Get the position of the DeliveryMan from the UI
        DeliveryMan deliveryMan = deliveryManList.get(position);
        // Use DeliveryManManager to get the DeliverMan's name
        DeliveryManManager dm = new DeliveryManManager(deliveryMan);

        holder.dlName.setText(dm.getName());
    }

    public int getItemCount(){
        return deliveryManList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        Button acceptBT;
        TextView dlName;

        ViewHolder(View view){
            super(view);
            this.acceptBT = view.findViewById(R.id.accept_button);
            this.dlName = view.findViewById(R.id.dl_name_tv);
        }
    }
}
