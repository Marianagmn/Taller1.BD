package controlador;

import modelo.Articulo;
import modelo.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CONTROLADOR: ArticuloControlador
 * Maneja la lógica de negocio para los artículos científicos
 */
public class ArticuloControlador {
    
    /**
     * Obtiene todos los artículos de la base de datos
     * @return Lista de artículos
     */
    public List<Articulo> obtenerTodosLosArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM articulos ORDER BY anio_publicacion DESC, id";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                articulos.add(mapearArticulo(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener artículos: " + e.getMessage());
            e.printStackTrace();
        }
        
        return articulos;
    }
    
    /**
     * Obtiene un artículo por su ID
     * @param id ID del artículo
     * @return Objeto Articulo o null si no existe
     */
    public Articulo obtenerArticuloPorId(int id) {
        String sql = "SELECT * FROM articulos WHERE id = ?";
        Articulo articulo = null;
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                articulo = mapearArticulo(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener artículo: " + e.getMessage());
        }
        
        return articulo;
    }
    
    /**
     * Obtiene artículos por búsqueda
     * @param busquedaId ID de la búsqueda
     * @return Lista de artículos
     */
    public List<Articulo> obtenerArticulosPorBusqueda(int busquedaId) {
        List<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM articulos WHERE busqueda_id = ? ORDER BY id";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, busquedaId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                articulos.add(mapearArticulo(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener artículos por búsqueda: " + e.getMessage());
        }
        
        return articulos;
    }
    
    /**
     * Busca artículos por palabra clave en título, autores o palabras clave
     * @param palabraClave Término de búsqueda
     * @return Lista de artículos encontrados
     */
    public List<Articulo> buscarArticulos(String palabraClave) {
        List<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM articulos WHERE " +
                    "titulo LIKE ? OR autores LIKE ? OR palabras_clave LIKE ? " +
                    "ORDER BY anio_publicacion DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String patron = "%" + palabraClave + "%";
            pstmt.setString(1, patron);
            pstmt.setString(2, patron);
            pstmt.setString(3, patron);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                articulos.add(mapearArticulo(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al buscar artículos: " + e.getMessage());
        }
        
        return articulos;
    }
    
    /**
     * Obtiene el conteo total de artículos
     * @return Número total de artículos
     */
    public int contarArticulos() {
        String sql = "SELECT COUNT(*) as total FROM articulos";
        int total = 0;
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                total = rs.getInt("total");
            }
            
        } catch (SQLException e) {
            System.err.println("Error al contar artículos: " + e.getMessage());
        }
        
        return total;
    }
    
    /**
     * Método auxiliar para mapear ResultSet a objeto Articulo
     * @param rs ResultSet con datos del artículo
     * @return Objeto Articulo
     * @throws SQLException si hay error al leer datos
     */
    private Articulo mapearArticulo(ResultSet rs) throws SQLException {
        Articulo articulo = new Articulo();
        articulo.setId(rs.getInt("id"));
        articulo.setBusquedaId(rs.getInt("busqueda_id"));
        articulo.setTitulo(rs.getString("titulo"));
        articulo.setAutores(rs.getString("autores"));
        articulo.setAnioPublicacion(rs.getInt("anio_publicacion"));
        articulo.setFuente(rs.getString("fuente"));
        articulo.setDoi(rs.getString("doi"));
        articulo.setResumen(rs.getString("resumen"));
        articulo.setPalabrasClave(rs.getString("palabras_clave"));
        articulo.setCitaAPA(rs.getString("cita_apa"));
        return articulo;
    }
}
