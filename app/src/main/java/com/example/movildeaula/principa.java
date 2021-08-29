package com.example.movildeaula;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.movildeaula.Models.Divice;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class principa extends AppCompatActivity {
    Toolbar toolb;
    DrawerLayout drawerLayout;
    Button AddDevice, security;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principa);
        toolb = (Toolbar) this.findViewById(R.id.toolbar);
        AddDevice = findViewById(R.id.btnAddDivice);
        setSupportActionBar(toolb);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        security = findViewById(R.id.texthome);
        inicializarFirebase();
        databaseReference.child("Security").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot objShaptshot : snapshot.getChildren()) {
                    String res= (String) objShaptshot.child("securit").getValue();
                    System.out.println(objShaptshot);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        AddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Divice = new Intent(v.getContext(), DiviceAdd.class);
                startActivity(Divice);
            }
        });
        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent security = new Intent(v.getContext(), ListaDispositivos.class);
                startActivity(security);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout = (DrawerLayout) findViewById(R.id.dwLayoutprincipal);
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}