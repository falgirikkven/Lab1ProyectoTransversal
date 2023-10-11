package lab1proyectotransversal.accesoADatos;

import java.sql.*;
import java.util.List;
import lab1proyectotransversal.entidades.Materia;

/**
 *
 * @author Grupo-3
 */
public class MateriaData {

    private Connection connection;

    public MateriaData(Connection connection) {
        this.connection = connection;
    }

    public void guardarMateria(Materia materia) {

    }

    public Materia buscarMateria(int idMateria) {
        // TODO: implementar este metodo
        return null;
    }

    public List<Materia> listarMaterias() {
        // TODO: implementar este metodo
        return null;
    }

    void modificarMateria(Materia materia) {
        // TODO: implementar este metodo
    }

    void eliminarMateria(int idMateria) {
        // TODO: implementar este metodo
    }

}
