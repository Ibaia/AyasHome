package com.example.ayashome.model;

import java.util.List;

public class Servicios {

    String nombreServicio;
    List<ItemsServicios> itemsServiciosArrayList;

    public Servicios(String nombreServicio, List<ItemsServicios> itemsServiciosArrayList) {
        this.nombreServicio = nombreServicio;
        this.itemsServiciosArrayList = itemsServiciosArrayList;
    }

    public List<ItemsServicios> getItemsServiciosArrayList() {
        return itemsServiciosArrayList;
    }

    public void setItemsServiciosArrayList(List<ItemsServicios> itemsServiciosArrayList) {
        this.itemsServiciosArrayList = itemsServiciosArrayList;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
}
