package com.example.ayashome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    FrameLayout listaServicios;
    private Toolbar MainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaServicios = findViewById(R.id.frameLayoutList);

        final Toolbar MainToolbar = findViewById(R.id.toolbarMain);
        MainToolbar.setTitle("Aya's Home");


    }
}