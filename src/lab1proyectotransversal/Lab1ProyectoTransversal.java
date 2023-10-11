package lab1proyectotransversal;

import java.sql.*;
import lab1proyectotransversal.accesoADatos.Conexion;
import lab1proyectotransversal.entidades.Alumno;

/**
 *
 * @author Grupo-3
 */
public class Lab1ProyectoTransversal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection con = Conexion.getInstance();
        if (con == null) {
            // JOptionPane mensaje de error
            return;
        }

        Alumno al = new Alumno();
        if (al.isEstado()== false) {
            System.out.println("es nulo");
        }
        System.out.printf("id alumno %d\n", al.getIdAlumno());
    }

}
