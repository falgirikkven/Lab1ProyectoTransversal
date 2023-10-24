package lab1proyectotransversal;

import java.sql.Connection;
import javax.swing.JOptionPane;
import lab1proyectotransversal.accesoADatos.Conexion;
import lab1proyectotransversal.vistas.MainFrame;

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
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MainFrame.ejecutar(args);
    }
}
