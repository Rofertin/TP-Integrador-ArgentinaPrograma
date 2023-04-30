package principal;
import java.util.Date;

public class Inscripcion {
    Alumno alumno;
    Materia materia;
    Date fecha;
    boolean aprobada;

    public Inscripcion(Alumno unAlumno, Materia unaMateria){
        alumno = unAlumno;
        materia = unaMateria;
    }
    private void aprobada(){
        if (materia.puedeCursar(alumno)){
            aprobada = true;
        }else{
            aprobada = false;
        }
    }

    public boolean fueAprobada(){
        return aprobada;
    }
    public String imprimirAprobado(){
        aprobada();
        if (aprobada){
            return "Aprobada";
        }else{
            return "Desaprobada";
        }
    }

    public String obtenerCadenaDeDatos(){
        return alumno.nombre + "   " + materia.nombre +  "    " +  imprimirAprobado();
    }
}
