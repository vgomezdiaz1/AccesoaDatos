package com.ejemploXML;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import java.io.File;

public class Exec {

    public static void main(String[] args) {
        XStream xs = new XStream(new DomDriver());

        xs.addPermission(NoTypePermission.NONE);

        xs.addPermission(NullPermission.NULL);

        xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
        
        xs.allowTypesByWildcard(new String[]{"com.**"});

        xs.alias("registro", Registro.class);
        xs.alias("estacion", Estacion.class);
        xs.addImplicitCollection(Estacion.class, "lista");
        
        Estacion s = (Estacion) xs.fromXML(new File("registros.xml"));
        
        int temperatura = 0;
        int humedad = 0;
        
        for (Registro r : s.getLista()) {
            temperatura += r.getTemperatura();
            humedad += r.getHumedad();
        }
        System.out.println("La temperatura es " + temperatura/s.getLista().size());
        System.out.println("La humedad es " + humedad/s.getLista().size());
    }
}
