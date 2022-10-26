package com.gestionFicheroAccesoAleatorio;

import java.io.Serializable;

public class Departamento implements Serializable{

    private int id;
    private StringBuilder nombre;
    private StringBuilder responsable;
    private int nEmpleados;
    private int nPlanta;

    //En los constructores llamamos a los seters de los StringBuilder para ponerles tamaño
    public Departamento(int id, String nombre, String responsable, int nEmpleados, int nPlanta) {
        this.id = id;
        this.setNombre(nombre);
        this.setResponsable(responsable);
        this.nEmpleados = nEmpleados;
        this.nPlanta = nPlanta;
    }

    public Departamento(String nombre, String responsable, int nEmpleados, int nPlanta) {
        this.setNombre(nombre);
        this.setResponsable(responsable);
        this.nEmpleados = nEmpleados;
        this.nPlanta = nPlanta;
    }

    public int getnEmpleados() {
        return nEmpleados;
    }

    public void setnEmpleados(int nEmpleados) {
        this.nEmpleados = nEmpleados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre.toString();
    }

    public void setNombre(String nombre) {
        //Siempre primero valor y luego tamaño, sino se fuma el tamaño
        this.nombre = new StringBuilder(nombre);
        this.nombre.setLength(50);
    }

    public String getResponsable() {
        return responsable.toString();
    }

    public void setResponsable(String responsable) {
        this.responsable = new StringBuilder(responsable);
        this.nombre.setLength(30);
    }

    public int getnPlanta() {
        return nPlanta;
    }

    public void setnPlanta(int nPlanta) {
        this.nPlanta = nPlanta;
    }

    @Override
    public String toString() {
        return "id: " + id + ", nombre: " + nombre + ", responsable: " + responsable + ", nEmpleados: " + nEmpleados + ", nDepartamento: " + nPlanta;
    }
    
    public int getSize(){
        return 398;
    }
}
