/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FicherosConfiguracion;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

public class LeerFicheroConfiguracion {

    public static void main(String[] args) {
        //Si sabes que hay en el Properties es asi
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("Configuracion.conf"));
        } catch (Exception ex) {
            System.out.println("Fallo al leer");
        }
        String procesos = p.getProperty("Procesos");
        String log = p.getProperty("log");

        System.out.println(procesos);
        System.out.println(log);

        
        //Si no sabes como es por dentro
        Properties p1 = new Properties();
        try {
            p1.load(new FileInputStream("Configuracion.conf"));
        } catch (Exception ex) {
            System.out.println("Fallo al leer");
        }
        //Guardamos cada clave/valor en una lista enumeration de objetos
        Enumeration<Object> lista = p1.keys();
        //Mientras haya elementos en lista sigue
        while (lista.hasMoreElements()) {
            //lista.nextElement(), devuelve el objeto y avanza el seek al siguiente
            Object o = lista.nextElement();
            System.out.println(o.toString() + " " + p1.get(o.toString()));
        }
        for (Map.Entry<Object, Object> entry : p1.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            
        }
    }
}
