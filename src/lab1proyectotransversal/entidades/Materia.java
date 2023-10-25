package lab1proyectotransversal.entidades;

/**
 *
 * @author Grupo-3
 */
public class Materia {

    private int idMateria;
    private String nombre;
    private int anio;
    private boolean estado;

    public Materia() {
    }

    public Materia(int idMateria, String nombre, int anio, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.anio = anio;
        this.estado = estado;
    }

    public Materia(String nombre, int anio, boolean estado) {
        this.idMateria = -1;    // Luego se utilizara para denotar que el verdadero id debe ser establecido por el gestor de BD
        this.nombre = nombre;
        this.anio = anio;
        this.estado = estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Se va a utilizar en la muestra de datos en las vistas
    @Override
    public String toString() {
        return nombre + ", " + anio;
    }
    
    // Se va a utilizar para la muestra de datos por consola
    public String mostrarMateria(){
        return "Materia{" + "idMateria=" + idMateria + ", nombre=" + nombre + ", año=" + anio + ", estado=" + estado + '}';
    }

}
