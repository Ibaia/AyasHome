package com.example.ayashome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    FrameLayout listaServicios;
    private Toolbar mainToolbar;
    private ImageView fotoPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaServicios = findViewById(R.id.frameLayoutList);

        //Se añaden los fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fotoPerfil = findViewById(R.id.imgPerfil);

        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentPerfil = new Intent(MainActivity.this, ActivityPerfil.class);
                startActivity(intentPerfil);
            }
        });

    }

}