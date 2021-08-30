package com.example.movildeaula;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movildeaula.Adapter.ListDispositivos;
import com.example.movildeaula.Models.Divice;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaDispositivos extends AppCompatActivity {
    List<Divice> listdispositivos = new ArrayList<>();
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dispositivos);
        usuario = getIntent().getExtras().getString("usuario");
        inicializarFirebase();
        recyclerView = findViewById(R.id.RvListDivice);
        Listadivice();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    private void Listadivice() {
        databaseReference.child("User/" + usuario + "/Divice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listdispositivos.clear();
                for (DataSnapshot objShaptshot : snapshot.getChildren()) {
                    Divice divi = objShaptshot.getValue(Divice.class);
                    listdispositivos.add(divi);
                }
                ejecuta();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ejecuta() {
        ListDispositivos listDivi = new ListDispositivos(listdispositivos, this);
        RecyclerView recyclerView = findViewById(R.id.RvListDivice);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listDivi);
    }
}