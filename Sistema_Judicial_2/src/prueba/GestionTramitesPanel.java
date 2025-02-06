package prueba;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionTramitesPanel extends JPanel {

    private JTabbedPane tabbedPane;
    private JPanel panelJudiciales, panelNotariados, panelReportes;
    private JTable tablaJudiciales, tablaNotariados, tablaReportes;
    private JButton btnAgregarJudicial, btnAgregarNotariado;
    private DefaultTableModel modeloJudiciales, modeloNotariados, modeloReportes;
    
    public GestionTramitesPanel() {
        setLayout(new BorderLayout());

        // Crear pestañas
        tabbedPane = new JTabbedPane();
        
        // Panel de Trámites Judiciales
        panelJudiciales = new JPanel(new BorderLayout());
        modeloJudiciales = new DefaultTableModel(new String[]{"ID", "Cliente", "Descripción", "Estado", "Fecha"}, 0);
        tablaJudiciales = new JTable(modeloJudiciales);
        btnAgregarJudicial = new JButton("Agregar Trámite Judicial");
        panelJudiciales.add(new JScrollPane(tablaJudiciales), BorderLayout.CENTER);
        panelJudiciales.add(btnAgregarJudicial, BorderLayout.SOUTH);
        
        // Panel de Trámites Notariados
        panelNotariados = new JPanel(new BorderLayout());
        modeloNotariados = new DefaultTableModel(new String[]{"ID", "Cliente", "Descripción", "Documentos", "Fecha Inicio", "Fecha Estimada", "Precio"}, 0);
        tablaNotariados = new JTable(modeloNotariados);
        btnAgregarNotariado = new JButton("Agregar Trámite Notariado");
        panelNotariados.add(new JScrollPane(tablaNotariados), BorderLayout.CENTER);
        panelNotariados.add(btnAgregarNotariado, BorderLayout.SOUTH);

        // Panel de Reportes
        panelReportes = new JPanel(new BorderLayout());
        modeloReportes = new DefaultTableModel(new String[]{"ID", "Cliente", "Tipo", "Descripción", "Estado", "Fecha"}, 0);
        tablaReportes = new JTable(modeloReportes);
        panelReportes.add(new JScrollPane(tablaReportes), BorderLayout.CENTER);
        
        // Agregar pestañas al TabbedPane
        tabbedPane.addTab("Trámites Judiciales", panelJudiciales);
        tabbedPane.addTab("Trámites Notariados", panelNotariados);
        tabbedPane.addTab("Reportes", panelReportes);
        
        // Agregar TabbedPane al panel principal
        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Trámites");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setContentPane(new GestionTramitesPanel());
        frame.setVisible(true);
    }
}
