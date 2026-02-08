package controlador;

import modelo.EstrategiaInversion;
import modelo.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CONTROLADOR: EstrategiaControlador
 * Implementa operaciones CRUD completas para Estrategias de Inversión
 */
public class EstrategiaControlador {
    
    /**
     * CREATE: Crea una nueva estrategia de inversión
     * @param estrategia Objeto EstrategiaInversion a crear
     * @return true si se creó exitosamente, false en caso contrario
     */
    public boolean crearEstrategia(EstrategiaInversion estrategia) {
        String sql = "INSERT INTO estrategias_inversion " +
                    "(nombre, descripcion, tipo_estrategia, nivel_riesgo, " +
                    "tecnologias_utilizadas, retorno_esperado, articulo_relacionado_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, estrategia.getNombre());
            pstmt.setString(2, estrategia.getDescripcion());
            pstmt.setString(3, estrategia.getTipoEstrategia());
            pstmt.setString(4, estrategia.getNivelRiesgo());
            pstmt.setString(5, estrategia.getTecnologiasUtilizadas());
            pstmt.setDouble(6, estrategia.getRetornoEsperado());
            
            if (estrategia.getArticuloRelacionadoId() != null) {
                pstmt.setInt(7, estrategia.getArticuloRelacionadoId());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                // Obtener el ID generado
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    estrategia.setId(rs.getInt(1));
                }
                System.out.println("✓ Estrategia creada exitosamente");
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al crear estrategia: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * READ: Obtiene todas las estrategias de inversión
     * @return Lista de estrategias
     */
    public List<EstrategiaInversion> obtenerTodasLasEstrategias() {
        List<EstrategiaInversion> estrategias = new ArrayList<>();
        String sql = "SELECT * FROM estrategias_inversion ORDER BY fecha_creacion DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                estrategias.add(mapearEstrategia(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener estrategias: " + e.getMessage());
            e.printStackTrace();
        }
        
        return estrategias;
    }
    
    /**
     * READ: Obtiene una estrategia por su ID
     * @param id ID de la estrategia
     * @return Objeto EstrategiaInversion o null si no existe
     */
    public EstrategiaInversion obtenerEstrategiaPorId(int id) {
        String sql = "SELECT * FROM estrategias_inversion WHERE id = ?";
        EstrategiaInversion estrategia = null;
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                estrategia = mapearEstrategia(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener estrategia: " + e.getMessage());
        }
        
        return estrategia;
    }
    
    /**
     * UPDATE: Actualiza una estrategia existente
     * @param estrategia Objeto EstrategiaInversion con datos actualizados
     * @return true si se actualizó exitosamente, false en caso contrario
     */
    public boolean actualizarEstrategia(EstrategiaInversion estrategia) {
        String sql = "UPDATE estrategias_inversion SET " +
                    "nombre = ?, descripcion = ?, tipo_estrategia = ?, " +
                    "nivel_riesgo = ?, tecnologias_utilizadas = ?, " +
                    "retorno_esperado = ?, articulo_relacionado_id = ? " +
                    "WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, estrategia.getNombre());
            pstmt.setString(2, estrategia.getDescripcion());
            pstmt.setString(3, estrategia.getTipoEstrategia());
            pstmt.setString(4, estrategia.getNivelRiesgo());
            pstmt.setString(5, estrategia.getTecnologiasUtilizadas());
            pstmt.setDouble(6, estrategia.getRetornoEsperado());
            
            if (estrategia.getArticuloRelacionadoId() != null) {
                pstmt.setInt(7, estrategia.getArticuloRelacionadoId());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            
            pstmt.setInt(8, estrategia.getId());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("✓ Estrategia actualizada exitosamente");
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar estrategia: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * DELETE: Elimina una estrategia por su ID
     * @param id ID de la estrategia a eliminar
     * @return true si se eliminó exitosamente, false en caso contrario
     */
    public boolean eliminarEstrategia(int id) {
        String sql = "DELETE FROM estrategias_inversion WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("✓ Estrategia eliminada exitosamente");
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar estrategia: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Busca estrategias por nombre o tipo
     * @param termino Término de búsqueda
     * @return Lista de estrategias encontradas
     */
    public List<EstrategiaInversion> buscarEstrategias(String termino) {
        List<EstrategiaInversion> estrategias = new ArrayList<>();
        String sql = "SELECT * FROM estrategias_inversion WHERE " +
                    "nombre LIKE ? OR tipo_estrategia LIKE ? OR descripcion LIKE ? " +
                    "ORDER BY fecha_creacion DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String patron = "%" + termino + "%";
            pstmt.setString(1, patron);
            pstmt.setString(2, patron);
            pstmt.setString(3, patron);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                estrategias.add(mapearEstrategia(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al buscar estrategias: " + e.getMessage());
        }
        
        return estrategias;
    }
    
    /**
     * Obtiene estrategias por nivel de riesgo
     * @param nivelRiesgo Nivel de riesgo ("Bajo", "Medio", "Alto")
     * @return Lista de estrategias
     */
    public List<EstrategiaInversion> obtenerEstrategiasPorRiesgo(String nivelRiesgo) {
        List<EstrategiaInversion> estrategias = new ArrayList<>();
        String sql = "SELECT * FROM estrategias_inversion WHERE nivel_riesgo = ? " +
                    "ORDER BY retorno_esperado DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nivelRiesgo);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                estrategias.add(mapearEstrategia(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener estrategias por riesgo: " + e.getMessage());
        }
        
        return estrategias;
    }
    
    /**
     * Método auxiliar para mapear ResultSet a objeto EstrategiaInversion
     * @param rs ResultSet con datos de la estrategia
     * @return Objeto EstrategiaInversion
     * @throws SQLException si hay error al leer datos
     */
    private EstrategiaInversion mapearEstrategia(ResultSet rs) throws SQLException {
        EstrategiaInversion estrategia = new EstrategiaInversion();
        estrategia.setId(rs.getInt("id"));
        estrategia.setNombre(rs.getString("nombre"));
        estrategia.setDescripcion(rs.getString("descripcion"));
        estrategia.setTipoEstrategia(rs.getString("tipo_estrategia"));
        estrategia.setNivelRiesgo(rs.getString("nivel_riesgo"));
        estrategia.setTecnologiasUtilizadas(rs.getString("tecnologias_utilizadas"));
        estrategia.setRetornoEsperado(rs.getDouble("retorno_esperado"));
        
        int articuloId = rs.getInt("articulo_relacionado_id");
        if (!rs.wasNull()) {
            estrategia.setArticuloRelacionadoId(articuloId);
        }
        
        estrategia.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
        estrategia.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion"));
        
        return estrategia;
    }
}
