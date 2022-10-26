package com.gestionFicheroAccesoAleatorio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Exec {

    public static void main(String[] args) {
        //En el constructor de RandomAccessFile es necesario poner primero el documento y en la coma el modo(r,rw,rws,rwd)
        try (RandomAccessFile raf = new RandomAccessFile("./departamento.dat", "rw");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            //Creamos los departamentos
            raf.seek(0);
            ArrayList<Departamento> al = new ArrayList<>();
            Departamento dep = new Departamento(1, "IT", "Gomez", 23, 45);
            oos.writeObject(dep);
            //Borramos la cache por si acaso
            oos.flush();
            //Lo combertimos a Bytes
            byte[] depByte = baos.toByteArray();
            //Lo escribimos en el fichero
            raf.write(depByte);
            System.out.println(raf.length());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try (RandomAccessFile raf = new RandomAccessFile("./departamento.dat", "rw");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            raf.seek(398);
            Departamento dep1 = new Departamento(2, "Ventas", "Diaz", 4, 5);
            oos.writeObject(dep1);
            //Borramos la cache por si acaso
            oos.flush();
            //Lo combertimos a Bytes
            byte[] depByte = baos.toByteArray();
            //Lo escribimos en el fichero
            raf.write(depByte);
            System.out.println(raf.length());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        //Lectura
        ByteArrayInputStream baos = null;
        ObjectInputStream oos = null;
        try (RandomAccessFile raf = new RandomAccessFile("./departamento.dat", "rw");) {
            raf.seek(0);
            byte[] array = new byte[398];
            raf.read(array, 0, 398);
            baos = new ByteArrayInputStream(array);
            oos = new ObjectInputStream(baos);
            Departamento dep = (Departamento) oos.readObject();
            System.out.println(dep.toString());

            raf.seek(398);
            array = new byte[398];
            raf.read(array, 398, 796);
            baos = new ByteArrayInputStream(array);
            oos = new ObjectInputStream(baos);
            dep = (Departamento) oos.readObject();
            System.out.println(dep.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
