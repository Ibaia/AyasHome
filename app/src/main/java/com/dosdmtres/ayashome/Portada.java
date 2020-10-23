package com.dosdmtres.ayashome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dosdmtres.ayashome.model.Items;
import com.dosdmtres.ayashome.model.Servicios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Portada extends AppCompatActivity
{
    FirebaseFirestore db;

    static ArrayList<Servicios> servicios = new ArrayList<>();;
    ArrayList<String> nombreServicios = new ArrayList<>();

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

        db = FirebaseFirestore.getInstance();

        final Context next = Portada.this;

        db.collection("Servicios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot document : task.getResult())
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
                    ArrayList<Items> items = new ArrayList<>();

                    if(task.isSuccessful())
                    {
                        for(QueryDocumentSnapshot document : task.getResult())
                        {
                            String nombre = document.getString("nombre");
                            String descripcion = document.getString("descripcion");
                            String precio = document.getString("precio");
                            String imageMini = document.getString("urlImagen");
                            String imageLarge = document.getString("urlMiniatura");

                            Log.w(Values.TAG,  nombreServicios.get(finalI) + " " + nombre);

                            items.add(new Items(nombre, descripcion, precio, imageMini, imageLarge));
                        }
                        servicios.add(new Servicios(nombreServicios.get(finalI), items));
                        Intent otherA = new Intent(next, MainActivity.class);
                        next.startActivity(otherA);
                    }
                    else
                    {
                        Log.v(Values.TAG, "ERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
                    }
                }
            });
        }
    }
}