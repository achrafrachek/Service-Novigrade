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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginScreen extends AppCompatActivity {
    EditText userLogin, passLogin;
    Button login, registerBut;

    FirebaseUser userr;


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
                                userr = FirebaseAuth.getInstance().getCurrentUser();
                                myref = FirebaseDatabase.getInstance().getReference("MyUsers").child(userr.getUid());

                                myref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        User userA = snapshot.getValue(User.class);

                                        if(userA.getUsertype().equals("Admin")){
                                            startActivity(new Intent(loginScreen.this, AdminServiceManager.class));
                                            finish();
                                        }
                                        else if (userA.getUsertype().equals("Employe")){

                                            Intent i = new Intent(loginScreen.this, EmployeHome.class);
                                            startActivity(i);
                                            finish();
                                        }
                                        else{
                                            Intent i = new Intent(loginScreen.this, Home.class);
                                            startActivity(i);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

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