package EjemplosXML.SAX;

import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


public class LecturaXMLSAX {

    public static void main(String[] args) throws FileNotFoundException, IOException, SAXException {
        XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
        GestionContenido gestor = new GestionContenido();
        procesadorXML.setContentHandler(gestor);
        InputSource fileXML = new InputSource("./FichaPersona.xml");
        procesadorXML.parse(fileXML);
    }
}

class GestionContenido extends DefaultHandler {

    public GestionContenido() {
        super();
    }

    @Override
    public void startDocument() {
        System.out.println("Comienzo del Documento XML");
    }

    @Override
    public void endDocument() {
        System.out.println("Final del Documento XML");
    }
    
    @Override
    public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
        System.out.printf("\tPrincipio Elemento: %s %n", nombre);
    }

    @Override
    public void endElement(String uri, String nombre, String nombreC) {
        System.out.printf("\tFin Elemento: %s %n", nombre);
    }

    @Override
    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
        String car = new String(ch, inicio, longitud);
        car = car.replaceAll(" [\t\n]", "");
        System.out.printf("\tCaracteres: %s %n", car);
    }
}
