package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminServiceManager extends AppCompatActivity {
    public static int count;
    private RecyclerView addServiceRecycler;
    private com.google.firebase.database.FirebaseDatabase FirebaseDatabase;
    private Button ajouterSer;
    private TextView textCount;
    
    private  final ArrayList<String> serviceName = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_service_manager);

        inStart();

        addServiceRecycler = findViewById(R.id.addServiceRecy);
        ajouterSer = findViewById(R.id.addService);
        textCount = findViewById(R.id.textCount);






        ajouterSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminServiceManager.this, AdminAddService.class);
                startActivity(i);
                finish();
            }
        });


    }
    public void inStart(){
        FirebaseDatabase.getInstance().getReference().child("Service")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        count = 0;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            try {
                                HashMap h = (HashMap) snapshot.getValue();
                                String n = h.get("serviceName").toString();
                                serviceName.add(n);

                            } catch (Exception e) {

                            }
                            count++;
                        }
                        serviceRecViewAdapter serviceAdapter = new serviceRecViewAdapter(AdminServiceManager.this);
                        serviceAdapter.setAddInfo(serviceName);

                        addServiceRecycler.setAdapter(serviceAdapter);
                        addServiceRecycler.setLayoutManager(new LinearLayoutManager(AdminServiceManager.this));

                        textCount.setText(" Service Disponible : " + count);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
    }




}
