package com.gestionFicheroAccesoAleatorio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

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

    //En el constructor de RandomAccessFile es necesario poner primero el documento y en la coma el modo(r,rw,rws,rwd) 
    public void alta(Departamento d) throws FileNotFoundException {
        try (RandomAccessFile raf = new RandomAccessFile(this.nombreFichero, "rw");) {
            raf.seek(raf.length());
            raf.write(String.valueOf(d.getNombre()).getBytes(), 0, 50);
            raf.write(String.valueOf(d.getResponsable()).getBytes(), 0, 30);
            raf.writeInt(d.getnEmpleados());
            raf.writeInt(d.getnPlanta());
            raf.writeBoolean(d.isEliminado());
            System.out.println(raf.length());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void baja(int posicion) {
        Departamento dep = null;
        ByteArrayInputStream bios = null;
        ObjectInputStream ois = null;
        int pos = posicion * Departamento.getSize();
        try (RandomAccessFile raf = new RandomAccessFile("./departamento.dat", "rw");) {
            raf.seek(pos);
            byte[] array = new byte[Departamento.getSize()];
            raf.read(array, 0, Departamento.getSize());
            bios = new ByteArrayInputStream(array);
            ois = new ObjectInputStream(bios);
            dep = (Departamento) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (bios != null) {
                try {
                    bios.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        dep.setEliminado(true);
        try (RandomAccessFile raf = new RandomAccessFile(this.nombreFichero, "rw");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            raf.seek(pos);
            oos.writeObject(dep);
            oos.flush();
            byte[] depByte = baos.toByteArray();
            raf.write(depByte);
            System.out.println(raf.length());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void modificado(int posicion, Departamento d) {
        int pos = posicion * Departamento.getSize();
        try (RandomAccessFile raf = new RandomAccessFile(this.nombreFichero, "rw");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            raf.seek(pos);
            oos.writeObject(d);
            oos.flush();
            byte[] depByte = baos.toByteArray();
            raf.write(depByte);
            System.out.println(raf.length());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Departamento consultaUnDepartamento(int posicion) {
        Departamento d = null;
        ByteArrayInputStream baos = null;
        ObjectInputStream oos = null;
        try (RandomAccessFile raf = new RandomAccessFile("./departamento.dat", "rw");) {
            int pos = posicion * Departamento.getSize();
            raf.seek(pos);
            byte[] array = new byte[Departamento.getSize()];
            raf.read(array, 0, Departamento.getSize());
            baos = new ByteArrayInputStream(array);
            oos = new ObjectInputStream(baos);
            d = (Departamento) oos.readObject();
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
        return d;
    }

    public ArrayList<Departamento> consultaCompleta() {
        ArrayList<Departamento> al = new ArrayList<>();
        Departamento d = null;
        ByteArrayInputStream bios = null;
        ObjectInputStream ois = null;
        try (RandomAccessFile raf = new RandomAccessFile("./departamento.dat", "r");) {
            int total = (int) (raf.length() / Departamento.getSize());
            byte[] nombre = null;
            byte[] responsable = null;
            for (int i = 0; i < total; i++) {
                raf.read(nombre, 0, 50);
                raf.read(responsable, 0, 30);
                int n = raf.readInt();
                int m = raf.readInt();
                boolean x = raf.readBoolean();
                d = new Departamento(nombre.toString(), responsable.toString(), n, m);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (bios != null) {
                try {
                    bios.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return al;
    }
}
