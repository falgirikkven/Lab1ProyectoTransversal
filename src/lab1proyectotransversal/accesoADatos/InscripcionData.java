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

    public InscripcionData(AlumnoData aluData, MateriaData matData) {
        this.connection = Conexion.getInstance();
        this.matData = matData;
        this.aluData = aluData;
    }

    public void guardarInscripcion(Inscripcion inscripcion) {
        try {
            String sql = "INSERT INTO inscripcion(idInscripto, nota, idAlumno, idMateria) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, inscripcion.getIdInscripto());
            ps.setInt(2, inscripcion.getNota());
            ps.setInt(3, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(4, inscripcion.getMateria().getIdMateria());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("\nInscripción agregada");
            }
            ps.close();
        } catch (SQLException sqle) {
            int errorCode = sqle.getErrorCode();
            if (errorCode != 1062) { // Ignorar inscripciones repetidas
                System.out.println("[Error " + errorCode + "] " + sqle.getMessage());
                sqle.printStackTrace();
            } else {
                System.out.println("[Inscripcion repetida] " + sqle.getMessage());
            }
        }
    }

    public List<Inscripcion> obtenerInscripciones() {
        List<Inscripcion> listaIncripciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM inscripcion";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripto(rs.getInt("idInscripcion"));
                inscripcion.setNota(rs.getInt("nota"));
                inscripcion.setAlumno(aluData.buscarAlumno(rs.getInt("idAlumno")));
                inscripcion.setMateria(matData.buscarMateria(rs.getInt("materia")));
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
                inscripcion.setIdInscripto(rs.getInt("idInscripcion"));
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
        listaMaterias = matData.listarMaterias();
        try {
            String sql = "SELECT idMateria FROM inscripcion WHERE idAlumno=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaMaterias.remove(matData.buscarMateria(rs.getInt("idMateria")));
            }
        } catch (SQLException sqle) {
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return listaMaterias;
    }

    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        try {
            String sql = "DELETE FROM inscripcion WHERE idAlumno=? AND idMateria=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("\nInscripción borrada.");
            } else {
                System.out.println("\nInscripción NO borrada.");
            }
        } catch (SQLException sqle) {
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
    }

    public void actualizarNota(int idAlumno, int idMateria, int nota) {
        try {
            String sql = "UPDATE inscripcion SET nota=? WHERE idAlumno=? AND idMateria=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("\nNota actualizada.");
            } else {
                System.out.println("\nNota NO actualizada.");
            }
        } catch (SQLException sqle) {
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
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
