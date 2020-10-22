package com.dosdmtres.ayashome.model;

public class Items {

    int id;
    String categoria;
    String nombre;
    String descripcion;
    double precio;
    int imageMini;
    int imageLarge;


    public Items(int id, String categoria, String nombre, String descripcion, double precio, int imageMini, int imageLarge) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imageMini = imageMini;
        this.imageLarge = imageLarge;
    }

    // Custom Constructor with only one image per item
    public Items(int id, String categoria, String nombre, String descripcion, double precio, int imageMini) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imageMini = imageMini;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getImageMini() {
        return imageMini;
    }

    public void setImageMini(int imageMini) {
        this.imageMini = imageMini;
    }

    public int getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(int imageLarge) {
        this.imageLarge = imageLarge;
    }
}
