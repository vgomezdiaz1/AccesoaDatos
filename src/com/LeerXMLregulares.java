package com;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import java.io.File;

public class LeerXMLregulares {

    public static void main(String[] args) {
        XStream xs = new XStream(new DomDriver());
        //Hay que dar estos permisos generales
        xs.addPermission(NoTypePermission.NONE);
        xs.addPermission(NullPermission.NULL);
        xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
        //Hay que darle este pirmiso en el paquete en el que estamos
        xs.allowTypesByWildcard(new String[]{"com.**"});

        xs.alias("departamento", Departamento.class);
        xs.alias("departamentos", DepartamentosArrayList.class);
        xs.addImplicitCollection(DepartamentosArrayList.class, "al");

        DepartamentosArrayList d = (DepartamentosArrayList) xs.fromXML(new File ("ficheroDepartamentos.xml"));
        System.out.println(d.toString());
    }
}
