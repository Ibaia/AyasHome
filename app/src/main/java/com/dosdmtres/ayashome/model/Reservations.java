package com.dosdmtres.ayashome.model;

import com.dosdmtres.ayashome.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class Reservations {
    List<Reservation> listReservations;

    public Reservations(List<Reservation> listReservations) {
        this.listReservations = listReservations;
    }

    public Reservations()
    {
        Reservation r1 = new Reservation("Alojamiento", "12/12/1234", "12/12/1234", "asd@gmail.com");
        Reservation r2 = new Reservation("Masaje","12/12/1234","12/12/1234", "njsdnjsd@gmail.com");

        listReservations = new ArrayList<>();

        listReservations.add(r1);
        listReservations.add(r2);
    }

    public List<Reservation> getListReservations() {
        return listReservations;
    }

    public void setListReservations(List<Reservation> listReservations) {
        this.listReservations = listReservations;
    }
}
