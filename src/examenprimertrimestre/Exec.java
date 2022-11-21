package examenprimertrimestre;

/**
 *
 * @author Victor
 */
public class Exec {
    
    final static String nombreFicheroAlumno = "./alumnos.dat";
    
    public static void main(String[] args) {
        ControladorAlumno ca = new ControladorAlumno(nombreFicheroAlumno);
        Alumno a1 = new Alumno("Victor", "Gomez", "Calle sin numero 1234", 
                "victorgomez@gmail.com", 28, 624, 8);
        Alumno a2 = new Alumno("Ismael", "Perez", "Calle sin sentido 5678", 
                "ismaelperez@gmail.com", 27, 874, 7);
        Alumno a3 = new Alumno("Juan", "Diaz", "Calle sin calle 4321", 
                "juandiaz@gmail.com", 31, 247, 6);
        //Alta de un alumno.
        ca.alta(a1);
        //Alta de otro alumno.
        ca.alta(a2);
        //Consulta de todos los alumnos.
        for (Alumno a : ca.consultaTodosAlumnos()) {
            System.out.println(a.toString());
        }
        System.out.println("------------------------");
        //Baja del primer alumno.
        ca.baja(0);
        //Modificación del nombre del segundo alumno.
        ca.modificarAlumno(1, new Alumno("Jaimito", "Perez", "Calle sin sentido 1234", 
                "ismaelperez@gmail.com", 27, 874, 7));
        //Consulta de todos los alumnos.
        for (Alumno a : ca.consultaTodosAlumnos()) {
            System.out.println(a.toString());
        }
        //Alta de un alumno nuevo.
        ca.alta(a3);
        System.out.println("------------------------");
        //Consulta la media de la nota media de todos los alumnos.
        System.out.println("La nota media de los alumnos es: " + ca.notaMediaAlumnos());
    }
}
