package com.example.servicenovigrad;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class BranchServiceAdapter  extends RecyclerView.Adapter<BranchServiceAdapter.ViewHolder>{
    private DatabaseReference myref;
    private Context context;
    private com.google.firebase.database.FirebaseDatabase FirebaseDatabase;
    private ArrayList<ServiceModel> addInfo = new ArrayList<>();
    public static ServiceModel currentService;

    public BranchServiceAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.branch_service_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.serviceName.setText("Nom du service  : " +addInfo.get(position).getServiceName());
        holder.servicePrice.setText("Prix du service  : " +addInfo.get(position).getServicePrice());
        holder.count.setText("Service Numero : "+(position+1));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInfo.remove(position);
                myref = com.google.firebase.database.FirebaseDatabase.getInstance().getReference().child("BranchService").child(addInfo.get(position).getServiceName());
                myref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return addInfo.size();
    }

    public void setAddInfo(ArrayList<ServiceModel> addInfo) {
        this.addInfo = addInfo;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView count, serviceName,servicePrice;
        private Button  delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            count = itemView.findViewById(R.id.addCount);
            serviceName = itemView.findViewById(R.id.addName);
            servicePrice = itemView.findViewById(R.id.addPrice);
            delete = itemView.findViewById(R.id.delteBranch);
        }
    }
}






