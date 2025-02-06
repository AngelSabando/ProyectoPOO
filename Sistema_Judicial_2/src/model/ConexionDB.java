
package model;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_judicial";
    private static final String USER = "root";
    private static final String PASSWORD = "password";  

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
    
    public static void cerrar(Connection conexion) throws SQLException{
        conexion.close();
    }
    
    public static void cerrar(Statement sentencia) throws SQLException{
        sentencia.close();
    }
    
    
    public static void cerrar(PreparedStatement sentencia) throws SQLException{
        sentencia.close();
    }
    
    public static void cerrar(ResultSet resultado) throws SQLException{
        resultado.close();
    }
    
    public static void main(String[] args) {
    Connection con = conectar();
    if (con != null) {
        System.out.println("¡Conexión exitosa!");
    } else {
        System.out.println("Error en la conexión.");
    }
}

}

