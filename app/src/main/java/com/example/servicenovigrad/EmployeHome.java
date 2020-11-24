package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    FirebaseUser userr;


    private FirebaseAuth auth;
    private DatabaseReference myref;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_home);

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
        Intent intent1= new Intent(EmployeHome.this, EmployeHome.class);
        switch (menuItem.getItemId()) {
            case R.id.nav_Home:

                startActivity(intent1);
                break;
            case R.id.nav_Request:
                intent1= new Intent(EmployeHome.this, EmployeRequestManager.class);
                startActivity(intent1);
                break;
            case R.id.nav_logOut:
                FirebaseAuth.getInstance().signOut();
                Intent AppStart = new Intent(getApplicationContext(), loginScreen.class);
                startActivity(AppStart);
                break;
            case R.id.nav_branch:
                intent1= new Intent(EmployeHome.this, EmployeBranchService.class);
                startActivity(intent1);
                break;
            case R.id.nav_horaire:
                intent1= new Intent(EmployeHome.this, employee_edit_working_hours.class);
                startActivity(intent1);
                break;
        }
        return true ;
    }

    }
