package com.example.ayashome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toolbar;

import java.util.Calendar;


public class Datos extends AppCompatActivity {


    private ImageView imageView;
    private TextView servicio;
    private TextView descripcion;
    private TextView precio;
    private Button reserva;
    private Toolbar toolbarMain;
    private ImageView imgPerfil;
    private TextView tvTitulo;
    private EditText fecha;
    private EditText hora;
    private int numHora, minuto,  dia, mes, ano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        final ImageView imageview = findViewById(R.id.imageView);
        final TextView servicio = findViewById(R.id.servicio);
        final TextView descripcion = findViewById(R.id.descripcion);
        final TextView precio = findViewById(R.id.tvPrecio);
        final Button reserva = findViewById(R.id.reserva);
        final Toolbar toolbarMain = findViewById(R.id.toolbarMain2);
        final ImageView imgPerfil = findViewById(R.id.imgPerfil);
        final EditText fecha = findViewById(R.id.etFecha);
        final EditText hora = findViewById(R.id.etHora);

    }

    public void listenerBotones(View v) {

        switch (v.getId()) {
            case R.id.etFecha:
                mostrarFecha();
                break;
            case R.id.etHora:
                mostrarHora();
                break;
        }
    }

    //Methods
    public void mostrarFecha() {
        final Calendar calendario = Calendar.getInstance();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH);
        ano = calendario.get(Calendar.YEAR);

        DatePickerDialog dp = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //month+1 because it starts being 0
                //String a = dayOfMonth + "/" + (month + 1) + "/" + year;
                //fecha.setText(a, TextView.BufferType.EDITABLE);
            }
        }, ano, mes, dia);
        dp.show();
    }

    public void mostrarHora() {
        final Calendar calendarioHora = Calendar.getInstance();
        numHora = calendarioHora.get(Calendar.HOUR_OF_DAY);
        minuto = calendarioHora.get(Calendar.MINUTE);

        TimePickerDialog tp = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String a = hourOfDay + ":" + minute;
                hora.setText(a, TextView.BufferType.EDITABLE);
            }
        }, numHora, minuto, false);
        tp.show();
    }

}