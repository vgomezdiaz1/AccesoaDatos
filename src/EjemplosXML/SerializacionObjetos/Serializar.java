package EjemplosXML.SerializacionObjetos;

import java.io.*;
import EjemplosXML.DOM.Persona;
import com.thoughtworks.xstream.XStream;


public class Serializar {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File fichero = new File("./FichaPersona.dat");
        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);
        System.out.println("Comienza el proceso...");
        ListaPersonas listaper = new ListaPersonas();
        try {
            while (true) {
                Persona persona = (Persona) dataIS.readObject();
                listaper.add(persona);
            }
        } catch (Exception e) {
            
        } finally {
            dataIS.close();
        }

        try {
            XStream xstream = new XStream();
            xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);
            xstream.alias("DatosPersona", Persona.class);
            xstream.addImplicitCollection(ListaPersonas.class, "lista");
            xstream.toXML(listaper, new FileOutputStream("./FichaPersonas.xml"));
            System.out.println("Creado fichero XML... ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
