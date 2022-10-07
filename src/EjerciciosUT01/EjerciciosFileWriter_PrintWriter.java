package EjerciciosUT01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class EjerciciosFileWriter_PrintWriter {

    public static void main(String[] args) {
        //Escriba un programa en Java que lea un fichero de texto con 
        //varias lineas y cuente cuantas vocales hay de cada
        //Guardalas en un array de 5 posiciones
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("a", 0);
        hm.put("e", 0);
        hm.put("i", 0);
        hm.put("o", 0);
        hm.put("u", 0);
        try (FileReader fr = new FileReader("textoparasabervocales.txt");
                BufferedReader bf = new BufferedReader(fr)) {
            while (bf.readLine() != null) {
                String texto = bf.readLine();
                for (int i = 0; i < texto.length(); i++) {
                    char letra = texto.charAt(i);
                    if (letra == 'a') {
                        hm.put("a", hm.get("a") + 1);
                    }
                    if (letra == 'e') {
                        hm.put("e", hm.get("e") + 1);
                    }
                    if (letra == 'i') {
                        hm.put("i", hm.get("i") + 1);
                    }
                    if (letra == 'o') {
                        hm.put("o", hm.get("o") + 1);
                    }
                    if (letra == 'u') {
                        hm.put("u", hm.get("u") + 1);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String s : hm.keySet()) {
            System.out.println("La letra " + s + " aparece " + hm.get(s));
        }
        //Crea un programa que cuente cuantas palabras hay en un archivo de texto
        int contador = 0;
        try (FileReader fr = new FileReader("textoparasabervocales.txt");
                BufferedReader bf = new BufferedReader(fr)) {
            while (bf.readLine() != null) {
                String texto = bf.readLine();
                String [] palabras = texto.split(texto);
                for (String p : palabras) {
                    System.out.println(p);
                }
                contador += palabras.length;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("El texto tienes " + contador + " palabras");
    }

}
