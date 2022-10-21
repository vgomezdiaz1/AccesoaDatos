package com.gestionFicheroBinario;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionDepartamento {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = new File("./Departamentos.dat");
        ControladorDepartamento dp = new ControladorDepartamento(f);
        int n = 0;
        do {
            n = menu(sc);
            switch (n) {
                case 1:
                    crearDepartamento(sc, dp);
                    break;
                case 2:
                    consultaDepartamento(sc, dp);
                    break;
                case 3:
                    consultaTodosDepartamento(dp);
                    break;
                case 4:
                    borrarDepartamento(sc, dp);
                    break;
                case 5:
                    modificarDepartamento(sc, dp);
                    break;
                case 6:
                    System.out.println("Hasta luego");
                    break;
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

    public static void crearDepartamento(Scanner sc, ControladorDepartamento cp) {
        int n = -1;
        int m = -1;
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
        do {
            m = -1;
            System.out.println("Dime el numero de planta");
            String nEmpleados = sc.nextLine();
            try {
                m = Integer.parseInt(nEmpleados);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (m < 0);
        Departamento d = new Departamento(nombre, responsable, n, m);
        System.out.println(cp.alta(d));
    }

    public static void consultaDepartamento(Scanner sc, ControladorDepartamento cp) {
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
        System.out.println(cp.consultaEspecifica(n).toString());
    }

    public static void consultaTodosDepartamento(ControladorDepartamento cp) {
        ArrayList<Departamento> al = cp.consultaCompleta();
        for (Departamento d : al) {
            System.out.println(d.toString());
        }
    }

    public static void borrarDepartamento(Scanner sc, ControladorDepartamento cp) {
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

    public static void modificarDepartamento(Scanner sc, ControladorDepartamento cp) {
        int m = -1;
        int n = -1;
        int x = -1;
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
        Departamento dep = cp.consultaEspecifica(m);
        System.out.println(dep.toString());
        if (!cp.consultaEspecifica(m).toString().equals("")) {
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
            if (nombre.equals("")) {
                nombre = dep.getNombre();
            }
            if (responsable.equals("")) {
                responsable = dep.getResponsable();
            }
            if (nEmpleados.equals("")) {
                n = dep.getnEmpleados();
            }
            do {
                System.out.println("Dime el numero de planta: ");
                nEmpleados = sc.nextLine();
                try {
                    x = Integer.parseInt(nEmpleados);
                } catch (Exception e) {
                    System.out.println("Lo introducido no es un numero");
                }
            } while (x < 0 || nEmpleados.equals(""));
            Departamento d = new Departamento(m, nombre, responsable, n, x);
            cp.modificarDepartamento(d);
            dep = cp.consultaEspecifica(m);
            System.out.println(dep.toString());
        } else {
            System.out.println("El departamento no existe");
        }
    }
}
