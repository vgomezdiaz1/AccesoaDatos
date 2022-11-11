package com;

import java.util.ArrayList;

public class DepartamentosArrayList {

    private ArrayList<Departamento> al = new ArrayList<>();

    public ArrayList<Departamento> getAl() {
        return al;
    }

    public void setAl(ArrayList<Departamento> al) {
        this.al = al;
    }

    @Override
    public String toString() {
        String cadena = "";
        for (Departamento d : al) {
            cadena += d.toString() + "\n";
        }
        return cadena;
    }

}
