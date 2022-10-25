package com.gestionFicheroBinario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ControladorEmpleado {
        File f;

    public ControladorEmpleado(File f) {
        this.f = f;
    }

    public String alta(Empleado d) {
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

    public ArrayList<Empleado> consultaCompleta() {
        ArrayList<Empleado> al = new ArrayList<>();
        Empleado dep = null;
        try (FileInputStream fos = new FileInputStream(f);
                ObjectInputStream dos = new ObjectInputStream(fos)) {
            try {
                while (true) {
                    dep = (Empleado) dos.readObject();
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

    public Empleado consultaEspecifica(int id) {
        Empleado dep = null;
        try (FileInputStream fos = new FileInputStream(f);
                ObjectInputStream dos = new ObjectInputStream(fos)) {
            try {
                while (true) {
                    dep = (Empleado) dos.readObject();
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

    public boolean borrarEmpleado(int id) {
        File f2 = new File("./aux.txt");
        Empleado dep = null;
        boolean ok = false;
        try (FileInputStream fis = new FileInputStream(f);
                ObjectInputStream dis = new ObjectInputStream(fis);
                FileOutputStream fos = new FileOutputStream(f2);
                ObjectOutputStream dos = new ObjectOutputStream(fos)) {
            String linea;
            try {
                while (true) {
                    dep = (Empleado) dis.readObject();
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

    public boolean modificarEmpleado(Empleado d) {
        File f2 = new File("./aux.txt");
        Empleado dep = null;
        boolean ok = false;
        try (FileInputStream fis = new FileInputStream(f);
                ObjectInputStream dis = new ObjectInputStream(fis);
                FileOutputStream fos = new FileOutputStream(f2);
                ObjectOutputStream dos = new ObjectOutputStream(fos)) {
            String linea;
            try {
                while (true) {
                    dep = (Empleado) dis.readObject();
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

    public int leerUltimoId() {
        try (FileInputStream fos = new FileInputStream(f);
                ObjectInputStream dos = new ObjectInputStream(fos)) {
            Empleado linea = null;
            String ultima = "";
            try {
                while (true) {
                    linea = (Empleado) dos.readObject();
                }
            } catch (Exception e) {
            }
            return linea.getId();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }
}
