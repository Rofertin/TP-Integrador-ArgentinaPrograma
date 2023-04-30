package principal;

import principal.Alumno;
import principal.Conexion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    public Menu(){
        scanner = new Scanner(System.in);
        Conexion.verificarBaseDeDatos();
        correrBucle();
    }

    private void correrBucle() {
        boolean salida = false;
        while(!salida){
            inicio();
            salida = decidir();
        }
    }

    public static void inicio(){
        imprimir("");
        imprimir("-----------------------------------");
        imprimir("TP-Integrador-ArgentinaPrograma");
        imprimir("\n1- Consultar inscripción para un alumno.");
        imprimir("2- Agregar Alumno");
        imprimir("3- Agregar Materia");
        imprimir("4. salir");
    }

    private static void imprimir(String texto){
        System.out.println(texto);
    }

    public boolean decidir(){
        int numero = scanner.nextInt();
        switch (numero) {
            case 1:
                return consultarInscripcion();
            case 2:
                return agregarAlumno();
            case 3:
                return agregarMateria();
            case 4:
                return salir();
            default:
                imprimir("Input incorrecto.");
                return false;
        }
    }

    public boolean consultarInscripcion(){

        Alumno alumno = verificarAlumno();
        if (alumno == null){
            return false;
        }
        Materia materia = verificarMateria();
        if(materia == null){
            return false;
        }

        Inscripcion inscripcion = new Inscripcion(alumno, materia);
        imprimir(inscripcion.imprimirAprobado());
        return false;
    }
    public boolean agregarAlumno(){
        imprimir("");
        scanner.nextLine(); //buffer things :D

        imprimir("Ingrese nombre del alumno");
        String nombre = scanner.nextLine();

        imprimir("Ingrese legajo del alumno");
        String legajo = scanner.nextLine();

        imprimir("Ingrese materias aprobadas por el alumno: (De manera secuencial, romper el bucle poniendo fin");

        List<String> materiasAprobadas = new ArrayList<String>();

        while(true){
            String nombreMateria = scanner.nextLine();
            if (nombreMateria.equalsIgnoreCase("fin")){
                break;
            }
            materiasAprobadas.add(nombreMateria);
        }

        Alumno alumno = new Alumno(nombre, legajo, materiasAprobadas);

        Conexion conexion = new Conexion();
        conexion.cargarAlumno(alumno);

        return false;
    }
    public boolean agregarMateria(){
        imprimir("");
        scanner.nextLine(); //buffer things :D

        imprimir("Ingrese nombre de la materia");
        String nombre = scanner.nextLine();

        imprimir("Ingrese materias aprobadas por el alumno: (De manera secuencial, romper el bucle poniendo fin");
        List<String> materiasCorrelativas = new ArrayList<String>();

        while(true){
            String nombreMateria = scanner.nextLine();
            if (nombreMateria.equalsIgnoreCase("fin")){
                break;
            }
            materiasCorrelativas.add(nombreMateria);
        }
        Materia materia = new Materia(nombre, materiasCorrelativas);

        Conexion conexion = new Conexion();
        conexion.cargarMateria(materia);

        return false;
    }
    public boolean salir(){
        return true;
    }

    public Materia verificarMateria(){
        imprimir("");


        imprimir("Ingrese nombre de la materia");
        String nombre = scanner.nextLine();

        Conexion conexion = new Conexion();
        Materia materia = conexion.buscarMateria(nombre);

        if(materia != null){
            imprimir("Materia encontrada.");
        }
        else{
            imprimir("Materia no encontrada");
        }
        return materia;
    }

    private Alumno verificarAlumno(){
        imprimir("");
        scanner.nextLine(); //buffer things :D

        imprimir("Ingrese nombre del alumno");
        String nombre = scanner.nextLine();

        Conexion conexion = new Conexion();
        Alumno alumno = conexion.buscarAlumno(nombre);

        if(alumno != null){
            imprimir("Alumno encontrado.");
        }
        else{
            imprimir("Alumno no encontrado");
        }
        return alumno;
    }



}
