/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.giv.pesados.vistas;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author bryan
 */
public class FrmVentas extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmVentas
     */
/**
     * Creates new form FrmVentas
     */
    private int rolUsuario;
    // Variable global para capturar el ID que nos manda el catálogo
    private int idProductoTemp = 0; 
    // Variable para guardar el ID del cliente que hará la compra
    private int idClienteTemp = 0;
    // NUEVA VARIABLE: Guardará el descuento según el tipo de cliente
    private double porcentajeDescuentoVIP = 0.0;
    
    // Modelo de la tabla (el carrito en memoria)
    private DefaultTableModel modeloVenta = new DefaultTableModel();
    
    public FrmVentas(int rol) {
        initComponents();
        this.rolUsuario = rol;
        
        // Configuramos las 6 columnas exactas para DETALLE_VENTAS
        modeloVenta.addColumn("ID Producto");
        modeloVenta.addColumn("Nombre Repuesto");
        modeloVenta.addColumn("Cantidad");
        modeloVenta.addColumn("Precio U.");
        modeloVenta.addColumn("Descuento");
        modeloVenta.addColumn("Subtotal");
        
        TbDetalleVenta.setModel(modeloVenta);
        
        // 1. APLICAMOS EL DISEÑO A LA TABLA DE VENTAS
        aplicarEstilosTabla();
    }
    
    // NUEVO MÉTODO: Estilos visuales de la tabla de ventas
    private void aplicarEstilosTabla() {
        // Altura de las filas (ajustada para el área de ventas)
        TbDetalleVenta.setRowHeight(40);

        // Colores y líneas de la cuadrícula
        TbDetalleVenta.setBackground(java.awt.Color.WHITE);
        TbDetalleVenta.setShowVerticalLines(false); // Sin divisiones verticales
        TbDetalleVenta.setShowHorizontalLines(true); // Con divisiones horizontales
        TbDetalleVenta.setGridColor(new java.awt.Color(240, 240, 240)); // Gris sutil

        // Estilizar el Encabezado (Azul oscuro)
        javax.swing.table.JTableHeader header = TbDetalleVenta.getTableHeader();
        header.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                setBackground(new java.awt.Color(11, 35, 71)); // Azul oscuro del encabezado
                setForeground(java.awt.Color.WHITE);
                setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
                
                return this;
            }
        });

        // Alinear y estilizar las celdas del cuerpo del carrito
        javax.swing.table.DefaultTableCellRenderer cellRenderer = new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                setHorizontalAlignment(javax.swing.SwingConstants.CENTER); 
                setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)); 
                
                // Efecto hover/selección al tocar un producto del carrito
                if (isSelected) {
                    setBackground(new java.awt.Color(230, 240, 255)); // Azul claro
                    setForeground(java.awt.Color.BLACK);
                } else {
                    setBackground(java.awt.Color.WHITE);
                    setForeground(new java.awt.Color(50, 50, 50)); 
                }
                return this;
            }
        };

        // Aplicar el renderizador a todas las columnas generadas
        for (int i = 0; i < TbDetalleVenta.getColumnCount(); i++) {
            TbDetalleVenta.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }
    
    public void calcularTotalPagar() {
        double total = 0.00;
        int numFilas = TbDetalleVenta.getRowCount();

        for (int i = 0; i < numFilas; i++) {
            // El subtotal está en la columna 5 (índice 5)
            double subtotalFila = Double.parseDouble(TbDetalleVenta.getValueAt(i, 5).toString());
            total += subtotalFila;
        }
        
        // Actualizamos tu JTextField del total (asumo que lo llamaste TxtTotalPagar)
        // Usa tu nombre de variable real aquí
        TxtTotalPagar.setText(String.format("%.2f", total)); 
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
        jPanel2 = new javax.swing.JPanel();
        CbTipoComprobante = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        TxtDuiCliente = new javax.swing.JTextField();
        TxtNombreCliente = new javax.swing.JTextField();
        BtnBuscarCliente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CbMetodoPago = new javax.swing.JComboBox<>();
        lblCliente = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        BtnBuscarModal = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TxtNombreProducto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TxtStock = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TxtPrecioUnitario = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        TxtCantidad = new javax.swing.JTextField();
        BtnAgregarCarrito = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TbDetalleVenta = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        TxtTotalPagar = new javax.swing.JTextField();
        BtnGenerarVenta = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(0, 52, 89));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 52, 89))); // NOI18N

        CbTipoComprobante.setBackground(new java.awt.Color(255, 255, 255));
        CbTipoComprobante.setForeground(new java.awt.Color(0, 0, 0));
        CbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ticket", "Factura Consumidor Final ", "Comprobante de Crédito Fiscal" }));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tipo de Comprobante:");

        TxtDuiCliente.setBackground(new java.awt.Color(255, 255, 255));
        TxtDuiCliente.setForeground(new java.awt.Color(0, 0, 0));

        TxtNombreCliente.setEditable(false);
        TxtNombreCliente.setBackground(new java.awt.Color(255, 255, 255));
        TxtNombreCliente.setForeground(new java.awt.Color(0, 0, 0));

        BtnBuscarCliente.setBackground(new java.awt.Color(0, 52, 89));
        BtnBuscarCliente.setForeground(new java.awt.Color(255, 255, 255));
        BtnBuscarCliente.setText("Buscar");
        BtnBuscarCliente.addActionListener(this::BtnBuscarClienteActionPerformed);

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Numero de DUI:");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nombre Cliente:");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Metodo de Pago:");

        CbMetodoPago.setBackground(new java.awt.Color(255, 255, 255));
        CbMetodoPago.setForeground(new java.awt.Color(0, 0, 0));
        CbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo ", "Tarjeta", " " }));

        lblCliente.setForeground(new java.awt.Color(0, 0, 0));
        lblCliente.setText("Descuento: N/A");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(CbTipoComprobante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(280, 280, 280))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(46, 46, 46)
                                    .addComponent(CbMetodoPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TxtDuiCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(BtnBuscarCliente)))
                            .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtDuiCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBuscarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CbMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCliente)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Area de Venta");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscador de repuestos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 52, 89))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        BtnBuscarModal.setBackground(new java.awt.Color(0, 52, 89));
        BtnBuscarModal.setForeground(new java.awt.Color(255, 255, 255));
        BtnBuscarModal.setText("Abrir Catalogo de Repuestos");
        BtnBuscarModal.addActionListener(this::BtnBuscarModalActionPerformed);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Nombre Producto:");

        TxtNombreProducto.setEditable(false);
        TxtNombreProducto.setBackground(new java.awt.Color(255, 255, 255));
        TxtNombreProducto.setForeground(new java.awt.Color(0, 0, 0));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Stock:");

        TxtStock.setEditable(false);
        TxtStock.setBackground(new java.awt.Color(255, 255, 255));
        TxtStock.setForeground(new java.awt.Color(0, 0, 0));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Precio Unitario:");

        TxtPrecioUnitario.setEditable(false);
        TxtPrecioUnitario.setBackground(new java.awt.Color(255, 255, 255));
        TxtPrecioUnitario.setForeground(new java.awt.Color(0, 0, 0));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Cantidad a Agregar:");

        TxtCantidad.setBackground(new java.awt.Color(255, 255, 255));
        TxtCantidad.setForeground(new java.awt.Color(0, 0, 0));

        BtnAgregarCarrito.setBackground(new java.awt.Color(0, 102, 102));
        BtnAgregarCarrito.setForeground(new java.awt.Color(255, 255, 255));
        BtnAgregarCarrito.setText("Agregar al Carrito");
        BtnAgregarCarrito.addActionListener(this::BtnAgregarCarritoActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnAgregarCarrito)
                .addGap(152, 152, 152))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnBuscarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(TxtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(TxtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(TxtCantidad)
                        .addComponent(TxtNombreProducto)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(BtnBuscarModal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(TxtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(TxtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(TxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnAgregarCarrito)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle de venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 52, 89))); // NOI18N

        TbDetalleVenta.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TbDetalleVenta);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Total a Pagar:");

        TxtTotalPagar.setEditable(false);
        TxtTotalPagar.setBackground(new java.awt.Color(255, 255, 255));
        TxtTotalPagar.setForeground(new java.awt.Color(0, 0, 0));

        BtnGenerarVenta.setBackground(new java.awt.Color(0, 102, 102));
        BtnGenerarVenta.setForeground(new java.awt.Color(255, 255, 255));
        BtnGenerarVenta.setText("Generar Venta");
        BtnGenerarVenta.addActionListener(this::BtnGenerarVentaActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnGenerarVenta))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(TxtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnGenerarVenta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(368, 368, 368))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarClienteActionPerformed
        // TODO add your handling code here:
        // 1. Capturamos el DUI escrito y quitamos espacios en blanco
        String dui = TxtDuiCliente.getText().trim();

        // 2. Validamos que no esté vacío
        if (dui.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Por favor ingrese el número de DUI para buscar.");
            return;
        }

        // 3. Instanciamos el DAO y usamos el método de búsqueda que ya creaste
        com.mycompany.giv.pesados.dao.ClienteDAO clienteDao = new com.mycompany.giv.pesados.dao.ClienteDAO();
        
        // Pasamos el DUI y el rol (asumiendo que tu FrmVentas ya tiene this.rolUsuario)
        java.util.List<com.mycompany.giv.pesados.modelos.Cliente> lista = clienteDao.buscarClientes(dui, this.rolUsuario);

        // 4. Evaluamos si encontró al cliente
        if (!lista.isEmpty()) {
            // Como buscamos por un DUI específico, agarramos el primer resultado de la lista
            com.mycompany.giv.pesados.modelos.Cliente cl = lista.get(0);
            
            // Mostramos el nombre en la caja de texto (recuerda ponerla como NO editable)
            TxtNombreCliente.setText(cl.getNombreCliente());
            
            // ¡CRUCIAL! Guardamos el ID del cliente para cuando le demos al botón "Procesar Venta"
            idClienteTemp = cl.getIdCliente(); 
            
            // --- NUEVA LÓGICA VIP ---
            // Asumiendo que agregaste getTipoCliente() a tu modelo Cliente
            String tipo = cl.getTipoCliente(); 
            if (tipo != null && tipo.equalsIgnoreCase("VIP")) {
                porcentajeDescuentoVIP = 0.10; // 10% de descuento
                this.lblCliente.setText("Descuento: VIP -10%");
            } else {
                porcentajeDescuentoVIP = 0.00; // Cliente normal
            }
            // ------------------------
            
        } else {
            // Si no hay resultados (o si el cliente está inactivo y es vendedor)
            javax.swing.JOptionPane.showMessageDialog(null, "Cliente no encontrado o inactivo. Verifique el DUI.");
            TxtNombreCliente.setText("");
            idClienteTemp = 0;
            porcentajeDescuentoVIP = 0.00; // Reiniciamos por si acaso
        }
    }//GEN-LAST:event_BtnBuscarClienteActionPerformed

    private void BtnBuscarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarModalActionPerformed
        // TODO add your handling code here:
        // 1. Abrimos el catálogo bloqueando el fondo (modal = true)
        DlgBuscarProducto modal = new DlgBuscarProducto(new javax.swing.JFrame(), true);
        modal.setLocationRelativeTo(null); // Centrar en pantalla
        modal.setVisible(true); // El código de FrmVentas se pausa aquí hasta que cierres el catálogo

        // 2. Cuando el vendedor haga doble clic en un repuesto, la ventana se cerrará y el código continuará aquí
        if (modal.idSeleccionado > 0) {
            // Asignamos los valores a los JTextFields de tu FrmVentas
            TxtNombreProducto.setText(modal.nombreSeleccionado);
            
            // Al ser un JTextField, es mejor pasar solo el número limpio como texto
            TxtStock.setText(String.valueOf(modal.stockSeleccionado)); 
            TxtPrecioUnitario.setText(String.valueOf(modal.precioSeleccionado));

            // CRUCIAL: Guardamos el ID del producto en la variable global para el botón Agregar al Carrito
            idProductoTemp = modal.idSeleccionado;

            // Mandamos el cursor a la caja de cantidad para que el vendedor escriba rápido
            TxtCantidad.requestFocus();
        }
    }//GEN-LAST:event_BtnBuscarModalActionPerformed

    private void BtnAgregarCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarCarritoActionPerformed
        // TODO add your handling code here:
        // 1. Validamos que haya un producto seleccionado y cantidad ingresada
        if (idProductoTemp == 0 || TxtCantidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe buscar un producto y especificar la cantidad a vender.");
            return;
        }

        try {
            int cantidadNueva = Integer.parseInt(TxtCantidad.getText());
            int stockDisponible = Integer.parseInt(TxtStock.getText());
            double precio = Double.parseDouble(TxtPrecioUnitario.getText());
            double descuento = 0.0; // Asumimos 0 si no hay campo de descuento
            
            if (cantidadNueva <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero.");
                return;
            }

            // 2. BUSCAMOS SI EL PRODUCTO YA ESTÁ EN EL CARRITO
            int filaExistente = -1;
            int cantidadPreviaEnCarrito = 0;

            for (int i = 0; i < modeloVenta.getRowCount(); i++) {
                int idProductoEnTabla = Integer.parseInt(modeloVenta.getValueAt(i, 0).toString());
                
                if (idProductoEnTabla == idProductoTemp) {
                    filaExistente = i; // Guardamos el índice de la fila
                    cantidadPreviaEnCarrito = Integer.parseInt(modeloVenta.getValueAt(i, 2).toString());
                    break;
                }
            }

            // 3. NUEVA VALIDACIÓN DE STOCK (Cantidad Anterior + Cantidad Nueva)
            int cantidadTotalProyectada = cantidadPreviaEnCarrito + cantidadNueva;

            if (cantidadTotalProyectada > stockDisponible) {
                JOptionPane.showMessageDialog(null, "Stock insuficiente.\nYa tienes " + cantidadPreviaEnCarrito + 
                                              " unidades de este repuesto en el carrito.\nSolo puedes agregar " + 
                                              (stockDisponible - cantidadPreviaEnCarrito) + " más.");
                return;
            }

            // 4. AGREGAR O ACTUALIZAR FILA
            if (filaExistente >= 0) {
                // El producto ya existe: Actualizamos la cantidad
                modeloVenta.setValueAt(cantidadTotalProyectada, filaExistente, 2);
                
                // --- NUEVA MATEMÁTICA CON DESCUENTO ---
                double subtotalBruto = cantidadTotalProyectada * precio;
                double descuentoCalculado = subtotalBruto * porcentajeDescuentoVIP;
                double nuevoSubtotal = subtotalBruto - descuentoCalculado;
                
                String descFormateado = String.format(java.util.Locale.US, "%.2f", descuentoCalculado);
                String subtotalFormateado = String.format(java.util.Locale.US, "%.2f", nuevoSubtotal);
                
                modeloVenta.setValueAt(descFormateado, filaExistente, 4); // Actualizamos la celda de descuento
                modeloVenta.setValueAt(subtotalFormateado, filaExistente, 5); // Actualizamos la celda de subtotal
                
            } else {
                // El producto es nuevo: Calculamos desde cero
                double subtotalBruto = cantidadNueva * precio;
                double descuentoCalculado = subtotalBruto * porcentajeDescuentoVIP;
                double subtotalNeto = subtotalBruto - descuentoCalculado;
                
                String precioFormat = String.format(java.util.Locale.US, "%.2f", precio);
                String descFormat = String.format(java.util.Locale.US, "%.2f", descuentoCalculado);
                String subtotalFormat = String.format(java.util.Locale.US, "%.2f", subtotalNeto);
                
                Object[] fila = new Object[6];
                fila[0] = idProductoTemp;
                fila[1] = TxtNombreProducto.getText();
                fila[2] = cantidadNueva;
                fila[3] = precioFormat;
                fila[4] = descFormat; // Aquí ya va el dinero real descontado
                fila[5] = subtotalFormat; // El dinero neto final

                modeloVenta.addRow(fila);
            }

            // 5. Limpiamos los campos para el siguiente producto
            idProductoTemp = 0;
            TxtNombreProducto.setText("");
            TxtStock.setText("");
            TxtPrecioUnitario.setText("");
            TxtCantidad.setText("");
            
            // 6. Ejecutamos el cálculo matemático para actualizar el Total a Pagar
            calcularTotalPagar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese valores numéricos válidos en Cantidad y Precio.");
        }
    }//GEN-LAST:event_BtnAgregarCarritoActionPerformed

    private void BtnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarVentaActionPerformed
        
        // 1. Validaciones iniciales de seguridad
        if (idClienteTemp == 0) {
            JOptionPane.showMessageDialog(this, "Debe buscar y seleccionar un cliente antes de procesar la venta.");
            return;
        }

        if (TbDetalleVenta.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "El carrito de compras está vacío. Agregue repuestos para continuar.");
            return;
        }

        try {
            // 2. Preparar el objeto de la cabecera (Venta)
            com.mycompany.giv.pesados.modelos.Venta nuevaVenta = new com.mycompany.giv.pesados.modelos.Venta();
            
            // Asignamos la fecha y hora actual del sistema
            nuevaVenta.setFechaVenta(new java.sql.Timestamp(System.currentTimeMillis()));
            // Convertimos el total de la caja de texto a double
            nuevaVenta.setTotalPagar(Double.parseDouble(TxtTotalPagar.getText().replace(",", ".")));
            nuevaVenta.setTipoComprobante(CbTipoComprobante.getSelectedItem().toString());
            nuevaVenta.setMetodoPago(CbMetodoPago.getSelectedItem().toString());
            nuevaVenta.setEstado(1); // 1 = Venta Activa
            nuevaVenta.setIdCliente(idClienteTemp); 
            
            // OJO: Aquí deberías poner el ID del usuario real que inició sesión. 
            // Por ahora uso la variable rolUsuario que ya tienes en el formulario, 
            // pero asegúrate de pasar el idUsuario exacto desde el Login en el futuro.
            nuevaVenta.setIdUsuario(this.rolUsuario); 

            // 3. Preparar la lista de detalles recorriendo el JTable
            java.util.List<com.mycompany.giv.pesados.modelos.DetalleVenta> listaDetalles = new java.util.ArrayList<>();
            
            for (int i = 0; i < TbDetalleVenta.getRowCount(); i++) {
                com.mycompany.giv.pesados.modelos.DetalleVenta detalle = new com.mycompany.giv.pesados.modelos.DetalleVenta();
                
                // Extraemos los datos de las columnas basándonos en cómo las llenaste en BtnAgregarCarrito
                detalle.setIdProducto(Integer.parseInt(TbDetalleVenta.getValueAt(i, 0).toString()));
                detalle.setCantidad(Integer.parseInt(TbDetalleVenta.getValueAt(i, 2).toString()));
                detalle.setPrecioUnitario(Double.parseDouble(TbDetalleVenta.getValueAt(i, 3).toString()));
                detalle.setDescuento(Double.parseDouble(TbDetalleVenta.getValueAt(i, 4).toString()));
                detalle.setSubtotal(Double.parseDouble(TbDetalleVenta.getValueAt(i, 5).toString()));
                
                listaDetalles.add(detalle);
            }

            // 4. Mandamos todo al Motor Transaccional (DAO)
            com.mycompany.giv.pesados.dao.VentaDAO ventaDao = new com.mycompany.giv.pesados.dao.VentaDAO();
            int idVentaGenerada = ventaDao.registrarVenta(nuevaVenta, listaDetalles);

            // 5. Evaluamos el resultado de la transacción
            if (idVentaGenerada > 0) {
                // ¡AQUÍ ESTÁ EL TRUCO, MAJE! Le asignamos el ID real de la DB al objeto cabecera
                nuevaVenta.setIdVenta(idVentaGenerada);
                
                JOptionPane.showMessageDialog(this, "¡Transacción Exitosa! Venta N° " + idVentaGenerada + " registrada correctamente en GIV-PESADOS.");
                
                // Instanciamos el generador con los parches aplicados
                com.mycompany.giv.pesados.config.GeneradorDocumentos docs = new com.mycompany.giv.pesados.config.GeneradorDocumentos();
                
                // 1. Exporta el JSON (ahora saldrá con el ID 8, 9, etc.)
                docs.exportarVentaJSON(nuevaVenta, listaDetalles);
                
                // 2. Genera el PDF usando el stream de recursos de Maven
                docs.generarComprobantePDF(idVentaGenerada, CbTipoComprobante.getSelectedItem().toString());

                // 6. Limpiamos el formulario para el siguiente cliente
                idClienteTemp = 0;
                TxtDuiCliente.setText("");
                TxtNombreCliente.setText("");
                TxtTotalPagar.setText("");
                modeloVenta.setRowCount(0); 
                
            } else {
                JOptionPane.showMessageDialog(this, "Error crítico: La venta fue cancelada mediante Rollback.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error de formato en los números calculados: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + e.getMessage());
        }
        
    }//GEN-LAST:event_BtnGenerarVentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarCarrito;
    private javax.swing.JButton BtnBuscarCliente;
    private javax.swing.JButton BtnBuscarModal;
    private javax.swing.JButton BtnGenerarVenta;
    private javax.swing.JComboBox<String> CbMetodoPago;
    private javax.swing.JComboBox<String> CbTipoComprobante;
    private javax.swing.JTable TbDetalleVenta;
    private javax.swing.JTextField TxtCantidad;
    private javax.swing.JTextField TxtDuiCliente;
    private javax.swing.JTextField TxtNombreCliente;
    private javax.swing.JTextField TxtNombreProducto;
    private javax.swing.JTextField TxtPrecioUnitario;
    private javax.swing.JTextField TxtStock;
    private javax.swing.JTextField TxtTotalPagar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliente;
    // End of variables declaration//GEN-END:variables
}
