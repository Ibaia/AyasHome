package com.example.ayashome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ayashome.adapter.MainRecyclerAdapter;
import com.example.ayashome.model.ItemsServicios;
import com.example.ayashome.model.Servicios;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.ayashome.Values.RC_SIGN_IN;
import static com.example.ayashome.Values.TAG;

    public class MainActivity extends AppCompatActivity {

    FrameLayout listaServicios;
    private Toolbar mainToolbar;
    private static ImageView fotoPerfil;

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
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestProfile().requestEmail().build();

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
            updateUI(account);
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
            Uri accUri = account.getPhotoUrl();

            if(accUri != null)
            {
                new DownloadImage().execute(accUri.toString());
            }
            //new DownloadImage().execute("");
        }
    }
    private static class DownloadImage extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... URL)
        {
            String imageURL = URL[0];

            Bitmap bitmap = null;
            try
            {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result)
        {
            // Set the bitmap into ImageView
            fotoPerfil.setImageBitmap(result);
        }
    }
}