package com;

import java.io.Serializable;

public class Departamento implements Serializable {

    private String nombre;
    private String responsable;
    private int nEmpleados;
    private int nPlanta;
    private String puerta;
    private boolean eliminado;

    public Departamento(String nombre, String responsable, int nEmpleados, int nPlanta, String puerta, boolean eliminado) {
        this.nombre = nombre;
        this.responsable = responsable;
        this.nEmpleados = nEmpleados;
        this.nPlanta = nPlanta;
        this.puerta = puerta;
        this.eliminado = eliminado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int getnEmpleados() {
        return nEmpleados;
    }

    public void setnEmpleados(int nEmpleados) {
        this.nEmpleados = nEmpleados;
    }

    public int getnPlanta() {
        return nPlanta;
    }

    public void setnPlanta(int nPlanta) {
        this.nPlanta = nPlanta;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public String toString() {
        return "Departamento{" + "nombre=" + nombre + ", responsable=" + responsable + ", nEmpleados=" + nEmpleados + ", nPlanta=" + nPlanta + ", puerta=" + puerta + ", eliminado=" + eliminado + '}';
    }

    

}
