package vista;

import controlador.BusquedaControlador;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Busqueda;

/**
 * VISTA: PanelBusquedas
 * Panel para visualizar información de búsquedas científicas
 */
public class PanelBusquedas extends JPanel {
    
    private BusquedaControlador controlador;
    private JTable tablaBusquedas;
    private DefaultTableModel modeloTabla;
    private JTextArea txtDetalles;
    private JLabel lblEstadisticas;
    
    public PanelBusquedas() {
        controlador = new BusquedaControlador();
        inicializarComponentes();
        cargarBusquedas();
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel superior con estadísticas
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setBackground(new Color(236, 240, 241));
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Estadísticas"));
        
        lblEstadisticas = new JLabel("Cargando...");
        lblEstadisticas.setFont(new Font("Arial", Font.BOLD, 14));
        panelSuperior.add(lblEstadisticas);
        
        add(panelSuperior, BorderLayout.NORTH);
        
        // Tabla de búsquedas
        String[] columnas = {"ID", "Estudiante", "Base de Datos", "Documentos Encontrados"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaBusquedas = new JTable(modeloTabla);
        tablaBusquedas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaBusquedas.setRowHeight(25);
        tablaBusquedas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaBusquedas.getTableHeader().setBackground(new Color(41, 128, 185));
        tablaBusquedas.getTableHeader().setForeground(Color.BLACK);
        tablaBusquedas.getTableHeader().setReorderingAllowed(false);
        
        // Listener para mostrar detalles
        tablaBusquedas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetallesBusqueda();
            }
        });
        
        JScrollPane scrollTabla = new JScrollPane(tablaBusquedas);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Búsquedas Realizadas"));
        
        // Panel de detalles
        JPanel panelDetalles = new JPanel(new BorderLayout());
        panelDetalles.setBorder(BorderFactory.createTitledBorder("Detalles de la Búsqueda"));
        
        txtDetalles = new JTextArea();
        txtDetalles.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtDetalles.setEditable(false);
        txtDetalles.setLineWrap(true);
        txtDetalles.setWrapStyleWord(true);
        txtDetalles.setText("Seleccione una búsqueda para ver sus detalles...");
        
        JScrollPane scrollDetalles = new JScrollPane(txtDetalles);
        panelDetalles.add(scrollDetalles, BorderLayout.CENTER);
        
        // Split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollTabla, panelDetalles);
        splitPane.setDividerLocation(250);
        splitPane.setResizeWeight(0.5);
        
        add(splitPane, BorderLayout.CENTER);
        
        // Panel inferior con botón refrescar
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRefrescar = new JButton("Refrescar");
        btnRefrescar.setFont(new Font("Arial", Font.BOLD, 12));
        btnRefrescar.addActionListener(e -> cargarBusquedas());
        panelInferior.add(btnRefrescar);
        
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private void cargarBusquedas() {
        modeloTabla.setRowCount(0);
        List<Busqueda> busquedas = controlador.obtenerTodasLasBusquedas();
        
        for (Busqueda b : busquedas) {
            Object[] fila = {
                b.getId(),
                b.getNombreEstudiante(),
                b.getBaseDatos(),
                b.getCantidadDocumentos()
            };
            modeloTabla.addRow(fila);
        }
        
        // Actualizar estadísticas
        int[] stats = controlador.obtenerEstadisticas();
        lblEstadisticas.setText(String.format(
            "Total de búsquedas: %d  |  Total de documentos encontrados: %,d",
            stats[0], stats[1]
        ));
    }
    
    private void mostrarDetallesBusqueda() {
        int filaSeleccionada = tablaBusquedas.getSelectedRow();
        
        if (filaSeleccionada >= 0) {
            int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            Busqueda busqueda = controlador.obtenerBusquedaPorId(id);
            
            if (busqueda != null) {
                StringBuilder detalles = new StringBuilder();
                detalles.append("═══════════════════════════════════════════════════\n");
                detalles.append("  INFORMACIÓN DE LA BÚSQUEDA\n");
                detalles.append("═══════════════════════════════════════════════════\n\n");
                detalles.append("ID: ").append(busqueda.getId()).append("\n\n");
                detalles.append("Estudiante: ").append(busqueda.getNombreEstudiante()).append("\n\n");
                detalles.append("Base de Datos: ").append(busqueda.getBaseDatos()).append("\n\n");
                detalles.append("Documentos Encontrados: ").append(busqueda.getCantidadDocumentos()).append("\n\n");
                detalles.append("Fecha de Búsqueda: ").append(busqueda.getFechaBusqueda()).append("\n\n");
                detalles.append("───────────────────────────────────────────────────\n");
                detalles.append("CADENA DE BÚSQUEDA:\n");
                detalles.append("───────────────────────────────────────────────────\n\n");
                detalles.append(busqueda.getCadenaBusqueda()).append("\n");
                
                txtDetalles.setText(detalles.toString());
                txtDetalles.setCaretPosition(0);
            }
        }
    }
}
