package EjerciciosUT01;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio7NotaMedia {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombre;
        double nota = 0;
        double notaMedia = 0;
        double contador = 0;
        String respuesta;
        notas n;
        ArrayList<notas> al = new ArrayList<>();
        ArrayList<notas> alf = new ArrayList<>();
        do {
            System.out.print("Introduce el nombre de la asignatura: ");
            nombre = sc.nextLine();
            System.out.print("Introduce la nota de " + nombre + ": ");
            try {
                nota = Double.parseDouble(sc.nextLine());
                n = new notas(nombre, nota);
                al.add(n);
            } catch (Exception e) {
                System.out.println("El valor que has introducido es incorrecto");
            }
            do {
                System.out.print("¿Quieres introducir otro nota(si/no)? ");
                respuesta = sc.nextLine();
                if (!(respuesta.equals("si") || respuesta.equals("no"))) {
                    System.out.println("Respuesta incorrecta");
                }
            } while (!(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("no")));

        } while (!respuesta.equalsIgnoreCase("no"));
        do {
            System.out.print("¿Quieres sobreescribir los datos anteriores? ");
            respuesta = sc.nextLine();
            if (!(respuesta.equals("si") || respuesta.equals("no"))) {
                System.out.println("Respuesta incorrecta");
            }
        } while (!(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("no")));
        if (respuesta.equalsIgnoreCase("si")) {
            try (FileOutputStream fos = new FileOutputStream("./notas.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (notas o : al) {
                    oos.writeObject(o);
                }
            } catch (Exception e) {
                System.out.println("Error al escribir en el fichero");
            }
        }else{
            try (FileOutputStream fos = new FileOutputStream("./notas.txt",true);
                    MiObjectOutputStream oos = new MiObjectOutputStream(fos)) {
                for (notas o : al) {
                    oos.writeObject(o);
                }
            } catch (Exception e) {
                System.out.println("Error al escribir en el fichero");
            }
        }

        try (FileInputStream fos = new FileInputStream("./notas.txt");
                ObjectInputStream ois = new ObjectInputStream(fos)) {
            while (true) {
                notas t = (notas) ois.readObject();
                alf.add(t);
            }
        } catch (EOFException ex) {
            for (notas o : alf) {
                notaMedia += o.getNota();
                contador++;
            }
            System.out.println("La nota media es: " + (notaMedia / contador));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
