package com.dosdmtres.ayashome.model;

public class Items {

    String nombre;
    String descripcion;
    String precio;
    String imageMini;
    String imageLarge;


    public Items(String nombre, String descripcion, String precio, String imageMini, String imageLarge) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imageMini = imageMini;
        this.imageLarge = imageLarge;
    }

    // Custom Constructor with only one image per item
    public Items(int id, String categoria, String nombre, String descripcion, String precio, String imageMini) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imageMini = imageMini;

    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImageMini() {
        return imageMini;
    }

    public void setImageMini(String imageMini) {
        this.imageMini = imageMini;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }
}
