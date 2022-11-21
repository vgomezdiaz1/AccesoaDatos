package examenprimertrimestre;

/**
 *
 * @author Victor
 */
public class Alumno {

    private StringBuilder nombre;
    private StringBuilder apellido;
    private StringBuilder direccon;
    private StringBuilder email;
    private int edad;
    private long id_matrícula;
    private float nota_media;
    private boolean alta;

    public Alumno(String nombre, String apellido, String direccon, String email,
            int edad, long id_matrícula, float nota_media) {
        super();
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDireccon(direccon);
        this.setEmail(email);
        this.edad = edad;
        this.id_matrícula = id_matrícula;
        this.nota_media = nota_media;
        this.alta = true;
    }

    public Alumno(String nombre, String apellido, String direccon, String email,
            int edad, long id_matrícula, float nota_media, boolean alta) {
        super();
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDireccon(direccon);
        this.setEmail(email);
        this.edad = edad;
        this.id_matrícula = id_matrícula;
        this.nota_media = nota_media;
        this.alta = alta;
    }

    public StringBuilder getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = new StringBuilder(nombre);
        this.nombre.setLength(30);
    }

    public StringBuilder getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = new StringBuilder(apellido);
        this.apellido.setLength(30);
    }

    public StringBuilder getDireccon() {
        return direccon;
    }

    public void setDireccon(String direccon) {
        this.direccon = new StringBuilder(direccon);
        this.direccon.setLength(60);
    }

    public StringBuilder getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = new StringBuilder(email);
        this.email.setLength(60);
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public long getId_matrícula() {
        return id_matrícula;
    }

    public void setId_matrícula(long id_matrícula) {
        this.id_matrícula = id_matrícula;
    }

    public float getNota_media() {
        return nota_media;
    }

    public void setNota_media(float nota_media) {
        this.nota_media = nota_media;
    }

    public static int getSize() {
        return 377;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString() {
        return "Alumno nombre=" + nombre + ", apellido=" + apellido
                + ", direccon=" + direccon + ", email=" + email + ", edad=" + edad
                + ", id_matricula=" + id_matrícula + ", nota_media=" + nota_media;
    }
}
