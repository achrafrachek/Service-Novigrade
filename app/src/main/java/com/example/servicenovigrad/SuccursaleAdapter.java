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

public class SuccursaleAdapter  extends RecyclerView.Adapter<SuccursaleAdapter.ViewHolder>{

    private Context context;
    private com.google.firebase.database.FirebaseDatabase FirebaseDatabase;
    private ArrayList<SuccursaleModel> addInfo;


    public SuccursaleAdapter(Context context,ArrayList<SuccursaleModel> addInfo) {
        this.context = context;
        this.addInfo = addInfo;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.succursale_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.adresse.setText("Adresse :  "+ addInfo.get(position).getAdresse());
        holder.zone.setText("Zone :  "+ addInfo.get(position).getZone());
        holder.horaire.setText("Horaire :  "+ addInfo.get(position).getHoraire());
        holder.service.setText("Service  :  "+ addInfo.get(position).getService());
        holder.count.setText("Succursale Numero   :  "+ position+1);

        holder.evaluer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustumDialogClass cdd=new CustumDialogClass(context);
                cdd.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return addInfo.size();
    }

    public void setAddInfo(ArrayList<SuccursaleModel> addInfo) {
        this.addInfo = addInfo;
        notifyDataSetChanged();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView zone,adresse,horaire,service,count;
        private Button evaluer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            zone = itemView.findViewById(R.id.succZone);
            adresse = itemView.findViewById(R.id.succAdresse);
            horaire = itemView.findViewById(R.id.succHoraire);
            service = itemView.findViewById(R.id.succService);
            count = itemView.findViewById(R.id.succCount);
            evaluer = itemView.findViewById(R.id.evaluer);

        }
    }
}

