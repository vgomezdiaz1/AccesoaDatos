package com.gestionFicheroAccesoAleatorio;

import java.io.Serializable;

public class Departamento implements Serializable{

    private StringBuilder nombre;
    private StringBuilder responsable;
    private int nEmpleados;
    private int nPlanta;
    private boolean eliminado;

    //En los constructores llamamos a los seters de los StringBuilder para ponerles tamaño
    public Departamento(String nombre, String responsable, int nEmpleados, int nPlanta) {
        this.setNombre(nombre);
        this.setResponsable(responsable);
        this.nEmpleados = nEmpleados;
        this.nPlanta = nPlanta;
        this.eliminado = false;
    }
    
    public Departamento(String nombre, String responsable, int nEmpleados, int nPlanta, boolean eliminado) {
        this.setNombre(nombre);
        this.setResponsable(responsable);
        this.nEmpleados = nEmpleados;
        this.nPlanta = nPlanta;
        this.eliminado = eliminado;
    }

    public int getnEmpleados() {
        return nEmpleados;
    }

    public void setnEmpleados(int nEmpleados) {
        this.nEmpleados = nEmpleados;
    }

    public StringBuilder getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        //Siempre primero valor y luego tamaño, sino se fuma el tamaño
        this.nombre = new StringBuilder(nombre);
        this.nombre.setLength(50);
    }

    public StringBuilder getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = new StringBuilder(responsable);
        this.responsable.setLength(30);
    }

    public int getnPlanta() {
        return nPlanta;
    }

    public void setnPlanta(int nPlanta) {
        this.nPlanta = nPlanta;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre + ", responsable: " + responsable + ", nEmpleados: " + nEmpleados + ", nDepartamento: " + nPlanta;
    }
    
    public static int getSize(){
        return 169;
    }
}
