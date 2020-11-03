package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminAddService extends AppCompatActivity {
    private DatabaseReference myref;
    private RecyclerView addInfoRecycler, addDocRecycler;
    private EditText serviceName, servicePrice, addInfo, addDoc;
    private Button ajouterInfo, ajouterDoc, submit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_service);

        addInfoRecycler = findViewById(R.id.addInfoRecycler);
        addDocRecycler = findViewById(R.id.addDocRecycler);
        serviceName = findViewById(R.id.serviceName);

        servicePrice = findViewById(R.id.servicePrice);
        addInfo = findViewById(R.id.addInfoTxt);
        addDoc = findViewById(R.id.addDocTxt);
        ajouterInfo = findViewById(R.id.buttonAjouterInfo);
        ajouterDoc = findViewById(R.id.buttonAjouterDoc);
        submit = findViewById(R.id.submitAdd);


        final ArrayList<String> infoList = new ArrayList<>();
        final ArrayList<String> docList = new ArrayList<>();

        final InfoRecViewAdapter infoAdapter = new InfoRecViewAdapter(AdminAddService.this);
        infoAdapter.setAddInfo(infoList);

        final InfoRecViewAdapter docAdapter = new InfoRecViewAdapter(AdminAddService.this);
        docAdapter.setAddInfo(docList);

        addInfoRecycler.setAdapter(infoAdapter);
        addInfoRecycler.setLayoutManager(new GridLayoutManager(this,3));

        addDocRecycler.setAdapter(docAdapter);
        addDocRecycler.setLayoutManager(new GridLayoutManager(this,3));



        ajouterInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoList.add(addInfo.getText().toString());
                addInfo.setText("");
                infoAdapter.notifyDataSetChanged();
            }
        });

        ajouterDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                docList.add(addDoc.getText().toString());
                addDoc.setText("");
                docAdapter.notifyDataSetChanged();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_serviceName = serviceName.getText().toString();
                String txt_servicePrice = servicePrice.getText().toString();

                addService(txt_serviceName,txt_servicePrice,infoList,docList);
            }
        });



    }

    public void addService(String serviceName, String servicePrice, ArrayList<String> infoList, ArrayList<String> docList){
        ServiceModel service = new ServiceModel(serviceName, servicePrice, infoList, docList);
        myref = FirebaseDatabase.getInstance().getReference("Service").child(serviceName);

        myref.setValue(service).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    AdminServiceManager.count++;
                    Intent i = new Intent(AdminAddService.this, AdminServiceManager.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(AdminAddService.this,"error", Toast.LENGTH_LONG);
                }
            }
        });


    }
}