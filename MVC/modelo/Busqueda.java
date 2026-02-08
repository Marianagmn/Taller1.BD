package modelo;

import java.sql.Timestamp;

/**
 * MODELO: Clase Busqueda
 * Representa una búsqueda realizada en bases de datos científicas
 */
public class Busqueda {
    
    private int id;
    private String nombreEstudiante;
    private String baseDatos;
    private String cadenaBusqueda;
    private int cantidadDocumentos;
    private Timestamp fechaBusqueda;
    
    // Constructores
    public Busqueda() {
    }
    
    public Busqueda(int id, String nombreEstudiante, String baseDatos, 
                   String cadenaBusqueda, int cantidadDocumentos) {
        this.id = id;
        this.nombreEstudiante = nombreEstudiante;
        this.baseDatos = baseDatos;
        this.cadenaBusqueda = cadenaBusqueda;
        this.cantidadDocumentos = cantidadDocumentos;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombreEstudiante() {
        return nombreEstudiante;
    }
    
    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }
    
    public String getBaseDatos() {
        return baseDatos;
    }
    
    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }
    
    public String getCadenaBusqueda() {
        return cadenaBusqueda;
    }
    
    public void setCadenaBusqueda(String cadenaBusqueda) {
        this.cadenaBusqueda = cadenaBusqueda;
    }
    
    public int getCantidadDocumentos() {
        return cantidadDocumentos;
    }
    
    public void setCantidadDocumentos(int cantidadDocumentos) {
        this.cantidadDocumentos = cantidadDocumentos;
    }
    
    public Timestamp getFechaBusqueda() {
        return fechaBusqueda;
    }
    
    public void setFechaBusqueda(Timestamp fechaBusqueda) {
        this.fechaBusqueda = fechaBusqueda;
    }
    
    @Override
    public String toString() {
        return "Búsqueda #" + id + " - " + nombreEstudiante + 
               " (" + cantidadDocumentos + " documentos)";
    }
}
