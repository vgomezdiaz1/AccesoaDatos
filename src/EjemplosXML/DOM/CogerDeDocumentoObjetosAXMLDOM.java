package EjemplosXML.DOM;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class CogerDeDocumentoObjetosAXMLDOM {

    public static void main(String[] args) {
        ArrayList<Persona> al = new ArrayList<>();
        try (FileInputStream fos = new FileInputStream("./FichaPersona.dat");
                ObjectInputStream ois = new ObjectInputStream(fos)) {
            while (true) {
                Persona t = (Persona) ois.readObject();
                al.add(t);
            }
        } catch (EOFException ex) {
//            DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
//            try {
//                DocumentBuilder builder = fac.newDocumentBuilder();
//                DOMImplementation implementation = builder.getDOMImplementation();
//                Document document = implementation.createDocument(null, "personas", null);
//                document.setXmlVersion("1.0");
//                for (Persona p : al) {
//                    Element individuo = document.createElement("persona");
//                    document.getDocumentElement().appendChild(individuo);
//                    CrearElemento("nombre", p.getNombre(), individuo, document);
//                    CrearElemento("edad", Integer.toString(p.getEdad()), individuo, document);
//                }
//                Source source = new DOMSource(document);
//                Result result = new StreamResult(new java.io.File("./FichaPersona.xml"));
//                Transformer transformer = TransformerFactory.newInstance().newTransformer();
//                transformer.transform(source, result);
//            } catch (TransformerException e) {
//                e.printStackTrace();
//            } catch (ParserConfigurationException exo) {
//                System.out.println(exo.getMessage());
//            }
            String[] etiquetasEnOrden = {"nombre", "edad"};
            crearXML("personas", al, "persona", etiquetasEnOrden, "./FichaPersona.xml");
        } catch (Exception e) {
            
        }

    }

    static void crearXML(String etiquetaGeneral, ArrayList<Persona> al, String etiquetaObjeto, String[] etiquetasEnOrden, String nombreFichero) {
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = fac.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, etiquetaGeneral, null);
            document.setXmlVersion("1.0");
            for (Persona p : al) {
                Element individuo = document.createElement(etiquetaObjeto);
                document.getDocumentElement().appendChild(individuo);
                
                //Aqui hay que cambiarlo a tantas llamadas como etiquetas tenga, cambiando cualquier valor a String
                CrearElemento(etiquetasEnOrden[0], p.getNombre(), individuo, document);
                CrearElemento(etiquetasEnOrden[1], Integer.toString(p.getEdad()), individuo, document);
                
                
            }
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File(nombreFichero));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException exo) {
            System.out.println(exo.getMessage());
        }
    }

    static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoEmple); //creamos hijo
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor
    }
}
