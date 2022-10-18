package com.gestionFicheroTexto;

import java.io.File;
import java.util.Scanner;

public class Exec {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ControladorDepartamentos cp = new ControladorDepartamentos(new File("./departamentos.txt"));
        int n = 0;
        do {
            n = menu(sc);
            if (n == 1) {
                crearDepartamento(sc, cp);
            } else if (n == 2) {
                consultaDepartamento(sc, cp);
            } else if (n == 3) {
                consultaTodosDepartamento(cp);
            } else if (n == 4) {
                borrarDepartamento(sc, cp);
            } else if (n == 5) {
                modificarDepartamento(sc, cp);
            } else if (n >= 6) {
                System.out.println("Hasta luego");
            }
        } while (n < 6);
    }

    public static int menu(Scanner sc) {
        int n = 0;
        do {
            System.out.println("Que quieres hacer: ");
            System.out.println("1-Crear departamento: ");
            System.out.println("2-Consulta departamento: ");
            System.out.println("3-Consulta todos los departamentos: ");
            System.out.println("4-Borrar departamento: ");
            System.out.println("5-Modificar departamento: ");
            System.out.println("6-Salida");
            String seleccion = sc.nextLine();
            try {
                n = Integer.parseInt(seleccion);
            } catch (Exception e) {
                System.out.println("La opcion introducida no es valida.");
            }
            if (!(n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6)) {
                System.out.println("Opcion no valida");
            }
        } while (!(n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6));
        return n;
    }

    public static void crearDepartamento(Scanner sc, ControladorDepartamentos cp) {
        int n = -1;
        System.out.println("Dime el nombre del departamento");
        String nombre = sc.nextLine();
        System.out.println("Dime el responsable: ");
        String responsable = sc.nextLine();
        do {
            n = -1;
            System.out.println("Dime el numero de empleados");
            String nEmpleados = sc.nextLine();
            try {
                n = Integer.parseInt(nEmpleados);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (n < 0);
        Departamento d = new Departamento(1, nombre, responsable, n);
        cp.alta(d);
    }

    public static void consultaDepartamento(Scanner sc, ControladorDepartamentos cp) {
        int n = -1;
        do {
            System.out.println("Dime el departamento a consultar");
            String nEmpleados = sc.nextLine();
            try {
                n = Integer.parseInt(nEmpleados);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (n < 0);
        cp.consultarDepartamento(n);
    }

    public static void consultaTodosDepartamento(ControladorDepartamentos cp) {
        cp.consultarTodosDepartamentos();
    }

    public static void borrarDepartamento(Scanner sc, ControladorDepartamentos cp) {
        int n = -1;
        do {
            System.out.println("Dime el departamento a borrar");
            String nEmpleados = sc.nextLine();
            try {
                n = Integer.parseInt(nEmpleados);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (n < 0);
        cp.borrarDepartamento(n);
    }

    public static void modificarDepartamento(Scanner sc, ControladorDepartamentos cp) {
        int m = -1;
        int n = -1;
        String nEmpleados = "";
        do {
            System.out.println("Dime el id:");
            nEmpleados = sc.nextLine();
            try {
                m = Integer.parseInt(nEmpleados);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (m < 0);
        System.out.println("Este es el departamento que quieres modificar:");
        cp.consultarDepartamento(m);
        Departamento dep = cp.devueltaDepartamento(m);
        System.out.println("Dime el nombre del departamento:");
        String nombre = sc.nextLine();
        System.out.println("Dime el responsable: ");
        String responsable = sc.nextLine();
        do {
            System.out.println("Dime el numero de empleados: ");
            nEmpleados = sc.nextLine();
            try {
                n = Integer.parseInt(nEmpleados);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (n < 0 || nEmpleados.equals(""));
        if(nombre.equals("")){
            nombre = dep.getNombre();
        }
        if(responsable.equals("")){
            responsable = dep.getResponsable();
        }
        if(nEmpleados.equals("")){
            n = dep.getnEmpleados();
        }
        Departamento d = new Departamento(m, nombre, responsable, n);
        cp.modificarDepartamento(d);
    }
}
