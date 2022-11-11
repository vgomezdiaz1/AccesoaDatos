package com.gestionBBDDSqlite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exec {

    public static void main(String[] args) {
        Departamento d = new Departamento("IT", "Victor", 3, 5);
        Departamento d1 = new Departamento("Hardware", "Juan", 5, 8);
        ControladorDepartamento cd = null;
        try {
            cd = new ControladorDepartamento("departamentos.sql");
        } catch (SQLException ex) {
            System.out.println("Error al abrir la BBDD");
        }
        Scanner sc = new Scanner(System.in);
        int n = 0;
        do {
            n = menu(sc);
            switch (n) {
                case 1:
                    crearDepartamento(sc, cd);
                    break;
                case 2:
                    consultaDepartamento(sc, cd);
                    break;
                case 3:
                    consultaTodosDepartamento(cd);
                    break;
                case 4:
                    borrarDepartamento(sc, cd);
                    break;
                case 5:
                    modificarDepartamento(sc, cd);
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
        try {
            cp.insertarDepartamento(d);
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
        Departamento d;
        try {
            d = cp.consultarDepartamento(n);
            if (d == null) {
                System.out.println("No existe ese departamento");
            } else {
                System.out.println(cp.consultarDepartamento(n).toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exec.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void consultaTodosDepartamento(ControladorDepartamento cp) {
        ArrayList<Departamento> al;
        try {
            al = cp.consultarTodosDepartamentos();
            for (Departamento d : al) {
                System.out.println(d.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exec.class.getName()).log(Level.SEVERE, null, ex);
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
            cp.borrarDepartamento(n);
        } catch (Exception e) {
            System.out.println("El departamento no existe");
        }

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
        try {
            Departamento dep = cp.consultarDepartamento(m);
            System.out.println("Este es el departamento que quieres modificar:");
            System.out.println(dep.toString());
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
                nombre = dep.getNombre().toString();
            }
            if (responsable.equals("")) {
                responsable = dep.getResponsable().toString();
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
            Departamento d = new Departamento(nombre, responsable, n, m);
            cp.modificarDepartamento(m, d);
            dep = cp.consultarDepartamento(m);
            System.out.println(dep.toString());
        } catch (Exception e) {
            System.out.println("El departamento no existe");
        }
    }
}
