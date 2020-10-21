package com.example.ayashome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.ayashome.adapter.ReservationAdapter;
import com.example.ayashome.model.Reservations;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.example.ayashome.MainActivity.mGoogleSignInClient;

public class ActivityPerfil extends AppCompatActivity {

    Button logOut;
    ListView rList;
    Reservations arrayReservas;
    ReservationAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        logOut = findViewById(R.id.button);

        rList = findViewById(R.id.rList);

        arrayReservas = new Reservations();

        myAdapter = new ReservationAdapter(ActivityPerfil.this, arrayReservas);

        rList.setAdapter(myAdapter);

        logOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                signOut();
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    private void signOut()
    {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent goBack = new Intent(ActivityPerfil.this, MainActivity.class);
                        startActivity(goBack);
                    }
                });
    }
}