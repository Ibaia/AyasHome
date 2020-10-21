package com.example.ayashome.model;

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
