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

    public void consultarTodosDepartamentos() {
        try (FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] array = linea.split(";");
                System.out.println("ID:" + array[0] + ", Nombre: " + array[1] + ", Responsable: " + array[2] + ", Numero empleados: " + array[3]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarDepartamento(int id) {
        try (FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] array = linea.split(";");
                if (Integer.parseInt(array[0]) == id) {
                    System.out.println("ID:" + array[0] + ", Nombre: " + array[1] + ", Responsable: " + array[2] + ", Numero empleados: " + array[3]);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrarDepartamento(int id) {
        File f2 = new File("./aux.txt");
        try (FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                FileWriter fw = new FileWriter(f2);
                BufferedWriter bw = new BufferedWriter(fw)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] array = linea.split(";");
                if (Integer.parseInt(array[0]) != id) {
                    fw.write(linea + ";\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        f2.renameTo(f);

    }

    public int leerUltimoId() {
        try (FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr)) {
            String linea;
            String ultima = "";
            while ((linea = br.readLine()) != null) {
                ultima = linea;
            }
            String[] array = ultima.split(";");
            return Integer.parseInt(array[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
