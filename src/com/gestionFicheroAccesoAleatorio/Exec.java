package com.gestionFicheroAccesoAleatorio;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Exec {

    public static void main(String[] args) {
        ControladorDepartamento cd = new ControladorDepartamento("./departamento.dat");
        Departamento d = new Departamento("ftuj", "6Gomez", 203, 405);
        Departamento d1 = new Departamento("ashbjgfjdgtyeuyyrtyrernehgfdhdhdfghdghd", "Vidfsghsgfsdgsdfgsdfgctor", 12, 3);
        try {
            cd.alta(d);
            cd.alta(d1);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        ArrayList<Departamento> al = cd.consultaCompleta();
        for (Departamento dep : al) {
            System.out.println(d);
        }
        
        
        //System.out.println("->" + cd.consultaUnDepartamento(6).toString());
            
        //cd.modificado(5, d1);
        
        //System.out.println("->" + cd.consultaUnDepartamento(5).toString());
    }
}
