package com.gestionFicheroBinario;

import java.io.Serializable;


public class Empleado extends Objeto implements Serializable{
    
    private int id;
    private String nombre;
    private String apellidos;
    private String cargo;
    private int edad;

    public Empleado(int id, String nombre, String apellidos, String cargo, int edad) {
        super.setId(id);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.edad = edad;
    }
    
    public Empleado(String nombre, String apellidos, String cargo, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", cargo=" + cargo + ", edad=" + edad;
    }
    
    
}
