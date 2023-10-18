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
    
    
    //Buscar Materia
    public Materia buscarMateria(int idMateria) {
        Materia materia = null;
        try{
            String sql="SELECT * FROM materia WHERE idmateria=?";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idmateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        }catch(SQLException e){
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }
        return materia;
    }

    //Listar materia
    public List<Materia> listarMaterias() {
        List<Materia> listamateria = new ArrayList();
        try{
            String sql="SELECT * FROM materia";
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idmateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                listamateria.add(materia);
            }
            ps.close();
        }catch(SQLException e){
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }
        return listamateria;
    }    
    
    //Modificar materia
    public void modificarMateria(Materia materia) {
        try{
            String sql="UPDATE materia SET nombre=?, año=?, estado=? WHERE idMateria=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, materia.getIdMateria());
            ps.setString(2, materia.getNombre());
            ps.setInt(3, materia.getAnio());
            ps.setBoolean(4, materia.isEstado());
            int fil=ps.executeUpdate();
            if(fil>0){
                System.out.println("materia modificada");
            }else{
                System.out.println("materia no modificada");
            }
            ps.close();
        }catch(SQLException e){
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    //Eliminar materia
    public void eliminarMateria(int idMateria) {
        try{
           String sql="Update materia SET estado=false WHERE idMateria=?";
           PreparedStatement ps = connection.prepareStatement(sql);
           ps.setInt(1, idMateria);
           int fil=ps.executeUpdate();
           if(fil>0){
               System.out.println("Materia eliminada");
           }else{
               System.out.println("Materia no eliminada");
           }
        }catch(SQLException e){
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }
    }

}
