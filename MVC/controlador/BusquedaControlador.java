package controlador;

import modelo.Busqueda;
import modelo.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CONTROLADOR: BusquedaControlador
 * Maneja la lógica de negocio para las búsquedas científicas
 */
public class BusquedaControlador {
    
    /**
     * Obtiene todas las búsquedas de la base de datos
     * @return Lista de búsquedas
     */
    public List<Busqueda> obtenerTodasLasBusquedas() {
        List<Busqueda> busquedas = new ArrayList<>();
        String sql = "SELECT * FROM busquedas ORDER BY id";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Busqueda busqueda = new Busqueda();
                busqueda.setId(rs.getInt("id"));
                busqueda.setNombreEstudiante(rs.getString("nombre_estudiante"));
                busqueda.setBaseDatos(rs.getString("base_datos"));
                busqueda.setCadenaBusqueda(rs.getString("cadena_busqueda"));
                busqueda.setCantidadDocumentos(rs.getInt("cantidad_documentos"));
                busqueda.setFechaBusqueda(rs.getTimestamp("fecha_busqueda"));
                
                busquedas.add(busqueda);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener búsquedas: " + e.getMessage());
            e.printStackTrace();
        }
        
        return busquedas;
    }
    
    /**
     * Obtiene una búsqueda por su ID
     * @param id ID de la búsqueda
     * @return Objeto Busqueda o null si no existe
     */
    public Busqueda obtenerBusquedaPorId(int id) {
        String sql = "SELECT * FROM busquedas WHERE id = ?";
        Busqueda busqueda = null;
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                busqueda = new Busqueda();
                busqueda.setId(rs.getInt("id"));
                busqueda.setNombreEstudiante(rs.getString("nombre_estudiante"));
                busqueda.setBaseDatos(rs.getString("base_datos"));
                busqueda.setCadenaBusqueda(rs.getString("cadena_busqueda"));
                busqueda.setCantidadDocumentos(rs.getInt("cantidad_documentos"));
                busqueda.setFechaBusqueda(rs.getTimestamp("fecha_busqueda"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener búsqueda: " + e.getMessage());
        }
        
        return busqueda;
    }
    
    /**
     * Obtiene estadísticas generales de las búsquedas
     * @return Array con [totalBusquedas, totalDocumentos]
     */
    public int[] obtenerEstadisticas() {
        String sql = "SELECT COUNT(*) as total, SUM(cantidad_documentos) as suma FROM busquedas";
        int[] stats = new int[2];
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                stats[0] = rs.getInt("total");
                stats[1] = rs.getInt("suma");
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener estadísticas: " + e.getMessage());
        }
        
        return stats;
    }
}
