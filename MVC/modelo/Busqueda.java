package modelo;

import java.sql.Timestamp;

// Esta clase representa una búsqueda realizada por un estudiante en una base de datos.
// Guarda información como quién hizo la búsqueda, qué buscó, cuántos documentos encontró, etc.
public class Busqueda {
    // Propiedades (atributos) de la búsqueda
    private int id;                    // Identificador único de la búsqueda
    private String nombreEstudiante;   // Nombre del estudiante que realizó la búsqueda
    private String baseDatos;          // Nombre de la base de datos donde se buscó
    private String cadenaBusqueda;     // Término que se buscó
    private int cantidadDocumentos;    // Cantidad de documentos encontrados
    private Timestamp fechaBusqueda;   // Fecha y hora en que se realizó la búsqueda

    // Constructor vacío - para una búsqueda sin datos iniciales
    public Busqueda() {
    }

    // Constructor con parámetros - para datos completos de la búsqueda
    public Busqueda(int id, String nombreEstudiante, String baseDatos, 
                   String cadenaBusqueda, int cantidadDocumentos) {
        this.id = id;
        this.nombreEstudiante = nombreEstudiante;
        this.baseDatos = baseDatos;
        this.cadenaBusqueda = cadenaBusqueda;
        this.cantidadDocumentos = cantidadDocumentos;
    }

    // GETTERS Y SETTERS
    public int getId() {
        return id;  // Devuelve el identificador de la búsqueda
    }
    
    public void setId(int id) {
        this.id = id;  // Asigna un identificador a la búsqueda
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
    
    // Método toString - convierte la búsqueda a texto para mostrarla en la interfaz
    @Override
    public String toString() {
        return "Búsqueda #" + id + " - " + nombreEstudiante + 
               " (" + cantidadDocumentos + " documentos)";  // Muestra resumen de la búsqueda
    }
}
