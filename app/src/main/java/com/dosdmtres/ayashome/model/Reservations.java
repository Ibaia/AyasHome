package com.dosdmtres.ayashome.model;

import com.dosdmtres.ayashome.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class Reservations {
    List<Reservation> listReservations;

    public Reservations(List<Reservation> listReservations) {
        this.listReservations = listReservations;
    }

    public List<Reservation> getListReservations() {
        return listReservations;
    }

    public void setListReservations(List<Reservation> listReservations) {
        this.listReservations = listReservations;
    }
}
