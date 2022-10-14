package com.gestionFicheroTexto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ControladorDepartamentos {

    File f;

    public ControladorDepartamentos(File f) {
        this.f = f;
    }

    public void alta(Departamento d) {
        int n = leerUltimoId() + 1;
        try (FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(n + ";" + d.getNombre() + ";" + d.getResponsable() + ";" + d.getnEmpleados() + ";\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int leerUltimoId() {
        try (FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr)) {
            String linea;
            String ultima = "";
            while((linea = br.readLine())!=null){
                ultima = linea;
            }
            String[] array= ultima.split(";");
            return Integer.parseInt(array[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
