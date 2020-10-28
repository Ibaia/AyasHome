package com.dosdmtres.ayashome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.dosdmtres.ayashome.model.Items;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import static com.dosdmtres.ayashome.Values.RC_SIGN_IN;
import static com.dosdmtres.ayashome.Values.TAG;


public class Datos extends AppCompatActivity {


    private ImageView imageView;
    private TextView servicio;
    private TextView descripcion;
    private TextView precio;
    private Button reserva;
    private Toolbar toolbarMain;
    private ImageView imgPerfil;
    private TextView tvTitulo;

    String descripcionItem;
    String precioItem;
    String imageLargeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        ImageView imageview = findViewById(R.id.imageView);
        TextView servicio = findViewById(R.id.servicio);
        TextView descripcion = findViewById(R.id.descripcion);
        TextView precio = findViewById(R.id.tvPrecio);
        Button reserva = findViewById(R.id.reserva);
        Toolbar toolbarMain = findViewById(R.id.toolbarMain2);
        imgPerfil = findViewById(R.id.imgPerfil);

        imgPerfil = findViewById(R.id.imgPerfil);
        imgPerfil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(Datos.this);
                if(account == null)
                {
                    signIn();
                }
                else
                {
                    MainActivity.goReservas(account, Datos.this);
                }
            }
        });
        
        String nombre;
        nombre = getIntent().getStringExtra("NOMBRE");
        Log.d("TAG",nombre);


/*        for (int i = 0; i < Portada.items.size(); i++) {
            Items item = Portada.items.get(i);
            if (item.getNombre().equals(nombre)) {
                descripcionItem = Portada.items.get(i).getDescripcion();
                precioItem = Portada.items.get(i).getPrecio();
                imageLargeItem = Portada.items.get(i).getImageLarge();
                servicio.setText(nombre);
                descripcion.setText(descripcionItem);
                precio.setText(precioItem);

                Log.d("TAG", nombre + descripcionItem + precioItem);
                break; //
            }
        }*/

        //Picasso.get().load(imageLargeItem).into(imageView);

    }
    void signIn()
    {
        Intent signInIntent = MainActivity.mGoogleSignInClient.getSignInIntent();
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
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
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
            imgPerfil.setImageResource(R.drawable.user);
        }
        else
        {
            Picasso.get().load(account.getPhotoUrl()).into(imgPerfil);
        }
    }
}