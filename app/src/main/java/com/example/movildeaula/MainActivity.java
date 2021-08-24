package com.example.movildeaula;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btnregistrar;
    Button btnlogin;
    Button btnFacebook;
    TextInputEditText usuario, contrasenia;
    private FirebaseAuth FirebaseAut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = (TextInputEditText) findViewById(R.id.textEmail);
        contrasenia = findViewById(R.id.textPass);
        btnregistrar = findViewById(R.id.btnRegistrar);

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActRegister.class);
                startActivityForResult(intent, 0);
            }
        });


        btnFacebook = findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // callbackManager = CallbackManager.Factory.create();
                //btnFacebook.setReadPermissions("email", "public_profile");
            }
        });
        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usuario.getText().toString();
                String pass = contrasenia.getText().toString();
              /*  if (!user.equals("") && !pass.equals("")) {
                    FirebaseAut.getInstance().signInWithEmailAndPassword(user, pass)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("Mesaje", "createUserWithEmail:success");
                                        Intent intent = new Intent(v.getContext(), principa.class);
                                        startActivityForResult(intent, 0);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("Mensaje no", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }

                                }
                            });

                } else {
                    Toast.makeText(MainActivity.this, "Hay Campos vacios", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }


}