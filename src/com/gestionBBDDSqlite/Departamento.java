package com.gestionBBDDSqlite;

public class Departamento {

    private int id;
    private String nombre;
    private String responsable;
    private int empleados;
    private int nPlanta;

    public Departamento(int id, String nombre, String responsable, int empleados, int nPlanta) {
        this.id = id;
        this.nombre = nombre;
        this.responsable = responsable;
        this.empleados = empleados;
        this.nPlanta = nPlanta;
    }
    
    public Departamento(String nombre, String responsable, int empleados, int nPlanta) {
        this.nombre = nombre;
        this.responsable = responsable;
        this.empleados = empleados;
        this.nPlanta = nPlanta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getnPlanta() {
        return nPlanta;
    }

    public void setnPlanta(int nPlanta) {
        this.nPlanta = nPlanta;
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

    public int getEmpleados() {
        return empleados;
    }

    public void setEmpleados(int empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "id=" + id + " nombre=" + nombre + ", responsable=" + responsable + ", empleados=" + empleados + ", nPlanta=" + nPlanta + '}';
    }

}
