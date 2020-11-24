package com.example.servicenovigrad;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestAdapter  extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private Context context;
    private com.google.firebase.database.FirebaseDatabase FirebaseDatabase;
    private ArrayList<RequestModel> addInfo = new ArrayList<>();
    public static ServiceModel currentService;

    public RequestAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item_list, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.clientName.setText("Client Name : " +addInfo.get(position).getName());
        holder.count.setText("Requete numero : " +(position+1));
        holder.serviceRequested.setText("Nom du Service : "+addInfo.get(position).getService());
        if(!addInfo.get(position).getStatue().equals("Pending")){
            holder.approve.setVisibility(View.INVISIBLE);
            holder.refuse.setVisibility(View.INVISIBLE);
            holder.statue.setText(addInfo.get(position).getStatue());
        }
        else{
            holder.statue.setText("Etat de la requete  :"+addInfo.get(position).getStatue());
        }
        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInfo.get(position).setStatue("Approved");
                notifyDataSetChanged();
            }
        });

        holder.refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInfo.get(position).setStatue("Refused");
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return addInfo.size();
    }

    public void setAddInfo(ArrayList<RequestModel> addInfo) {
        this.addInfo = addInfo;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView count, clientName, serviceRequested,statue;
        Button approve, refuse;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clientName = itemView.findViewById(R.id.nameSetup);
            count = itemView.findViewById(R.id.requestItemcount);
            serviceRequested = itemView.findViewById(R.id.serviceSetup);
            approve = itemView.findViewById(R.id.requestapprove);
            refuse = itemView.findViewById(R.id.requestRefuse);
            statue = itemView.findViewById(R.id.requestStatue);
        }
    }
}


