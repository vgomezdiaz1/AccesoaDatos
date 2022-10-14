package com.gestionFicheroTexto;

import java.io.File;

public class Exec {

    public static void main(String[] args) {
        Departamento dep1 = new Departamento(1, "IT", "Juan", 34);
        ControladorDepartamentos cp = new ControladorDepartamentos(new File ("./departamentos.txt"));
        cp.alta(dep1);
    }
}
