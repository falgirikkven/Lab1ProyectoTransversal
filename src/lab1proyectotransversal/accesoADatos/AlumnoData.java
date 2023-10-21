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

    public AlumnoData() {
        this.connection = Conexion.getInstance();
    }

    public boolean guardarAlumno(Alumno alumno) {
        boolean result = true;

        try {
            // Preparar la estructura de la consulta
            String sql = "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado, idAlumno) VALUES (?, ?, ?, ?, ?, ?)";
            if (alumno.getIdAlumno() == -1) {
                sql = "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado) VALUES (?, ?, ?, ?, ?)";
            }

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, java.sql.Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());

            // Si idAlumno == -1 entonces se asigna automaticamente
            if (alumno.getIdAlumno() != -1) {
                ps.setInt(6, alumno.getIdAlumno());
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
            if (errorCode != 1062) { // Ignorar alumnos repetidos
                System.out.println("[Error " + errorCode + "] " + e.getMessage());
                e.printStackTrace();
            } else {
                System.out.println("[Alumno repetido] " + e.getMessage());
            }
        }

        return result;
    }

    public Alumno buscarAlumno(int idAlumno) {
        Alumno alumno = null;

        try {
            // Preparar la estructura de la consulta
            String sql = "SELECT * FROM alumno WHERE idAlumno=?";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);

            // Ejecutar querry
            ResultSet rs = ps.executeQuery();

            // Crear nuevo objeto alumno con los datos obtenidos
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(idAlumno);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }

        return alumno;
    }

    public Alumno buscarAlumnoPorDni(int dni) {
        /*
         * El proposito de esta funcion es poder buscarAlumnos con dni, como
         * idAlumno y dni son enteros la funcion no se puede sobrecargar.
         */
        Alumno alumno = null;

        try {
            // Preparar la estructura de la consulta
            String sql = "SELECT * FROM alumno WHERE dni=?";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, dni);

            // Ejecutar querry
            ResultSet rs = ps.executeQuery();

            // Crear nuevo objeto alumno con los datos obtenidos
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
            }

            // Cerrar el preparedStatement
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
            // Preparar la estructura de la consulta
            String sql = "SELECT * FROM alumno";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);

            // Ejecutar querry
            ResultSet rs = ps.executeQuery();

            // Guardar Alumnos en la lista
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                alumnos.add(alumno);
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }

        return alumnos;
    }

    public boolean modificarAlumno(Alumno alumno) {
        boolean result = true;

        try {
            // Preparar la estructura de la consulta
            String sqlLine = "UPDATE alumno SET dni=?, apellido=?, nombre=?, fechaNacimiento=?, estado=? WHERE idAlumno=?";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sqlLine);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, java.sql.Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.setInt(6, alumno.getIdAlumno());

            // Ejecutar querry
            int filas = ps.executeUpdate();

            if (filas > 0) { // Exito
                System.out.println("Alumno modificado");
            } else { // Fracaso
                result = false;
                System.out.println("Alumno no modificado");
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            result = false;
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    // Borrado logico
    public boolean eliminarAlumno(int idAlumno) {
        boolean result = true;

        try {
            // Preparar la estructura de la consulta
            String sql = "UPDATE alumno SET estado=false WHERE idAlumno=?";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);

            // Ejecutar querry
            int filas = ps.executeUpdate();

            if (filas > 0) { // Exito
                System.out.println("Alumno dado de baja");
            } else { // Fracaso
                result = false;
                System.out.println("Alumno no dado de baja");
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            result = false;
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    /*
    // Borrado fisico
    public boolean eliminarAlumnos(int idAlumno) {
        boolean result = true;

        try {
            // Preparar la estructura de la consulta
            String sql = "DELETE FROM alumno WHERE idAlumno=?";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);

            // Ejecutar querry
            int filas = ps.executeUpdate();
    
            if (filas > 0) { // Exito
                System.out.println("Alumno eliminado");
            } else { // Fracaso
                System.out.println("Alumno no eliminado");
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }
     */
}
