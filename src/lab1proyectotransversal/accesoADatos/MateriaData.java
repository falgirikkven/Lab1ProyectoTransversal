package lab1proyectotransversal.accesoADatos;

import java.sql.*;
import java.util.ArrayList;
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
                System.out.println("Materia agregada");
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

    //Buscar Materia
    public Materia buscarMateria(int idMateria) {
        Materia materia = null;
        try {
            String sql = "SELECT * FROM materia WHERE idMateria=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }
        return materia;
    }

    //Listar materia
    public List<Materia> listarMaterias() {
        List<Materia> listaMaterias = new ArrayList();
        Materia materia;
        try {
            String sql = "SELECT * FROM materia";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                listaMaterias.add(materia);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }
        return listaMaterias;
    }

    //Modificar materia
    public boolean modificarMateria(Materia materia) {
        boolean result = true;        
        try {
            String sql = "UPDATE materia SET nombre=?, año=?, estado=? WHERE idMateria=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.isEstado());
            ps.setInt(4, materia.getIdMateria());

            int fil = ps.executeUpdate();
            if (fil > 0) {
                System.out.println("Materia modificada");
            } else {
                result = false;
                System.out.println("Materia no modificada");
            }
            ps.close();
        } catch (SQLException e) {
            result = false;
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    //Eliminar materia
    public boolean eliminarMateria(int idMateria) {
        boolean result = true;

        try {
            String sql = "Update materia SET estado=false WHERE idMateria=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idMateria);
            int fil = ps.executeUpdate();
            if (fil > 0) {
                System.out.println("Materia eliminada");
            } else {
                System.out.println("Materia no eliminada");
                result = false;
            }
        } catch (SQLException e) {
            result = false;
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

}
