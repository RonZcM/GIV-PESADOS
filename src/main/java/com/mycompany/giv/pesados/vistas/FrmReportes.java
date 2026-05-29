/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.giv.pesados.vistas;

public class FrmReportes extends javax.swing.JInternalFrame {

 
    public FrmReportes() {
        initComponents();
        configurarColumnas();
    }

    // Método para reconfigurar las columnas de la tabla según el reporte seleccionado
    private void configurarColumnas() {
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
        int indice = cbTipoReporte.getSelectedIndex();
        
        switch(indice) {
            case 0: // Rendimiento de Vendedores
                modelo.addColumn("Vendedor");
                modelo.addColumn("Total Transacciones");
                modelo.addColumn("Ingresos Generados ($)");
                break;
            case 1: // Top Repuestos
                modelo.addColumn("Nombre Repuesto");
                modelo.addColumn("Marca");
                modelo.addColumn("Unidades Vendidas");
                modelo.addColumn("Ingresos Totales ($)");
                break;
            case 2: // Inventario Critico
                modelo.addColumn("Nombre Repuesto");
                modelo.addColumn("Marca");
                modelo.addColumn("Stock Actual");
                modelo.addColumn("Stock Mínimo");
                modelo.addColumn("Déficit");
                break;
            case 3: // Ingresos por Fechas
                modelo.addColumn("Fecha");
                modelo.addColumn("Cantidad Ventas");
                modelo.addColumn("Ingresos del Día ($)");
                break;
            case 4: // Ranking Clientes
                modelo.addColumn("Cliente");
                modelo.addColumn("Tipo");
                modelo.addColumn("Compras Realizadas");
                modelo.addColumn("Total Gastado ($)");
                break;
            case 5: // Movimiento por Categorias
                modelo.addColumn("Categoría");
                modelo.addColumn("Repuestos Vendidos");
                modelo.addColumn("Ingresos de Categoría ($)");
                break;
        }
        
        tblReportes.setModel(modelo);
        
        // Habilitar o deshabilitar los campos de fecha
        if (indice == 3) {
            txtFechaInicio.setEnabled(true);
            txtFechaFin.setEnabled(true);
        } else {
            txtFechaInicio.setEnabled(false);
            txtFechaFin.setEnabled(false);
            txtFechaInicio.setText("");
            txtFechaFin.setText("");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cbTipoReporte = new javax.swing.JComboBox<>();
        txtFechaFin = new javax.swing.JTextField();
        txtFechaInicio = new javax.swing.JTextField();
        btnGenerar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReportes = new javax.swing.JTable();
        btnExportarExcel = new javax.swing.JButton();
        btnExportarPDF = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbTipoReporte.setBackground(new java.awt.Color(255, 255, 255));
        cbTipoReporte.setForeground(new java.awt.Color(0, 0, 0));
        cbTipoReporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rendimiento de Vendedores", "Top de Repuestos Más Vendidos", "Alerta de Inventario Crítico", "Ingresos por Rango de Fechas", "Ranking de Mejores Clientes", "Movimiento por Categorías" }));
        cbTipoReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoReporteActionPerformed(evt);
            }
        });
        jPanel1.add(cbTipoReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 210, -1));

        txtFechaFin.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaFin.setForeground(new java.awt.Color(0, 0, 0));
        txtFechaFin.setText("fecha fin");
        jPanel1.add(txtFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 100, -1));

        txtFechaInicio.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaInicio.setForeground(new java.awt.Color(0, 0, 0));
        txtFechaInicio.setText("fecha inicio");
        jPanel1.add(txtFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 100, -1));

        btnGenerar.setBackground(new java.awt.Color(0, 102, 153));
        btnGenerar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGenerar.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerar.setText("Generar Reporte");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 102));

        tblReportes.setBackground(new java.awt.Color(255, 255, 255));
        tblReportes.setForeground(new java.awt.Color(0, 0, 0));
        tblReportes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblReportes.setGridColor(new java.awt.Color(0, 0, 102));
        tblReportes.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblReportes.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(tblReportes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 710, 210));

        btnExportarExcel.setBackground(new java.awt.Color(0, 153, 51));
        btnExportarExcel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExportarExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarExcel.setText("Exportar a Excel");
        btnExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarExcelActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, -1, -1));

        btnExportarPDF.setBackground(new java.awt.Color(255, 153, 153));
        btnExportarPDF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExportarPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarPDF.setText("Exportar a PDF");
        btnExportarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarPDFActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportarPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tipo de reporte:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Fin:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Inicio:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADMINISTRACIÓN DE REPORTES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jLabel1)
                .addContainerGap(201, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbTipoReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoReporteActionPerformed
        configurarColumnas();
    }//GEN-LAST:event_cbTipoReporteActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        int indice = cbTipoReporte.getSelectedIndex();
        String fechaInicio = txtFechaInicio.getText().trim();
        String fechaFin = txtFechaFin.getText().trim();

        // Validacion estricta para el reporte de fechas (Indice 3)
        if (indice == 3) {
            if (fechaInicio.isEmpty() || fechaFin.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar las fechas de inicio y fin (Formato: YYYY-MM-DD).", "Validacion", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Instancia del DAO y obtencion de datos
        com.mycompany.giv.pesados.dao.ReporteDAO dao = new com.mycompany.giv.pesados.dao.ReporteDAO();
        java.util.List<Object[]> datos = dao.generarReporte(indice, fechaInicio, fechaFin);

        // Obtenemos el modelo actual de la tabla (ya configurado con sus columnas)
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tblReportes.getModel();
        
        // Limpiamos los registros anteriores
        modelo.setRowCount(0);

        // Volcamos la nueva informacion fila por fila
        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }

        // Notificacion al usuario en caso de resultado vacio
        if (datos.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "La consulta se ejecuto correctamente, pero no se encontraron registros.", "Informacion", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarExcelActionPerformed
        if (tblReportes.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe generar un reporte primero antes de exportarlo.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String nombreReporte = cbTipoReporte.getSelectedItem().toString();
        com.mycompany.giv.pesados.config.GeneradorReportes generador = new com.mycompany.giv.pesados.config.GeneradorReportes();
        
        // Le pasamos la tabla completa al generador para que la convierta en Excel
        generador.exportarTablaAExcel(tblReportes, nombreReporte);
    }//GEN-LAST:event_btnExportarExcelActionPerformed

    private void btnExportarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarPDFActionPerformed
       int indice = cbTipoReporte.getSelectedIndex();
        String nombreReporte = cbTipoReporte.getSelectedItem().toString();
        String fechaInicio = txtFechaInicio.getText().trim();
        String fechaFin = txtFechaFin.getText().trim();
        
        if (indice == 3 && (fechaInicio.isEmpty() || fechaFin.isEmpty())) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar las fechas de inicio y fin para este reporte.", "Validacion", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        com.mycompany.giv.pesados.config.GeneradorReportes generador = new com.mycompany.giv.pesados.config.GeneradorReportes();
        generador.exportarReportePDF(indice, nombreReporte, fechaInicio, fechaFin);
    }//GEN-LAST:event_btnExportarPDFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportarExcel;
    private javax.swing.JButton btnExportarPDF;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JComboBox<String> cbTipoReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReportes;
    private javax.swing.JTextField txtFechaFin;
    private javax.swing.JTextField txtFechaInicio;
    // End of variables declaration//GEN-END:variables
}
