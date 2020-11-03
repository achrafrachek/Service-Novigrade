package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Admin_Modifier_Service extends AppCompatActivity {
    private EditText modifierNom, modifierPrice, modifierInfo, modifierDoc;
    private DatabaseReference myref;
    private RecyclerView ModifierInfoRecycler, ModifierDocRecycler;
    private Button ajouterInfo, ajouterDoc, submitchange, delete;
    private String userChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__modifier__service);




        ModifierInfoRecycler = findViewById(R.id.ModifierInfoRecycler);
        ModifierDocRecycler = findViewById(R.id.ModifierDocRecycler);
        modifierNom = findViewById(R.id.modifierserviceName);

        modifierPrice = findViewById(R.id.modifierservicePrice);
        modifierInfo = findViewById(R.id.ModifierInfoTxt);
        modifierDoc = findViewById(R.id.ModifierDocTxt);
        ajouterInfo = findViewById(R.id.buttonAjouterInfo);
        ajouterDoc = findViewById(R.id.buttonAjouterDoc);
        submitchange = findViewById(R.id.submitchange);
        delete= findViewById(R.id.delete);


        modifierNom.setText(serviceRecViewAdapter.currentService.getServiceName());
        userChild = modifierNom.getText().toString();
        modifierPrice.setText(serviceRecViewAdapter.currentService.getServicePrice());



        final ArrayList<String> ModifieinfoList = serviceRecViewAdapter.currentService.getInfoList();
        final ArrayList<String> ModifiedocList = serviceRecViewAdapter.currentService.getDocList();


        final InfoRecViewAdapter modifieinfoAdapter = new InfoRecViewAdapter(Admin_Modifier_Service.this);
        modifieinfoAdapter.setAddInfo(ModifieinfoList);

        final InfoRecViewAdapter modifiedocAdapter = new InfoRecViewAdapter(Admin_Modifier_Service.this);
        modifiedocAdapter.setAddInfo(ModifiedocList);

        ModifierInfoRecycler.setAdapter(modifieinfoAdapter);
        ModifierInfoRecycler.setLayoutManager(new GridLayoutManager(this,3));

        ModifierDocRecycler.setAdapter(modifiedocAdapter);
        ModifierDocRecycler.setLayoutManager(new GridLayoutManager(this,3));



        ajouterInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModifieinfoList.add(modifierInfo.getText().toString());
                modifierInfo.setText("");
                modifieinfoAdapter.notifyDataSetChanged();
            }
        });


        ajouterDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModifiedocList.add(modifierDoc.getText().toString());
                modifierDoc.setText("");
                modifiedocAdapter.notifyDataSetChanged();
            }
        });


        submitchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_serviceName = modifierNom.getText().toString();
                String txt_servicePrice = modifierPrice.getText().toString();


                serviceRecViewAdapter.currentService.setServiceName(txt_serviceName);
                serviceRecViewAdapter.currentService.setServicePrice(txt_servicePrice);
                serviceRecViewAdapter.currentService.setInfoList(ModifieinfoList);
                serviceRecViewAdapter.currentService.setDocList(ModifiedocList);


                if (TextUtils.isEmpty(txt_serviceName) || TextUtils.isEmpty(txt_servicePrice) || ModifiedocList.size() == 0 || ModifieinfoList.size() == 0) {
                    Toast.makeText(Admin_Modifier_Service.this, "Please complete all required Fields", Toast.LENGTH_LONG).show();
                } else if (!InputTest.numberOrNot(txt_servicePrice)) {
                    Toast.makeText(Admin_Modifier_Service.this, "Please enter a number as a price", Toast.LENGTH_LONG).show();
                } else {
                    myref = FirebaseDatabase.getInstance().getReference().child("Service").child(userChild);

                    myref.setValue(serviceRecViewAdapter.currentService).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent i = new Intent(Admin_Modifier_Service.this, AdminServiceManager.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(Admin_Modifier_Service.this, "Erreur lors du changement des donnes", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
            });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myref = FirebaseDatabase.getInstance().getReference().child("Service").child(userChild);
                myref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(Admin_Modifier_Service.this, AdminServiceManager.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(Admin_Modifier_Service.this, "Error while deleting account", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }



}

