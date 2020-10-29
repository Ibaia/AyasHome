package com.dosdmtres.ayashome;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dosdmtres.ayashome.model.Items;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.dosdmtres.ayashome.Portada.items;

import static com.dosdmtres.ayashome.Values.RC_SIGN_IN;
import static com.dosdmtres.ayashome.Values.TAG;


public class Datos extends AppCompatActivity {

    private static final String TAG = null;
    private ImageView imageView;
    private TextView servicio;
    private TextView descripcion;
    private TextView precio;
    private Button btnreserva;
    private Toolbar toolbarMain;

    String descripcionItem;
    String precioItem;
    String imageLargeItem;
    String nombreItem;
    String emailUser;
    GoogleSignInAccount account;
    private ImageView imgPerfil;
    private ImageView imageLogo;
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

        //check if you are logged
        account = GoogleSignIn.getLastSignedInAccount(this);

        //Declare butons and other attributes that we will need
        ImageView imgItem = findViewById(R.id.imgGrande);
        TextView tvServicio = findViewById(R.id.servicio);
        TextView tvDescripcion = findViewById(R.id.descripcion);
        TextView tvPrecio = findViewById(R.id.tvPrecio);
        btnreserva = findViewById(R.id.reserva);
        fecha = findViewById(R.id.etFecha);
        hora = findViewById(R.id.etHora);
        fechaSalida = findViewById(R.id.etFechaSalida);

        //Push the logo to go back
        ImageView imagenLogo = findViewById(R.id.imgLogo);
        imagenLogo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainActivity.goHome(Datos.this);
            }
        });

        //Pushing the image perfil you go to reservations
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

        // Dinamic load of item's data
        nombreItem = getIntent().getStringExtra("NOMBRE");
        descripcionItem = getIntent().getStringExtra("DESCRIPCION");
        precioItem = getIntent().getStringExtra("PRECIO");
        imageLargeItem = getIntent().getStringExtra("IMAGEN");

        //Dinamic load with data from the previous activity
        tvDescripcion.setText(descripcionItem);
        tvPrecio.setText(precioItem);
        tvServicio.setText(nombreItem);

        //Check if you are logged
        //comprobacionCuentaUsuario();

        //Load images
        try {
            Picasso.get().load(imageLargeItem)
                    .fit()
                    .centerCrop()
                    .into(imgItem);
        } catch (Exception e) {
            imgItem.setImageResource(R.drawable.logo_icono);
        }

        // Calendar
        comprobarTipoReserva(nombreItem);

        //cumprueba que estes
        comprobar();


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
        //Reservation Button
        btnreserva.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (calFechaSalida == null){
                    serviceReservation(nombreItem, fecha, hora, emailUser);
                }else{
                    serviceReservationHabitacion(nombreItem, fecha, fechaSalida, emailUser);
                }

            }
        });
    }



    private void serviceReservation(String nombreItem, EditText fecha, EditText hora, String emailUser) {

        String horaReserva=hora.getText().toString();
        String fechaReserva=fecha.getText().toString();

        //Estruture for the insert of the reserve
        Map<String, Object> updateMap = new HashMap();
        updateMap.put("cliente", emailUser);
        updateMap.put("fechaEntrada", fechaReserva);
        updateMap.put("hora", horaReserva);
        updateMap.put("servicio", nombreItem);

        //Connect with the database to insert the reserve
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //hacemos la insert
        db.collection("Reservas")
                .document()
                .set(updateMap)
                //Create a toast that tell the user the status of the reservation when it work proprerly
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Context context = getApplicationContext();
                        CharSequence text = "Exito al añadir la reserva";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                })
                //Create a toast that tell the user the status of the reservation when it work
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Context context = getApplicationContext();
                        CharSequence text = "Error al añadir la reserva";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
    }


    private void serviceReservationHabitacion(String nombreItem, EditText fecha, EditText fechaSalida, String emailUser) {

        String fechaSalidaET=fechaSalida.getText().toString();
        String fechaReserva=fecha.getText().toString();

        //Estruture for the insert of the reserve
        Map<String, Object> updateMap = new HashMap();
        updateMap.put("cliente", emailUser);
        updateMap.put("fechaEntrada", fechaReserva);
        updateMap.put("fechaSalida", fechaSalidaET);
        updateMap.put("servicio", nombreItem);

        //Connect with the database to insert the reserve
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //hacemos la insert
        db.collection("Reservas")
                .document()
                .set(updateMap)
                //Create a toast that tell the user the status of the reservation when it work proprerly
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Context context = getApplicationContext();
                        CharSequence text = "Exito al añadir la reserva";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                })
                //Create a toast that tell the user the status of the reservation when it work
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Context context = getApplicationContext();
                        CharSequence text = "Error al añadir la reserva";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
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

    void signIn()
    {
        Intent signInIntent = MainActivity.mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

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

            if (account.getPhotoUrl() == null){
                imgPerfil.setImageResource(R.drawable.user);
            }else{
                Picasso.get().load(account.getPhotoUrl()).into(imgPerfil);
            }
        }
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
                //comprobacionCuentaUsuario();
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
    private void comprobarTipoReserva(String nombreItem) {
        String serviceName = nombreItem;
        if (serviceName.equals("Cama Matrimonio")||serviceName.equals("Cama doble")|| serviceName.equals("Double Bed")||serviceName.equals("King Size")){
            tipoAlojamiento = true;

            hora.setVisibility(View.GONE);
            fechaSalida.setVisibility(View.VISIBLE);
        }else{
            tipoAlojamiento = false;
            hora.setVisibility(View.VISIBLE);
            fechaSalida.setVisibility(View.GONE);
        }
    }

    //Checks if the two dates from housing are valid
    //if the final date id smaller than the first, or the same,
    public void comprobarEntreFechas() {
        if (tipoAlojamiento == true) {
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
    //Check if the user is logged
    private void comprobacionCuentaUsuario() {
        if(account == null) {
            Context context = getApplicationContext();
            String locale = Locale.getDefault().getLanguage();
            CharSequence text = locale.equals("es") ? "Inicia session para poder hacer la reserva" : "Log in to create a reservation";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else{
            emailUser= account.getEmail();
            Log.d("email", emailUser);
        }

    }

    //check if the form is correct and completed
    public void comprobar() {
        boolean formularioCompleto = false;
        //Check if you are logged
        comprobacionCuentaUsuario();

        if (!tipoAlojamiento) {
            if (formFecha && formHora && account != null) {
                formularioCompleto = true;
            } else {
                formularioCompleto = false;
            }
        } else {
            if (formFecha && formFechaSalida && account != null) {
                formularioCompleto = true;
            } else {
                formularioCompleto = false;
            }
        }
        if (!formularioCompleto) {
            btnreserva.setEnabled(false);
        } else {
            btnreserva.setEnabled(true);
        }
    }

}