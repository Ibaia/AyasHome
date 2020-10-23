package com.dosdmtres.ayashome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.type.DayOfWeek;

import java.util.Calendar;


public class Datos extends AppCompatActivity {


    private static final String TAG = null;
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
    private boolean formularioCorrecto = true;
    private boolean formFecha = false;
    private boolean formHora = false;
    private boolean formularioCompleto = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        imageView = findViewById(R.id.imageView);
        servicio = findViewById(R.id.servicio);
        descripcion = findViewById(R.id.descripcion);
        precio = findViewById(R.id.tvPrecio);
        reserva = findViewById(R.id.reserva);
        toolbarMain = findViewById(R.id.toolbarMain2);
        imgPerfil = findViewById(R.id.imgPerfil);
        fecha = findViewById(R.id.etFecha);
        hora = findViewById(R.id.etHora);

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
                String a = dayOfMonth + "/" + (month + 1) + "/" + year;
                if((month + 1) < 10) {
                    a = dayOfMonth + "/" + "0" + (month + 1) + "/" + year;
                }
                fecha.setText(a, TextView.BufferType.EDITABLE);

                //If the selected day is monday, show a warning
                Calendar max = Calendar.getInstance();
                max.add(Calendar.YEAR, 1);

                //takes every day, one by one with a for
                for (Calendar contador = Calendar.getInstance(); contador.before(max); contador.add(Calendar.DATE, 1)) {
                    //takes the day of the for
                    int fechaSeleccionada = contador.get(Calendar.DAY_OF_WEEK);

                    //for each day it takes, checks if the selected day is monday, if it is, sends a toast and block the "reservar" button
                    if (fechaSeleccionada == Calendar.MONDAY) {
                        /*Calendar[] disabledDays =  new Calendar[1];
                        disabledDays[0] = contador;*/
                        Log.d(TAG, "esto es" + fechaSeleccionada);

                        if(dayOfMonth == fechaSeleccionada) {
                            CharSequence text = "Por favor, seleccione un día que no sea lunes.";
                            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);

                            toast.show();

                            formularioCorrecto = false;
                        }else {
                            formFecha = true;
                        }
                    }
                }

            }
        }, ano, mes, dia);
        //Setting min date to the current date
        dp.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        // Setting Max Date to next 2 years
       // dp.getDatePicker().setMaxDate(Calendar.YEAR + 1);


        dp.setTitle("Seleccionar fecha");
        dp.show();

        comprobar();

    }

    public void mostrarHora() {
        final Calendar calendarioHora = Calendar.getInstance();
        numHora = calendarioHora.get(Calendar.HOUR_OF_DAY);
        minuto = calendarioHora.get(Calendar.MINUTE);

        TimePickerDialog tp = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String a = hourOfDay + ":" + minute;
                if(minute < 10) {
                    a = hourOfDay + ":" + "0" + minute;
                }
                hora.setText(a, TextView.BufferType.EDITABLE);

            }
        }, numHora, minuto, false);
        tp.show();

        formHora = true;
        comprobar();
    }

    //check if the form is completed and correct
    public void comprobar() {
        if(formFecha && formHora) {
            formularioCompleto = true;
        }
        if(!formularioCorrecto && !formularioCompleto) {
            reserva.setEnabled(false);
        }
    }

}