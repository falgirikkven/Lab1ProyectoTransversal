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
            // Preparar la estructura de la sentencia SQL
            String sql;
            if (materia.getIdMateria() == -1) {
                sql = "INSERT INTO materia(nombre, año, estado) VALUES (?, ?, ?);";
            } else {
                sql = "INSERT INTO materia(nombre, año, estado, idMateria) VALUES (?, ?, ?, ?);";
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

            // Ejecutar la sentencia SQL
            int filas = ps.executeUpdate();
            
            // Comunicar resultado por consola
            if (filas > 0) {
                System.out.println("Materia agregada");
            } else {
                result = false;
                System.out.println("No se pudo agregar la materia");
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            result = false;
            int errorCode = e.getErrorCode();
            if (errorCode == 1062) {    // Materia repetida
                System.out.println("[Error " + errorCode + "] (Materia repetida)");
                                
            } else {
                System.out.println("[Error " + errorCode + "]");                
            }
            e.printStackTrace();
        }

        return result;
    }
    
    public Materia buscarMateria(int idMateria) {
        Materia materia = null;
        try {
            String sql = "SELECT * FROM materia WHERE idMateria=?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            
            /* Si se encontro la materia, crear nuevo objeto de tipo Materia con los datos obtenidos 
               y, en todo caso, comunicar el resultado por consola */
            if (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(idMateria);
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "]");
            e.printStackTrace();
        }
        return materia;
    }
    
    public Materia buscarMateriaSegunEstado(int idMateria, boolean estado) {
        Materia materia = null;
        try {
            String sql = "SELECT * FROM materia WHERE idMateria=? AND estado=?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ps.setBoolean(1, estado);
            ResultSet rs = ps.executeQuery();
            
            /* Si se encontro la materia, crear nuevo objeto de tipo Materia con los datos obtenidos 
               y, en todo caso, comunicar el resultado por consola */
            if (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(idMateria);
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(estado);
                                
                System.out.println("Se encontró la materia");
            }else{
                System.out.println("No se encontró la materia");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "]");
            e.printStackTrace();
        }
        return materia;
    }
    
    public List<Materia> listarMaterias() {
        List<Materia> listaMaterias = new ArrayList();
        
        try {
            String sql = "SELECT * FROM materia;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Materia materia;
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
            System.out.println("[Error " + e.getErrorCode() + "]");
            e.printStackTrace();
        }
        return listaMaterias;
    }
    
    public List<Materia> listarMateriasSegunEstado(boolean estado) {
        List<Materia> listaMaterias = new ArrayList();
        
        try {
            String sql = "SELECT * FROM materia WHERE estado=?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, estado);
            ResultSet rs = ps.executeQuery();
            Materia materia;
            while (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(estado);
                listaMaterias.add(materia);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "]");
            e.printStackTrace();
        }
        return listaMaterias;
    }
    
    public boolean modificarMateria(Materia materia) {
        boolean result = true;
        try {
            String sql = "UPDATE materia SET nombre=?, año=? WHERE idMateria=?";

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
            System.out.println("[Error " + e.getErrorCode() + "]");
            e.printStackTrace();
        }

        return result;
    }

    // Borrado lógico
    public boolean eliminarMateria(int idMateria) {
        boolean result = true;

        try {
            String sql = "UPDATE materia SET estado=false WHERE idMateria=?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idMateria);
            
            int fil = ps.executeUpdate();
            if (fil > 0) {
                System.out.println("Materia eliminada");
            } else {
                result = false;
                System.out.println("No se pudo dar de baja a la materia");                
            }
        } catch (SQLException e) {
            result = false;
            System.out.println("[Error " + e.getErrorCode() + "]");
            e.printStackTrace();
        }

        return result;
    }

}
