package Ejemplos1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PrimerDia {

    public static void main(String[] args) {
        //Con esta propiedad te dice la carpeta del usuario actual
        System.out.println("Hola " + System.getProperty("user.home"));
        //Con esta propiedad te dice la carpeta actual
        System.out.println("Hola " + System.getProperty("user.dir"));
        //Con esta propiedad te dice el SO
        System.out.println("Hola " + System.getProperty("os.name"));
        //Te dice la version de java
        System.out.println("Hola " + System.getProperty("java.version"));
        //Creamos un fichero
        File f = new File("./prueba");
        try (FileWriter fw = new FileWriter(f, true);) {
            fw.write("Hola que ases" + "\n");
        } catch (IOException ex) {
            System.out.println("error");
        }
        try (FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr)) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (Exception e) {
            System.out.println("Ha pasado algo al leer");
        }
        System.out.println(f.exists());
    }
}
