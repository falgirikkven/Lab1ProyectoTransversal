package lab1proyectotransversal.accesoADatos;

import java.sql.*;
import java.util.List;
import lab1proyectotransversal.entidades.Alumno;

/**
 *
 * @author Grupo-3
 */
public class AlumnoData {

    private Connection connection;

    public AlumnoData(Connection connection) {
        this.connection = connection;
    }

    public void guardarAlumno(Alumno alumno) {
        // TODO: implementar este metodo
    }

    public Alumno buscarAlumno(int idAlumno) {
        // TODO: implementar este metodo
        return null;
    }

    public List<Alumno> listarAlumnos() {
        // TODO: implementar este metodo
        return null;
    }

    void modificarAlumno(Alumno alumno) {
        // TODO: implementar este metodo
    }

    void eliminarAlumno(int idAlumno) {
        // TODO: implementar este metodo
    }
}
