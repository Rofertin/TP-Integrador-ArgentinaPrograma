package principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conexion {
    private final static String user = "root";
    private final static String password = "3041";
    private final static String puerto = "3306";
    private final static String bd = "argentina_programa_final"; //base de datos
    private static final String url = "jdbc:mysql://" + "localhost" + ":" + puerto + "/" + bd;

    private String pasarObjetoAJson(Object objeto){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(objeto);
            return json;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> pasarDeJsonAListaStrings(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> lista = new ArrayList<>();

        String[] arr = objectMapper.readValue(json, String[].class);

        for (String valor : arr) {
            lista.add(valor);
        }
        return lista;
    }

    public void cargarAlumno(Alumno alumno){

        try (Connection conexion = DriverManager.getConnection(url, user, password)){

            String query = "INSERT INTO Alumnos (nombre, legajo, materias_aprobadas) VALUES (?, ?, ?)";
            String materiasAprobadas = pasarObjetoAJson(alumno.obtenerMateriasAprobadas());


            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, alumno.nombre());
            statement.setString(2, alumno.legajo());
            statement.setString(3, materiasAprobadas);
            statement.executeUpdate();

            cerrarConexion(conexion);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cargarMateria(Materia materia) {
        try (Connection conexion = DriverManager.getConnection(url, user, password)){

            String query = "INSERT INTO Materias (nombre, correlativas) VALUES (?, ?)";
            String correlativas = pasarObjetoAJson(materia.obtenerCorrelativas());


            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, materia.nombre());
            statement.setString(2, correlativas);
            statement.executeUpdate();

            cerrarConexion(conexion);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void verificarBaseDeDatos() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url,user,password);


            ResultSet resultado = conexion.getMetaData().getCatalogs();

            boolean existeBaseDeDatos = false;

            while (resultado.next()) {
                String databaseName = resultado.getString(1);
                if (databaseName.equals(bd)) {
                    existeBaseDeDatos = true;
                    break;
                }
            }

            if (!existeBaseDeDatos) {
                Statement statement = conexion.createStatement();
                String query = "CREATE DATABASE" + bd;
                statement.executeUpdate(query);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        verificarTablas(conexion);
        cerrarConexion(conexion);
    }

    private static void verificarTablas(Connection conexion) {
        try {
            Statement statement = conexion.createStatement();
            String crearTablaAlumnos = "CREATE TABLE IF NOT EXISTS Alumnos ("
                    + "nombre VARCHAR(50) NOT NULL,"
                    + "legajo VARCHAR(50) NOT NULL,"
                    + "materias_aprobadas JSON, "
                    + "PRIMARY KEY (nombre)" + ")";
            statement.executeUpdate(crearTablaAlumnos);

            String crearTablaMaterias = "CREATE TABLE IF NOT EXISTS Materias ("
                    + "nombre VARCHAR(50) NOT NULL,"
                    + "correlativas JSON, "
                    + "PRIMARY KEY (nombre)" + ")";
            statement.executeUpdate(crearTablaMaterias);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Alumno buscarAlumno(String nombreBuscado){
        Alumno alumno = null;
        try {
            Connection conexion = DriverManager.getConnection(url,user,password);
            Statement  statement = conexion.createStatement();
            String query = "SELECT * FROM alumnos WHERE nombre='" + nombreBuscado + "'";
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                String legajo = rs.getString("legajo");
                String nombre = rs.getString("nombre");
                String listaJson = rs.getString("materias_aprobadas");

                List<String> materiasAprobadas = pasarDeJsonAListaStrings(listaJson);

                alumno = new Alumno(nombre,legajo, materiasAprobadas);
            } else {
                return alumno;
            }
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return alumno;
    }

    public Materia buscarMateria(String nombreBuscado){
        Materia materia = null;
        try {
            Connection conexion = DriverManager.getConnection(url,user,password);
            Statement  statement = conexion.createStatement();
            String query = "SELECT * FROM materias WHERE nombre='" + nombreBuscado + "'";
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String correlativasJson = rs.getString("correlativas");

                List<String> correlativas = pasarDeJsonAListaStrings(correlativasJson);

                materia = new Materia(nombre, correlativas);

            } else {
                return materia;
            }
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return materia;
    }

    private static void cerrarConexion(Connection conexion){
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
