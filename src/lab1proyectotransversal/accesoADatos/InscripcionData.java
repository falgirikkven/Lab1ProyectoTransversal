package lab1proyectotransversal.accesoADatos;

import java.sql.Connection;
import java.util.List;
import lab1proyectotransversal.entidades.Inscripcion;

/**
 *
 * @author Grupo-3
 */
public class InscripcionData {

    private Connection connection;

    public InscripcionData(Connection connection) {
        this.connection = connection;
    }

    public void guardarInscripcion(Inscripcion inscripcion) {
        // TODO: implementar este metodo
    }

    public Inscripcion buscarInscripcion(int idMInscripcion) {
        // TODO: implementar este metodo
        return null;
    }

    public List<Inscripcion> listarInscripciones() {
        // TODO: implementar este metodo
        return null;
    }

    void modificarInscripcion(Inscripcion inscripcion) {
        // TODO: implementar este metodo
    }

    void eliminarInscripcion(int idMInscripcion) {
        // TODO: implementar este metodo
    }

}
