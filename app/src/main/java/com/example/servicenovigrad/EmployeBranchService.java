package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeBranchService extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<ServiceModel> serviceArrayList;
    private TextView textCount;
    private Button addService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_branch_service);
        textCount = findViewById(R.id.homebranchCount);
        addService = findViewById(R.id.addService);

        recyclerView = findViewById(R.id.homeEmployeRecycler);
        serviceArrayList = new ArrayList<>();
        inStart();




        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EmployeBranchService.this, EmployeBranchAddService.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void inStart(){
        FirebaseDatabase.getInstance().getReference().child("BranchService")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            try {
                                ServiceModel current = snapshot.getValue(ServiceModel.class);

                                serviceArrayList.add(current);

                            } catch (Exception e) {

                            }

                        }
                        BranchServiceAdapter branchServiceAdapter = new BranchServiceAdapter(EmployeBranchService.this);
                        branchServiceAdapter.setAddInfo(serviceArrayList);

                        recyclerView.setAdapter(branchServiceAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(EmployeBranchService.this));
                        textCount.setText(" Service Disponible " + serviceArrayList.size());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
    }
}