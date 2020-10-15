package com.example.ayashome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.ayashome.adapter.MainRecyclerAdapter;
import com.example.ayashome.model.ItemsServicios;
import com.example.ayashome.model.Servicios;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FrameLayout listaServicios;
    private Toolbar mainToolbar;
    private ImageView fotoPerfil;

    RecyclerView rvVertical;
    MainRecyclerAdapter mainRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fotoPerfil = findViewById(R.id.imgPerfil);

        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentPerfil = new Intent(MainActivity.this, ActivityPerfil.class);
                startActivity(intentPerfil);
            }
        });

        // estupidos datos de prueba

        List<ItemsServicios> peluqueriaServicios = new ArrayList<>();
        peluqueriaServicios.add(new ItemsServicios(1, R.drawable.pelu, "Corte"));
        peluqueriaServicios.add(new ItemsServicios(1, R.drawable.pelu, "Mechas"));
        peluqueriaServicios.add(new ItemsServicios(1, R.drawable.pelu, "Marcados"));
        peluqueriaServicios.add(new ItemsServicios(1, R.drawable.pelu, "Trenzados"));
        peluqueriaServicios.add(new ItemsServicios(1, R.drawable.pelu, "Planchas"));
        peluqueriaServicios.add(new ItemsServicios(1, R.drawable.pelu, "Color"));
        peluqueriaServicios.add(new ItemsServicios(1, R.drawable.pelu, "Coloración"));
        peluqueriaServicios.add(new ItemsServicios(1, R.drawable.pelu, "Decoloraciones"));


        List<ItemsServicios> esteticaServicios = new ArrayList<>();
        esteticaServicios.add(new ItemsServicios(1, R.drawable.este, "Facial"));
        esteticaServicios.add(new ItemsServicios(1, R.drawable.este, "Manicura"));
        esteticaServicios.add(new ItemsServicios(1, R.drawable.este, "Depilación"));

        List<ItemsServicios> yogaServicios = new ArrayList<>();
        yogaServicios.add(new ItemsServicios(1, R.drawable.yoga, "Yoga"));

        List<ItemsServicios> masajeServicios = new ArrayList<>();
        masajeServicios.add(new ItemsServicios(1, R.drawable.masaje, "Masajes"));

        List<ItemsServicios> alojamientoServicios = new ArrayList<>();
        alojamientoServicios.add(new ItemsServicios(1, R.drawable.doscamas, "Dos Camas"));
        alojamientoServicios.add(new ItemsServicios(1, R.drawable.unacama, "Una Cama"));

        List<ItemsServicios> comidaServicios = new ArrayList<>();
        comidaServicios.add(new ItemsServicios(1, R.drawable.comida, "Menu 1"));
        comidaServicios.add(new ItemsServicios(1, R.drawable.comida, "Menu 2"));
        comidaServicios.add(new ItemsServicios(1, R.drawable.comida, "Menu 3"));
        comidaServicios.add(new ItemsServicios(1, R.drawable.comida, "Menu 4"));
        comidaServicios.add(new ItemsServicios(1, R.drawable.comida, "Menu 5"));
        comidaServicios.add(new ItemsServicios(1, R.drawable.comida, "Menu 6"));
        comidaServicios.add(new ItemsServicios(1, R.drawable.comida, "Menu 7"));
        comidaServicios.add(new ItemsServicios(1, R.drawable.comida, "Menu 8"));



        List<Servicios> todosServicios = new ArrayList<>();
        todosServicios.add(new Servicios("Peluquería", peluqueriaServicios));
        todosServicios.add(new Servicios("Estética", esteticaServicios));
        todosServicios.add(new Servicios("Yoga", yogaServicios));
        todosServicios.add(new Servicios("Masaje", masajeServicios));
        todosServicios.add(new Servicios("Alojamiento", alojamientoServicios));
        todosServicios.add(new Servicios("Comida", comidaServicios));

        setMaincategoryRecycler(todosServicios);

    }

    private void setMaincategoryRecycler(List<Servicios>todosServicios){

        rvVertical = findViewById(R.id.rvVertical);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvVertical.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, todosServicios);
        rvVertical.setAdapter(mainRecyclerAdapter);

    }

}