/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.giv.pesados.vistas;

import com.mycompany.giv.pesados.dao.CategoriaDAO;
import com.mycompany.giv.pesados.modelos.Categoria;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uto
 */
public class FrmCategorias extends javax.swing.JInternalFrame {
    private CategoriaDAO categoriaDAO;
    private int idCategoriaSeleccionada = 0;
    /**
     * Creates new form FrmCategorias
     */
    private void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Categoría");
        modelo.addColumn("Descripción");

        List<Categoria> lista = categoriaDAO.listarCategoriasActivas();
        Object[] fila = new Object[3];
        
        for (Categoria c : lista) {
            fila[0] = c.getIdCategoria();
            fila[1] = c.getNombreCategoria();
            fila[2] = c.getDescripcion();
            modelo.addRow(fila);
        }
        
        tblCategorias.setModel(modelo);
    }
    public FrmCategorias() {
        initComponents();
        categoriaDAO = new CategoriaDAO();
        cargarTabla();
        // ==========================================
    // 1. FONDOS, BANNER Y TÍTULO
    // ==========================================
    // Fondo general de la ventana
    if (panel1 != null) {
        panel1.setBackground(com.mycompany.giv.pesados.config.EstiloUI.FONDO_APP);
    }
    
    // Banner superior oscuro
    if (baner2 != null) {
        baner2.setBackground(com.mycompany.giv.pesados.config.EstiloUI.AZUL_OSCURO);
    }
    
    // Tarjeta blanca para el formulario (Si creas un panel llamado panelTarjeta en el diseñador)
    // if (panelTarjeta != null) {
    //     panelTarjeta.setBackground(com.mycompany.giv.pesados.config.EstiloUI.FONDO_TARJETA);
    //     panelTarjeta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 231, 235), 1, true));
    // }
    
    if (titulo2 != null) {
        titulo2.setForeground(java.awt.Color.WHITE);
        titulo2.setFont(com.mycompany.giv.pesados.config.EstiloUI.FUENTE_TITULO);
    }

    // ==========================================
    // 2. BOTONES
    // ==========================================
    com.mycompany.giv.pesados.config.EstiloUI.aplicarEstiloBoton(btnGuardar, com.mycompany.giv.pesados.config.EstiloUI.BTN_GUARDAR);
    com.mycompany.giv.pesados.config.EstiloUI.aplicarEstiloBoton(btnActualizar, com.mycompany.giv.pesados.config.EstiloUI.BTN_ACTUALIZAR);
    com.mycompany.giv.pesados.config.EstiloUI.aplicarEstiloBoton(btnEliminar, com.mycompany.giv.pesados.config.EstiloUI.BTN_ELIMINAR);
    com.mycompany.giv.pesados.config.EstiloUI.aplicarEstiloBoton(btnLimpiar, com.mycompany.giv.pesados.config.EstiloUI.AZUL_OSCURO);

    // ==========================================
    // 3. ETIQUETAS (LABELS)
    // ==========================================
    javax.swing.JLabel[] etiquetas = {jLabel1, jLabel2};
    for (javax.swing.JLabel l : etiquetas) {
        if (l != null) {
            l.setFont(com.mycompany.giv.pesados.config.EstiloUI.FUENTE_GENERAL);
            l.setForeground(com.mycompany.giv.pesados.config.EstiloUI.TEXTO_PRINCIPAL);
        }
    }

    // ==========================================
    // 4. CAMPOS DE TEXTO Y ÁREAS
    // ==========================================
    javax.swing.JComponent[] campos = {txtNombreCategoria, txaDescripcion};
    for (javax.swing.JComponent c : campos) {
        if (c != null) {
            com.mycompany.giv.pesados.config.EstiloUI.aplicarEstiloCampo(c);
        }
    }

    // Limpiar el borde del ScrollPane del área de texto para que no haya doble borde
    if (jScrollPane1 != null) {
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }

    // ==========================================
    // 5. ESTILO CARD BLANCO PARA LA TABLA Y SU SCROLL
    // ==========================================
    if (jScrollPane2 != null) {
        jScrollPane2.setBackground(com.mycompany.giv.pesados.config.EstiloUI.FONDO_TARJETA);
        jScrollPane2.getViewport().setBackground(com.mycompany.giv.pesados.config.EstiloUI.FONDO_TARJETA);
        // Borde redondeado y un poco de padding interno
        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 231, 235), 1, true),
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    // ==========================================
    // 6. DISEÑO DE LA TABLA
    // ==========================================
    if (tblCategorias != null) {
        tblCategorias.setRowHeight(35); 
        tblCategorias.setShowVerticalLines(false);
        tblCategorias.setShowHorizontalLines(true);
        tblCategorias.setGridColor(new java.awt.Color(229, 231, 235));
        
        tblCategorias.setSelectionBackground(com.mycompany.giv.pesados.config.EstiloUI.BTN_ACTUALIZAR);
        tblCategorias.setSelectionForeground(java.awt.Color.WHITE);
        tblCategorias.setFont(com.mycompany.giv.pesados.config.EstiloUI.FUENTE_GENERAL);
        
        // Cabecera oscura
        tblCategorias.getTableHeader().setBackground(com.mycompany.giv.pesados.config.EstiloUI.AZUL_OSCURO);
        tblCategorias.getTableHeader().setForeground(java.awt.Color.WHITE);
        tblCategorias.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        tblCategorias.getTableHeader().setOpaque(false);
    }
    }
    private void limpiarCampos() {
        txtNombreCategoria.setText("");
        txaDescripcion.setText("");
        idCategoriaSeleccionada = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        txtNombreCategoria = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaDescripcion = new javax.swing.JTextArea();
        baner2 = new javax.swing.JPanel();
        titulo2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        panel1.setBackground(new java.awt.Color(204, 204, 204));
        panel1.setPreferredSize(new java.awt.Dimension(600, 500));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel1.add(txtNombreCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 220, 40));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre de categoria:");
        panel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Descripcion:");
        panel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        panel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, -1, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);
        panel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 310, -1, -1));

        btnLimpiar.setText("limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        panel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, -1, -1));

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategorias);

        panel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 580, 260));

        btnActualizar.setText("Editar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        panel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, -1, -1));

        txaDescripcion.setColumns(20);
        txaDescripcion.setRows(5);
        jScrollPane2.setViewportView(txaDescripcion);

        panel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 300, 90));

        titulo2.setFont(new java.awt.Font("sansserif", 0, 48)); // NOI18N
        titulo2.setText("Gestion de Categorias");

        javax.swing.GroupLayout baner2Layout = new javax.swing.GroupLayout(baner2);
        baner2.setLayout(baner2Layout);
        baner2Layout.setHorizontalGroup(
            baner2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baner2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(titulo2)
                .addContainerGap(238, Short.MAX_VALUE))
        );
        baner2Layout.setVerticalGroup(
            baner2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, baner2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titulo2)
                .addGap(25, 25, 25))
        );

        panel1.add(baner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String nombre = txtNombreCategoria.getText().trim();
        String descripcion = txaDescripcion.getText().trim();
        if (categoriaDAO.existeCategoria(nombre, 0)) {
            JOptionPane.showMessageDialog(this, "Esta categoría ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la categoría es obligatorio.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Categoria cat = new Categoria();
        cat.setNombreCategoria(nombre);
        cat.setDescripcion(descripcion);

        if (categoriaDAO.registrarCategoria(cat)) {
            JOptionPane.showMessageDialog(this, "Categoría guardada exitosamente.");
            limpiarCampos();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar la categoría.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (idCategoriaSeleccionada == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una categoría de la tabla para eliminar.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar esta categoría?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (categoriaDAO.eliminarCategoria(idCategoriaSeleccionada)) {
                JOptionPane.showMessageDialog(this, "Categoría eliminada (baja lógica) exitosamente.");
                limpiarCampos();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriasMouseClicked
        // TODO add your handling code here:
        int fila = tblCategorias.getSelectedRow();
        if (fila >= 0) {
            idCategoriaSeleccionada = Integer.parseInt(tblCategorias.getValueAt(fila, 0).toString());
            txtNombreCategoria.setText(tblCategorias.getValueAt(fila, 1).toString());
            txaDescripcion.setText(tblCategorias.getValueAt(fila, 2).toString());
        }
    }//GEN-LAST:event_tblCategoriasMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        // 1. Validar que haya tocado un registro en la tabla primero
        
        if (idCategoriaSeleccionada == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una categoría de la tabla para editar.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Extraer el texto
        String nombre = txtNombreCategoria.getText().trim();
        if (categoriaDAO.existeCategoria(nombre, idCategoriaSeleccionada)) {
            JOptionPane.showMessageDialog(this, "El nombre ya está asignado a otra categoría.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String descripcion = txaDescripcion.getText().trim();

        // 3. Validar que no esté vacío
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la categoría es obligatorio.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 4. Empaquetar en el DTO
        Categoria cat = new Categoria();
        cat.setIdCategoria(idCategoriaSeleccionada); // Pasamos el ID oculto
        cat.setNombreCategoria(nombre);
        cat.setDescripcion(descripcion);

        // 5. Enviar al DAO
        if (categoriaDAO.actualizarCategoria(cat)) {
            JOptionPane.showMessageDialog(this, "Categoría actualizada exitosamente.");
            limpiarCampos();
            cargarTabla(); // Refresca la tabla para ver el cambio
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar la categoría.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel baner2;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel1;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JLabel titulo2;
    private javax.swing.JTextArea txaDescripcion;
    private javax.swing.JTextField txtNombreCategoria;
    // End of variables declaration//GEN-END:variables
}
