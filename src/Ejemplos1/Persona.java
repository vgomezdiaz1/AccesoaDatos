/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejemplos1;

import java.io.Serializable;

/**
 *
 * @author dev
 */
public class Persona implements Serializable{
    String nombre;
    int edad;
    Double estatura;

    public Persona(String nombre, int edad, Double estatura) {
        this.nombre = nombre;
        this.edad = edad;
        this.estatura = estatura;
    }
    
}
