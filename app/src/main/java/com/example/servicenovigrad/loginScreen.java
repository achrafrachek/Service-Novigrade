package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class loginScreen extends AppCompatActivity {
    EditText userLogin, passLogin;
    Button login, registerBut;


    private FirebaseAuth auth;
    private DatabaseReference myref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        userLogin = (EditText) findViewById(R.id.editUsername);
        passLogin = (EditText) findViewById(R.id.editPassword);
        login = (Button) findViewById(R.id.butLogin);
        registerBut = (Button) findViewById(R.id.butSwitch);


        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textUser = userLogin.getText().toString();
                String textPass = passLogin.getText().toString();
                if(TextUtils.isEmpty(textUser)  || TextUtils.isEmpty(textPass)){
                    Toast.makeText(loginScreen.this, "Please complete all Fields", Toast.LENGTH_LONG).show();
                }
                else {
                    auth.signInWithEmailAndPassword(textUser, textPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(loginScreen.this, Home.class));
                                finish();
                            }
                            else{
                                Toast.makeText(loginScreen.this, "Invalid Email or Password", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        registerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginScreen.this, Register.class));
                finish();
            }
        });




    }
}