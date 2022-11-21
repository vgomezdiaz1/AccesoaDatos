package examenprimertrimestre;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class ControladorAlumno {

    private String nombreFichero;

    public ControladorAlumno(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }

    public void alta(Alumno d) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(this.nombreFichero, "rw");
            raf.seek(raf.length());
        } catch (Exception ex) {
        }
        escribirAlumno(raf, d);
    }

    public ArrayList<Alumno> consultaTodosAlumnos() {
        Alumno a = null;
        ArrayList<Alumno> al = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(this.nombreFichero, "r");) {
            int total = (int) (raf.length() / Alumno.getSize());
            for (int i = 0; i < total; i++) {
                a = (leerAlumno(raf));
                if (a.isAlta()) {
                    al.add(a);
                }
            }
        } catch (Exception ex) {
        }
        return al;
    }

    public void baja(int posicion) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(this.nombreFichero, "rw");
            raf.seek(Alumno.getSize() * posicion);
            Alumno a = leerAlumno(raf);
            raf.seek(Alumno.getSize() * posicion);
            a.setAlta(false);
            escribirAlumno(raf, a);
        } catch (Exception ex) {
        }
    }

    public void modificarAlumno(int posicion, Alumno a) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(this.nombreFichero, "rw");
            raf.seek(Alumno.getSize() * posicion);
        } catch (Exception ex) {
        }
        escribirAlumno(raf, a);
    }

    public float notaMediaAlumnos() {
        ArrayList<Alumno> al = new ArrayList<>();
        al = consultaTodosAlumnos();
        int contador = 0;
        float notaMedia = 0;
        for (Alumno a : al) {
            contador++;
            notaMedia += a.getNota_media();
        }
        float media = notaMedia/contador;
        return media;
    }

    public void escribirAlumno(RandomAccessFile raf, Alumno a) {
        try {
            raf.writeChars(a.getNombre().toString());
            raf.writeChars(a.getApellido().toString());
            raf.writeChars(a.getDireccon().toString());
            raf.writeChars(a.getEmail().toString());
            raf.writeInt(a.getEdad());
            raf.writeLong(a.getId_matrícula());
            raf.writeFloat(a.getNota_media());
            raf.writeBoolean(a.isAlta());
        } catch (IOException ex) {
        }
    }

    public Alumno leerAlumno(RandomAccessFile raf) {
        Alumno a = null;
        try {
            byte[] nombreArray = new byte[60];
            raf.read(nombreArray);
            String nombre = new String(nombreArray);
            byte[] apellidoArray = new byte[60];
            raf.read(apellidoArray);
            String apellido = new String(apellidoArray);
            byte[] direccionArray = new byte[120];
            raf.read(direccionArray);
            String direccion = new String(direccionArray);
            byte[] emailArray = new byte[120];
            raf.read(emailArray);
            String email = new String(emailArray);
            int edad = raf.readInt();
            long idMatricula = raf.readLong();
            float notaMedia = raf.readFloat();
            boolean alta = raf.readBoolean();
            a = new Alumno(nombre, apellido, direccion, email, edad, idMatricula, notaMedia, alta);
        } catch (Exception e) {
        }
        return a;
    }
}
