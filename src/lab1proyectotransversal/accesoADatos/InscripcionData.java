package lab1proyectotransversal.accesoADatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lab1proyectotransversal.entidades.*;

/**
 *
 * @author Grupo-3
 */
public class InscripcionData {

    private Connection connection;
    private MateriaData matData;
    private AlumnoData aluData;

    public InscripcionData(MateriaData matData, AlumnoData aluData) {
        this.connection = Conexion.getInstance();
        this.matData = matData;
        this.aluData = aluData;
    }

    public boolean guardarInscripcion(Inscripcion inscripcion) {
        boolean result = true;
        try {
            // Preparando la sentencia SQL
            String sql;
            if (inscripcion.getIdInscripto() == -1) {   // El idInscripto no es ingresado por usuario
                sql = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES (?,?,?)";
            } else {
                sql = "INSERT INTO inscripcion(nota, idAlumno, idMateria, idInscripto) VALUES (?,?,?,?)";
            }

            // PreparedStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, inscripcion.getNota());
            ps.setInt(2, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(3, inscripcion.getMateria().getIdMateria());
            if (inscripcion.getIdInscripto() != -1) {
                ps.setInt(4, inscripcion.getIdInscripto());
            }

            // Corroborando si se llevó a cabo exitosamente la operación
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Inscripción agregada");
            }

            // Cerrando ps
            ps.close();

        } catch (SQLException sqle) {
            result = false;     // No se pudo llevar a cabo exitosamente la operación
            int errorCode = sqle.getErrorCode();
            if (errorCode != 1062) { // Ignorar inscripciones repetidas
                System.out.println("[Error " + errorCode + "] " + sqle.getMessage());
                sqle.printStackTrace();
            } else {
                System.out.println("[Inscripcion repetida] " + sqle.getMessage());
            }
        }
        return result;
    }

    public List<Inscripcion> obtenerInscripciones() {
        List<Inscripcion> listaIncripciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM inscripcion";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripto(rs.getInt("idInscripto"));
                inscripcion.setNota(rs.getInt("nota"));
                inscripcion.setAlumno(aluData.buscarAlumno(rs.getInt("idAlumno")));
                inscripcion.setMateria(matData.buscarMateria(rs.getInt("idMateria")));
                listaIncripciones.add(inscripcion);
            }
            ps.close();
        } catch (SQLException sqle) {
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return listaIncripciones;
    }

    public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno) {
        List<Inscripcion> listaIncripciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM inscripcion WHERE idAlumno=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripto(rs.getInt("idInscripto"));
                inscripcion.setNota(rs.getInt("nota"));
                inscripcion.setAlumno(aluData.buscarAlumno(rs.getInt("idAlumno")));
                inscripcion.setMateria(matData.buscarMateria(rs.getInt("idMateria")));
                listaIncripciones.add(inscripcion);
            }
            ps.close();
        } catch (SQLException sqle) {
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return listaIncripciones;
    }

    public List<Materia> obtenerMateriasCursadas(int idAlumno) {
        List<Materia> listaMaterias = new ArrayList<>();
        try {
            String sql = "SELECT idMateria FROM inscripcion WHERE idAlumno=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaMaterias.add(matData.buscarMateria(rs.getInt("idMateria")));
            }
        } catch (SQLException sqle) {
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return listaMaterias;
    }

    public List<Materia> obtenerMateriasNOCursadas(int idAlumno) {
        List<Materia> listaMaterias = new ArrayList<>();
        try {
            String sql = "SELECT idMateria FROM materia WHERE idMateria NOT IN (SELECT idMateria FROM inscripcion WHERE idAlumno=?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaMaterias.add(matData.buscarMateria(rs.getInt("idMateria")));
            }
        } catch (SQLException sqle) {
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return listaMaterias;
    }

    public boolean borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        boolean result = true;
        try {
            String sql = "DELETE FROM inscripcion WHERE idAlumno=? AND idMateria=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Inscripción borrada.");
            } else {
                System.out.println("Inscripción NO borrada.");
            }
        } catch (SQLException sqle) {
            result = false;
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return result;
    }

    public boolean actualizarNota(int idAlumno, int idMateria, int nota) {
        boolean result = true;
        try {
            String sql = "UPDATE inscripcion SET nota=? WHERE idAlumno=? AND idMateria=? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Nota actualizada.");
            } else {
                System.out.println("Nota NO actualizada.");
            }
        } catch (SQLException sqle) {
            result = false;
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return result;
    }

    public List<Alumno> obtenerAlumnoXMateria(int idMateria) {
        List<Alumno> listaAlumnos = new ArrayList<>();
        try {
            String sql = "SELECT idAlumno FROM inscripcion WHERE idMateria=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaAlumnos.add(aluData.buscarAlumno(rs.getInt("idAlumno")));
            }
        } catch (SQLException sqle) {
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return listaAlumnos;
    }
}
