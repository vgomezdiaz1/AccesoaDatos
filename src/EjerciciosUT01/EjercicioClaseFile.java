package EjerciciosUT01;

import java.io.File;
import java.io.FileWriter;

public class EjercicioClaseFile {

    public static void main(String[] args) {
        //Crear un programa que muestre todas las entradas 
        //del directorio del usuario actual
        File f = new File("./");
        String[] carpetas = f.list();
        for (String carpeta : carpetas) {
            System.out.println(carpeta);
        }
        //Crear un programa que muestre ademas los directorios que cuelgan de el, 
        //mostrando el nombre y tipo de cada fichero o directorio
        for (String carpeta : carpetas) {
            comprobarQueEs(carpeta);
        }
        //Crear un programa que cree la siguiente estructura de directorios y ficheros:
        //home
        //  directorio1
        //      f1.txt
        //  directorio2
        //      f2.txt
        File general = new File("/home/dev/general");
        if (!general.exists()) {
            System.out.println("Creado");
            general.mkdir();
        } else {
            System.out.println("Ya existe");
        }
        int cantidad = 2;
        for (int i = 0; i < cantidad; i++) {
            File f1 = new File("/home/dev/general/directorio" + (i + 1));
            if (!f1.exists()) {
                System.out.println("Creado");
                f1.mkdir();
                File f2 = new File(f1 + "/f" + (i + 1) + ".txt");
                try (FileWriter fw = new FileWriter(f2);) {
                    f2.createNewFile();
                    fw.write("Fichero numero " + (i + 1) + " creado.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Ya existe");
            }
        }
        //Crear un programa que renombre el fichero f1.txt por f11.txt;
        //mueva el fichero f2.txt al directorio1 y borre directorio2
        for (int i = 0; i < cantidad; i++) {
            general = new File("/home/dev/general");
            String[] comprobacion = general.list();
            for (String s : comprobacion) {
                File comp = new File(general + "/" + s);
                if (comp.isDirectory()) {
                    String[] comprobacionSup = comp.list();
                    for (String t : comprobacionSup) {
                        File compSup = new File(comp + "/" + t);
                        if (t.equals("f1.txt")) {
                            File sustituto = new File(comp + "/f11.txt");
                            compSup.renameTo(sustituto);
                            System.out.println("Nombre cambiado de " + compSup + " a " + sustituto);
                        }
                        if(compSup.getPath().equals("/home/dev/general/directorio2/f2.txt")){
                            File sustituto = new File("/home/dev/general/directorio1/f2.txt");
                            compSup.renameTo(sustituto);
                            System.out.println("Movido de " + compSup + " a " + sustituto);
                            if(comp.getName().equals("directorio2")){
                                comp.delete();
                                System.out.println("Borrado " + comp);
                            }                            
                        }
                    }
                }
            }
        }
    }

    public static void comprobarQueEs(String m) {
        File f = new File("./" + m);
        if (f.isDirectory()) {
            System.out.println(m + " es un directorio");
            String[] listado = f.list();
            for (String s : listado) {
                comprobarQueEs(m + "/" + s);
            }
        } else if (f.isFile()) {
            System.out.println(m + " es un archivo");
        } else {
            System.out.println(m + " no se lo que es");
        }
    }
}
