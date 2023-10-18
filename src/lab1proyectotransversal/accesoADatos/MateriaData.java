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

    public MateriaData() {
        this.connection = Conexion.getInstance();
    }

    public boolean guardarMateria(Materia materia) {
        boolean result = true;

        try {
            // Preparar la estructura de la consulta
            String sql = "INSERT INTO materia(nombre, año, estado, idMateria) VALUES (?, ?, ?, ?)";
            if (materia.getIdMateria() == -1) {
                sql = "INSERT INTO materia(nombre, año, estado) VALUES (?, ?, ?)";
            }

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.isEstado());

            // Si idMateria == -1 entonces se asigna automaticamente
            if (materia.getIdMateria() != -1) {
                ps.setInt(4, materia.getIdMateria());
            }

            // Ejecutar querry
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Alumno agregado");
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            result = false;
            int errorCode = e.getErrorCode();
            if (errorCode != 1062) { // Ignorar materias repetidas
                System.out.println("[Error " + errorCode + "] " + e.getMessage());
                e.printStackTrace();
            } else {
                System.out.println("[Materia repetida] " + e.getMessage());
            }
        }

        return result;
    }

    public Materia buscarMateria(int idMateria) {
        // TODO: implementar este metodo
        return null;
    }

    public List<Materia> listarMaterias() {
        // TODO: implementar este metodo
        return null;
    }

    public boolean modificarMateria(Materia materia) {
        // TODO: implementar este metodo
        return false;
    }

    public boolean eliminarMateria(int idMateria) {
        // TODO: implementar este metodo
        return false;
    }

}
