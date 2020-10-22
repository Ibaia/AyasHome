package com.dosdmtres.ayashome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;


public class Datos extends AppCompatActivity {


    private ImageView imageView;
    private TextView servicio;
    private TextView descripcion;
    private TextView precio;
    private Button reserva;
    private Toolbar toolbarMain;
    private ImageView imgPerfil;
    private TextView tvTitulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        final ImageView imageview = findViewById(R.id.imageView);
        final TextView servicio = findViewById(R.id.servicio);
        final TextView descripcion = findViewById(R.id.descripcion);
        final TextView precio = findViewById(R.id.precio);
        final Button reserva = findViewById(R.id.reserva);
        final Toolbar toolbarMain = findViewById(R.id.toolbarMain2);
        final ImageView imgPerfil = findViewById(R.id.imgPerfil);



    }
}