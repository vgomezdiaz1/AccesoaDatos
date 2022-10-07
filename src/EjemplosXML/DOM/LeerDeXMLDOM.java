package EjemplosXML.DOM;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LeerDeXMLDOM {
    public static void main(String[] args) {
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = fac.newDocumentBuilder();
            Document doc= builder.parse(new File("FichaPersona.xml"));
            NodeList personas = doc.getElementsByTagName("persona");
            System.out.println(personas.getLength());
            for (int i = 0; i < personas.getLength(); i++) {
                Node emple = personas.item(i);
                if(emple.getNodeType()== Node.ELEMENT_NODE){
                    Element el = (Element) emple;
                    System.out.println("Nombre: "+ el.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("Edad: " + el.getElementsByTagName("edad").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
