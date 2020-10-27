package com.dosdmtres.ayashome;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.dosdmtres.ayashome.model.Items;
import com.squareup.picasso.Picasso;

import static com.dosdmtres.ayashome.Portada.items;


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
    String nombreItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        ImageView imgItem = findViewById(R.id.imgGrande);
        TextView tvServicio = findViewById(R.id.servicio);
        TextView tvDescripcion = findViewById(R.id.descripcion);
        TextView tvPrecio = findViewById(R.id.tvPrecio);
        Button reserva = findViewById(R.id.reserva);


        nombreItem = getIntent().getStringExtra("NOMBRE");
        descripcionItem = getIntent().getStringExtra("DESCRIPCION");
        precioItem = getIntent().getStringExtra("PRECIO");
        imageLargeItem = getIntent().getStringExtra("IMAGEN");

        tvDescripcion.setText(descripcionItem);
        tvPrecio.setText(precioItem);
        tvServicio.setText(nombreItem);

        try {
            Picasso.get().load(imageLargeItem)
                    .fit()
                    .centerCrop()
                    .into(imgItem);
        } catch (Exception e) {
            imgItem.setImageResource(R.drawable.logo_icono);
        }


    }
}