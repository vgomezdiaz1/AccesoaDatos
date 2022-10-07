package EjemplosXML.DOM;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class GuardarPersonasFichero {

    public static void main(String[] args) {
        Persona p1 = new Persona("Victor", 28);
        Persona p2 = new Persona("Andrea", 29);
        Persona p3 = new Persona("Ismael", 27);
        try (FileOutputStream fos = new FileOutputStream("./FichaPersona.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(p1);
            oos.writeObject(p2);
            oos.writeObject(p3);
        } catch (Exception e) {
            System.out.println("Error al escribir en el fichero");
        }
    }
}
