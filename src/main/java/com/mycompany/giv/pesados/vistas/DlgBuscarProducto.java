/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.giv.pesados.vistas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

import com.mycompany.giv.pesados.dao.ProductoDAO;
import com.mycompany.giv.pesados.modelos.Producto;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 *
 * @author bryan
 */
public class DlgBuscarProducto extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DlgBuscarProducto.class.getName());

    /**
     * Creates new form DlgBuscarProducto
     */
    
    // Variables globales para enviar a FrmVentas
    public int idSeleccionado = 0;
    public String nombreSeleccionado = "";
    public double precioSeleccionado = 0.0;
    public int stockSeleccionado = 0;
    
    // El panel interno que tendrá la cuadrícula
    private JPanel panelCuadricula;
    
    public DlgBuscarProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        // Configuramos el panel interno con un GridLayout de 3 columnas (0 filas significa filas infinitas)
        panelCuadricula = new JPanel(new GridLayout(0, 3, 15, 15));
        panelCuadricula.setBackground(Color.WHITE);
        
        // Le metemos este panel a tu ScrollPane que creaste en el diseño
        ScrollPaneProductos.setViewportView(panelCuadricula);
        
        // 1. Primero llenamos el ComboBox con los datos de la DB
        cargarComboCategorias();
        // Cargamos todos los productos al iniciar (AHORA PASAMOS TEXTO VACÍO Y "Todos")
        cargarCatalogo("", "Todos");
    }
    
// ==============================================================
    // MÉTODO PARA CREAR LAS TARJETAS DINÁMICAS (LIMPIEZA FINAL DE CARACTERES)
    // ==============================================================
    public void cargarCatalogo(String filtroBusqueda, String categoria) {
        panelCuadricula.removeAll();
        
        ProductoDAO prodDao = new ProductoDAO();
        List<Producto> lista = prodDao.filtrarCatalogo(filtroBusqueda, categoria);
        
        // Paleta de colores profesional
        Color colFondoCard = Color.decode("#ffffff");
        Color colBorde = Color.decode("#e2e8f0");
        Color colFondoImagen = Color.decode("#cbd5e1");
        Color colBadge = Color.decode("#10b981"); 
        Color colMarca = Color.decode("#64748b"); 
        Color colTitulo = Color.decode("#0f172a"); 
        Color colFooter = Color.decode("#f8fafc");
        Color colBtnBg = Color.decode("#1e293b");
        
        for (Producto prod : lista) {
            
            // --- 1. CONTENEDOR PRINCIPAL ---
            JPanel tarjeta = new JPanel(new BorderLayout());
            tarjeta.setBackground(colFondoCard);
            tarjeta.setBorder(BorderFactory.createLineBorder(colBorde, 1, true));
            tarjeta.setPreferredSize(new Dimension(260, 420)); 
            tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // --- 2. IMAGEN Y ETIQUETA (DISPONIBLE) ---
            JPanel panelImagen = new JPanel(new BorderLayout());
            panelImagen.setBackground(colFondoImagen);
            panelImagen.setPreferredSize(new Dimension(260, 150));
            
            // SIN ICONO: El color verde ya indica éxito/disponible
            JLabel lblBadge = new JLabel(" DISPONIBLE ");
            lblBadge.setOpaque(true);
            lblBadge.setBackground(colBadge);
            lblBadge.setForeground(Color.WHITE);
            lblBadge.setFont(new Font("Segoe UI", Font.BOLD, 11));
            lblBadge.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8)); 
            
            JPanel panelBadgeContenedor = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelBadgeContenedor.setOpaque(false);
            panelBadgeContenedor.add(lblBadge);
            
            JLabel lblImagen = new JLabel("", SwingConstants.CENTER);
            String rutaImg = prod.getRutaImagen(); 
            if (rutaImg == null || rutaImg.isEmpty() || !new File(rutaImg).exists()) {
                rutaImg = "imagenes_repuestos/default.png"; 
            }
            try {
                ImageIcon icono = new ImageIcon(rutaImg);
                Image imgEscalada = icono.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(imgEscalada));
            } catch (Exception e) {
                lblImagen.setText("Sin Imagen"); 
            }
            
            panelImagen.add(panelBadgeContenedor, BorderLayout.NORTH);
            panelImagen.add(lblImagen, BorderLayout.CENTER);
            tarjeta.add(panelImagen, BorderLayout.NORTH);

            // --- 3. CUERPO DE DATOS TÉCNICOS ---
            JPanel panelCuerpo = new JPanel();
            panelCuerpo.setLayout(new BoxLayout(panelCuerpo, BoxLayout.Y_AXIS));
            panelCuerpo.setBackground(colFondoCard);
            panelCuerpo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 
            
            // SIN ICONO: Solo el texto limpio
            String textoMarca = prod.getMarca() != null && !prod.getMarca().isEmpty() ? prod.getMarca().toUpperCase() : "MARCA GENÉRICA";
            JLabel lblMarca = new JLabel("MARCA: " + textoMarca);
            lblMarca.setForeground(colMarca);
            lblMarca.setFont(new Font("Segoe UI", Font.BOLD, 11));
            
            // Título (Mantenemos el cuadro pequeño que sí funcionó)
            JLabel lblTitulo = new JLabel("▪ " + prod.getNombreRepuesto());
            lblTitulo.setForeground(colTitulo);
            lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
            
            // Descripción (Mantenemos el lápiz que sí funcionó)
            String desc = prod.getDescripcion() != null && !prod.getDescripcion().isEmpty() ? prod.getDescripcion() : "Sin descripción adicional.";
            if (desc.length() > 50) {
                desc = desc.substring(0, 47) + "...";
            }
            JLabel lblDesc = new JLabel("<html><p style='width:200px; color:#64748b; font-size:9px; font-style:italic;'>✎ " + desc + "</p></html>");
            
            // Añadimos los primeros textos
            panelCuerpo.add(lblMarca);
            panelCuerpo.add(Box.createRigidArea(new Dimension(0, 3)));
            panelCuerpo.add(lblTitulo);
            panelCuerpo.add(Box.createRigidArea(new Dimension(0, 5)));
            panelCuerpo.add(lblDesc);
            panelCuerpo.add(Box.createRigidArea(new Dimension(0, 15)));
            
            // Cuadrícula de Especificaciones (Mantenemos los que sí funcionaron)
            JPanel panelSpecs = new JPanel(new GridLayout(2, 2, 5, 8)); 
            panelSpecs.setBackground(colFondoCard);
            
            String numSerie = prod.getNumSerie() != null ? prod.getNumSerie() : "N/A";
            String htmlSerie = "<html><font color='#475569' size='3'># Serie:<br><b>" + numSerie + "</b></font></html>";
            String htmlPrecio = "<html><font color='#475569' size='3'>$ Precio:<br><b>$" + String.format("%.2f", prod.getPrecioVenta()) + "</b></font></html>";
            String htmlStockA = "<html><font color='#475569' size='3'>≡ Stock:<br><b>" + prod.getStockActual() + " un.</b></font></html>";
            
            panelSpecs.add(new JLabel(htmlSerie));
            panelSpecs.add(new JLabel(htmlPrecio));
            panelSpecs.add(new JLabel(htmlStockA));
            panelSpecs.add(new JLabel("")); 
            
            panelCuerpo.add(panelSpecs);
            tarjeta.add(panelCuerpo, BorderLayout.CENTER);

            // --- 4. PIE DE LA TARJETA Y BOTÓN ---
            JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
            panelFooter.setBackground(colFooter);
            panelFooter.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, colBorde));
            
            // SIN ICONO: Botón limpio
            JButton btnSeleccionar = new JButton("Seleccionar Equipo");
            btnSeleccionar.setPreferredSize(new Dimension(220, 35));
            btnSeleccionar.setBackground(colBtnBg);
            btnSeleccionar.setForeground(Color.WHITE);
            btnSeleccionar.setFont(new Font("Segoe UI", Font.BOLD, 13));
            btnSeleccionar.setFocusPainted(false);
            btnSeleccionar.setBorderPainted(false);
            btnSeleccionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            // Eventos del Botón y Tarjeta
            btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    idSeleccionado = prod.getIdProducto();
                    nombreSeleccionado = prod.getNombreRepuesto();
                    precioSeleccionado = prod.getPrecioVenta();
                    stockSeleccionado = prod.getStockActual();
                    dispose(); 
                }
            });

            tarjeta.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        btnSeleccionar.doClick(); 
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e) { 
                    tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
                }
                @Override
                public void mouseExited(MouseEvent e) { 
                    tarjeta.setBorder(BorderFactory.createLineBorder(colBorde, 1, true));
                }
            });

            panelFooter.add(btnSeleccionar);
            tarjeta.add(panelFooter, BorderLayout.SOUTH);

            // Se agrega al panel principal
            panelCuadricula.add(tarjeta);
        }
        
        panelCuadricula.revalidate();
        panelCuadricula.repaint();
    }
    // ==============================================================
    // MÉTODO PARA LLENAR EL COMBOBOX DINÁMICAMENTE
    // ==============================================================
    private void cargarComboCategorias() {
        // 1. Limpiamos cualquier texto basura que tenga el ComboBox desde NetBeans
        CbCategorias.removeAllItems();
        
        // 2. Llamamos al DAO para traer la lista de la Base de Datos
        com.mycompany.giv.pesados.dao.CategoriaDAO catDao = new com.mycompany.giv.pesados.dao.CategoriaDAO();
        List<String> categoriasDB = catDao.obtenerNombresCategorias();
        
        // 3. Recorremos la lista y metemos cada palabra en el ComboBox
        for (String categoria : categoriasDB) {
            CbCategorias.addItem(categoria);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtBuscar = new javax.swing.JTextField();
        BtnBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        CbCategorias = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ScrollPaneProductos = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 52, 89));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Gestione y filtre el inventario");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nombre o Código:");

        TxtBuscar.setForeground(new java.awt.Color(0, 0, 0));

        BtnBuscar.setBackground(new java.awt.Color(0, 180, 216));
        BtnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        BtnBuscar.setText("Buscar");
        BtnBuscar.setContentAreaFilled(false);
        BtnBuscar.setOpaque(true);
        BtnBuscar.addActionListener(this::BtnBuscarActionPerformed);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Categorias:");

        CbCategorias.setForeground(new java.awt.Color(0, 0, 0));
        CbCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Autobuses", "Rastras" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnBuscar)
                        .addComponent(jLabel4)
                        .addComponent(CbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Catálogo de Productos");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(ScrollPaneProductos)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPaneProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(299, 299, 299))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        // TODO add your handling code here:
        // Obtenemos el texto ingresado
        String texto = TxtBuscar.getText().trim();
        
        // Obtenemos la categoría seleccionada en el ComboBox
        String categoria = CbCategorias.getSelectedItem().toString();
        
        // Llamamos al método con los dos filtros
        cargarCatalogo(texto, categoria);
    }//GEN-LAST:event_BtnBuscarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DlgBuscarProducto dialog = new DlgBuscarProducto(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JComboBox<String> CbCategorias;
    private javax.swing.JScrollPane ScrollPaneProductos;
    private javax.swing.JTextField TxtBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
