package com.gestionFicheroBBDD;

public class Multa {
    private int id;
    private String localidad;
    private double coste;
    private boolean pagada;
    private boolean eliminado;
    private int idAgente;

    public Multa(int id, String localidad, double coste, boolean pagada, boolean eliminado, int idAgente) {
        this.id = id;
        this.localidad = localidad;
        this.coste = coste;
        this.pagada = pagada;
        this.eliminado = eliminado;
        this.idAgente = idAgente;
    }
    
    public Multa(String localidad, double coste, int idAgente) {
        this.localidad = localidad;
        this.coste = coste;
        this.pagada = false;
        this.eliminado = false;
        this.idAgente = idAgente;
    }

    public int getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(int idAgente) {
        this.idAgente = idAgente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public String toString() {
        return "Multas{" + "id=" + id + ", localidad=" + localidad + ", coste=" + coste + ", pagada=" + pagada + ", idAgente=" + idAgente;
    }
}
