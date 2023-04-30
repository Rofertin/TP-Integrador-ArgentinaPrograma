import org.junit.Test;
import principal.*;

import java.util.ArrayList;
import java.util.List;


public class InscripcionTest {
    @Test
    public void test01UnaMateriaSinCorrelativas(){
        System.out.println();
        System.out.println("test01 Una Materia Sin Correlativas");
        Materia ProgramacionUno = new Materia("Programacion 1", new ArrayList<String>());


        Alumno pepito = new Alumno("Pepito", "108275", new ArrayList<String>());
        Alumno maria = new Alumno("Maria", "118275", new ArrayList<String>());
        Alumno german = new Alumno("German", "128275", new ArrayList<String>());

        List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();

        inscripciones.add(new Inscripcion(pepito, ProgramacionUno));
        inscripciones.add(new Inscripcion(maria, ProgramacionUno));
        inscripciones.add(new Inscripcion(german, ProgramacionUno));

        for(Inscripcion inscripcion : inscripciones) {
            System.out.println(inscripcion.obtenerCadenaDeDatos());
        }
        System.out.println();
    }
    @Test
    public void test02UnaMateriaConCorrelativasYQueElAlumnoLasTenga(){
        System.out.println();
        System.out.println("test02 Una materia con correlativas y que el alumno las tenga");
        Materia ProgramacionUno = new Materia("Programacion 1", new ArrayList<String>());

        List<String> materiasNecesariasParaAlgoritmosDOS = new ArrayList<String>();
        materiasNecesariasParaAlgoritmosDOS.add("Programacion 1");
        Materia ProgramacionDOS = new Materia("Programacion 2", materiasNecesariasParaAlgoritmosDOS);

        Alumno pepito = new Alumno("Pepito", "108275", new ArrayList<String>());
        Alumno maria = new Alumno("Maria", "118275", materiasNecesariasParaAlgoritmosDOS);
        Alumno german = new Alumno("German", "128275", new ArrayList<String>());

        List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();

        inscripciones.add(new Inscripcion(pepito, ProgramacionDOS));
        inscripciones.add(new Inscripcion(maria, ProgramacionDOS));
        inscripciones.add(new Inscripcion(german, ProgramacionDOS));

        for(Inscripcion inscripcion : inscripciones) {
            System.out.println(inscripcion.obtenerCadenaDeDatos());
        }

    }
    @Test
    public void test03OtraMateriaSinCorrelativasYQueElAlumnoNoLasTenga(){
        System.out.println();
        System.out.println("test03 Otra materia sin correlativas, y que el alumno no las tenga");

        Materia ProgramacionUno = new Materia("Programacion 1", new ArrayList<String>());

        List<String> materiasNecesariasParaAlgoritmosDOS = new ArrayList<String>();
        materiasNecesariasParaAlgoritmosDOS.add("Programacion 1");
        Materia ProgramacionDOS = new Materia("Programacion 2", materiasNecesariasParaAlgoritmosDOS);

        Alumno pepito = new Alumno("Pepito", "108275", materiasNecesariasParaAlgoritmosDOS);
        Alumno maria = new Alumno("Maria", "118275", materiasNecesariasParaAlgoritmosDOS);
        Alumno german = new Alumno("German", "128275", materiasNecesariasParaAlgoritmosDOS);

        List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();

        inscripciones.add(new Inscripcion(pepito, ProgramacionDOS));
        inscripciones.add(new Inscripcion(maria, ProgramacionDOS));
        inscripciones.add(new Inscripcion(german, ProgramacionDOS));

        for(Inscripcion inscripcion : inscripciones) {
            System.out.println(inscripcion.obtenerCadenaDeDatos());
        }
    }
}
