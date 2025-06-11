package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String URL = "jdbc:mysql://localhost:3306/BDRentaCar";
    private static String USER = "root";
    private static String PASS = "Niggatepas12";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
