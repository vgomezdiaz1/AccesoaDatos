package com.gestionFicheroBBDD;

public class Agente {
    private int id;
    private String nombre;
    private boolean eliminado;

    public Agente(int id, String nombre, boolean eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
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

    @Override
    public String toString() {
        return "Agente{" + "id=" + id + ", nombre=" + nombre + ", eliminado=" + eliminado + '}';
    }
    
    
}
