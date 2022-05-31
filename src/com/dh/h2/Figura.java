package com.dh.h2;

public class Figura {
    private String nombre; //Atributos
    private Long id;
    private String color;


    public Figura(String nombre, String color) { //Constructor
        this.nombre = nombre;
        this.color = color;
    }

    public String getNombre() { //Métodos accesores
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
