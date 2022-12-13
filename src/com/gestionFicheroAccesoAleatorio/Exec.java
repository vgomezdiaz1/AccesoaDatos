package com.gestionFicheroAccesoAleatorio;

import java.util.ArrayList;
import java.util.Scanner;

public class Exec {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ControladorDepartamento dp = new ControladorDepartamento("./departamento.dat");
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
        System.out.println("Dime la puerta: ");
        String puerta = sc.nextLine();
        Departamento d = new Departamento(nombre, responsable, n, m, puerta);
        try {
            cp.alta(d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        Departamento d = cp.consultaUnDepartamento(n);
        if (d == null) {
            System.out.println("No existe ese departamento");
        } else {
            System.out.println(cp.consultaUnDepartamento(n).toString());
        }
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
            String id = sc.nextLine();
            try {
                n = Integer.parseInt(id);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (n < 0);
        try {
            cp.baja(n);
        } catch (Exception e) {
            System.out.println("El departamento no existe");
        }

    }

    public static void modificarDepartamento(Scanner sc, ControladorDepartamento cp) {
        int m = -1;
        int n = -1;
        int x = -1;
        String id = "";
        do {
            System.out.println("Dime el id:");
            id = sc.nextLine();
            try {
                m = Integer.parseInt(id);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (m < 0);
        try {
            Departamento dep = cp.consultaUnDepartamento(m);
            System.out.println("Este es el departamento que quieres modificar:");
            System.out.println(dep.toString());
            System.out.println("Dime el nombre del departamento:");
            String nombre = sc.nextLine();
            System.out.println("Dime el responsable: ");
            String responsable = sc.nextLine();
            do {
                System.out.println("Dime el numero de empleados: ");
                id = sc.nextLine();
                try {
                    n = Integer.parseInt(id);
                } catch (Exception e) {
                    System.out.println("Lo introducido no es un numero");
                }
            } while (n < 0 || id.equals(""));
            if (nombre.equals("")) {
                nombre = dep.getNombre().toString();
            }
            if (responsable.equals("")) {
                responsable = dep.getResponsable().toString();
            }
            if (id.equals("")) {
                n = dep.getnEmpleados();
            }
            do {
                System.out.println("Dime el numero de planta: ");
                id = sc.nextLine();
                try {
                    x = Integer.parseInt(id);
                } catch (Exception e) {
                    System.out.println("Lo introducido no es un numero");
                }
            } while (x < 0 || id.equals(""));
            System.out.println("Dime la puerta: ");
            String puerta = sc.nextLine();
            Departamento d = new Departamento(nombre, responsable, n, m, puerta);
            cp.modificado(m, d);
            dep = cp.consultaUnDepartamento(m);
            System.out.println(dep.toString());
        } catch (Exception e) {
            System.out.println("El departamento no existe");
        }

    }
}
