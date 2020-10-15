package com.example.ayashome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.ayashome.adapter.MainRecyclerAdapter;
import com.example.ayashome.model.ItemsServicios;
import com.example.ayashome.model.Servicios;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    SignInButton gButton;
    GoogleSignInClient mGoogleSignInClient;
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
            //updateUI(account);
            goReservas();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

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
        //updateUI(account);
    }

/*    private void updateUI(GoogleSignInAccount account)
    {
        if(account != null)
        {
            fotoPerfil.setImageDrawable();
        }
        else
        {
            Drawable img = new Drawable()
            fotoPerfil.setImageDrawable();
        }
    }*/
}