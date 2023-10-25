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
            // Creando la estructura de la sentencia SQL
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
            } else {
                System.out.println("No se ha podido agregar la inscripción");
            }

            ps.close();

        } catch (SQLException e) {
            result = false;
            // Informando sobre el error por consola
            int errorCode = e.getErrorCode();
            if (errorCode == 1062) {        // Inscripcion repetida
                System.out.println("[Error " + errorCode + "] (Inscripcion repetido)");
            } else {
                System.out.println("[Error " + errorCode + "]");
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<Inscripcion> obtenerInscripciones() {
        List<Inscripcion> listaIncripciones = new ArrayList<>();

        try {
            // Trayendo las incripciones 
            String sql = "SELECT * FROM inscripcion";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Pasando las inscripciones a listaIncripciones
            Inscripcion inscripcion;
            while (rs.next()) {
                inscripcion = new Inscripcion();
                inscripcion.setIdInscripto(rs.getInt("idInscripto"));
                inscripcion.setNota(rs.getInt("nota"));
                inscripcion.setAlumno(aluData.buscarAlumno(rs.getInt("idAlumno")));     // Se utilizan las clases AlumnoData y MateriaData aunque se podría utilizar una sentencia SQL más compleja
                inscripcion.setMateria(matData.buscarMateria(rs.getInt("idMateria")));  //

                listaIncripciones.add(inscripcion);
            }
            ps.close();
        } catch (SQLException sqle) {
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "]");
            sqle.printStackTrace();
        }
        return listaIncripciones;
    }

    public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno) {
        List<Inscripcion> listaIncripciones = new ArrayList<>();

        try {
            // Trayendo las incripciones (junto con los alumnos y las materias involucrados en las mismas) 
            String sql = "SELECT * FROM alumno JOIN inscripcion JOIN materia ON (materia.idMateria=inscripcion.idMateria AND alumno.idAlumno=inscripcion.idAlumno) WHERE alumno.idAlumno=?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            // Creando Alumno, Materia e Incripcion para luego pasar Inscripcion a listaIncripciones
            Alumno alumno;
            Materia materia;
            Inscripcion inscripcion;
            while (rs.next()) {
                // Creando y asignando una instancia de Alumno
                alumno = new Alumno();
                alumno.setIdAlumno(idAlumno);
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
            System.out.println("[Error " + sqle.getErrorCode() + "]");
            sqle.printStackTrace();
        }
        return listaIncripciones;
    }

    public List<Inscripcion> obtenerInscripPorAlumSegunEstado(int idAlumno, boolean estadoAlum, boolean estadoMat) {
        List<Inscripcion> listaIncripciones = new ArrayList<>();

        try {
            // Trayendo las incripciones (junto con los alumnos y las materias involucrados en las mismas) 
            String sql = "SELECT * FROM alumno JOIN inscripcion JOIN materia ON (materia.idMateria=inscripcion.idMateria AND alumno.idAlumno=inscripcion.idAlumno) WHERE alumno.idAlumno=? AND alumno.estado=? AND materia.estado=?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setBoolean(2, estadoAlum);
            ps.setBoolean(3, estadoMat);
            ResultSet rs = ps.executeQuery();

            // Creando Alumno, Materia e Incripcion para luego pasar Inscripcion a listaIncripciones
            Alumno alumno;
            Materia materia;
            Inscripcion inscripcion;
            while (rs.next()) {
                // Creando y asignando una instancia de Alumno
                alumno = new Alumno();
                alumno.setIdAlumno(idAlumno);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("alumno.nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(estadoAlum);

                // Creando y asignando una instancia de Materia
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("materia.nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(estadoMat);

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
            System.out.println("[Error " + sqle.getErrorCode() + "]");
            sqle.printStackTrace();
        }
        return listaIncripciones;
    }

    public List<Materia> obtenerMateriasCursadas(int idAlumno) {
        List<Materia> listaMaterias = new ArrayList<>();

        try {
            // Trayendo las materias en las cuales esté inscripto un alumno específico
            String sql = "SELECT * FROM materia WHERE idMateria IN (SELECT idMateria FROM inscripcion WHERE idAlumno=?) AND estado=true;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            // Pasando las materias encontradas a listaMaterias
            Materia materia;
            while (rs.next()) {
                // Creando y asignando una instancia de Materia
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(true);

                listaMaterias.add(materia);
            }
            ps.close();
        } catch (SQLException sqle) {
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "]");
            sqle.printStackTrace();
        }
        return listaMaterias;
    }

    public List<Materia> obtenerMateriasNOCursadas(int idAlumno) {
        List<Materia> listaMaterias = new ArrayList<>();

        try {
            // Trayendo las materias que no estén involucradas en una inscripción con un alumno específico
            String sql = "SELECT * FROM materia WHERE idMateria NOT IN (SELECT idMateria FROM inscripcion WHERE idAlumno=?) AND estado=true;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            // Pasando las materias a listaMaterias
            Materia materia;
            while (rs.next()) {
                // Creando y asignando una instancia de Materia
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(true);

                listaMaterias.add(materia);
            }
            ps.close();
        } catch (SQLException sqle) {
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "]");
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
                result = false;
                System.out.println("No se pudo borrar la inscripción.");
            }
            ps.close();
        } catch (SQLException sqle) {
            result = false;
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "]");
            sqle.printStackTrace();
        }
        return result;
    }

    public boolean actualizarNota(int idAlumno, int idMateria, int nota) {
        boolean result = true;

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
                result = false;
                System.out.println("No se ha podido actualizar la nota");
            }
            ps.close();
        } catch (SQLException sqle) {
            result = false;
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "]");
            sqle.printStackTrace();
        }
        return result;
    }

    public List<Alumno> obtenerAlumnoXMateria(int idMateria) {
        List<Alumno> listaAlumnos = new ArrayList<>();

        try {
            // Trayendo los alumnos que se hayan inscripto en una materia específica
            String sql = "SELECT * FROM alumno WHERE idAlumno IN (SELECT idAlumno FROM inscripcion WHERE idMateria=?) AND estado=true;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();

            // Pasando los alumnos a listaAlumnos
            Alumno alumno;
            while (rs.next()) {
                // Creando y asignando una instancia de Alumno
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);

                listaAlumnos.add(alumno);
            }
        } catch (SQLException sqle) {
            // Informando sobre el error por consola
            System.out.println("[Error " + sqle.getErrorCode() + "]");
            sqle.printStackTrace();
        }
        return listaAlumnos;
    }
}
