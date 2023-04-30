package principal;

import java.util.List;

public class Alumno {
    String nombre;
    String legajo;
    List<String> materiasAprobadas;

    public Alumno(String unNombre, String unLegajo ,List<String> unaLista){
        nombre = unNombre;
        legajo = unLegajo;
        materiasAprobadas = unaLista;
    }
    public List<String> obtenerMateriasAprobadas() {
        return materiasAprobadas;
    }

    public String legajo() {
        return legajo;
    }

    public String nombre() {
        return nombre;
    }
    public boolean aproboCorrelativas(List<String> correlativas) {
        return materiasAprobadas.containsAll(correlativas);
    }

    public void aprobarMateria(String unaMateria){
        materiasAprobadas.add(unaMateria);
    }

    private boolean revisarLegajo(){
        return false;
    }


}
