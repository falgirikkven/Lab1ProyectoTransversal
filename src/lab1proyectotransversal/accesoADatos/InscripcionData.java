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

    private final Connection connection;
    private final MateriaData matData;
    private final AlumnoData aluData;

    public InscripcionData(MateriaData matData, AlumnoData aluData) {
        this.connection = Conexion.getInstance();
        this.matData = matData;
        this.aluData = aluData;
    }

    public boolean guardarInscripcion(Inscripcion inscripcion) {
        boolean result = true;
        try {
            // Creando la sentencia SQL
            String sql;
            if (inscripcion.getIdInscripto() == -1) {   // Si idInscripto no es ingresado por usuario, utilizar un constructor que no lo tenga como parámetro
                sql = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES (?,?,?)";
            } else {
                sql = "INSERT INTO inscripcion(nota, idAlumno, idMateria, idInscripto) VALUES (?,?,?,?)";
            }
            // Crando el PreparedStatement
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
            }else{
                System.out.println("No se ha podido agregar la inscripción");
            }
            
            ps.close();

        } catch (SQLException sqle) {
            result = false;     
            // Informando sobre el error por consola
            int errorCode = sqle.getErrorCode();
            if (errorCode != 1062) {        
                System.out.println("[Error " + errorCode + "] " + sqle.getMessage());
                sqle.printStackTrace();
            } else {        // Inscripción repetida
                System.out.println("[Inscripcion repetida] " + sqle.getMessage());
            }
        }
        return result;
    }

    public List<Inscripcion> obtenerInscripciones() {
        List<Inscripcion> listaIncripciones = new ArrayList<>();
        Inscripcion inscripcion;        
        try {
            // Trayendo las incripciones 
            String sql = "SELECT * FROM inscripcion";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            // Pasando las inscripciones a listaIncripciones
            while (rs.next()) {
                // Creando y asignando una instancia de Inscripcion
                inscripcion = new Inscripcion();                
                inscripcion.setIdInscripto(rs.getInt("idInscripto"));
                inscripcion.setNota(rs.getInt("nota"));
                inscripcion.setAlumno(aluData.buscarAlumno(rs.getInt("idAlumno")));
                inscripcion.setMateria(matData.buscarMateria(rs.getInt("idMateria")));
                
                listaIncripciones.add(inscripcion);
            }
            ps.close();
        } catch (SQLException sqle) {
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return listaIncripciones;
    }

    public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno) {
        List<Inscripcion> listaIncripciones = new ArrayList<>();    
        Alumno alumno;
        Materia materia;
        Inscripcion inscripcion;
        try {
            // Trayendo las incripciones (junto con los alumnos y las materias involucrados en las mismas) 
            String sql = "SELECT * FROM alumno JOIN inscripcion JOIN materia ON (materia.idMateria=inscripcion.idMateria AND alumno.idAlumno=inscripcion.idAlumno) WHERE alumno.idAlumno=?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            // Pasando las incripciones a listaIncripciones
            while (rs.next()) {                        
                // Creando y asignando una instancia de Alumno
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("alumno.nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("alumno.estado"));
                // Creando y asignando una instancia de Materia
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("materia.nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("materia.estado"));
                // Creando y asignando una instancia de Inscripcion
                inscripcion = new Inscripcion(); 
                inscripcion.setIdInscripto(rs.getInt("idInscripto"));
                inscripcion.setNota(rs.getInt("nota"));
                inscripcion.setAlumno(alumno);         
                inscripcion.setMateria(materia);
                
                listaIncripciones.add(inscripcion);
            }
            ps.close();
        } catch (SQLException sqle) {
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return listaIncripciones;
    }

    public List<Materia> obtenerMateriasCursadas(int idAlumno) {
        List<Materia> listaMaterias = new ArrayList<>();
        Materia materia;
        try {
            // Trayendo las materias en las cuales esté inscripto un alumno específico
            String sql = "SELECT * FROM materia WHERE idMateria IN (SELECT idMateria FROM inscripcion WHERE idAlumno=?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            // Pasando las materias a listaMaterias
            while (rs.next()) {
                // Creando y asignando una instancia de Materia
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                
                listaMaterias.add(materia);
            }
            ps.close();
        } catch (SQLException sqle) {
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return listaMaterias;
    }

    public List<Materia> obtenerMateriasNOCursadas(int idAlumno) {
        List<Materia> listaMaterias = new ArrayList<>();
        Materia materia;
        try {
            // Trayendo las materias que no estén involucradas en una inscripción en un alumno específico
            String sql = "SELECT * FROM materia WHERE idMateria NOT IN (SELECT idMateria FROM inscripcion WHERE idAlumno=?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            // Pasando las materias a listaMaterias
            while (rs.next()) {
                // Creando y asignando una instancia de Materia
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                
                listaMaterias.add(materia);
            }
            ps.close();
        } catch (SQLException sqle) {
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return listaMaterias;
    }

    public boolean borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        boolean result = true;
        try {
            // Borrando las inscripciones que involucren a un alumno específico y una materia específica
            String sql = "DELETE FROM inscripcion WHERE idAlumno=? AND idMateria=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            // Corroborando si se llevó a cabo exitosamente la operación
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Inscripción borrada.");
            } else {
                System.out.println("Inscripción NO borrada.");
                result = false;
            }
            ps.close();
        } catch (SQLException sqle) {
            result = false;
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return result;
    }

    public boolean actualizarNota(int idAlumno, int idMateria, int nota) {
        boolean result = true;
        // Corroborando si la nota pasada por parámetro es válida
        if (nota < 1 || nota > 10) {
            System.out.println("Error: la nota a cargar debe ser mayor o igual que 1 y menor o igual que 10.");
            return false;
        }
        try {
            // Actualizando las notas de las inscripciones que involucran a un alumno y una materia específicos
            String sql = "UPDATE inscripcion SET nota=? WHERE idAlumno=? AND idMateria=?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            // Corroborando si la operación se llevó a cabo correctamente
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Nota actualizada");
            } else {
                System.out.println("No se ha podido actualizar la nota");
                result = false;
            }
            ps.close();
        } catch (SQLException sqle) {            
            result = false;
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return result;
    }

    public List<Alumno> obtenerAlumnoXMateria(int idMateria) {
        List<Alumno> listaAlumnos = new ArrayList<>();
        Alumno alumno;
        try {
            // Trayendo los alumnos que se hayan inscripto en una materia específica
            String sql = "SELECT * FROM alumno WHERE idAlumno IN (SELECT idAlumno FROM inscripcion WHERE idMateria=?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            // Pasando los alumnos a listaAlumnos
            while (rs.next()) {
                // Creando y asignando una instancia de Alumno
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                
                listaAlumnos.add(alumno);
            }
        } catch (SQLException sqle) {
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "] " + sqle.getMessage());
            sqle.printStackTrace();
        }
        return listaAlumnos;
    }
}
