package EjerciciosUT01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Ejercicio4Haches {

    public static void main(String[] args) {
        int h = 0;
        File f = new File("./pruebahaches.txt");
        try (FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr)) {
            String linea = bf.readLine();
            while (linea != null) {
                for (int i = 0; i < linea.length(); i++) {
                    String letra = Character.toString(linea.charAt(i));
                    if (letra.equals("h")) {
                        String c = Character.toString(linea.charAt(i - 1));
                        if (!(c.equalsIgnoreCase("c") || c.equalsIgnoreCase(" "))) {
                            h++;
                        }
                    }
                }
                linea = bf.readLine();
            }
            System.out.println("En todo el texto hay " + h + " h intercalada");

        } catch (Exception e) {
            e.printStackTrace();
        }
        h = 0;
        try (FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr)) {
            String linea = bf.readLine();
            while (linea != null) {
                for (int i = 0; i < linea.length(); i++) {
                    String letra = Character.toString(linea.charAt(i));
                    if (letra.equals("h")) {
                        String c = Character.toString(linea.charAt(i - 1));
                        if (!(c.equalsIgnoreCase("c") || c.equalsIgnoreCase(" "))) {
                            h++;
                            while(letra.equals(" ")){
                                letra = Character.toString(linea.charAt(i++));
                            }
                        }
                    }
                }
                linea = bf.readLine();
            }
            System.out.println("En todo el texto hay " + h + " h intercalada");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
