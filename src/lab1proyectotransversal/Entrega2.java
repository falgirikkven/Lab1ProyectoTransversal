package lab1proyectotransversal;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javax.swing.JOptionPane;
import lab1proyectotransversal.accesoADatos.AlumnoData;
import lab1proyectotransversal.accesoADatos.Conexion;
import lab1proyectotransversal.accesoADatos.InscripcionData;
import lab1proyectotransversal.accesoADatos.MateriaData;
import lab1proyectotransversal.entidades.Alumno;
import lab1proyectotransversal.entidades.Inscripcion;
import lab1proyectotransversal.entidades.Materia;

/**
 *
 * @author Grupo-3
 */
public class Entrega2 {

    public static void main(String[] args) {

        // Establecer la conexion
        Connection con = Conexion.getInstance();
        if (con == null) {
            JOptionPane.showMessageDialog(null, "Error al establecer la conexión", "Error", 0);
            return;
        }

        // Pruebas con AlumnoData
        //
        // Crear alumnoData
        AlumnoData alumnoData = new AlumnoData();

        // Crear alumnos
        Alumno alumno1 = new Alumno(1, 11000111, "Nahuel", "Lucero", LocalDate.of(1998, Month.AUGUST, 1), true);
        Alumno alumno2 = new Alumno(2, 37666666, "Leonel", "Nievas", LocalDate.of(1993, Month.AUGUST, 7), true);
        Alumno alumno3 = new Alumno(3, 40000444, "Nahuel", "Ochoa", LocalDate.of(1999, Month.OCTOBER, 18), true);
        Alumno alumnos[] = new Alumno[]{alumno1, alumno2, alumno3};

        // Guardar alumnos
        System.out.println("\n-----Guardar Alumnos-----");
        for (Alumno alumno : alumnos) {
            alumnoData.guardarAlumno(alumno);
        }

        // Listar Alumnos
        System.out.println("\n-----Listar Alumnos-----");
        List<Alumno> listaAlumnos = alumnoData.listarAlumnos();
        for (Alumno alumno : listaAlumnos) {
            System.out.println(alumno.toString());
        }

        // Buscar Alumno
        System.out.println("\n-----Buscar Alumno-----");
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

        // Editar Alumno
        int cualAlumnoEditar = 2;
        System.out.println("\n-----Editar Alumno (Edito el " + cualAlumnoEditar + ")-----");
        Alumno alumnoEdit;
        alumnoEdit = new Alumno(cualAlumnoEditar, 42897241, "Ramiro", "Moran", LocalDate.of(2000, Month.NOVEMBER, 13), true);
        alumnoData.modificarAlumno(alumnoEdit);

        // Listar Alumnos para ver los efectos de la edición
        System.out.println("\n-----Listar Alumnos-----");
        listaAlumnos = alumnoData.listarAlumnos();
        for (Alumno alumno : listaAlumnos) {
            System.out.println(alumno.toString());
        }

        // Eliminar Alumno
        int cualAlumnoEliminar = 2;
        System.out.println("\n-----Eliminar Alumno (Edito el " + cualAlumnoEliminar + ")-----");
        alumnoData.eliminarAlumno(cualAlumnoEliminar);
        //

        // Listar Alumnos para ver los efectos de la baja
        System.out.println("\n-----Listar Alumnos-----");
        listaAlumnos = alumnoData.listarAlumnos();
        for (Alumno alumno : listaAlumnos) {
            System.out.println(alumno.toString());
        }
        // Fin Pruebas con AlumnoData

        // Pruebas MateriaData
        //
        // Crear materiaData
        MateriaData materiaData = new MateriaData();

        // Crear materias        
        Materia materia1 = new Materia(1, "Matematica", 2013, true);
        Materia materia2 = new Materia(2, "Literatura", 2015, true);
        Materia materia3 = new Materia(3, "Fisica", 2020, true);
        Materia materias[] = new Materia[]{materia1, materia2, materia3};

        // Guardar materias
        System.out.println("\n\n\n-----Guardar Materia-----");
        for (Materia materia : materias) {
            materiaData.guardarMateria(materia);
        }

        // Listar materias
        System.out.println("\n-----Listar materias-----");
        List<Materia> listaMateria = materiaData.listarMaterias();
        for (Materia materia
                : listaMateria) {
            System.out.println(materia.toString());
        }

        // Buscar materia
        System.out.println("\n-----Buscar materias-----");
        int materiaBuscada = 3;
        Materia materiaEnco = materiaData.buscarMateria(materiaBuscada);
        System.out.println("Datos de la materia con id=" + materiaBuscada + ":");       // búsqueda exitosa 
        if (materiaEnco != null) {
            System.out.println(materiaEnco.toString());
        } else {
            System.out.println("No encontrada");
        }
        materiaBuscada = 99;
        materiaEnco = materiaData.buscarMateria(materiaBuscada);
        System.out.println("Datos de la materia con id=" + materiaBuscada + ":");       // búsqueda fallida 
        if (materiaEnco != null) {
            System.out.println(materiaEnco.toString());
        } else {
            System.out.println("No encontrada");
        }

        // Modificar materia
        int idmateriaMod = 1;

        System.out.println("\n-----Modificar materia-----");
        Materia modifMateria;
        modifMateria = new Materia(idmateriaMod, "Matematicas", 2003, true);

        materiaData.modificarMateria(modifMateria);

        // Listar materias para ver el efecto de la modificación
        System.out.println("\n-----Listar materias-----");
        listaMateria = materiaData.listarMaterias();
        for (Materia materia
                : listaMateria) {
            System.out.println(materia.toString());
        }

        // Eliminar materia(logico)
        int materiaElim = 2;

        System.out.println("\n-----Eliminar materia-----");
        materiaData.eliminarMateria(materiaElim);
        //

        // Listar materias para ver el efecto de la baja
        System.out.println("\n-----Listar materias-----");
        listaMateria = materiaData.listarMaterias();
        for (Materia materia
                : listaMateria) {
            System.out.println(materia.toString());
        }

        // Fin Pruebas Materia Data
        // Pruebas con InscripcionData
        //
        // Instanciar InscripcionData
        InscripcionData inscripcionData = new InscripcionData(materiaData, alumnoData);

        // Crear inscripciones
        Inscripcion inscripcion1 = new Inscripcion(1, alumno1, materia1, 7);
        Inscripcion inscripcion2 = new Inscripcion(2, alumno1, materia2, 9);
        Inscripcion inscripcion3 = new Inscripcion(3, alumno2, materia1, 8);
        Inscripcion inscripcion4 = new Inscripcion(4, alumno2, materia3, 8);
        Inscripcion inscripcion5 = new Inscripcion(5, alumno3, materia2, 5);
        Inscripcion inscripcion6 = new Inscripcion(6, alumno3, materia3, 10);

        // Arreglo de inscripciones
        Inscripcion[] inscripciones = {inscripcion1, inscripcion2, inscripcion3, inscripcion4, inscripcion5, inscripcion6};

        // Agregar inscripciones
        System.out.println("\n\n\n-----Guardar inscripciones-----");
        for (Inscripcion inscripcion : inscripciones) {
            inscripcionData.guardarInscripcion(inscripcion);
        }

        // Mostrar todas las inscripciones
        System.out.println("\n-----Mostrar todas las inscripciones-----");
        List<Inscripcion> listaInscripciones = inscripcionData.obtenerInscripciones();
        for (Inscripcion inscripcion : listaInscripciones) {
            System.out.println(inscripcion.toString());
        }

        // Mostrar todas las inscripciones que correspondan a un alumno en particular
        System.out.println("\n-----Mostrar todas las inscripciones que correspondan al alumno con id=1-----");
        List<Inscripcion> listaInscripcionesDeAlumno = inscripcionData.obtenerInscripcionesPorAlumno(1);
        for (Inscripcion inscripcion : listaInscripcionesDeAlumno) {
            System.out.println(inscripcion.toString());
        }

        // Mostrar todas las materias a las que alguna vez se inscribió un alumno en particular
        System.out.println("\n-----Mostrar todas las materias a las que alguna vez se inscribió el alumno con id=1-----");
        List<Materia> listaMatCurPorAlumno = inscripcionData.obtenerMateriasCursadas(1);
        for (Materia materia : listaMatCurPorAlumno) {
            System.out.println(materia.toString());
        }

        // Mostrar todas la materias en las que nunca se inscribió un alumno en particular
        System.out.println("\n-----Mostrar todas la materias en las que nunca se inscribió el alumno con id=1-----");
        List<Materia> listaMatNoCurPorAlumno = inscripcionData.obtenerMateriasNOCursadas(1);
        for (Materia materia : listaMatNoCurPorAlumno) {
            System.out.println(materia.toString());
        }

        // Borrar inscripcion (baja física)
        System.out.println("\n-----Borrar la inscripción con idAlumno=3 e idMateria=3-----");
        inscripcionData.borrarInscripcionMateriaAlumno(3, 3);

        // Mostrar todas las inscripciones para verificar la baja 
        System.out.println("\n-----Mostrar todas las inscripciones-----");
        List<Inscripcion> listaInscripciones2 = inscripcionData.obtenerInscripciones();
        for (Inscripcion inscripcion : listaInscripciones2) {
            System.out.println(inscripcion.toString());
        }

        // Actualizar nota
        System.out.println("\n-----Actualizar nota de inscripción con idAlumno=3 e idMateria=2-----");
        inscripcionData.actualizarNota(3, 2, 7);

        // Mostrar todas las inscripciones para verificar la modificación de la nota 
        System.out.println("\n-----Recuperar y mostrar todas las inscripciones-----");
        List<Inscripcion> listaInscripciones3 = inscripcionData.obtenerInscripciones();
        for (Inscripcion inscripcion : listaInscripciones3) {
            System.out.println(inscripcion.toString());
        }

        // Mostrar alumnos inscriptos en una materia en particular
        System.out.println("\n-----Mostrar todos los alumnos que se han inscripto en la materia con id=2-----");
        List<Alumno> listaAlumEnMat = inscripcionData.obtenerAlumnoXMateria(2);
        for (Alumno alumno : listaAlumEnMat) {
            System.out.println(alumno.toString());
        }
    }
}
