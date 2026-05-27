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

import com.mycompany.giv.pesados.dao.ProductoDAO;
import com.mycompany.giv.pesados.modelos.Producto;

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
        
        // Cargamos todos los productos al iniciar (AHORA PASAMOS TEXTO VACÍO Y "Todos")
        cargarCatalogo("", "Todos");
    }
    
    // ==============================================================
    // MÉTODO PARA CREAR LAS TARJETAS DINÁMICAS (ACTUALIZADO)
    // ==============================================================
    public void cargarCatalogo(String filtroBusqueda, String categoria) {
        // 1. Limpiamos el panel por si estamos filtrando
        panelCuadricula.removeAll();
        
        // 2. Traemos los datos de la base usando el NUEVO método del DAO
        ProductoDAO prodDao = new ProductoDAO();
        List<Producto> lista = prodDao.filtrarCatalogo(filtroBusqueda, categoria);
        
        // 3. Recorremos cada producto de la DB
        for (Producto prod : lista) {
            
            // --- CREACIÓN DE LA TARJETA (JPanel) ---
            JPanel tarjeta = new JPanel(new BorderLayout(5, 5));
            tarjeta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            tarjeta.setBackground(Color.WHITE);
            tarjeta.setPreferredSize(new Dimension(150, 200)); // Tamaño de cada cuadrito
            tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Manita al pasar el mouse

            // --- IMAGEN ---
            JLabel lblImagen = new JLabel("", SwingConstants.CENTER);
            
            // Extraemos la ruta directamente de la base de datos
            String rutaImg = prod.getRutaImagen(); 
            
            // Validación por si la ruta está vacía o el archivo se borró de la carpeta
            if (rutaImg == null || rutaImg.isEmpty() || !new File(rutaImg).exists()) {
                rutaImg = "imagenes_repuestos/default.png"; 
            }
            
            try {
                // Ajustamos la imagen a 100x100
                ImageIcon icono = new ImageIcon(rutaImg);
                Image imgEscalada = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(imgEscalada));
            } catch (Exception e) {
                lblImagen.setText("Sin Imagen");
            }
            tarjeta.add(lblImagen, BorderLayout.NORTH);

            // --- TEXTOS (Nombre, Precio, Stock) ---
            String texto = "<html><center>"
                    + "<b>" + prod.getNombreRepuesto() + "</b><br>"
                    + "<font color='green'>$" + String.format("%.2f", prod.getPrecioVenta()) + "</font><br>"
                    + "<font color='gray'>Stock: " + prod.getStockActual() + "</font>"
                    + "</center></html>";
            
            JLabel lblDatos = new JLabel(texto, SwingConstants.CENTER);
            lblDatos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            tarjeta.add(lblDatos, BorderLayout.CENTER);

            // --- EVENTO DE DOBLE CLIC EN LA TARJETA ---
            tarjeta.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2) { // Doble clic
                        // Guardamos los datos en las variables globales
                        idSeleccionado = prod.getIdProducto();
                        nombreSeleccionado = prod.getNombreRepuesto();
                        precioSeleccionado = prod.getPrecioVenta();
                        stockSeleccionado = prod.getStockActual();
                        
                        // Cerramos el JDialog
                        dispose(); 
                    }
                }
                
                // Efecto hover (cambiar color al pasar el ratón)
                @Override
                public void mouseEntered(MouseEvent e) { tarjeta.setBackground(new Color(240, 248, 255)); }
                @Override
                public void mouseExited(MouseEvent e) { tarjeta.setBackground(Color.WHITE); }
            });

            // Agregamos la tarjeta terminada a la cuadrícula
            panelCuadricula.add(tarjeta);
        }
        
        // 4. Refrescamos la pantalla para que se dibujen los componentes nuevos
        panelCuadricula.revalidate();
        panelCuadricula.repaint();
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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Filtros y Búsqueda");

        jLabel3.setText("Nombre o Codigo:");

        BtnBuscar.setText("Buscar");
        BtnBuscar.addActionListener(this::BtnBuscarActionPerformed);

        jLabel4.setText("Categorias:");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(BtnBuscar)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnBuscar)
                        .addComponent(jLabel4)
                        .addComponent(CbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Catalogo de Productos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(ScrollPaneProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPaneProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(309, 309, 309)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
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
