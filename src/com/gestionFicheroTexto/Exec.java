package com.gestionFicheroTexto;

import java.io.File;

public class Exec {

    public static void main(String[] args) {
        Departamento dep1 = new Departamento(1, "IT", "Juan", 34);
        Departamento dep2 = new Departamento(1, "IT", "Victor", 3);
        Departamento dep3 = new Departamento(1, "IT", "Andrea", 4);
        ControladorDepartamentos cp = new ControladorDepartamentos(new File("./departamentos.txt"));
        cp.alta(dep1);
        cp.alta(dep2);
        cp.alta(dep3);
        cp.consultarTodosDepartamentos();
        System.out.println("");
        cp.consultarDepartamento(4);
        cp.borrarDepartamento(4);
        System.out.println("");
        cp.consultarTodosDepartamentos();
    }
}
