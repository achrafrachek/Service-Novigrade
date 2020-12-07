package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class requestService extends AppCompatActivity {
    private TextView serviceCount,pictureUpload,idUpload,id;
    private EditText fName,adress,phone;
    private Button selectPicture,selectID,submit;
    Intent i;
    DatabaseReference myref;
    Uri pdfUri;
    int pressed =0;
    int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_service);
        i = getIntent();
        final String service = i.getStringExtra("service");

        serviceCount = findViewById(R.id.textviewCount);
        pictureUpload = findViewById(R.id.pictureUploaded);
        idUpload = findViewById(R.id.cardUploaded);
        fName = findViewById(R.id.editFullName);
        adress = findViewById(R.id.editAdress);
        phone = findViewById(R.id.editPhone);
        selectPicture = findViewById(R.id.buttSelected);
        selectID = findViewById(R.id.buttSelectedcard);
        submit = findViewById(R.id.requestSubmit);
        id = findViewById(R.id.idCard);


        myref = FirebaseDatabase.getInstance().getReference("Service").child(service);

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               ServiceModel serviceModel = snapshot.getValue(ServiceModel.class);
                serviceCount.setText(serviceCount.getText().toString()+ serviceModel.getServiceName());
                if(service.equals("ID Card")){
                    idUpload.setVisibility(View.INVISIBLE);
                    id.setVisibility(View.INVISIBLE);
                    selectID.setVisibility(View.INVISIBLE);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        selectPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ContextCompat.checkSelfPermission(requestService.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    pressed = 1;
                    selectPdf();
                }
                else{
                    ActivityCompat.requestPermissions(requestService.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},  9);
                }
            }
        });

        selectID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ContextCompat.checkSelfPermission(requestService.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    pressed = 2;
                    selectPdf();
                }
                else{
                    ActivityCompat.requestPermissions(requestService.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},  9);
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myref = FirebaseDatabase.getInstance().getReference("Request");
                myref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {

                            count++;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                String fname_string = fName.getText().toString();
                String adress_string = adress.getText().toString();
                String phone_string = phone.getText().toString();
                if(TextUtils.isEmpty(fname_string) || TextUtils.isEmpty(adress_string) || TextUtils.isEmpty(phone_string) ){
                    Toast.makeText(requestService.this, "Please complete all required Fields", Toast.LENGTH_LONG).show();
                }
                else if(!InputTest.numberOrNot(phone_string)){
                    Toast.makeText(requestService.this, "Please Enter a valid phone number", Toast.LENGTH_LONG).show();
                }
                else {
                    RequestModel requestModel = new RequestModel(fname_string,service,"Pending");
                    myref.child("Request"+count+1);
                    myref.setValue(requestModel);
                    startActivity(new Intent(requestService.this,Home.class));
                    finish();
                }

            }
        });







    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if( requestCode ==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPdf();
        }
        else{
            Toast.makeText(requestService.this,"Please provide permission..",Toast.LENGTH_LONG).show();

        }
    }

    private void selectPdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);

    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==86 && requestCode ==RESULT_OK && data!= null){
            pdfUri = data.getData();
            if(pressed ==1) {
                pictureUpload.setText(data.getData().getLastPathSegment());
            }
            else{
                idUpload.setText(data.getData().getLastPathSegment());
            }
        }
        else{
            Toast.makeText(requestService.this,"Please select a file...",Toast.LENGTH_LONG).show();
        }
    }
}