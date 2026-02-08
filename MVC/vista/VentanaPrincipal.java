package vista;

import javax.swing.*;
import java.awt.*;

/**
 * VISTA: VentanaPrincipal
 * Interfaz principal del sistema con pestañas para cada módulo
 */
public class VentanaPrincipal extends JFrame {
    
    private JTabbedPane pestanas;
    private PanelBusquedas panelBusquedas;
    private PanelArticulos panelArticulos;
    private PanelEstrategias panelEstrategias;
    
    public VentanaPrincipal() {
        configurarVentana();
        inicializarComponentes();
    }
    
    private void configurarVentana() {
        setTitle("Sistema de Gestión de Artículos Fintech - MVC");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Icono y look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void inicializarComponentes() {
        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(41, 128, 185));
        panelTitulo.setPreferredSize(new Dimension(0, 80));
        
        JLabel lblTitulo = new JLabel("Sistema de Gestión de Artículos Científicos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        
        JLabel lblSubtitulo = new JLabel("Arquitectura MVC - Fintech e IA en Finanzas");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubtitulo.setForeground(Color.WHITE);
        
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTitulo.add(Box.createVerticalGlue());
        panelTitulo.add(lblTitulo);
        panelTitulo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelTitulo.add(lblSubtitulo);
        panelTitulo.add(Box.createVerticalGlue());
        
        add(panelTitulo, BorderLayout.NORTH);
        
        // Pestañas
        pestanas = new JTabbedPane();
        pestanas.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Inicializar paneles
        panelBusquedas = new PanelBusquedas();
        panelArticulos = new PanelArticulos();
        panelEstrategias = new PanelEstrategias();
        
        // Agregar pestañas
        pestanas.addTab("🏠 Inicio", crearPanelInicio());
        pestanas.addTab("🔍 Búsquedas", panelBusquedas);
        pestanas.addTab("📚 Artículos", panelArticulos);
        pestanas.addTab("💰 Estrategias (CRUD)", panelEstrategias);
        
        add(pestanas, BorderLayout.CENTER);
        
        // Panel inferior
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(52, 73, 94));
        panelInferior.setPreferredSize(new Dimension(0, 30));
        
        JLabel lblFooter = new JLabel("© 2026 - Fundación Universitaria Konrad Lorenz");
        lblFooter.setForeground(Color.WHITE);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 11));
        panelInferior.add(lblFooter);
        
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelInicio() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel de bienvenida
        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setLayout(new BoxLayout(panelBienvenida, BoxLayout.Y_AXIS));
        panelBienvenida.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblBienvenida = new JLabel("Bienvenido al Sistema de Gestión de Artículos");
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JTextArea txtDescripcion = new JTextArea();
        txtDescripcion.setText(
            "Este sistema implementa el patrón de arquitectura MVC (Modelo-Vista-Controlador)\n" +
            "para la gestión de artículos científicos sobre Fintech e IA en Finanzas.\n\n" +
            "Funcionalidades:\n\n" +
            "• Búsquedas: Visualiza información de las búsquedas realizadas en Scopus,\n" +
            "  incluyendo cadenas de búsqueda y cantidad de documentos encontrados.\n\n" +
            "• Artículos: Consulta los 9 artículos científicos con información completa:\n" +
            "  título, autores, año, fuente, DOI, resumen, palabras clave y cita APA.\n\n" +
            "• Estrategias (CRUD): Gestión completa de estrategias de inversión basadas\n" +
            "  en IA y tecnología financiera. Permite Crear, Leer, Actualizar y Eliminar.\n\n" +
            "Desarrollado con Java, MySQL y patrón MVC."
        );
        txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
        txtDescripcion.setEditable(false);
        txtDescripcion.setOpaque(false);
        txtDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelBienvenida.add(lblBienvenida);
        panelBienvenida.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBienvenida.add(txtDescripcion);
        
        panel.add(panelBienvenida, BorderLayout.CENTER);
        
        // Panel de información de estudiantes
        JPanel panelEstudiantes = new JPanel(new GridLayout(1, 3, 10, 10));
        
        String[] estudiantes = {
            "Julián David Cristancho Niño",
            "Mariana Alejandra Gordillo Meneses",
            "Ana Sofía Fajardo Leal"
        };
        
        String[] busquedas = {
            "financial + simulation + python",
            "financial simulation + AI + deep learning",
            "fintech + machine learning + fraud detection"
        };
        
        int[] cantidades = {121, 867, 1956};
        
        for (int i = 0; i < 3; i++) {
            JPanel cardEstudiante = crearCardEstudiante(
                estudiantes[i], 
                busquedas[i], 
                cantidades[i]
            );
            panelEstudiantes.add(cardEstudiante);
        }
        
        panel.add(panelEstudiantes, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel crearCardEstudiante(String nombre, String busqueda, int cantidad) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(new Color(236, 240, 241));
        
        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblBusqueda = new JLabel("<html><center>" + busqueda + "</center></html>");
        lblBusqueda.setFont(new Font("Arial", Font.PLAIN, 10));
        lblBusqueda.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblCantidad = new JLabel(cantidad + " documentos");
        lblCantidad.setFont(new Font("Arial", Font.BOLD, 14));
        lblCantidad.setForeground(new Color(41, 128, 185));
        lblCantidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(lblNombre);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(lblBusqueda);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(lblCantidad);
        
        return card;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
