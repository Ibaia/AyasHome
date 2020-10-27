package com.dosdmtres.ayashome;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import androidx.appcompat.app.AppCompatActivity;

import com.dosdmtres.ayashome.model.Items;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import static com.dosdmtres.ayashome.Portada.items;


public class Datos extends AppCompatActivity {

    private static final String TAG = null;
    private ImageView imageView;
    private TextView servicio;
    private TextView descripcion;
    private TextView precio;
    private Button reserva;
    private Toolbar toolbarMain;

    String descripcionItem;
    String precioItem;
    String imageLargeItem;
    String nombreItem;
    private EditText fecha;
    private EditText hora;
    private EditText fechaSalida;
    public static Calendar calFecha;
    public static Calendar calFechaSalida;
    private int numHora, minuto,  dia, mes, ano;
    private boolean formFecha = false;
    private boolean formFechaSalida = false;
    private boolean formHora = false;
    private boolean tipoAlojamiento = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        ImageView imgItem = findViewById(R.id.imgGrande);
        TextView tvServicio = findViewById(R.id.servicio);
        TextView tvDescripcion = findViewById(R.id.descripcion);
        TextView tvPrecio = findViewById(R.id.tvPrecio);
        reserva = findViewById(R.id.reserva);
        fecha = findViewById(R.id.etFecha);
        hora = findViewById(R.id.etHora);
        fechaSalida = findViewById(R.id.etFechaSalida);

        // Dinamic load of item's data
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

        // Calendar
        comprobarTipoReserva();
        comprobar();

    }
    public void listenerBotones(View v) {

        switch (v.getId()) {
            case R.id.etFecha:
                mostrarFecha();
                break;
            case R.id.etHora:
                mostrarHora();
                break;
            case R.id.etFechaSalida:
                mostrarFechaSalida();
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

                calendario.set(year, month, dayOfMonth);

                int fechaSeleccionada = calendario.get(Calendar.DAY_OF_WEEK);
                boolean esLunes = (fechaSeleccionada == Calendar.MONDAY);
                if (esLunes) {
                    CharSequence text = "Por favor, seleccione un día que no sea lunes.";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);

                    toast.show();
                    formFecha = false;
                }
                else {
                    formFecha = true;
                }
                calFecha = calendario;

                comprobarEntreFechas();
                comprobar();
            }
        }, ano, mes, dia);

        //Setting min date to the current date
        dp.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        // Setting Max Date to next 2 years
        // dp.getDatePicker().setMaxDate(Calendar.YEAR + 1);


        dp.setTitle("Seleccionar fecha");
        dp.show();

    }

    public void mostrarFechaSalida() {
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
                fechaSalida.setText(a, TextView.BufferType.EDITABLE);

                calendario.set(year, month, dayOfMonth);

                int fechaSeleccionada = calendario.get(Calendar.DAY_OF_WEEK);
                boolean esLunes = (fechaSeleccionada == Calendar.MONDAY);
                if (esLunes) {
                    CharSequence text = "Por favor, seleccione un día que no sea lunes.";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);

                    toast.show();
                    formFechaSalida = false;
                }
                else {
                    formFechaSalida = true;
                }
                calFechaSalida = calendario;

                comprobarEntreFechas();
                comprobar();
            }
        }, ano, mes, dia);

        //Setting min date to the current date
        dp.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        dp.setTitle("Seleccionar fecha salida");
        dp.show();

    }

    //Checks if the selected time is valid
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

                formHora = true;
                comprobar();

            }
        }, numHora, minuto, false);
        tp.show();

    }

    //Checks if the service is for housing or not
    public void comprobarTipoReserva() {
        tipoAlojamiento = true;
        fechaSalida.setVisibility(View.VISIBLE);
        hora.setVisibility(View.GONE);
    }

    //Checks if the two dates from housing are valid
    //if the final date id smaller than the first, or the same,
    public void comprobarEntreFechas() {
        if (tipoAlojamiento = true) {
            if (calFecha != null && calFechaSalida != null) {
                if ((calFechaSalida.get(Calendar.YEAR) - calFecha.get(Calendar.YEAR) >= 0)) {
                    if ((calFechaSalida.get(Calendar.MONTH) - calFecha.get(Calendar.MONTH) >= 0)) {
                        if ((calFechaSalida.get(Calendar.DAY_OF_MONTH) - calFecha.get(Calendar.DAY_OF_MONTH) > 0)) {
                            formFechaSalida = true;
                            fechaSalida.setTextColor(0xff000000);
                            fecha.setTextColor(0xff000000);
                        } else {
                            formFechaSalida = false;
                            fechaSalida.setTextColor(0xFFFF0000);
                            fecha.setTextColor(0xFFFF0000);
                        }
                    } else {
                        formFechaSalida = false;
                        fechaSalida.setTextColor(0xFFFF0000);
                        fecha.setTextColor(0xFFFF0000);
                    }
                } else {
                    formFechaSalida = false;
                    fechaSalida.setTextColor(0xFFFF0000);
                    fecha.setTextColor(0xFFFF0000);
                }
            }

        }
    }

    //check if the form is correct and completed
    public void comprobar() {
        boolean formularioCompleto = false;

        if (!tipoAlojamiento) {
            if (formFecha && formHora) {
                formularioCompleto = true;
            } else {
                formularioCompleto = false;
            }
        } else {
            if (formFecha && formFechaSalida) {
                formularioCompleto = true;
            } else {
                formularioCompleto = false;
            }
        }
        if (!formularioCompleto) {
            reserva.setEnabled(false);
        } else {
            reserva.setEnabled(true);
        }
    }





}