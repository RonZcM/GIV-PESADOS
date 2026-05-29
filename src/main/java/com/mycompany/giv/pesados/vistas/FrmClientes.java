/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.giv.pesados.vistas;
import com.mycompany.giv.pesados.dao.ClienteDAO;
import com.mycompany.giv.pesados.modelos.Cliente;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author bryan
 */
public class FrmClientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmClientes
     */
    Cliente cl = new Cliente();
    ClienteDAO clienteDao = new ClienteDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    int idClienteActual = 0; // Para almacenar el ID al seleccionar un registro para actualizar/eliminar
    private int rolUsuario;
    
    public FrmClientes(int rol) {
        initComponents();
        this.rolUsuario = rol; // Asignamos el rol real del usuario logueado
        
        // ¡Tu bloqueo de seguridad visual! Funciona perfecto ahora que el rol es real
        if (this.rolUsuario == 2) { 
            BtnEliminar.setVisible(false);
        }
        
// 1. APLICAMOS EL DISEÑO A LA TABLA ANTES DE LLENARLA
        aplicarEstilosTabla();
        
        // 2. Ejecutamos el método para llenar la tabla al abrir la pantalla
        ListarClientes();
    }

    // NUEVO MÉTODO: Separa el diseño visual de la lógica de la base de datos
    private void aplicarEstilosTabla() {
        // Altura de las filas para que no se vea amontonado
        TbClientes.setRowHeight(45);

        // Colores y líneas de la cuadrícula
        TbClientes.setBackground(java.awt.Color.WHITE);
        TbClientes.setShowVerticalLines(false); // Quita la división vertical
        TbClientes.setShowHorizontalLines(true); // Mantiene la división horizontal
        TbClientes.setGridColor(new java.awt.Color(240, 240, 240)); // Gris muy sutil para el borde

        // Estilizar el Encabezado (Header)
        javax.swing.table.JTableHeader header = TbClientes.getTableHeader();
        header.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                setBackground(new java.awt.Color(11, 35, 71)); // Azul oscuro
                setForeground(java.awt.Color.WHITE);
                setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
                
                return this;
            }
        });

        // Alinear y estilizar las celdas (Cuerpo de la tabla)
        javax.swing.table.DefaultTableCellRenderer cellRenderer = new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                setHorizontalAlignment(javax.swing.SwingConstants.CENTER); 
                setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)); 
                
                // Color de fondo si el usuario hace clic en la fila
                if (isSelected) {
                    setBackground(new java.awt.Color(230, 240, 255)); // Azul clarito
                    setForeground(java.awt.Color.BLACK);
                } else {
                    setBackground(java.awt.Color.WHITE);
                    setForeground(new java.awt.Color(50, 50, 50)); // Gris oscuro para el texto
                }
                return this;
            }
        };

        // Aplicar el renderizador de celdas a todas las columnas
        for (int i = 0; i < TbClientes.getColumnCount(); i++) {
            TbClientes.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }
    public void ListarClientes() {
        // LE PASAMOS EL ROL AL DAO para que decida si muestra inactivos o no
        List<Cliente> listarCl = clienteDao.listarClientes(this.rolUsuario);
        modelo = (DefaultTableModel) TbClientes.getModel();
        
        // Limpia las filas vacías por defecto o búsquedas anteriores
        modelo.setRowCount(0); 
        
        Object[] obj = new Object[8];
        for (int i = 0; i < listarCl.size(); i++) {
            obj[0] = listarCl.get(i).getIdCliente();
            obj[1] = listarCl.get(i).getNombreCliente();
            obj[2] = listarCl.get(i).getCorreo();
            obj[3] = listarCl.get(i).getDireccion();
            obj[4] = listarCl.get(i).getEstado();
            obj[5] = listarCl.get(i).getTipoCliente();
            obj[6] = listarCl.get(i).getDocumentoIdentidad();
            obj[7] = listarCl.get(i).getTelefono();
            modelo.addRow(obj);
        }
        TbClientes.setModel(modelo);
    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void LimpiarCajas() {
        TxtNombre.setText("");
        TxtCorreo.setText("");
        TxtDireccion.setText("");
        TxtDui.setText("");
        TxtTelefono.setText("");
        CbEstado.setSelectedItem(1); // Valor por defecto Activo
        CbTipoCliente.setSelectedIndex(1);
        idClienteActual = 0; // Reiniciamos el ID
        TxtBuscador.setText("");
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
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TxtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtDui = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TxtTelefono = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtCorreo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtDireccion = new javax.swing.JTextField();
        CbTipoCliente = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CbEstado = new javax.swing.JComboBox<>();
        BtnGuardar = new javax.swing.JButton();
        BtnActualizar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TbClientes = new javax.swing.JTable();
        TxtBuscador = new javax.swing.JTextField();
        BtnBuscar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(0, 52, 89));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Administrar Clientes");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion del cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 52, 89))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre Cliente:");

        TxtNombre.setBackground(new java.awt.Color(255, 255, 255));
        TxtNombre.setForeground(new java.awt.Color(0, 0, 0));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("DUI:");

        TxtDui.setBackground(new java.awt.Color(255, 255, 255));
        TxtDui.setForeground(new java.awt.Color(0, 0, 0));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Telefono:");

        TxtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        TxtTelefono.setForeground(new java.awt.Color(0, 0, 0));
        TxtTelefono.addActionListener(this::TxtTelefonoActionPerformed);

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Correo:");

        TxtCorreo.setBackground(new java.awt.Color(255, 255, 255));
        TxtCorreo.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Direccion:");

        TxtDireccion.setBackground(new java.awt.Color(255, 255, 255));
        TxtDireccion.setForeground(new java.awt.Color(0, 0, 0));

        CbTipoCliente.setBackground(new java.awt.Color(255, 255, 255));
        CbTipoCliente.setForeground(new java.awt.Color(0, 0, 0));
        CbTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VIP", "Normal" }));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tipo_Cliente:");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Estado:");

        CbEstado.setBackground(new java.awt.Color(255, 255, 255));
        CbEstado.setForeground(new java.awt.Color(0, 0, 0));
        CbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inactivo(0)", "Activo(1)", " " }));

        BtnGuardar.setBackground(new java.awt.Color(0, 102, 102));
        BtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        BtnGuardar.setText("💾Guardar");
        BtnGuardar.addActionListener(this::BtnGuardarActionPerformed);

        BtnActualizar.setBackground(new java.awt.Color(0, 153, 204));
        BtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        BtnActualizar.setText("⟳Actualizar");
        BtnActualizar.addActionListener(this::BtnActualizarActionPerformed);

        BtnEliminar.setBackground(new java.awt.Color(204, 51, 0));
        BtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        BtnEliminar.setText("🗑Eliminar");
        BtnEliminar.addActionListener(this::BtnEliminarActionPerformed);

        BtnLimpiar.setBackground(new java.awt.Color(0, 52, 89));
        BtnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.addActionListener(this::BtnLimpiarActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(CbTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel4)
                                .addGap(26, 26, 26)
                                .addComponent(CbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(TxtDireccion)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(TxtDui)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TxtNombre)
                            .addComponent(TxtCorreo))))
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(BtnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(BtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(64, 64, 64))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(TxtDui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(CbTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(CbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "⌕ Buscar clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 52, 89))); // NOI18N

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Buscador:");

        TbClientes.setBackground(new java.awt.Color(255, 255, 255));
        TbClientes.setForeground(new java.awt.Color(0, 0, 0));
        TbClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Correo", "Dirección", "Estado", "Tipo", "DUI/NIT", "Teléfono"
            }
        ));
        TbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TbClientes);

        TxtBuscador.setBackground(new java.awt.Color(255, 255, 255));
        TxtBuscador.setForeground(new java.awt.Color(0, 0, 0));
        TxtBuscador.addActionListener(this::TxtBuscadorActionPerformed);

        BtnBuscar.setBackground(new java.awt.Color(0, 52, 89));
        BtnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        BtnBuscar.setText("Buscar");
        BtnBuscar.addActionListener(this::BtnBuscarActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1003, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(TxtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(385, 385, 385))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel8)
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        // TODO add your handling code here:
        if (idClienteActual > 0) {
        int pregunta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar a este cliente?");
        if (pregunta == 0) {
            if (clienteDao.eliminarCliente(idClienteActual)) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado.");
                LimpiarTable();
                LimpiarCajas();
                ListarClientes();
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.");
    }
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        // TODO add your handling code here:
        LimpiarCajas();
        ListarClientes();
        
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        // TODO add your handling code here:
        if (!"".equals(TxtNombre.getText()) && !"".equals(TxtDui.getText())) {
        cl.setNombreCliente(TxtNombre.getText());
        cl.setCorreo(TxtCorreo.getText());
        cl.setDireccion(TxtDireccion.getText());
        cl.setEstado(Integer.parseInt(CbEstado.getSelectedItem().toString()));
        cl.setTipoCliente(CbTipoCliente.getSelectedItem().toString());
        cl.setDocumentoIdentidad(TxtDui.getText());
        cl.setTelefono(TxtTelefono.getText());

        if (clienteDao.registrarCliente(cl)) {
            JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
            LimpiarTable();
            LimpiarCajas();
            ListarClientes();
        }
    } else {
        JOptionPane.showMessageDialog(null, "Los campos Nombre y Documento (DUI/NIT) son obligatorios.");
    }
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void TbClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbClientesMouseClicked
        // TODO add your handling code here:
        int fila = TbClientes.rowAtPoint(evt.getPoint());
    if (fila > -1) {
        idClienteActual = Integer.parseInt(TbClientes.getValueAt(fila, 0).toString());
        TxtNombre.setText(TbClientes.getValueAt(fila, 1).toString());
        TxtCorreo.setText(TbClientes.getValueAt(fila, 2).toString());
        TxtDireccion.setText(TbClientes.getValueAt(fila, 3).toString());
        CbEstado.setSelectedItem(Integer.parseInt(TbClientes.getValueAt(fila, 4).toString()));
        CbTipoCliente.setSelectedItem(TbClientes.getValueAt(fila, 5).toString());
        TxtDui.setText(TbClientes.getValueAt(fila, 6).toString());
        TxtTelefono.setText(TbClientes.getValueAt(fila, 7).toString());
    }
    }//GEN-LAST:event_TbClientesMouseClicked

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
        // TODO add your handling code here:
        if ("".equals(TxtNombre.getText())) {
        JOptionPane.showMessageDialog(null, "Seleccione un cliente de la tabla para actualizar.");
    } else {
        cl.setNombreCliente(TxtNombre.getText());
        cl.setCorreo(TxtCorreo.getText());
        cl.setDireccion(TxtDireccion.getText());
        cl.setEstado(Integer.parseInt(CbEstado.getSelectedItem().toString()));
        cl.setTipoCliente(CbTipoCliente.getSelectedItem().toString());
        cl.setDocumentoIdentidad(TxtDui.getText());
        cl.setTelefono(TxtTelefono.getText());
        cl.setIdCliente(idClienteActual); // Usamos el ID capturado al hacer clic en la tabla

        if (clienteDao.actualizarCliente(cl)) {
            JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente.");
            LimpiarTable();
            LimpiarCajas();
            ListarClientes();
        }
    }
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void TxtBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtBuscadorActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        // TODO add your handling code here:
        // 1. Obtener el texto del buscador y limpiarlo de espacios
        String busqueda = TxtBuscador.getText().trim();
        
        // Si el buscador está vacío, volvemos a cargar toda la tabla normal
        if (busqueda.isEmpty()) {
            ListarClientes();
            return; // Terminamos la ejecución aquí
        }
        
        // LE PASAMOS EL ROL AL DAO AQUÍ ↓
        List<Cliente> lista = clienteDao.buscarClientes(busqueda, this.rolUsuario);
        modelo = (DefaultTableModel) TbClientes.getModel();
        
        // Limpiamos la tabla antes de mostrar los resultados filtrados
        modelo.setRowCount(0); 
        
        Object[] obj = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            obj[0] = lista.get(i).getIdCliente();
            obj[1] = lista.get(i).getNombreCliente();
            obj[2] = lista.get(i).getCorreo();
            obj[3] = lista.get(i).getDireccion();
            obj[4] = lista.get(i).getEstado();
            obj[5] = lista.get(i).getTipoCliente();
            obj[6] = lista.get(i).getDocumentoIdentidad();
            obj[7] = lista.get(i).getTelefono();
            modelo.addRow(obj);
        }
        TbClientes.setModel(modelo);
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void TxtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTelefonoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JComboBox<String> CbEstado;
    private javax.swing.JComboBox<String> CbTipoCliente;
    private javax.swing.JTable TbClientes;
    private javax.swing.JTextField TxtBuscador;
    private javax.swing.JTextField TxtCorreo;
    private javax.swing.JTextField TxtDireccion;
    private javax.swing.JTextField TxtDui;
    private javax.swing.JTextField TxtNombre;
    private javax.swing.JTextField TxtTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
