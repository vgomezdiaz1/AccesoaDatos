package com.gestionFicheroBinario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestionDepartamento {

    public static void main(String[] args) {
        Departamento d = new Departamento(1, "IT", "Victor", 5, 5);
        File f = new File("./Departamentos.dat");
        try (FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream dos = new ObjectOutputStream(fos)) {
            dos.writeObject(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (FileInputStream fos = new FileInputStream(f);
                ObjectInputStream dos = new ObjectInputStream(fos)) {
            d = (Departamento) dos.readObject();
            System.out.println(d.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
