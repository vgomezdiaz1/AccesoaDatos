package EjemplosXML.SerializacionObjetos;

import EjemplosXML.DOM.Persona;
import com.thoughtworks.xstream.XStream;
import java.io.FileInputStream;
import java.util.List;

public class LeerDeSerializacion {

    public static void main(String[] args){
        XStream xstream = new XStream();
        xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);
        xstream.alias("DatosPersona", Persona.class);
        xstream.addImplicitCollection(ListaPersonas.class, "lista");
        ListaPersonas listadoTodas = null;
        try (FileInputStream fis = new FileInputStream("./FichaPersonas.xml")) {
            listadoTodas = (ListaPersonas) xstream.fromXML(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Numero de Personas: " + listadoTodas.getListaPersonas().size());
        List<Persona> listaPersonas = listadoTodas.getListaPersonas();
        for (Persona p : listaPersonas) {
            System.out.printf("Nombre: %s, edad: %d %n", p.getNombre(), p.getEdad());
            System.out.println("Fin de listado .....");
        }
    }
}
