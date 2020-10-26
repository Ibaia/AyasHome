package com.dosdmtres.ayashome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.dosdmtres.ayashome.adapter.ItemsServiciosRecyclerAdapter;
import com.dosdmtres.ayashome.model.Items;
import com.squareup.picasso.Picasso;

import static com.dosdmtres.ayashome.adapter.ItemsServiciosRecyclerAdapter.itemsList;


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
        ImageView imgPerfil = findViewById(R.id.imgPerfil);

        String nombre;
        nombre = getIntent().getStringExtra("NOMBRE");
        Log.d("TAG",nombre);


       /* for (int i = 0; i < itemsList.size(); i++) {
            Items item = itemsList.get(i);
            if (item.getNombre().equals(nombre)) {
                descripcionItem = itemsList.get(i).getDescripcion();
                precioItem = itemsList.get(i).getPrecio();
                imageLargeItem = itemsList.get(i).getImageLarge();
                servicio.setText(nombre);
                descripcion.setText(descripcionItem);
                precio.setText(precioItem);

                Log.d("TAG", nombre + descripcionItem + precioItem);
                break; //
            }
        }*/

        //Picasso.get().load(imageLargeItem).into(imageView);

    }
}