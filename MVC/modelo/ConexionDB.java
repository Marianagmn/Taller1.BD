package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MODELO: Clase para gestionar la conexión a la base de datos MySQL
 * Patrón Singleton para una única instancia de conexión
 */
public class ConexionDB {
    
    // Configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/articulos_fintech";
    private static final String USUARIO = "root";
    private static final String PASSWORD = ""; // Cambiar según configuración
    
    private static Connection conexion = null;
    
    /**
     * Constructor privado (Patrón Singleton)
     */
    private ConexionDB() {
    }
    
    /**
     * Obtiene la conexión a la base de datos
     * @return Objeto Connection
     * @throws SQLException si hay error de conexión
     */
    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                // Cargar el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establecer conexión
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("✓ Conexión exitosa a la base de datos");
                
            } catch (ClassNotFoundException e) {
                System.err.println("Error: Driver de MySQL no encontrado");
                System.err.println("Asegúrate de tener mysql-connector-java.jar en lib/");
                throw new SQLException("Driver no encontrado", e);
            }
        }
        return conexion;
    }
    
    /**
     * Cierra la conexión a la base de datos
     */
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("✓ Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
    
    /**
     * Verifica si la conexión está activa
     * @return true si está conectada, false en caso contrario
     */
    public static boolean estaConectado() {
        try {
            return conexion != null && !conexion.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
