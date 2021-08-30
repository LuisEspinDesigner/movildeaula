package com.example.movildeaula;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

public class principa extends AppCompatActivity {
    Toolbar toolb;
    DrawerLayout drawerLayout;
    Button AddDevice, security,btnState;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principa);
        toolb = (Toolbar) this.findViewById(R.id.toolbar);
        btnState = findViewById(R.id.textOn);
        String usuario = getIntent().getExtras().getString("usuario");
        inicializarFirebase();
        databaseReference.child("Security").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> td = (HashMap<String, Object>) snapshot.getValue();
                if (td==null)return;
                res=td.get("securit").toString();
                System.out.println(res);
                if (res.equals("True")){
                    btnState.setBackgroundColor(Color.BLUE);
                    btnState.setText("ON");
                }else {
                    btnState.setBackgroundColor(Color.RED);
                    btnState.setText("OFF");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (res.equals("True")){
                    HashMap map = new HashMap();
                    map.put("securit", "False");
                    databaseReference.child("Security").updateChildren(map);
                    btnState.setBackgroundColor(Color.RED);
                    btnState.setText("ON");
                }else {
                    HashMap map = new HashMap();
                    map.put("securit", "True");
                    databaseReference.child("Security").updateChildren(map);
                    btnState.setBackgroundColor(Color.BLUE);
                    btnState.setText("ON");
                }
            }
        });
        AddDevice = findViewById(R.id.btnAddDivice);
        setSupportActionBar(toolb);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        security = findViewById(R.id.texthome);
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
                Intent security = new Intent(v.getContext(), ListaDispositivos.class)
                        .putExtra("usuario",usuario);
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