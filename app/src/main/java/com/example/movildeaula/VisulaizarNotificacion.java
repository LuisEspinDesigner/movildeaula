package com.example.movildeaula;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class VisulaizarNotificacion extends AppCompatActivity {
    String image;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visulaizar_notificacion);
        image = getIntent().getExtras().getString("imagen");
        String base64Image = image.split(",")[1];
        String base=base64Image.replace("%2F","/").replace("%2B","+");
        byte[] decodedString = Base64.decode(base, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        img.setImageBitmap(decodedByte);
    }

    private void cargaImagen(String Img) {

    }
}