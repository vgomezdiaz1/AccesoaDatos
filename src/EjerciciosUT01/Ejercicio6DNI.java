package EjerciciosUT01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Ejercicio6DNI {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] letrasDNI = {"T", "R", "W", "A", "G", "M", "Y", "F", "P",
            "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "k", "E"};
        String DNI = "";
        String letra = "";
        int dniNum;
        String respuesta;
        File f = new File("./dni.bin");
        do {
            System.out.print("Dime un DNI: ");
            DNI = sc.nextLine();
            if (DNI.length() != 9) {
                System.out.println("La extension del DNI no corresponde");
            } else {
                letra = Character.toString(DNI.charAt(8));
                DNI = DNI.substring(0, 8);
                try {
                    dniNum = Integer.parseInt(DNI);
                    if (letrasDNI[dniNum % 23].equalsIgnoreCase(letra)) {
                        System.out.println("DNI correcto");

                        try (FileWriter fw = new FileWriter(f, true)) {
                            fw.write(DNI + letra.toUpperCase() + ";\n");
                        } catch (Exception e) {
                            System.out.println("Se ha producido un error en la escritura");
                        }
                    } else {
                        System.out.println("DNI incorrecto");
                    }
                } catch (Exception e) {
                    System.out.println("Error al introducir el DNI");
                }

            }
            do {
                System.out.print("¿Quieres introducir otro DNI(si/no)? ");
                respuesta = sc.nextLine();
                if (!(respuesta.equals("si") || respuesta.equals("no"))) {
                    System.out.println("Respuesta incorrecta");
                }
            } while (!(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("no")));

        } while (!respuesta.equalsIgnoreCase("no"));
        System.out.println("Los DNI que tenemos en la lista son: ");
        try (FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr)) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el fichero " + f.getPath());
        }
    }
}
