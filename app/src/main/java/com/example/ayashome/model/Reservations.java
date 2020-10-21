package com.example.ayashome.model;

import java.util.List;

public class Reservations {
    List<Reservation> listReservations;

    public Reservations(List<Reservation> listReservations) {
        this.listReservations = listReservations;
    }

    public Reservations()
    {
        Reservation r1 = new Reservation("Pelu", "10/10/01", "15/15/15", "asd@gmail.com");
        Reservation r2 = new Reservation("Masaje","123/123/233","123/124/4", "njsdnjsd@gmail.com");
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
