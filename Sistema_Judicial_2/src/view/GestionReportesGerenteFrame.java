
package view;

import controller.TramiteController;
import model.ConexionDB;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;


public class GestionReportesGerenteFrame extends javax.swing.JFrame {

    private TramiteController tramiteController;
    private DefaultTableModel modeloReportes;
    
    public GestionReportesGerenteFrame() {
        initComponents();
        setLocationRelativeTo(null);
        tramiteController = new TramiteController();
        
        modeloReportes = new DefaultTableModel();
        modeloReportes.setColumnIdentifiers(new String[]{"ID", "Cliente", "Tipo", "Subtipo", "Descripción", "Estado", 
                                                         "Fecha Creación", "Abogado"});
        tablaReportes.setModel(modeloReportes);
        
        comboFiltroEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "Todos", "Pendiente", "En Proceso", "Finalizado"
        }));


        cargarReportes();
        cargarAbogados();
    }
    
    // Método para obtener y mostrar todos los reportes
    private void cargarReportes() {
    modeloReportes.setRowCount(0); // Limpiar la tabla antes de cargar datos

    try (Connection con = ConexionDB.conectar();
         PreparedStatement pst = con.prepareStatement(
                 "SELECT t.id, t.cliente, t.tipo_tramite, t.subtipo_tramite, t.descripcion, " +
                 "t.estado, t.fecha_creacion, u.nombre AS abogado_nombre " +
                 "FROM tramites t INNER JOIN usuarios u ON t.abogado_id = u.id")) {

        ResultSet rs = pst.executeQuery();

        // Si no hay datos, mostrar un mensaje en la consola
        if (!rs.isBeforeFirst()) {
            System.out.println("No hay trámites registrados en la base de datos.");
        }

        // Agregar datos a la tabla
        while (rs.next()) {
            System.out.println("Cargando trámite ID: " + rs.getInt("id")); // Depuración en consola
            modeloReportes.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("cliente"),
                    rs.getString("tipo_tramite"),
                    rs.getString("subtipo_tramite"),
                    rs.getString("descripcion"),
                    rs.getString("estado"),
                    rs.getTimestamp("fecha_creacion"),
                    rs.getString("abogado_nombre")
            });
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar reportes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    
    // Método para llenar el ComboBox de abogados
    private void cargarAbogados() {
        comboFiltroAbogado.addItem("Todos"); // Opción por defecto
        try (Connection con = ConexionDB.conectar(); PreparedStatement pst = con.prepareStatement("SELECT nombre FROM usuarios WHERE rol = 'Abogado'")) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                comboFiltroAbogado.addItem(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar abogados: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void filtrarReportes() {
        String abogadoSeleccionado = (String) comboFiltroAbogado.getSelectedItem();
        String estadoSeleccionado = (String) comboFiltroEstado.getSelectedItem();
        String busqueda = txtBuscar.getText().trim();

        modeloReportes.setRowCount(0);

        try (Connection con = ConexionDB.conectar(); PreparedStatement pst = con.prepareStatement(
                "SELECT t.id, t.cliente, t.tipo_tramite, t.subtipo_tramite, t.descripcion, "
                + "t.estado, t.fecha_creacion, u.nombre AS abogado_nombre "
                + "FROM tramites t INNER JOIN usuarios u ON t.abogado_id = u.id "
                + "WHERE (u.nombre = ? OR ? = 'Todos') AND (t.estado = ? OR ? = 'Todos') "
                + "AND (t.cliente LIKE ? OR t.tipo_tramite LIKE ?)")) {

            pst.setString(1, abogadoSeleccionado);
            pst.setString(2, abogadoSeleccionado);
            pst.setString(3, estadoSeleccionado);
            pst.setString(4, estadoSeleccionado);
            pst.setString(5, "%" + busqueda + "%");
            pst.setString(6, "%" + busqueda + "%");

            ResultSet rs = pst.executeQuery();

            // Si no hay resultados, mostrar un mensaje en la consola
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron trámites con los filtros aplicados.");
            }

            while (rs.next()) {
                System.out.println("Trámite filtrado ID: " + rs.getInt("id")); // Depuración
                modeloReportes.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("cliente"),
                    rs.getString("tipo_tramite"),
                    rs.getString("subtipo_tramite"),
                    rs.getString("descripcion"),
                    rs.getString("estado"),
                    rs.getTimestamp("fecha_creacion"),
                    rs.getString("abogado_nombre")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al filtrar reportes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCerrarSesion = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        comboFiltroAbogado = new javax.swing.JComboBox<>();
        comboFiltroEstado = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReportes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCerrarSesion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        comboFiltroAbogado.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        comboFiltroAbogado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFiltroAbogadoActionPerformed(evt);
            }
        });

        comboFiltroEstado.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        comboFiltroEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFiltroEstadoActionPerformed(evt);
            }
        });

        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel1.setText("Filtrar por Abogado");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setText("Filtrar por Estado");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setText("Buscar:");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        jLabel4.setText("GESTION GERENCIAL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(comboFiltroAbogado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(comboFiltroEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboFiltroAbogado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(comboFiltroEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        tablaReportes.setBackground(new java.awt.Color(118, 145, 154));
        tablaReportes.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tablaReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaReportes);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        dispose();
        new LoginFrame1().setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void comboFiltroAbogadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFiltroAbogadoActionPerformed
        // TODO add your handling code here:
        filtrarReportes();
    }//GEN-LAST:event_comboFiltroAbogadoActionPerformed

    private void comboFiltroEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFiltroEstadoActionPerformed
        // TODO add your handling code here:
        filtrarReportes();
    }//GEN-LAST:event_comboFiltroEstadoActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        filtrarReportes();
    }//GEN-LAST:event_txtBuscarKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionReportesGerenteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionReportesGerenteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionReportesGerenteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionReportesGerenteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionReportesGerenteFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JComboBox<String> comboFiltroAbogado;
    private javax.swing.JComboBox<String> comboFiltroEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReportes;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
