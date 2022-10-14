package com.gestionFicheroTexto;

public class Departamento {

    private int id;
    private String nombre;
    private String responsable;
    private int nEmpleados;

    public Departamento(int id, String nombre, String responsable, int nEmpleados) {
        this.id = id;
        this.nombre = nombre;
        this.responsable = responsable;
        this.nEmpleados = nEmpleados;
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

}
