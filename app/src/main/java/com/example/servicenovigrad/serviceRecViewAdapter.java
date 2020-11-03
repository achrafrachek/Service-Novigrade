package com.example.servicenovigrad;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class serviceRecViewAdapter  extends RecyclerView.Adapter<serviceRecViewAdapter.ViewHolder>{

    private Context context;
    private com.google.firebase.database.FirebaseDatabase FirebaseDatabase;
    private ArrayList<String> addInfo = new ArrayList<>();
    public static ServiceModel currentService;

    public serviceRecViewAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_card, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtInfoName.setText(addInfo.get(position));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Service").child(addInfo.get(position))
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                 currentService = dataSnapshot.getValue(ServiceModel.class);
                                 Intent i  = new Intent(context, Admin_Modifier_Service.class);
                                 context.startActivity(i);


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }


            });
            }
        });
        }

    @Override
    public int getItemCount() {
        return addInfo.size();
    }

    public void setAddInfo(ArrayList<String> addInfo) {
        this.addInfo = addInfo;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtInfoName;
        private RelativeLayout parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtInfoName = itemView.findViewById(R.id.cardServiceName);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}


