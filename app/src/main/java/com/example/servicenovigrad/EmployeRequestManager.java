package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeRequestManager extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView count;
    private ArrayList<RequestModel> requestList ;

    FirebaseAuth auth;
    DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_request_manager);
        createRequest();

        recyclerView = findViewById(R.id.requestRecycler);
        count = findViewById(R.id.Managercount);


        requestList = new ArrayList<>();
        getRequestList();

    }

    private void getRequestList() {
        FirebaseDatabase.getInstance().getReference().child("Request")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            try {
                                RequestModel current = snapshot.getValue(RequestModel.class);
                                requestList.add(current);

                            } catch (Exception e) {

                            }

                        }
                        RequestAdapter requestAdapter = new RequestAdapter(EmployeRequestManager.this);
                        requestAdapter.setAddInfo(requestList);

                        recyclerView.setAdapter(requestAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(EmployeRequestManager.this));

                        count.setText(" Nombre de requete " + requestList.size());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
    }

    public void createRequest(){
        myref = FirebaseDatabase.getInstance().getReference("Request").child("Request2");

        RequestModel request = new RequestModel("Cleint 2", "PassPort", "Pending");


        myref.setValue(request).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                }
                else{
                }
            }
        });

    }
}