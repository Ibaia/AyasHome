package com.example.ayashome.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayashome.R;
import com.example.ayashome.model.Reservation;
import com.example.ayashome.model.Reservations;

public class ReservationAdapter extends BaseAdapter {

    Activity mActivity;
    Reservations list;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View oneReservationLine;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        oneReservationLine = inflater.inflate(R.layout.one_reservation_line, parent, false);

        TextView correo = oneReservationLine.findViewById(R.id.correo);
        TextView tipoServicio = oneReservationLine.findViewById(R.id.tipoServicio);
        TextView fechaEntrada = oneReservationLine.findViewById(R.id.fechaEntrada);
        TextView fechaSalida = oneReservationLine.findViewById(R.id.fechaSalida);
        ImageView btn_delete = oneReservationLine.findViewById(R.id.btn_delete);

        Reservation r = this.getItem(position);

        correo.setText(r.getCliente());
        tipoServicio.setText(r.getServicio());
        fechaEntrada.setText(r.getFechaEntrada());
        fechaSalida.setText(r.getFechaSalida());

        return oneReservationLine;
    }
}
