package com.dosdmtres.ayashome.model;

import java.util.Comparator;
import java.util.List;

public class Servicios implements Comparable<Servicios>
{

    String nombreServicio;
    List<Items> itemsArrayList;

    public Servicios(String nombreServicio, List<Items> itemsArrayList) {
        this.nombreServicio = nombreServicio;
        this.itemsArrayList = itemsArrayList;
    }

    public Servicios(String nombreServicio) {
        this.nombreServicio = nombreServicio;
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

    @Override
    public int compareTo(Servicios o)
    {
        return this.nombreServicio.compareTo(o.nombreServicio);
    }
}
