/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.giv.pesados.vistas;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.mycompany.giv.pesados.dao.CategoriaDAO;
import com.mycompany.giv.pesados.dao.ProductoDAO;
import com.mycompany.giv.pesados.modelos.Categoria;
import com.mycompany.giv.pesados.modelos.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author uto
 */
public class FrmProductos extends javax.swing.JInternalFrame {
    private ProductoDAO productoDAO;
    private CategoriaDAO categoriaDAO;
    private int idProductoSeleccionado = 0;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<DefaultTableModel> sorter;

    /**
     * Creates new form FrmProductos
     */
    public FrmProductos() {
        initComponents();
        productoDAO = new ProductoDAO();
        categoriaDAO = new CategoriaDAO();
        
        cargarCategorias();
        cargarTabla();
        // ==========================================
    // 1. FONDOS, BANNER Y TARJETAS
    // ==========================================
    // Fondo general de la ventana
    panel2.setBackground(com.mycompany.giv.pesados.config.EstiloUI.FONDO_APP);
    
    // Banner superior oscuro
    if (banner1 != null) {
        banner1.setBackground(com.mycompany.giv.pesados.config.EstiloUI.AZUL_OSCURO);
    }
    
    // Tarjeta blanca para el formulario
    if (panelTarjeta != null) {
        panelTarjeta.setBackground(com.mycompany.giv.pesados.config.EstiloUI.FONDO_TARJETA);
        panelTarjeta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 231, 235), 1, true));
    }
    
    if (titulo1 != null) {
        titulo1.setForeground(java.awt.Color.WHITE);
        titulo1.setFont(com.mycompany.giv.pesados.config.EstiloUI.FUENTE_TITULO);
    }

    // ==========================================
    // 2. BOTONES
    // ==========================================
    com.mycompany.giv.pesados.config.EstiloUI.aplicarEstiloBoton(btnGuardar, com.mycompany.giv.pesados.config.EstiloUI.BTN_GUARDAR);
    com.mycompany.giv.pesados.config.EstiloUI.aplicarEstiloBoton(btnActualizar, com.mycompany.giv.pesados.config.EstiloUI.BTN_ACTUALIZAR);
    com.mycompany.giv.pesados.config.EstiloUI.aplicarEstiloBoton(btnEliminar, com.mycompany.giv.pesados.config.EstiloUI.BTN_ELIMINAR);
    com.mycompany.giv.pesados.config.EstiloUI.aplicarEstiloBoton(btnLimpiar, com.mycompany.giv.pesados.config.EstiloUI.AZUL_OSCURO);
    com.mycompany.giv.pesados.config.EstiloUI.aplicarEstiloBoton(btnSeleccion, com.mycompany.giv.pesados.config.EstiloUI.AZUL_OSCURO);

    // ==========================================
    // 3. ETIQUETAS (LABELS)
    // ==========================================
    javax.swing.JLabel[] etiquetas = {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9};
    for (javax.swing.JLabel l : etiquetas) {
        if (l != null) {
            l.setFont(com.mycompany.giv.pesados.config.EstiloUI.FUENTE_GENERAL);
            l.setForeground(com.mycompany.giv.pesados.config.EstiloUI.TEXTO_PRINCIPAL);
        }
    }

    // ==========================================
    // 4. CAMPOS DE TEXTO, ÁREAS Y LISTAS
    // ==========================================
    javax.swing.JComponent[] campos = {
        txtBuscar, txtNombreRepuesto, txtNumSerie, txtMarca, 
        txtPrecioVenta, txtStockActual, txtStockMinimo, txtImage, 
        txaDescripcion, lstCategorias
    };
    for (javax.swing.JComponent c : campos) {
        if (c != null) {
            com.mycompany.giv.pesados.config.EstiloUI.aplicarEstiloCampo(c);
        }
    }

    // Limpiar los bordes de los JScrollPanes internos para evitar bordes dobles (aplica a la lista y al área de texto)
    if (jScrollPane1 != null) jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    if (jScrollPane3 != null) jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder());

    // Color de selección para la lista
    if (lstCategorias != null) {
        lstCategorias.setSelectionBackground(com.mycompany.giv.pesados.config.EstiloUI.BTN_ACTUALIZAR);
        lstCategorias.setSelectionForeground(java.awt.Color.WHITE);
    }

    // Borde sutil para el contenedor de la imagen
    if (lblImage != null) {
        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 231, 235), 1, true));
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
    if (tblProductos != null) {
        tblProductos.setRowHeight(35); 
        tblProductos.setShowVerticalLines(false);
        tblProductos.setShowHorizontalLines(true);
        tblProductos.setGridColor(new java.awt.Color(229, 231, 235));
        
        tblProductos.setSelectionBackground(com.mycompany.giv.pesados.config.EstiloUI.BTN_ACTUALIZAR);
        tblProductos.setSelectionForeground(java.awt.Color.WHITE);
        tblProductos.setFont(com.mycompany.giv.pesados.config.EstiloUI.FUENTE_GENERAL);
        
        // Cabecera oscura
        tblProductos.getTableHeader().setBackground(com.mycompany.giv.pesados.config.EstiloUI.AZUL_OSCURO);
        tblProductos.getTableHeader().setForeground(java.awt.Color.WHITE);
        tblProductos.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        tblProductos.getTableHeader().setOpaque(false);
    }
  

    }
    
    private void mostrarImagen(String ruta) {
        if (ruta != null && !ruta.trim().isEmpty()) {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                ImageIcon iconoOriginal = new ImageIcon(ruta);
                // Ajustamos la imagen al ancho y alto del lblImage
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
                lblImage.setIcon(new ImageIcon(imagenEscalada));
                return; // Si todo salió bien, cortamos la ejecución aquí
            } 
        }
        
        // Si la ruta está vacía, es nula o el archivo no existe, cargamos el default
        java.net.URL imgUrl = getClass().getResource("/img/default.jpg");
        if (imgUrl != null) {
            ImageIcon iconoDefault = new ImageIcon(imgUrl);
            Image imagenEscalada = iconoDefault.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(imagenEscalada));
        } else {
            lblImage.setIcon(null);
        }
    }
    
    private void cargarCategorias() {
        DefaultListModel<Categoria> modeloLista = new DefaultListModel<>();
        List<Categoria> categorias = categoriaDAO.listarCategoriasActivas();
        
        for (Categoria c : categorias) {
            modeloLista.addElement(c);
        }
        lstCategorias.setModel(modeloLista);
    }
    ///cargar tabla
    ///
    private void cargarTabla() {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Repuesto");
        modeloTabla.addColumn("N° Serie");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Precio ($)");
        modeloTabla.addColumn("Stock");
        modeloTabla.addColumn("Mínimo"); // 
        modeloTabla.addColumn("Ruta Imagen"); // Columna 7
        modeloTabla.addColumn("Descripción"); // Columna 8 (Nueva)
        
        List<Producto> lista = productoDAO.listarProductosActivos();
        Object[] fila = new Object[9];
        
        for (Producto p : lista) {
            fila[0] = p.getIdProducto();
            fila[1] = p.getNombreRepuesto();
            fila[2] = p.getNumSerie();
            fila[3] = p.getMarca();
            fila[4] = p.getPrecioVenta();
            fila[5] = p.getStockActual();
            fila[6] = p.getStockMinimo();
            fila[7] = p.getRutaImagen();
            fila[8] = p.getDescripcion();
            modeloTabla.addRow(fila);
        }
        
        tblProductos.setModel(modeloTabla);
        
        // Inicializar el motor de búsqueda en memoria
        sorter = new TableRowSorter<>(modeloTabla);
        tblProductos.setRowSorter(sorter);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        banner1 = new javax.swing.JPanel();
        titulo1 = new javax.swing.JLabel();
        panelTarjeta = new javax.swing.JPanel();
        txtNombreRepuesto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNumSerie = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaDescripcion = new javax.swing.JTextArea();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtStockActual = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtStockMinimo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCategorias = new javax.swing.JList<>();
        txtImage = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnSeleccion = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        panel2.setBackground(new java.awt.Color(204, 204, 204));
        panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        panel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, -1, -1));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        panel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, -1, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);
        panel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 420, -1, -1));

        btnLimpiar.setText("limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        panel2.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 430, -1, -1));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProductos);

        panel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, 790, 180));

        titulo1.setBackground(new java.awt.Color(255, 255, 255));
        titulo1.setFont(new java.awt.Font("sansserif", 0, 48)); // NOI18N
        titulo1.setText("Gestion de productos");

        javax.swing.GroupLayout banner1Layout = new javax.swing.GroupLayout(banner1);
        banner1.setLayout(banner1Layout);
        banner1Layout.setHorizontalGroup(
            banner1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(banner1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(titulo1)
                .addContainerGap(552, Short.MAX_VALUE))
        );
        banner1Layout.setVerticalGroup(
            banner1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(banner1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo1)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        panel2.add(banner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 70));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nombre del Repuesto:");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Número de serie:");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("marca:");

        txaDescripcion.setColumns(20);
        txaDescripcion.setRows(5);
        jScrollPane3.setViewportView(txaDescripcion);

        txtBuscar.addActionListener(this::txtBuscarActionPerformed);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Buscar:");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Descripción:");

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Precio venta:");

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Stock actual:");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Stock mínimo:");

        jScrollPane1.setViewportView(lstCategorias);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Imagen:");

        btnSeleccion.setText("Seleccionar...");
        btnSeleccion.addActionListener(this::btnSeleccionActionPerformed);

        lblImage.setForeground(new java.awt.Color(0, 0, 0));
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default.jpg"))); // NOI18N

        javax.swing.GroupLayout panelTarjetaLayout = new javax.swing.GroupLayout(panelTarjeta);
        panelTarjeta.setLayout(panelTarjetaLayout);
        panelTarjetaLayout.setHorizontalGroup(
            panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTarjetaLayout.createSequentialGroup()
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTarjetaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(62, 62, 62)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTarjetaLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTarjetaLayout.createSequentialGroup()
                        .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTarjetaLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTarjetaLayout.createSequentialGroup()
                                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelTarjetaLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel3))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTarjetaLayout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(jLabel4)))
                                .addGap(28, 28, 28)))
                        .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTarjetaLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(txtNombreRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNumSerie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTarjetaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelTarjetaLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTarjetaLayout.createSequentialGroup()
                                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtStockActual, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTarjetaLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTarjetaLayout.createSequentialGroup()
                        .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSeleccion))
                    .addComponent(lblImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        panelTarjetaLayout.setVerticalGroup(
            panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTarjetaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnSeleccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTarjetaLayout.createSequentialGroup()
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTarjetaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTarjetaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStockActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(txtNombreRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNumSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTarjetaLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTarjetaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panel2.add(panelTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1080, 330));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1112, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (!validarCampos()) return; // Si la validación falla, detenemos la ejecución

        Producto p = crearProductoDesdeFormulario();
        List<Integer> idCategorias = obtenerCategoriasSeleccionadas();

        if (productoDAO.registrarProductoConCategorias(p, idCategorias)) {
            JOptionPane.showMessageDialog(this, "¡Repuesto registrado exitosamente en el inventario!");
            limpiarCampos();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el repuesto.", "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        if (idProductoSeleccionado == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un repuesto de la tabla para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validarCampos()) return;

        Producto p = crearProductoDesdeFormulario();
        p.setIdProducto(idProductoSeleccionado); // Asignamos el ID oculto
        List<Integer> idCategorias = obtenerCategoriasSeleccionadas();

        if (productoDAO.actualizarProductoConCategorias(p, idCategorias)) {
            JOptionPane.showMessageDialog(this, "Inventario actualizado correctamente.");
            limpiarCampos();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el repuesto.", "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (idProductoSeleccionado == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un repuesto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de dar de baja este repuesto del inventario?", "Confirmar Baja", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (productoDAO.eliminarProducto(idProductoSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Repuesto eliminado del sistema.");
                limpiarCampos();
                cargarTabla();
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        int filaVista = tblProductos.getSelectedRow();
        if (filaVista >= 0) {
            int filaModelo = tblProductos.convertRowIndexToModel(filaVista);
            
            // 1. Extraer los datos básicos
            idProductoSeleccionado = Integer.parseInt(modeloTabla.getValueAt(filaModelo, 0).toString());
            txtNombreRepuesto.setText(modeloTabla.getValueAt(filaModelo, 1) != null ? modeloTabla.getValueAt(filaModelo, 1).toString() : "");
            txtNumSerie.setText(modeloTabla.getValueAt(filaModelo, 2) != null ? modeloTabla.getValueAt(filaModelo, 2).toString() : "");
            txtMarca.setText(modeloTabla.getValueAt(filaModelo, 3) != null ? modeloTabla.getValueAt(filaModelo, 3).toString() : "");
            txtPrecioVenta.setText(modeloTabla.getValueAt(filaModelo, 4) != null ? modeloTabla.getValueAt(filaModelo, 4).toString() : "");
            txtStockActual.setText(modeloTabla.getValueAt(filaModelo, 5) != null ? modeloTabla.getValueAt(filaModelo, 5).toString() : "");
            txtStockMinimo.setText(modeloTabla.getValueAt(filaModelo, 6) != null ? modeloTabla.getValueAt(filaModelo, 6).toString() : "");
            
            // 2. Extraer Imagen y Descripción (Las columnas que agregamos)
            String rutaImg = modeloTabla.getValueAt(filaModelo, 7) != null ? modeloTabla.getValueAt(filaModelo, 7).toString() : "";
            txtImage.setText(rutaImg);
            mostrarImagen(rutaImg); // Llama a tu método mágico
            
            String descripcion = modeloTabla.getValueAt(filaModelo, 8) != null ? modeloTabla.getValueAt(filaModelo, 8).toString() : "";
            txaDescripcion.setText(descripcion);

            // 3. Seleccionar las categorías en el JList
            List<Integer> idsCategorias = categoriaDAO.obtenerIdCategoriasPorProducto(idProductoSeleccionado);
            List<Integer> indicesSeleccionados = new ArrayList<>();
            
            // Recorremos el modelo visual del JList para ver cuáles coinciden con la BD
            for (int i = 0; i < lstCategorias.getModel().getSize(); i++) {
                Categoria cat = lstCategorias.getModel().getElementAt(i);
                if (idsCategorias.contains(cat.getIdCategoria())) {
                    indicesSeleccionados.add(i);
                }
            }
            
            // Convertimos la lista de Integer a un int[] porque así lo pide Java Swing
            int[] arrIndices = indicesSeleccionados.stream().mapToInt(i -> i).toArray();
            lstCategorias.setSelectedIndices(arrIndices);
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        String textoFiltro = txtBuscar.getText().trim();
        // Filtramos buscando en todas las columnas (magia pura de Java Swing)
        if (textoFiltro.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            // (?i) hace que la búsqueda ignore mayúsculas y minúsculas
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoFiltro)); 
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Imagen del Repuesto");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (JPG, PNG, GIF)", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filtro);

        int respuesta = fileChooser.showOpenDialog(this);
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            String rutaSeleccionada = fileChooser.getSelectedFile().getAbsolutePath();
            txtImage.setText(rutaSeleccionada);
            mostrarImagen(rutaSeleccionada);
        }
    }//GEN-LAST:event_btnSeleccionActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

private void limpiarCampos() {
        txtNombreRepuesto.setText("");
        txtNumSerie.setText("");
        txtMarca.setText("");
        txaDescripcion.setText("");
        txtPrecioVenta.setText("");
        txtStockActual.setText("");
        txtStockMinimo.setText("");
        txtBuscar.setText("");
        lstCategorias.clearSelection();
        idProductoSeleccionado = 0;
        txtImage.setText("");
        java.net.URL imgUrl = getClass().getResource("/img/default.jpg");
        if (imgUrl != null) {
            ImageIcon iconoDefault = new ImageIcon(imgUrl);
            Image imagenEscalada = iconoDefault.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(imagenEscalada));
        } else {
            lblImage.setIcon(null);
        }
        
        if(sorter != null) sorter.setRowFilter(null); // Quitar filtro
    }
private Producto crearProductoDesdeFormulario() {
        Producto p = new Producto();
        p.setNombreRepuesto(txtNombreRepuesto.getText().trim());
        p.setNumSerie(txtNumSerie.getText().trim());
        p.setMarca(txtMarca.getText().trim());
        p.setDescripcion(txaDescripcion.getText().trim());
        p.setPrecioVenta(Float.parseFloat(txtPrecioVenta.getText().trim()));
        p.setStockActual(Integer.parseInt(txtStockActual.getText().trim()));
        p.setStockMinimo(Integer.parseInt(txtStockMinimo.getText().trim()));
        p.setRutaImagen(txtImage.getText().trim());
        return p;
    }
private List<Integer> obtenerCategoriasSeleccionadas() {
        List<Categoria> seleccionadas = lstCategorias.getSelectedValuesList();
        List<Integer> ids = new ArrayList<>();
        for (Categoria c : seleccionadas) {
            ids.add(c.getIdCategoria());
        }
        return ids;
    }
private boolean validarCampos() {
        if (txtImage.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una imagen para el repuesto.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtNombreRepuesto.getText().trim().isEmpty() || txtPrecioVenta.getText().trim().isEmpty() || 
            txtStockActual.getText().trim().isEmpty() || txtStockMinimo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llene los campos obligatorios (Nombre, Precio y Stocks).", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            Float.parseFloat(txtPrecioVenta.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio de venta debe ser un número válido (ej: 150.50).", "Validación Numérica", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            Integer.parseInt(txtStockActual.getText().trim());
            Integer.parseInt(txtStockMinimo.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Las cantidades de stock deben ser números enteros sin decimales.", "Validación Numérica", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (lstCategorias.getSelectedValuesList().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe clasificar el repuesto en al menos una categoría de la lista.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel banner1;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSeleccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblImage;
    private javax.swing.JList<Categoria> lstCategorias;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panelTarjeta;
    private javax.swing.JTable tblProductos;
    private javax.swing.JLabel titulo1;
    private javax.swing.JTextArea txaDescripcion;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtImage;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNombreRepuesto;
    private javax.swing.JTextField txtNumSerie;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtStockActual;
    private javax.swing.JTextField txtStockMinimo;
    // End of variables declaration//GEN-END:variables
}
