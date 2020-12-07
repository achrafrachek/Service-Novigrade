package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FirebaseUser userr;
    DatabaseReference myref;

    private RecyclerView recyclerView;
    private ArrayList<ServiceModel> requestList;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        requestList = new ArrayList<>();
        recyclerView = findViewById(R.id.clientRequestRecycler);


        userr = FirebaseAuth.getInstance().getCurrentUser();
        myref = FirebaseDatabase.getInstance().getReference("MyUsers").child(userr.getUid());

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userA = snapshot.getValue(User.class);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
        inStart();

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView =(NavigationView)findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        userName = findViewById(R.id.menuUsername);

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_Home);






    }

    public void createSucc(){
        myref = FirebaseDatabase.getInstance().getReference("Succursale").child("Succursale1");

       SuccursaleModel succursaleModel = new SuccursaleModel("Sandy Hill","374 Friel st","Mon-Fri : 9h-17h","id");


        myref.setValue(succursaleModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                }
                else{
                }
            }
        });

        myref = FirebaseDatabase.getInstance().getReference("Succursale").child("Succursale2");

        SuccursaleModel succursaleModel1 = new SuccursaleModel("Rideau","377 Friel st","holidays","passport");


        myref.setValue(succursaleModel1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                }
                else{
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

                                requestList.add(current);

                            } catch (Exception e) {

                            }

                        }
                        HomeRecycler listServiceAdapter = new HomeRecycler( Home.this);
                        listServiceAdapter.setAddInfo(requestList);

                        recyclerView.setAdapter(listServiceAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
    }

    //Allow opening and closing the navigator bar
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent1= new Intent(Home.this, Home.class);
        switch (menuItem.getItemId()) {
            case R.id.nav_Home:

                startActivity(intent1);
                break;
            case R.id.nav_Rechercher:
                intent1= new Intent(Home.this, ClientSearch.class);
                startActivity(intent1);
                break;
            case R.id.nav_logOut:
                FirebaseAuth.getInstance().signOut();
                Intent AppStart = new Intent(getApplicationContext(), loginScreen.class);
                startActivity(AppStart);
                break;

        }
        return true ;
    }
}