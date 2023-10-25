/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1proyectotransversal.entidades;

/**
 *
 * @author Grupo-3
 */
public class Inscripcion {

    private int idInscripto;
    private Alumno alumno;
    private Materia materia;
    private int nota;       // Permanece como entero por que así se vio en el modelo de la BD pasado 

    public Inscripcion(int idInscripto, Alumno alumno, Materia materia, int nota) {
        this.idInscripto = idInscripto;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public Inscripcion(Alumno alumno, Materia materia, int nota) {
        this.idInscripto = -1;      // Luego se utilizara para denotar que el verdadero id debe ser establecido por el gestor de BD
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public Inscripcion() {
    }

    public int getIdInscripto() {
        return idInscripto;
    }

    public void setIdInscripto(int idInscripto) {
        this.idInscripto = idInscripto;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
    @Override
    public String toString() {
        return "Inscripcion{" + "idInscripto=" + idInscripto + ", idAlumno=" + alumno.getIdAlumno() 
                + ", dniAlumno=" + alumno.getDni() + ", apellidoAlumno=" + alumno.getApellido() 
                + ", idMateria=" + materia.getIdMateria() + ", nombreMateria=" + materia.getNombre() 
                +  ", nota=" + nota + '}';
    }
    
    /* Este metodo existe para no generar confusion por el uso de nombres distintos en relacion con los 
    metodos equivalentes utilizados en las otras clases del parquete 'entidades' a la hora de mostrar 
    mensajes por consola, si bien se podría haber omitido su existencia y solo usar toString() */
    public String mostrarInscripcion() {        
        return "Inscripcion{" + "idInscripto=" + idInscripto + ", idAlumno=" + alumno.getIdAlumno() 
                + ", dniAlumno=" + alumno.getDni() + ", apellidoAlumno=" + alumno.getApellido() 
                + ", idMateria=" + materia.getIdMateria() + ", nombreMateria=" + materia.getNombre() 
                +  ", nota=" + nota + '}';
    }

}
