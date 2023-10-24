package lab1proyectotransversal.accesoADatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grupo-3
 */
public class Conexion {

    private static final String CLASSPATH = "org.mariadb.jdbc.Driver";
    private static final String URL = "jdbc:mariadb://localhost:3306/";
    private static final String DB = "proyecto_transversal";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private static Connection connection = null;

    private Conexion() {
    }

    public static Connection getInstance() {
        if (connection == null) {
            try {
                Class.forName(CLASSPATH);
                connection = DriverManager.getConnection(URL + DB, USUARIO, PASSWORD);

                System.out.println("Conexión exitosa");

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }

}
