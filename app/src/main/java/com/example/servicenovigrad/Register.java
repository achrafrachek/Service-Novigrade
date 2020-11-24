package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Register extends AppCompatActivity {
    private EditText username, password, email;
    private Button createAcc;
    private Spinner userType;
    private String userTypeSelected;

    FirebaseAuth auth;
    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        username = findViewById(R.id.regiUsername);
        password = findViewById(R.id.regiPassword);
        email = findViewById(R.id.regiEmail);
        createAcc = findViewById(R.id.butCreate);
        userType = findViewById(R.id.SpinnerUserT);

        final ArrayList<String> userT = new ArrayList<>();
        userT.add("Customer");
        userT.add("Employe");

        ArrayAdapter<String> UserTadapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                userT
        );


        userType.setAdapter(UserTadapter);

        userType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                userTypeSelected = userT.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //Firebase
        auth = FirebaseAuth.getInstance();

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_text = username.getText().toString();
                String password_text = password.getText().toString();
                String email_text = email.getText().toString();

                if(TextUtils.isEmpty(username_text) || TextUtils.isEmpty(email_text) || TextUtils.isEmpty(password_text) ){
                    Toast.makeText(Register.this, "Please complete all required Fields", Toast.LENGTH_LONG).show();
                }
                else if (!InputTest.verifyEmail(email_text)){
                    Toast.makeText(Register.this, "Invalid Email", Toast.LENGTH_LONG).show();
                }
                else if(!InputTest.verifyPassword(password_text)){
                    Toast.makeText(Register.this, "Invalid Password, Requiired : 8caracters,1Capital Letter, 1number", Toast.LENGTH_LONG).show();
                }
                else {
                    createAccount(username_text, password_text, email_text);
                }
            }
        });



    }

    public void createAccount(final String username, String password, String email){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser users = auth.getCurrentUser();
                    String userId = users.getUid();
                    User createUser;
                    myref = FirebaseDatabase.getInstance().getReference("MyUsers").child(userId);
                    if(!userTypeSelected.equals("Employe")) {


                        createUser = new User(userId.toString(),userTypeSelected,username);

                    }else{

                        createUser = (employeeAccount) new employeeAccount(userId.toString(),userTypeSelected,username);
                    }


                    myref.setValue(createUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent i = new Intent(Register.this, loginScreen.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                Toast.makeText(Register.this,"error", Toast.LENGTH_LONG);
                            }
                        }
                    });


                }
            }
        });

    }
}