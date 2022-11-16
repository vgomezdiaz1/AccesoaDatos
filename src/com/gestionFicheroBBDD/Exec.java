package com.gestionFicheroBBDD;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exec {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ControladorBBDD cm = null;
        try {
            cm = new ControladorBBDD("./DGT.bbdd");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al abrir la bbdd");
        }
        int n = 0;
        do {
            n = menu(sc);
            switch (n) {
                case 1:
                    crearMulta(sc, cm);
                    break;
                case 2:
                    crearAgente(sc, cm);
                    break;
                case 3:
                    eliminarMulta(sc, cm);
                    break;
                case 4:
                    eliminarAgente(sc, cm);
                    break;
                case 5:
                    modificarMulta(sc, cm);
                    break;
                case 6:
                    modificarAgente(sc, cm);
                    break;
                case 7:
                    pagarMulta(sc, cm);
                    break;
                case 8:
                    consultarMultasSinPagar(sc, cm);
                    break;
                case 9:
                    consultarTodosLosAgentesActivos(sc, cm);
                    break;
                case 10:
                    consultarMultasPorNombreDeAgente(sc, cm);
                    break;
                case 11:
                    consultarMultaPorID(sc, cm);
                    break;
                case 12:
                    consultarTodasLasMultas(sc, cm);
                    break;
                case 13:
                    System.out.println("Hasta luego");
                    break;
            }
        } while (n < 13);
    }

    public static int menu(Scanner sc) {
        int n = 0;
        do {
            System.out.println("Que quieres hacer: ");
            System.out.println("1-Crear multa: ");
            System.out.println("2-Crear agente: ");
            System.out.println("3-Eliminar multa: ");
            System.out.println("4-Eliminar agente: ");
            System.out.println("5-Modificar multa: ");
            System.out.println("6-Modificar agente:");
            System.out.println("7-Pagar multa:");
            System.out.println("8-Consultar todas las multas pendientes de pagar:");
            System.out.println("9-Consultar todos los agentes:");
            System.out.println("10-Buscar multas por nombre de agente");
            System.out.println("11-Buscar multas por número de multa");
            System.out.println("--------EXTRA--------");
            System.out.println("12-Consultar todas las multas pagadas o no");
            System.out.println("13-Salir");
            String seleccion = sc.nextLine();
            try {
                n = Integer.parseInt(seleccion);
            } catch (Exception e) {
                System.out.println("La opcion introducida no es valida.");
            }
            if (!(n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6
                    || n == 7 || n == 8 || n == 9 || n == 10 || n == 11
                    || n == 12 || n == 13)) {
                System.out.println("Opcion no valida");
            }
        } while (!(n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6
                || n == 7 || n == 8 || n == 9 || n == 10 || n == 11
                || n == 12 || n == 13));
        return n;
    }

    public static void crearMulta(Scanner sc, ControladorBBDD cm) {
        boolean existeAgente = false;
        double coste = -1;
        int nAgente = existeElAgente(sc, cm);
        System.out.println("Dime la localidad: ");
        String localidad = sc.nextLine();
        do {
            coste = -1;
            System.out.println("Dime el coste:");
            String c = sc.nextLine();
            try {
                coste = Integer.parseInt(c);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (coste < 0);
        Multa m = new Multa(localidad, coste, nAgente);
        try {
            cm.crearMulta(m);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void crearAgente(Scanner sc, ControladorBBDD ca) {
        System.out.println("Dime el nombre del agente: ");
        String nombre = sc.nextLine();
        Agente m = new Agente(nombre);
        try {
            ca.crearAgente(m);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void eliminarMulta(Scanner sc, ControladorBBDD cm) {
        int nMulta = conseguirNumero("numero de multa", sc);
        try {
            cm.borrarMulta(nMulta);
        } catch (Exception e) {
            System.out.println("La multa no existe");
        }
    }

    public static void eliminarAgente(Scanner sc, ControladorBBDD cm) {
        int nAgente = conseguirNumero("numero de agente", sc);
        try {
            cm.borrarAgente(nAgente);
        } catch (Exception e) {
        }
    }

    public static void modificarMulta(Scanner sc, ControladorBBDD cm) {
        double coste = -1;
        int nMulta = conseguirNumero("numero de multa", sc);
        Multa aux = null;
        try {
            aux = cm.consultarMulta(nMulta);
        } catch (SQLException ex) {
            Logger.getLogger(Exec.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (aux != null) {
            System.out.println("Este es la multa a modificar: " + aux.toString());
            int nAgente = existeElAgente(sc, cm);
            System.out.println("Dime la localidad: ");
            String localidad = sc.nextLine();
            do {
                coste = -1;
                System.out.println("Dime el coste:");
                String c = sc.nextLine();
                try {
                    coste = Integer.parseInt(c);
                } catch (Exception e) {
                    System.out.println("Lo introducido no es un numero");
                }
            } while (coste < 0);
            Multa m = new Multa(localidad, coste, nAgente);
            try {
                cm.modificarMulta(nMulta, m);
            } catch (SQLException ex) {
                Logger.getLogger(Exec.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Esa multa no existe");
        }
    }

    public static void modificarAgente(Scanner sc, ControladorBBDD cm) {
        int nAgente = existeElAgente(sc, cm);
        Agente aux;
        try {
            aux = cm.consultarAgente(nAgente);
            System.out.println("Este es el agente a modificar: " + aux.toString());
            if (aux != null) {
                System.out.println("Dime su nuevo nombre: ");
                String nombre = sc.nextLine();
                Agente a = new Agente(nombre);
                cm.modificarAgente(nAgente, a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exec.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void pagarMulta(Scanner sc, ControladorBBDD cm) {
        String respuesta = "";
        boolean posibilidad = false;
        boolean equivocado = false;
        boolean escapar = false;
        int nMulta = -1;
        do {
            equivocado = false;
            nMulta = consultarMultaPorID(sc, cm);
            do {
                System.out.println("¿Esta es la multa a pagar(S/N) o ponga una X para salir?");
                respuesta = sc.nextLine();
                if (respuesta.equalsIgnoreCase("S")) {
                    posibilidad = true;
                    equivocado = true;
                } else if (respuesta.equalsIgnoreCase("N")) {
                    equivocado = true;
                } else if (respuesta.equalsIgnoreCase("X")) {
                    posibilidad = true;
                    equivocado = true;
                    escapar = true;
                } else {
                    System.out.println("Respuesta incorrecta");
                }
            } while (!equivocado);
        } while (!posibilidad);
        if (!escapar) {
            System.out.println(nMulta);
            try {
                cm.pagarMulta(nMulta);
            } catch (SQLException ex) {
                Logger.getLogger(Exec.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void consultarMultasSinPagar(Scanner sc, ControladorBBDD cm) {
        ArrayList<Multa> al = new ArrayList<>();
        try {
            al = cm.consultarTodasMulta();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (Multa m : al) {
            System.out.println(m.toString());
        }
    }

    public static void consultarTodosLosAgentesActivos(Scanner sc, ControladorBBDD ca) {
        ArrayList<Agente> al = new ArrayList<>();
        try {
            al = ca.consultarTodasAgente();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (Agente a : al) {
            System.out.println(a.toString());
        }
    }

    public static int consultarMultaPorID(Scanner sc, ControladorBBDD cm) {
        int nMulta = existeLaMulta(sc, cm);
        try {
            Multa m = cm.consultarMulta(nMulta);
        } catch (SQLException ex) {
            Logger.getLogger(Exec.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nMulta;
    }

    public static void consultarMultasPorNombreDeAgente(Scanner sc, ControladorBBDD cm) {
        System.out.println("Dime el nombre del agente:");
        String nombre = sc.nextLine();
        int nAgente;
        try {
            nAgente = cm.consultarAgentePorNombre(nombre).getId();
            if (nAgente == -1) {
                System.out.println("No existe el agente con ese nombre");
            } else {
                ArrayList<Multa> al = cm.consultarTodasMultaPorAgente(nombre);
                for (Multa m : al) {
                    System.out.println(m.toString());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exec.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void consultarTodasLasMultas(Scanner sc, ControladorBBDD cm) {
        try {
            for (Multa m : cm.consultarTodasMulta()) {
                System.out.println(m.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exec.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int conseguirNumero(String necesidad, Scanner sc) {
        String n;
        int x = -1;
        do {
            x = -1;
            System.out.println("Dime " + necesidad + ":");
            n = sc.nextLine();
            try {
                x = Integer.parseInt(n);
            } catch (Exception e) {
                System.out.println("Lo introducido no es un numero");
            }
        } while (x < 0);
        return x;
    }

    public static int existeElAgente(Scanner sc, ControladorBBDD cm) {
        boolean existeAgente = false;
        double coste = -1;
        int nAgente = -1;
        do {
            try {
                nAgente = conseguirNumero("numero de agente", sc);
                Agente m = cm.consultarAgente(nAgente);
                System.out.println("El agente seleccionado es " + m.getNombre());
                existeAgente = true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("El agente no existe, seleccione otro");
            }
        } while (!existeAgente);
        return nAgente;
    }

    public static int existeLaMulta(Scanner sc, ControladorBBDD cm) {
        boolean existeMulta = false;
        double coste = -1;
        int nMulta = -1;
        do {
            try {
                nMulta = conseguirNumero("numero de multa", sc);
                Multa m = cm.consultarMulta(nMulta);
                System.out.println("La multa seleccionada es " + m.toString());
                existeMulta = true;
            } catch (Exception e) {
                System.out.println("La multa no existe, seleccione otra");
            }
        } while (!existeMulta);
        return nMulta;
    }
}
