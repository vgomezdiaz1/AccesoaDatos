package com.gestionFicheroAccesoAleatorio;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Exec {

    public static void main(String[] args) {
        ControladorDepartamento cd = new ControladorDepartamento("./departamento.dat");
        Departamento d = new Departamento("ftquj", "6Gomez", 203, 405);
        Departamento d1 = new Departamento("ashbjgfjdgtyeuyyrtyrernehgfdhdhdfghdghd", "Vidfsghsgfsdgsdfgsdfgctor", 12, 3);
        Departamento d2 = new Departamento("ashbjgfjdgtyrnehgfdhdhdfghdghd", "Vidfsghsgffgctor", 23, 5);
        Departamento d3 = new Departamento("Victor", "Gomez", 58, 9);
        Departamento d4 = new Departamento("Ismael", "Perez", 8, 8);
        try {
            cd.alta(d);
            cd.alta(d1);
            cd.alta(d2);
            cd.alta(d3);
            cd.alta(d4);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        ArrayList<Departamento> al = cd.consultaCompleta();
        int contador = 0;
        for (Departamento dep : al) {
            System.out.println(contador + " " +dep);
            contador++;
        }
        System.out.println("------------------------------");
        System.out.println(cd.consultaUnDepartamento(1).toString());
        try {
           cd.baja(2); 
        } catch (Exception e) {
            System.out.println("Departamento ya borrado");
        }
        System.out.println("------------------------------");
        
        al = cd.consultaCompleta();
        contador = 0;
        for (Departamento dep : al) {
            System.out.println(contador + " " +dep);
            contador++;
        }
        Departamento d5 = new Departamento("Andrea", "Urtiaga", 5, 89);
        cd.modificado(3, d5);
        System.out.println("------------------------------");
        al = cd.consultaCompleta();
        contador = 0;
        for (Departamento dep : al) {
            System.out.println(contador + " " +dep);
            contador++;
        }
    }
}
