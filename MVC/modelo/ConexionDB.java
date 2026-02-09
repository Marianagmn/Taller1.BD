package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/articulos_fintech?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USUARIO = "root"; 
    private static final String PASSWORD = ""; 

    private static Connection conexion = null;

    private ConexionDB() {}

    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("✓ Conexión exitosa a la base de datos (XAMPP)");
            } catch (ClassNotFoundException e) {
                System.err.println("Error: Driver de MySQL no encontrado");
                e.printStackTrace();
                throw new SQLException("Driver no encontrado", e);
            }
        }
        return conexion;
    }

    public static void main(String[] args) throws Exception {
        getConexion(); 
    }
}
