package com.dosdmtres.ayashome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;

import com.dosdmtres.ayashome.model.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Portada extends AppCompatActivity
{
    FirebaseFirestore db;
    ArrayList<String> servicios;
    static List<Items> peluqueriaServicios = new ArrayList<>();
    static List<Items> esteticaServicios = new ArrayList<>();
    static List<Items> yogaServicios = new ArrayList<>();
    static List<Items> masajeServicios = new ArrayList<>();
    static List<Items> alojamientoServicios = new ArrayList<>();
    static List<Items> comidaServicios = new ArrayList<>();

    static int size = 0;

    private ImageView imgPortada;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);
        imgPortada = findViewById(R.id.imgPortada);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Bundle b=new Bundle();

        servicios = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        db.collection("Servicios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot document : task.getResult())
                    {
                        servicios.add(document.getString("nombre"));
                        size++;
                    }
                }
            }
        });

        for (int i = 0; i < servicios.size(); i++)
        {
            db.collection("Servicios").document(servicios.get(i)).collection(servicios.get(i)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
            {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task)
                {
                    if(task.isSuccessful())
                    {
                        for(QueryDocumentSnapshot document : task.getResult())
                        {
                            servicios.add(document.getString("nombre"));
                        }
                    }
                }
            });
        }

    }
}