package modelo;

import java.sql.Timestamp;

/**
 * MODELO: Clase EstrategiaInversion
 * Representa una estrategia de inversión basada en IA/Fintech
 * Esta es la entidad principal para el CRUD
 */
public class EstrategiaInversion {
    
    private int id;
    private String nombre;
    private String descripcion;
    private String tipoEstrategia;
    private String nivelRiesgo; // "Bajo", "Medio", "Alto"
    private String tecnologiasUtilizadas;
    private double retornoEsperado;
    private Integer articuloRelacionadoId; // Puede ser null
    private Timestamp fechaCreacion;
    private Timestamp fechaActualizacion;
    
    // Constructores
    public EstrategiaInversion() {
    }
    
    public EstrategiaInversion(String nombre, String descripcion, String tipoEstrategia, 
                              String nivelRiesgo, String tecnologiasUtilizadas, 
                              double retornoEsperado, Integer articuloRelacionadoId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoEstrategia = tipoEstrategia;
        this.nivelRiesgo = nivelRiesgo;
        this.tecnologiasUtilizadas = tecnologiasUtilizadas;
        this.retornoEsperado = retornoEsperado;
        this.articuloRelacionadoId = articuloRelacionadoId;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getTipoEstrategia() {
        return tipoEstrategia;
    }
    
    public void setTipoEstrategia(String tipoEstrategia) {
        this.tipoEstrategia = tipoEstrategia;
    }
    
    public String getNivelRiesgo() {
        return nivelRiesgo;
    }
    
    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }
    
    public String getTecnologiasUtilizadas() {
        return tecnologiasUtilizadas;
    }
    
    public void setTecnologiasUtilizadas(String tecnologiasUtilizadas) {
        this.tecnologiasUtilizadas = tecnologiasUtilizadas;
    }
    
    public double getRetornoEsperado() {
        return retornoEsperado;
    }
    
    public void setRetornoEsperado(double retornoEsperado) {
        this.retornoEsperado = retornoEsperado;
    }
    
    public Integer getArticuloRelacionadoId() {
        return articuloRelacionadoId;
    }
    
    public void setArticuloRelacionadoId(Integer articuloRelacionadoId) {
        this.articuloRelacionadoId = articuloRelacionadoId;
    }
    
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }
    
    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    @Override
    public String toString() {
        return nombre + " - " + tipoEstrategia + " (Riesgo: " + nivelRiesgo + ")";
    }
}
