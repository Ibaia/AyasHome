package com.dosdmtres.ayashome.model;

public class Reservation {
    String servicio;
    String fechaEntrada;
    String fechaSalida;
    String cliente;
    String id;

    public Reservation(String cliente, String fechaEntrada, String fechaSalida, String servicio, String id)
    {
        this.servicio = servicio;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.cliente = cliente;
        this.id = id;
    }

    public String getServicio() {
        return servicio;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getCliente() {
        return cliente;
    }

    public String getId() { return id; }
}
