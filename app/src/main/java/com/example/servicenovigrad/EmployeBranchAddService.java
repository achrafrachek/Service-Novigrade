package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeBranchAddService extends AppCompatActivity {
    private RecyclerView toAdd, listService;
    private ArrayList<ServiceModel> toAddList, choiceLise;
    private Button submit;
    private DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_branch_add_service);
        toAdd = findViewById(R.id.branchToAddServices);
        listService = findViewById(R.id.allServices);
        submit = findViewById(R.id.submitBranch);

        toAddList = new ArrayList<>();
        choiceLise = new ArrayList<>();



        inStart();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(ServiceModel i : toAddList){
                    addService(i);
                }
            }
        });


    }
    public void inStart(){
        FirebaseDatabase.getInstance().getReference().child("Service")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            try {
                                ServiceModel current = snapshot.getValue(ServiceModel.class);

                                choiceLise.add(current);

                            } catch (Exception e) {

                            }

                        }
                        ListServiceAdapter listServiceAdapter = new ListServiceAdapter( toAdd,EmployeBranchAddService.this, toAddList);
                        listServiceAdapter.setAddInfo(choiceLise);

                        listService.setAdapter(listServiceAdapter);
                        listService.setLayoutManager(new LinearLayoutManager(EmployeBranchAddService.this));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
    }
    public void addService(ServiceModel service){

        myref = FirebaseDatabase.getInstance().getReference("BranchService").child(service.getServiceName());

        myref.setValue(service).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    AdminServiceManager.count++;
                    Intent i = new Intent(EmployeBranchAddService.this, EmployeBranchService.class);
                    startActivity(i);
                    finish();

                }
                else{
                    Toast.makeText(EmployeBranchAddService.this,"error", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}