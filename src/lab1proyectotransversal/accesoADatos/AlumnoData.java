package lab1proyectotransversal.accesoADatos;

import java.sql.*;
import java.util.ArrayList;
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
        try {
            String sqlLine = "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado, idAlumno) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sqlLine);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, java.sql.Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.setInt(6, alumno.getIdAlumno());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Alumno agregado");
            }
            ps.close();
        } catch (SQLException e) {
            int errorCode = e.getErrorCode();
            if (errorCode != 1062) { // Ignorar alumnos repetidos
                System.out.println("[Error " + errorCode + "] " + e.getMessage());
                e.printStackTrace();
            } else {
                System.out.println("[Alumno repetido] " + e.getMessage());
            }
        }
    }

    public Alumno buscarAlumno(int idAlumno) {
        Alumno alumno = null;
        try {
            String sql = "SELECT * FROM alumno WHERE idAlumno=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(idAlumno);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
            } else {
                // System.out.println("Alumno no encontrado");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }
        return alumno;
    }

    public List<Alumno> listarAlumnos() {
        List<Alumno> alumnos = new ArrayList();
        try {
            String sql = "SELECT * FROM alumno";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(alumno.getIdAlumno());
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                alumnos.add(alumno);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }
        return alumnos;
    }

    public void modificarAlumno(Alumno alumno) {
        try {
            String sqlLine = "UPDATE alumno SET dni=?, apellido=?, nombre=?, fechaNacimiento=?, estado=? WHERE idAlumno=?";
            PreparedStatement ps = connection.prepareStatement(sqlLine);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, java.sql.Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.setInt(6, alumno.getIdAlumno());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Alumno modificado");
            } else {
                System.out.println("Alumno no modificado");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminarAlumno(int idAlumno) {
        try {
            String sqlLine = "UPDATE alumno SET estado=false WHERE idAlumno=?";
            PreparedStatement ps = connection.prepareStatement(sqlLine);
            ps.setInt(1, idAlumno);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Alumno dado de baja");
            } else {
                System.out.println("Alumno no dado de baja");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /*
    public void eliminarAlumno(int idAlumno) {
        try {
            String sql = "DELETE FROM alumno WHERE idAlumno=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Alumno eliminado");
            } else {
                System.out.println("Alumno no eliminado");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }
    }
    */
}
