package com.gestionFicheroBinario;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionGenericaEmpleado {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = new File("./Empleados.dat");
        ControladorGenerico dp = new ControladorGenerico(f);
        int n = 0;
        do {
            n = menu(sc);
            switch (n) {
                case 1:
                    crearEmpleado(sc, dp);
                    break;
                case 2:
                    consultaEmpleado(sc, dp);
                    break;
                case 3:
                    consultaTodosEmpleado(dp);
                    break;
                case 4:
                    borrarEmpleado(sc, dp);
                    break;
                case 5:
                    modificarEmpleado(sc, dp);
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
            System.out.println("1-Crear empleado: ");
            System.out.println("2-Consulta empleado: ");
            System.out.println("3-Consulta todos los empleado: ");
            System.out.println("4-Borrar empleado: ");
            System.out.println("5-Modificar empleado: ");
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

    public static void crearEmpleado(Scanner sc, ControladorGenerico cp) {
        int m = -1;
        System.out.println("Dime el nombre:");
        String nombre = sc.nextLine();
        System.out.println("Dime el apellidos: ");
        String responsable = sc.nextLine();
        System.out.println("Dime el cargo: ");
        String cargo = sc.nextLine();
        do {
            m = -1;
            System.out.println("Dime su edad:");
            String nEmpleados = sc.nextLine();
            try {
                m = Integer.parseInt(nEmpleados);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (m < 0);
        Empleado d = new Empleado(nombre, responsable, cargo, m);
        System.out.println(cp.alta(d));
    }

    public static void consultaEmpleado(Scanner sc, ControladorGenerico cp) {
        int n = -1;
        do {
            System.out.println("Dime el empleado a consultar");
            String nEmpleados = sc.nextLine();
            try {
                n = Integer.parseInt(nEmpleados);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (n < 0);
        Empleado d = (Empleado) cp.consultaEspecifica(n);
        if (d == null) {
            System.out.println("No existe ese empleado");
        } else {
            System.out.println(cp.consultaEspecifica(n).toString());
        }
    }

    public static void consultaTodosEmpleado(ControladorGenerico cp) {
        ArrayList<Objeto> al = cp.consultaCompleta();
        for (Objeto d : al) {
            System.out.println(d.toString());
        }
    }

    public static void borrarEmpleado(Scanner sc, ControladorGenerico cp) {
        int n = -1;
        do {
            System.out.println("Dime el empleado a borrar");
            String nEmpleados = sc.nextLine();
            try {
                n = Integer.parseInt(nEmpleados);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (n < 0);
        cp.borrar(n);
    }

    public static void modificarEmpleado(Scanner sc, ControladorGenerico cp) {
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
        System.out.println("Este es el empleado que quieres modificar:");
        Empleado dep = (Empleado) cp.consultaEspecifica(m);
        System.out.println(dep.toString());
        if (!cp.consultaEspecifica(m).toString().equals("")) {
            System.out.println("Dime el nombre:");
            String nombre = sc.nextLine();
            System.out.println("Dime el apellido: ");
            String apellidos = sc.nextLine();
            System.out.println("Dime el cargo: ");
            String cargo = sc.nextLine();
            do {
                System.out.println("Dime su edad: ");
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
            if (apellidos.equals("")) {
                apellidos = dep.getApellidos();
            }
            if (cargo.equals("")) {
                cargo = dep.getCargo();
            }
            if (nEmpleados.equals("")) {
                n = dep.getEdad();
            }
            Empleado d = new Empleado(m, nombre, apellidos, cargo, n);
            cp.modificar(d);
            dep = (Empleado) cp.consultaEspecifica(m);
            System.out.println(dep.toString());
        } else {
            System.out.println("El departamento no existe");
        }
    }
}
