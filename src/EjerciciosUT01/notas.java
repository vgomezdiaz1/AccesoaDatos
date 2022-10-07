package EjerciciosUT01;

import java.io.Serializable;

public class notas implements Serializable{
    String asignatura;
    double nota;

    public notas(String asignatura, double nota) {
        this.asignatura = asignatura;
        this.nota = nota;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Asignatura: " + asignatura + ", nota: " + nota;
    }
    
    
}
