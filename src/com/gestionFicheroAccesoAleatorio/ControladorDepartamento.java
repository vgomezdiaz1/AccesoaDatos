package com.gestionFicheroAccesoAleatorio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorDepartamento {

    private String nombreFichero;

    public ControladorDepartamento(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }

    public String getNombreFichero() {
        return nombreFichero;
    }

    public void setNombreFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }

    public void alta(Departamento d) throws FileNotFoundException {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(this.nombreFichero, "rw");
            raf.seek(raf.length());
        } catch (Exception ex) {
        }
        escribirDepartamento(raf, d);
    }

    public void baja(int posicion) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(this.nombreFichero, "rw");
            raf.seek(Departamento.getSize() * posicion);
        } catch (Exception ex) {
        }
        Departamento d = consultaUnDepartamento(posicion);
        d.setEliminado(true);
        escribirDepartamento(raf, d);
    }
    //En el constructor de RandomAccessFile es necesario poner primero el documento y en la coma el modo(r,rw,rws,rwd) 

    public void escribirDepartamento(RandomAccessFile raf, Departamento d) {
        try {
            raf.writeChars(d.getNombre().toString());
            raf.writeChars(d.getResponsable().toString());
            raf.writeInt(d.getnEmpleados());
            raf.writeInt(d.getnPlanta());
            raf.writeBoolean(d.isEliminado());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void modificado(int posicion, Departamento d) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(this.nombreFichero, "rw");
            raf.seek(Departamento.getSize() * posicion);
        } catch (Exception ex) {
        }
        escribirDepartamento(raf, d);
    }

    public Departamento consultaUnDepartamento(int posicion) {
        Departamento d = null;
        Departamento dep = null;
        try (RandomAccessFile raf = new RandomAccessFile("./departamento.dat", "r");) {
            raf.seek(Departamento.getSize() * posicion);
            d = (leerDepartamento(raf));
            if (!d.isEliminado()) {
                dep = d;
            }
        } catch (IOException ex) {
            System.out.println("Intentas leer fuera del fichero");
        }
        return dep;
    }

    public ArrayList<Departamento> consultaCompleta() {
        Departamento d = null;
        ArrayList<Departamento> al = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile("./departamento.dat", "r");) {
            int total = (int) (raf.length() / Departamento.getSize());
            for (int i = 0; i < total; i++) {
                d = (leerDepartamento(raf));
                if (!d.isEliminado()) {
                    al.add(d);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return al;
    }

    public Departamento leerDepartamento(RandomAccessFile raf) {
        Departamento d = null;
        try {
            byte[] nombreArray = new byte[100];
            raf.read(nombreArray);
            String nombre = new String(nombreArray);
            byte[] responsableArray = new byte[60];
            raf.read(responsableArray);
            String responsable = new String(responsableArray);
            int nEmpleado = raf.readInt();
            int nPlanta = raf.readInt();
            boolean borrado = raf.readBoolean();
            d = new Departamento(nombre, responsable, nEmpleado, nPlanta, borrado);
        } catch (Exception e) {
        }
        return d;
    }
}
