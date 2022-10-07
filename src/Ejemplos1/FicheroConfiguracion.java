package Ejemplos1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class FicheroConfiguracion {

    public static void main(String[] args) {
        Properties conf = new Properties();
        conf.setProperty("user", "Victor");
        conf.setProperty("pass", "Hola");
        conf.setProperty("server", "qwerty");
        conf.setProperty("extra", "qwerty");
        try {
            conf.store(new FileOutputStream("configuracion.conf"), "Fichero de configuracion");
        } catch (Exception e) {
            System.out.println("Error");
        }
        File f = new File("./configuracion.conf");
        try (FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr)) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (Exception e) {
            System.out.println("Error al leer");
        }
        Path path = Paths.get("/home/dev/NetBeansProjects/");
        
        System.out.format("toString: %s%n", path.toString());
        System.out.format("getFileName: %s%n", path.getFileName());
        System.out.format("getName(0): %s%n", path.getName(0));
        System.out.format("getNameCount: %d%n", path.getNameCount());
        System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
        System.out.format("getParent: %s%n", path.getParent());
        System.out.format("getRoot: %s%n", path.getRoot());
        System.out.println(path.toUri());
        
        String usuario, pass, server, port;
        try {
            conf.load(new FileInputStream("configuration.props"));
            usuario = conf.getProperty("use");
            pass = conf.getProperty("pas");
            server = conf.getProperty("serv");
            port = conf.getProperty("por");
            System.out.println(usuario + pass + server + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
