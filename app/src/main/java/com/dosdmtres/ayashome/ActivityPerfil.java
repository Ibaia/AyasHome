package com.dosdmtres.ayashome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.dosdmtres.ayashome.adapter.ReservationAdapter;
import com.dosdmtres.ayashome.model.Reservation;
import com.dosdmtres.ayashome.model.Reservations;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.dosdmtres.ayashome.MainActivity.allReser;
import static com.dosdmtres.ayashome.MainActivity.mGoogleSignInClient;

public class ActivityPerfil extends AppCompatActivity {

    Button logOut;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        logOut = findViewById(R.id.button);
        list = findViewById(R.id.rList);

        Reservations rs1 = new Reservations(allReser);

        ReservationAdapter rAdapter1 = new ReservationAdapter(ActivityPerfil.this, rs1);

        list.setAdapter(rAdapter1);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

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