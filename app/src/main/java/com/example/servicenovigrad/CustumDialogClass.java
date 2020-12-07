package com.example.servicenovigrad;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustumDialogClass extends Dialog implements
        View.OnClickListener {

    FirebaseUser user;
    ArrayList<Integer> progre;
    DatabaseReference myRef;
    User cUser;
    public Context c;
    public Dialog d;
    public ImageView vsad, sad, meh,good,awesome;
    private int ratting;

    public CustumDialogClass(Context a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_progres);
        vsad = findViewById(R.id.rating0);
        sad = findViewById(R.id.rating1);
        meh = findViewById(R.id.rating2);
        good = findViewById(R.id.rating3);
        awesome = findViewById(R.id.rating4);
        vsad.setOnClickListener(this);
        sad.setOnClickListener(this);
        meh.setOnClickListener(this);
        good.setOnClickListener(this);
        awesome.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rating0:
                ratting = 1;

                break;
            case R.id.rating1:
                ratting = 2;
                break;
            case R.id.rating2:
                ratting = 3;
                break;
            case R.id.rating3:
                ratting = 4;
                break;
            case R.id.rating4:
                ratting = 5;
                break;

        }



        dismiss();
    }



}
