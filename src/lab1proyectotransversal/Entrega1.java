package lab1proyectotransversal;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import lab1proyectotransversal.accesoADatos.AlumnoData;
import lab1proyectotransversal.accesoADatos.Conexion;
import lab1proyectotransversal.entidades.Alumno;

/**
 *
 * @author Grupo-3
 */
public class Entrega1 {

    public static void main(String args[]) {
        
        // Establecer la conexion
        Connection con = Conexion.getInstance();
        if (con == null) {
            // TODO: JOptionPane mensaje de error
            return;
        }

        // Crear alumno data
        AlumnoData alumnoData = new AlumnoData(con);

        // Crear alumnos
        Alumno alumno1 = new Alumno(1, 11000111, "Nahuel", "Lucero", LocalDate.of(1998, Month.AUGUST, 1), true);
        Alumno alumno2 = new Alumno(2, 37666666, "Leonel", "Nievas", LocalDate.of(1993, Month.AUGUST, 7), true);
        Alumno alumno3 = new Alumno(3, 40000444, "Nahuel", "Ochoa", LocalDate.of(1999, Month.OCTOBER, 18), true);
        Alumno alumnos[] = new Alumno[]{alumno1, alumno2, alumno3};

        // Guardar alumnos
        System.out.println("\nGuardar Alumnos");
        for (Alumno alumno : alumnos) {
            alumnoData.guardarAlumno(alumno);
        }

        // Buscar Alumno
        System.out.println("\nBuscar Alumnos");
        int cualAlumnoBuscar;
        Alumno alumnoEncontrado;
        cualAlumnoBuscar = 2;
        alumnoEncontrado = alumnoData.buscarAlumno(cualAlumnoBuscar);
        System.out.println("Datos del alumno " + cualAlumnoBuscar + ":"); // Deberia funcionar
        if (alumnoEncontrado != null) {
            System.out.println(alumnoEncontrado.toString());
        } else {
            System.out.println("No encontrado");
        }
        cualAlumnoBuscar = 666;
        alumnoEncontrado = alumnoData.buscarAlumno(cualAlumnoBuscar);
        System.out.println("Datos del alumno " + cualAlumnoBuscar + ":"); // No deberia funcionar
        if (alumnoEncontrado != null) {
            System.out.println(alumnoEncontrado.toString());
        } else {
            System.out.println("No encontrado");
        }

        // Listar Alumnos
        System.out.println("\nListar Alumnos");
        List<Alumno> listaAlumnos = alumnoData.listarAlumnos();
        for (Alumno alumno : listaAlumnos) {
            System.out.println(alumno.toString());
        }

        // Editar Alumno
        int cualAlumnoEditar = 2;
        System.out.println("\nEditar Alumno (Edito el " + cualAlumnoEditar + ")");
        Alumno alumnoEdit;
        alumnoEdit = new Alumno(cualAlumnoEditar, 42897241, "Ramiro", "Moran", LocalDate.of(2000, Month.NOVEMBER, 13), true);
        alumnoData.modificarAlumno(alumnoEdit);

        // Eliminar Alumno
        int cualAlumnoEliminar = 2;
        System.out.println("\nEliminar Alumno (Edito el " + cualAlumnoEliminar + ")");
        alumnoData.eliminarAlumno(cualAlumnoEliminar);

    }

}
