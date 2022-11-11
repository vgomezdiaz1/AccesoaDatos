package com;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileWriter;

public class CrearXMLregulares {

    public static void main(String[] args) {
        Departamento d1 = new Departamento("Victor", "Andrea", 10, 5, "A", true);
        Departamento d2 = new Departamento("Andrea", "Jefa", 2, 8, "B", true);
        //Hay que crearse un objeto intermedio para poder guardarlo en XML
        DepartamentosArrayList deps = new DepartamentosArrayList();
        deps.getAl().add(d1);
        deps.getAl().add(d2);
        //Creamos la clase constructora
        XStream xs = new XStream(new DomDriver());
        //Cambiamos nombres para que salga bonito
        xs.alias("departamento", Departamento.class);
        xs.alias("departamentos", DepartamentosArrayList.class);
        //Quitamos para que no ponga el nombre del array list que guarda todos los departamento
        xs.addImplicitCollection(DepartamentosArrayList.class, "al");
        //Usamos la funcion que devuelve un string con el xml
        String cadena = xs.toXML(deps);
        System.out.println(cadena);
        File f = new File("ficheroDepartamentos.xml");
        try (FileWriter fw = new FileWriter(f);){
            fw.write(cadena);
        } catch (Exception e) {
        }
        
    }
}
