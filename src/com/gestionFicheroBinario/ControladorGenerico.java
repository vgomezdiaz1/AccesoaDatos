package com.gestionFicheroBinario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ControladorGenerico {

    File f;

    public ControladorGenerico(File f) {
        this.f = f;
    }

    public String alta(Objeto d) {
        int n = leerUltimoId() + 1;
        d.setId(n);
        if (f.exists()) {
            try (FileOutputStream fos = new FileOutputStream(f, true);
                    MiObjectOutputStream dos = new MiObjectOutputStream(fos)) {
                dos.writeObject(d);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        } else {
            try (FileOutputStream fos = new FileOutputStream(f);
                    ObjectOutputStream dos = new ObjectOutputStream(fos)) {
                dos.writeObject(d);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        return "Se ha añadido el empleado " + d.toString();
    }

    public int leerUltimoId() {
        try (FileInputStream fos = new FileInputStream(f);
                ObjectInputStream dos = new ObjectInputStream(fos)) {
            Objeto linea = null;
            String ultima = "";
            try {
                while (true) {
                    linea = (Objeto)dos.readObject();
                }
            } catch (Exception e) {
            }
            return linea.getId();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Objeto> consultaCompleta() {
        ArrayList<Objeto> al = new ArrayList<>();
        Objeto dep = null;
        try (FileInputStream fos = new FileInputStream(f);
                ObjectInputStream dos = new ObjectInputStream(fos)) {
            try {
                while (true) {
                    dep = (Objeto) dos.readObject();
                    al.add(dep);
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return al;
    }

    public Objeto consultaEspecifica(int id) {
        Objeto dep = null;
        try (FileInputStream fos = new FileInputStream(f);
                ObjectInputStream dos = new ObjectInputStream(fos)) {
            try {
                while (true) {
                    dep = (Objeto) dos.readObject();
                    if (dep.getId() == id) {
                        break;
                    }
                }
            } catch (Exception e) {
                dep = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dep;
    }

    public boolean borrar(int id) {
        File f2 = new File("./aux.txt");
        Objeto dep = null;
        boolean ok = false;
        try (FileInputStream fis = new FileInputStream(f);
                ObjectInputStream dis = new ObjectInputStream(fis);
                FileOutputStream fos = new FileOutputStream(f2);
                ObjectOutputStream dos = new ObjectOutputStream(fos)) {
            String linea;
            try {
                while (true) {
                    dep = (Objeto) dis.readObject();
                    if (dep.getId() != id) {
                        dos.writeObject(dep);
                    }
                }
            } catch (Exception e) {
                ok = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        f2.renameTo(f);
        return ok;
    }

    public boolean modificar(Objeto d) {
        File f2 = new File("./aux.txt");
        Objeto dep = null;
        boolean ok = false;
        try (FileInputStream fis = new FileInputStream(f);
                ObjectInputStream dis = new ObjectInputStream(fis);
                FileOutputStream fos = new FileOutputStream(f2);
                ObjectOutputStream dos = new ObjectOutputStream(fos)) {
            String linea;
            try {
                while (true) {
                    dep = (Objeto) dis.readObject();
                    if (dep.getId() == d.getId()) {
                        dos.writeObject(d);
                    }else{
                        dos.writeObject(dep);
                    }
                }
            } catch (Exception e) {
                ok = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        f2.renameTo(f);
        return ok;
    }
}
