package lab1proyectotransversal;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import lab1proyectotransversal.accesoADatos.AlumnoData;
import lab1proyectotransversal.accesoADatos.Conexion;
import lab1proyectotransversal.accesoADatos.MateriaData;
import lab1proyectotransversal.entidades.Alumno;
import lab1proyectotransversal.entidades.Materia;

/**
 *
 * @author Grupo-3
 */
public class Lab1ProyectoTransversal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        System.out.println("Datos del alumno " + cualAlumnoBuscar + ":"); // Deberia funcionar
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
        
        //Crear materia data
        MateriaData materiadata=new MateriaData(con);
        
        //Crear materias
        System.out.println("\nGuardar materia");
        Materia materia1 = new Materia(1, "Matematica", 2013, true);
        Materia materia2= new Materia(2, "Literatura", 2015, true);
        Materia materia3= new Materia(3, "Fisica", 2020, true);
        Materia materias[] = new Materia[]{materia1,materia2,materia3};
        
        //Guardar materias
        System.out.println("\nGuardar Materia");
        for (Materia materia : materias) {
            materiadata.guardarMateria(materia);
        }
        
        //Buscar materia
        System.out.println("\nBuscar materias");
        int buscarmateria;
        Materia materiaEnco;
        buscarmateria=1;
        materiaEnco=materiadata.buscarMateria(buscarmateria);
        if(materiaEnco!=null){
            System.out.println("Materia encontrada");
        }else{
            System.out.println("Materia no encontrada");
        }
        
        //Listar materias
        System.out.println("\nListar materias");
        List<Materia> listaMateria = materiadata.listarMaterias();
        for(Materia materia: listaMateria){
            System.out.println(materia.toString());
        }
        
        //Modificar materia
        int idmateriaMod=2;
        System.out.println("\nModificar materia");
        Materia modifMateria;
        modifMateria=new Materia(idmateriaMod,"Matematicas",2003,true);
        materiadata.modificarMateria(modifMateria);
        
        //Eliminar materia(logico)
        int materiaElim=1;
        System.out.println("\nEliminar materia");
        materiadata.eliminarMateria(materiaElim);
    }
}
