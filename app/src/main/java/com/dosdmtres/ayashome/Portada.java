package com.dosdmtres.ayashome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dosdmtres.ayashome.model.Items;
import com.dosdmtres.ayashome.model.Servicios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class Portada extends AppCompatActivity
{
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    static ArrayList<Servicios> servicios = new ArrayList<>();
    public static ArrayList<String> nombreServicios = new ArrayList<>();
    public static ArrayList<Items> items;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        final Context next = Portada.this;

        db.collection("Servicios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult()))
                    {
                        nombreServicios.add(document.getString("nombre"));
                    }
                    secAccess(next);
                }
            }
        });
    }

    private void secAccess(final Context next)
    {
        for (int i = 0; i < nombreServicios.size(); i++)
        {
            final int finalI = i;
            db.collection("Servicios").document(nombreServicios.get(i)).collection(nombreServicios.get(i)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
            {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task)
                {
                   items = new ArrayList<>();

                    if(task.isSuccessful())
                    {
                        for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult()))
                        {
                            String nombre = document.getString("nombre");
                            String descripcion = document.getString("descripcion");
                            String precio = document.getString("precio");
                            String imageMini = document.getString("urlMiniatura");
                            String imageLarge = document.getString("urlImagen");


                            items.add(new Items(nombre, descripcion, precio, imageMini, imageLarge));
                        }
                        servicios.add(new Servicios(nombreServicios.get(finalI), items));
                        Intent otherA = new Intent(next, MainActivity.class);

                        next.startActivity(otherA);
                    }
                }
            });
        }
    }
}