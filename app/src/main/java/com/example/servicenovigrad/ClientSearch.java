package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientSearch extends AppCompatActivity {
    private Spinner adresse,horaire,service,filtre;
    private RecyclerView recherche;
    private Button rechercher;
    private TextView adresseT,horaireT,serviceT;
    ArrayList<String> listAdresse, listHoraire,listeService,listFiltre;
    ArrayList<SuccursaleModel> list;
    String selected;
    SuccursaleAdapter serviceAdapter;


    private  int  type = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_search);
        adresse = findViewById(R.id.spinnerAdresse);
        horaire = findViewById(R.id.spinnerHoraire);
        service = findViewById(R.id.spinnerService);
        recherche = findViewById(R.id.rechercheRecycler);
        rechercher = findViewById(R.id.rechercher);
        filtre = findViewById(R.id.choix);
        adresseT = findViewById(R.id.adresse);
        horaireT = findViewById(R.id.horaire);
        serviceT = findViewById(R.id.service);

        list = new ArrayList<>();

        listFiltre = new ArrayList<>();
        listFiltre.add("Adresse");
        listFiltre.add("Horaire");
        listFiltre.add("Service");

        listAdresse = new ArrayList<>();
        listAdresse.add("Sandy Hill");
        listAdresse.add("Rideau");
        listAdresse.add("DownTown");
        listAdresse.add("Hurdman");

        listHoraire = new ArrayList<>();
        listHoraire.add("Mon-Fri : 9h-17h");
        listHoraire.add("week-end : 9h-12h");
        listHoraire.add("holidays");

        listeService = new ArrayList<>();
        listeService.add("id");
        listeService.add("passport");
        listeService.add("driving license");

        ArrayAdapter<String> Horaireadapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                listHoraire
        );
        horaire.setAdapter(Horaireadapter);

        ArrayAdapter<String> Adresseadapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                listAdresse
        );
        adresse.setAdapter(Adresseadapter);

        ArrayAdapter<String> Serviceadapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                listeService
        );
        service.setAdapter(Serviceadapter);


        ArrayAdapter<String> Filtreadapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                listFiltre
        );
        filtre.setAdapter(Filtreadapter);

        filtre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(listFiltre.get(i).equals("Adresse")){
                    horaire.setVisibility(View.INVISIBLE);
                    horaireT.setVisibility(View.INVISIBLE);
                    service.setVisibility(View.INVISIBLE);
                    serviceT.setVisibility(View.INVISIBLE);
                    adresseT.setVisibility(View.VISIBLE);
                    adresse.setVisibility(View.VISIBLE);
                    type = 0;
                }
                else if(listFiltre.get(i).equals("Horaire")){
                    horaire.setVisibility(View.VISIBLE);
                    horaireT.setVisibility(View.VISIBLE);
                    service.setVisibility(View.INVISIBLE);
                    serviceT.setVisibility(View.INVISIBLE);
                    adresseT.setVisibility(View.INVISIBLE);
                    adresse.setVisibility(View.INVISIBLE);
                    type=1;
                }else{
                    horaire.setVisibility(View.INVISIBLE);
                    horaireT.setVisibility(View.INVISIBLE);
                    service.setVisibility(View.VISIBLE);
                    serviceT.setVisibility(View.VISIBLE);
                    adresseT.setVisibility(View.INVISIBLE);
                    adresse.setVisibility(View.INVISIBLE);
                    type=2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adresse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = listAdresse.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        horaire.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = listHoraire.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = listeService.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search(type,selected);
            }
        });

        getSucc();

    }

    private void search(int type_int, final String s) {
        FirebaseDatabase.getInstance().getReference().child("Succursale")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        list.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            SuccursaleModel succursaleModel = snapshot.getValue(SuccursaleModel.class);
                            if(type ==0 && succursaleModel.getZone().equals(s)){
                                list.add(succursaleModel);
                            }
                            else if(type ==1 && succursaleModel.getHoraire().equals(s)){
                                list.add(succursaleModel);
                            }
                            else if(type ==2 && succursaleModel.getService().equals(s)){
                                list.add(succursaleModel);
                            }

                        }
                        serviceAdapter.setAddInfo(list);





                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
    }

    public void getSucc(){

        FirebaseDatabase.getInstance().getReference().child("Succursale")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            SuccursaleModel succursaleModel = snapshot.getValue(SuccursaleModel.class);

                            list.add(succursaleModel);


                        }
                        serviceAdapter = new SuccursaleAdapter(ClientSearch.this,list);
                        recherche.setAdapter(serviceAdapter);
                        recherche.setLayoutManager(new LinearLayoutManager(ClientSearch.this));




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });

    }
}