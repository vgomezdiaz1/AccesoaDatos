/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejemplos1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class EjemploSerializacionObjetos {
    public static void main(String[] args) {
        Persona p = new Persona("Victor",28, 1.9);
        try (FileOutputStream fos = new FileOutputStream("obj.txt");
                ObjectOutputStream oos =  new ObjectOutputStream(fos)){
            oos.writeObject(p);
        }catch (Exception e) {
            e.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream("obj.txt");
                ObjectInputStream ois = new ObjectInputStream(fis)){
            Persona l = (Persona) ois.readObject();
            System.out.println(l.nombre + ", " + l.edad + ", " + l.estatura);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
