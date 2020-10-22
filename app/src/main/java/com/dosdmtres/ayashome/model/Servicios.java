package com.dosdmtres.ayashome.model;

import java.util.List;

public class Servicios {

    String nombreServicio;
    List<Items> itemsArrayList;

    public Servicios(String nombreServicio, List<Items> itemsArrayList) {
        this.nombreServicio = nombreServicio;
        this.itemsArrayList = itemsArrayList;
    }

    public List<Items> getItemsArrayList() {
        return itemsArrayList;
    }

    public void setItemsArrayList(List<Items> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
}
