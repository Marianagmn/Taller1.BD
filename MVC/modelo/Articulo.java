package modelo;

/**
 * MODELO: Clase Articulo
 * Representa un artículo científico con toda su información bibliográfica
 */
public class Articulo {
    
    private int id;
    private int busquedaId;
    private String titulo;
    private String autores;
    private int anioPublicacion;
    private String fuente;
    private String doi;
    private String resumen;
    private String palabrasClave;
    private String citaAPA;
    
    // Constructores
    public Articulo() {
    }
    
    public Articulo(int id, int busquedaId, String titulo, String autores, 
                   int anioPublicacion, String fuente, String doi, 
                   String resumen, String palabrasClave, String citaAPA) {
        this.id = id;
        this.busquedaId = busquedaId;
        this.titulo = titulo;
        this.autores = autores;
        this.anioPublicacion = anioPublicacion;
        this.fuente = fuente;
        this.doi = doi;
        this.resumen = resumen;
        this.palabrasClave = palabrasClave;
        this.citaAPA = citaAPA;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getBusquedaId() {
        return busquedaId;
    }
    
    public void setBusquedaId(int busquedaId) {
        this.busquedaId = busquedaId;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getAutores() {
        return autores;
    }
    
    public void setAutores(String autores) {
        this.autores = autores;
    }
    
    public int getAnioPublicacion() {
        return anioPublicacion;
    }
    
    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
    
    public String getFuente() {
        return fuente;
    }
    
    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
    
    public String getDoi() {
        return doi;
    }
    
    public void setDoi(String doi) {
        this.doi = doi;
    }
    
    public String getResumen() {
        return resumen;
    }
    
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
    
    public String getPalabrasClave() {
        return palabrasClave;
    }
    
    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }
    
    public String getCitaAPA() {
        return citaAPA;
    }
    
    public void setCitaAPA(String citaAPA) {
        this.citaAPA = citaAPA;
    }
    
    @Override
    public String toString() {
        return titulo + " (" + anioPublicacion + ")";
    }
}
