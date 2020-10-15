package com.example.ayashome.model;

public class ItemsServicios{

    int id;
    int imageUrl;
    String nombre;

    public ItemsServicios(int id, Integer imageUrl, String nombre) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
