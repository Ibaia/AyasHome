package com.dosdmtres.ayashome;

import androidx.annotation.NonNull;
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

import com.dosdmtres.ayashome.adapter.MainRecyclerAdapter;
import com.dosdmtres.ayashome.model.Items;
import com.dosdmtres.ayashome.model.Servicios;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.dosdmtres.ayashome.Values.*;
import com.dosdmtres.ayashome.Portada;

public class MainActivity extends AppCompatActivity {

    FrameLayout listaServicios;
    private Toolbar mainToolbar;
    private ImageView fotoPerfil;
    private ImageView imageItem;

    static GoogleSignInClient mGoogleSignInClient;
    FirebaseFirestore db;

    RecyclerView rvMain;
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

        List<Servicios> todosServicios = new ArrayList<>();

        for(int i = 0; i < Portada.servicios.size(); i++)
        {
            todosServicios.add(new Servicios(Portada.servicios.get(i).getNombreServicio(), Portada.servicios.get(i).getItemsArrayList()));
        }

        setMaincategoryRecycler(todosServicios);
    }

    // Set up the vertical RecyclerView with its adapter
    private void setMaincategoryRecycler(List<Servicios>todosServicios){
        rvMain = findViewById(R.id.rvMain);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvMain.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, todosServicios);
        rvMain.setAdapter(mainRecyclerAdapter);

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