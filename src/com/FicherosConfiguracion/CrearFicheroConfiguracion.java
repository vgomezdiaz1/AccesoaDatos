package com.FicherosConfiguracion;

import java.io.FileOutputStream;
import java.util.Properties;

public class CrearFicheroConfiguracion {
    public static void main(String[] args) {
        Properties p = new Properties();
        p.setProperty("Procesos", "10");
        p.setProperty("log", "./log.txt");
        
        
        try {
            p.store(new FileOutputStream("Configuracion.conf"), "Esto sirve para poner un comentario en la entrada");
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }
}
