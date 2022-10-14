package com.leerFicheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LeerFicheroDesdeArgs {

    public static void main(String[] args) {
        if (args.length == 1) {
            File f = new File(args[0]);
            try (FileReader fr = new FileReader(f);
                    BufferedReader bf = new BufferedReader(fr)) {
                String linea;
                while ((linea = bf.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (Exception ex) {
                System.out.println("Error al leer el fichero");
            }
        }else{
            System.out.println("Necesito un argumento");
        }

    }
}
