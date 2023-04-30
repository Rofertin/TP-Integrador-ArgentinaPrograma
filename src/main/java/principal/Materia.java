package principal;
import java.util.List;

public class Materia {
    String nombre;
    List<String> correlativas;

    public Materia(String unNombre, List<String> unasCorrelativas){
        nombre = unNombre;
        correlativas = unasCorrelativas;
    }

    public boolean puedeCursar(Alumno alumno){
        return alumno.aproboCorrelativas(correlativas);
    }

    public List<String> obtenerCorrelativas() {
        return correlativas;
    }

    public String nombre() {
        return nombre;
    }
}
