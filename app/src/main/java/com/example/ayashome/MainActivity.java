package com.example.ayashome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.ayashome.adapter.MainRecyclerAdapter;
import com.example.ayashome.model.Items;
import com.example.ayashome.model.Servicios;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static com.example.ayashome.Values.*;

    public class MainActivity extends AppCompatActivity {

    FrameLayout listaServicios;
    private Toolbar mainToolbar;
    private ImageView fotoPerfil;
    private ImageView imageItem;

    static GoogleSignInClient mGoogleSignInClient;
    FirebaseFirestore db;

    RecyclerView rvVertical;
    MainRecyclerAdapter mainRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        fotoPerfil = findViewById(R.id.imgPerfil);
        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(MainActivity.this);
                if(account == null)
                {
                    signIn();
                }
                else
                {
                    goReservas();
                }
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        // Test data to check the app
        List<Items> peluqueriaServicios = new ArrayList<>();
        peluqueriaServicios.add(new Items(1, "Peluquería", "Corte", "",50, R.drawable.pelu));
        peluqueriaServicios.add(new Items(1, "Peluquería", "Mechas", "",50, R.drawable.pelu));
        peluqueriaServicios.add(new Items(1, "Peluquería", "Marcados", "",50, R.drawable.pelu));
        peluqueriaServicios.add(new Items(1, "Peluquería", "trenzados", "",50, R.drawable.pelu));
        peluqueriaServicios.add(new Items(1, "Peluquería", "planchas", "",50, R.drawable.pelu));
        peluqueriaServicios.add(new Items(1, "Peluquería", "Color", "",50, R.drawable.pelu));
        peluqueriaServicios.add(new Items(1, "Peluquería", "Coloración ", "",50, R.drawable.pelu));
        peluqueriaServicios.add(new Items(1, "Peluquería", "Decoloraciones", "",50, R.drawable.pelu));

        List<Items> esteticaServicios = new ArrayList<>();
        esteticaServicios.add(new Items(1, "Estética", "Corte", "",50, R.drawable.este));
        esteticaServicios.add(new Items(1, "Estética", "Corte", "",50, R.drawable.este));
        esteticaServicios.add(new Items(1, "Estética", "Corte", "",50, R.drawable.este));

        List<Items> yogaServicios = new ArrayList<>();
        yogaServicios.add(new Items(1, "Masaje", "Masaje", "",50, R.drawable.yoga));

        List<Items> masajeServicios = new ArrayList<>();
        masajeServicios.add(new Items(1, "Yoga", "Yoga", "",50, R.drawable.masaje));

        List<Items> alojamientoServicios = new ArrayList<>();
        alojamientoServicios.add(new Items(1, "Alojamiento", "Cama Grande", "",50, R.drawable.unacama));
        alojamientoServicios.add(new Items(1, "Alojamiento", "Doble Cama", "",50, R.drawable.doscamas));

        List<Items> comidaServicios = new ArrayList<>();
        comidaServicios.add(new Items(1, "Comida", "Menu 1", "",50, R.drawable.comida));
        comidaServicios.add(new Items(1, "Comida", "Menu 2", "",50, R.drawable.comida));
        comidaServicios.add(new Items(1, "Comida", "Menu 3", "",50, R.drawable.comida));
        comidaServicios.add(new Items(1, "Comida", "Menu 4", "",50, R.drawable.comida));
        comidaServicios.add(new Items(1, "Comida", "Menu 5", "",50, R.drawable.comida));
        comidaServicios.add(new Items(1, "Comida", "Menu 6", "",50, R.drawable.comida));
        comidaServicios.add(new Items(1, "Comida", "Menu 7", "",50, R.drawable.comida));
        comidaServicios.add(new Items(1, "Comida", "Menu 8", "",50, R.drawable.comida));

        List<Servicios> todosServicios = new ArrayList<>();
        todosServicios.add(new Servicios("Peluquería", peluqueriaServicios));
        todosServicios.add(new Servicios("Estética", esteticaServicios));
        todosServicios.add(new Servicios("Yoga", yogaServicios));
        todosServicios.add(new Servicios("Masaje", masajeServicios));
        todosServicios.add(new Servicios("Alojamiento", alojamientoServicios));
        todosServicios.add(new Servicios("Comida", comidaServicios));

        setMaincategoryRecycler(todosServicios);

    }



    public void click (View view) {
        Intent intent = new Intent(MainActivity.this, Datos.class);
        startActivity(intent);
        }

    // Set up the vertical RecyclerView with its adapter
    private void setMaincategoryRecycler(List<Servicios>todosServicios){
                rvVertical = findViewById(R.id.rvVertical);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvVertical.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, todosServicios);
        rvVertical.setAdapter(mainRecyclerAdapter);

    }

    private void signIn()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN)
        {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
            goReservas();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }


    // Method to go to the Reservas Activity
    private void goReservas()
    {
        Intent intentPerfil = new Intent(MainActivity.this, ActivityPerfil.class);
        startActivity(intentPerfil);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account)
    {
        if(account == null)
        {
            fotoPerfil.setImageResource(R.drawable.user);
        }
        else
        {
            fotoPerfil.setImageResource(R.drawable.comida);
        }
    }
}