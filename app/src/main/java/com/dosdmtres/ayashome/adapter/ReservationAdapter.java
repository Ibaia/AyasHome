package com.dosdmtres.ayashome.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dosdmtres.ayashome.ActivityPerfil;
import com.dosdmtres.ayashome.MainActivity;
import com.dosdmtres.ayashome.R;
import com.dosdmtres.ayashome.model.Reservation;
import com.dosdmtres.ayashome.model.Reservations;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class ReservationAdapter extends BaseAdapter {

    Activity mActivity;
    Reservations list;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ReservationAdapter(Activity mActivity, Reservations list) {
        this.mActivity = mActivity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.getListReservations().size();
    }

    @Override
    public Reservation getItem(int position) {
        return list.getListReservations().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View oneReservationLine;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        oneReservationLine = inflater.inflate(R.layout.one_reservation_line, parent, false);

        final TextView correo = oneReservationLine.findViewById(R.id.correo);
        correo.setMovementMethod(new ScrollingMovementMethod());
        final TextView tipoServicio = oneReservationLine.findViewById(R.id.tipoServicio);
        final TextView fechaEntrada = oneReservationLine.findViewById(R.id.fechaEntrada);
        final TextView fechaSalida = oneReservationLine.findViewById(R.id.fechaSalida);
        ImageView btn_delete = oneReservationLine.findViewById(R.id.btn_delete);

        final Reservation r = this.getItem(position);

        btn_delete.setOnClickListener(new View.OnClickListener()
        {

            int two = 1;
            @Override
            public void onClick(View v)
            {
                if(two != 0)
                {
                    Context context = mActivity.getApplicationContext();
                    String locale = Locale.getDefault().getLanguage();
                    CharSequence text = locale.equals("es") ? "Presione otra vez para confirmar" : "Press again to confirm";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    two--;
                }
                else
                {
                    db.collection("Reservas").document(r.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            list.delReser(position);

                            ActivityPerfil.rAdapter1.notifyDataSetChanged();
                        }
                    });
                }
            }
        });

        correo.setText(r.getCliente());
        tipoServicio.setText(r.getServicio());
        fechaEntrada.setText(r.getFechaEntrada());
        fechaSalida.setText(r.getFechaSalida());

        return oneReservationLine;
    }
}
