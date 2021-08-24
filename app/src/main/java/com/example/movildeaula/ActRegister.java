package com.example.movildeaula;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class ActRegister extends AppCompatActivity {
    private FirebaseAuth FirebaseAut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_register);
        //FirebaseAut.getInstance().createUserWithEmailAndPassword();
    }
}