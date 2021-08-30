package com.example.movildeaula;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movildeaula.Adapter.ListDispositivos;
import com.example.movildeaula.Adapter.ListanotifyDivice;
import com.example.movildeaula.Models.Divice;
import com.example.movildeaula.Models.Notify;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VisualizacionDispositivo extends AppCompatActivity {
    List<Notify> listNotify = new ArrayList<>();
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacion_dispositivo);
        inicializarFirebase();
        recyclerView = findViewById(R.id.RvNotify);
        nombre = findViewById(R.id.NombreDispositivo);
        String DiviceNombre=getIntent().getExtras().getString("NombreDivice");
        nombre.setText("Nombre: "+DiviceNombre);
        ListaNotify();
    }

    private void ListaNotify() {
        databaseReference.child("Notify").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listNotify.clear();
                for (DataSnapshot objShaptshot : snapshot.getChildren()) {
                    Notify notif = objShaptshot.getValue(Notify.class);
                    listNotify.add(notif);
                }
                ejecuta();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ejecuta() {

        ListanotifyDivice listNoti = new ListanotifyDivice(listNotify, this);
        RecyclerView recyclerView = findViewById(R.id.RvNotify);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listNoti);
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

}