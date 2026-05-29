/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.giv.pesados.vistas;

import com.mycompany.giv.pesados.vistas.FrmUsuarios;
import com.mycompany.giv.pesados.vistas.FrmCategorias;
import com.mycompany.giv.pesados.vistas.FrmProductos;
import com.mycompany.giv.pesados.vistas.FrmClientes;


/**
 *
 * @author willi
 */
public class MdiPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MdiPrincipal.class.getName());

    /**
     * Creates new form MdiPrincipal
     */
// Variable global para guardar el rol del usuario que entró
    private int rolUsuario;

    
    
    
    
    
    
    // Modificamos el constructor para que exija el rol al abrirse
    public MdiPrincipal(int rol) {
        initComponents();
        

        jDesktopPane1.setLayout(null);

        
        this.rolUsuario = rol;
        
        
        // === MEJORAS VISUALES EXTRA (ÍCONO Y MENÚS) ===
        
        // 1. Quitar el pingüino y poner el logo oficial en la esquina de la ventana
        try {
            java.net.URL urlIcono = getClass().getResource("/GIV-PESADOS.png");
            if (urlIcono != null) {
                this.setIconImage(new javax.swing.ImageIcon(urlIcono).getImage());
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar el ícono de la ventana.");
        }

        // 2. Pintar la barra de menú (Fondo Blanco Humo)
        jMenuBar1.setBackground(new java.awt.Color(211, 211, 211));
        jMenuBar1.setOpaque(true); 
        
        // 3. Pintar las letras de los menús (Azul Marino)
        java.awt.Color colorLetraMenu = new java.awt.Color(0, 52, 89);
        jMenu1.setForeground(colorLetraMenu); // Mantenimiento
        jMenu3.setForeground(colorLetraMenu); // Sistema
        jMenu4.setForeground(colorLetraMenu); // Administracion
        jMenu5.setForeground(colorLetraMenu); // Comercial
        // ==============================================
        
        
        // Esta línea hace que el MDI se abra en pantalla completa automáticamente
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); 
        
        // Bloqueo de seguridad: Si es vendedor (Rol 2), ocultamos el menú Mantenimiento
        if (this.rolUsuario == 2) { 
            jMenu1.setVisible(false);
            jMenu4.setVisible(false);
        }
        
        
        // ==========================================================
        // MEJORA UI 1: FONDO DE MARCA DE AGUA CENTRADO DINÁMICAMENTE
        // ==========================================================
        // 1. Removemos el DesktopPane original que generó NetBeans
        this.remove(jDesktopPane1);
        
        // 2. Creamos uno nuevo sobreescribiendo su método de pintura
        jDesktopPane1 = new javax.swing.JDesktopPane() {
            java.awt.Image logoAgua = new javax.swing.ImageIcon(getClass().getResource("/GIV-PESADOS.png")).getImage();
            
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                // Pintamos el fondo azul marino oscuro
                g.setColor(new java.awt.Color(0, 52, 89));
                g.fillRect(0, 0, getWidth(), getHeight());
                
                // Dibujamos el logo exactamente en el centro
                if (logoAgua != null) {
                    int x = (getWidth() - logoAgua.getWidth(null)) / 2;
                    int y = (getHeight() - logoAgua.getHeight(null)) / 2;
                    g.drawImage(logoAgua, x, y, this);
                }
            }
        };
        // 3. Lo volvemos a agregar al centro de la ventana
        this.add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        // ==========================================================
        // MEJORA UI 2: BARRA DE ESTADO INFERIOR CON RELOJ EN VIVO
        // ==========================================================
        javax.swing.JPanel pnlStatusBar = new javax.swing.JPanel(new java.awt.BorderLayout());
        pnlStatusBar.setBackground(new java.awt.Color(230, 230, 230)); // Gris claro corporativo
        pnlStatusBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 10, 3, 10));

        // Etiqueta Izquierda: Info del Sistema y Usuario
        String nombreRol = (this.rolUsuario == 1) ? "Administrador" : "Vendedor";
        javax.swing.JLabel lblInfo = new javax.swing.JLabel("GIV-PESADOS V1.0  |  Usuario Activo: " + nombreRol);
        lblInfo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        lblInfo.setForeground(new java.awt.Color(100, 100, 100));

        // Etiqueta Derecha: Reloj en vivo
        javax.swing.JLabel lblReloj = new javax.swing.JLabel();
        lblReloj.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        lblReloj.setForeground(new java.awt.Color(0, 52, 89));

        // Timer para actualizar el reloj cada segundo
        new javax.swing.Timer(1000, e -> {
            lblReloj.setText(new java.text.SimpleDateFormat("dd/MM/yyyy  hh:mm:ss a").format(new java.util.Date()));
        }).start();

        pnlStatusBar.add(lblInfo, java.awt.BorderLayout.WEST);
        pnlStatusBar.add(lblReloj, java.awt.BorderLayout.EAST);

        // Agregamos la barra a la parte inferior (SOUTH) de la ventana principal
        this.add(pnlStatusBar, java.awt.BorderLayout.SOUTH);
        
        
        // ==========================================================
        // MEJORA UI 3: ÍCONOS DINÁMICOS EN LOS MENÚS
        // ==========================================================
        
        // --- Menú Sistema ---
        aplicarIconoAMenu(jMenuItem6, "/img/salir.png"); // Cerrar Sesión

        // --- Menú Comercial ---
        aplicarIconoAMenu(jMenuItem9, "/img/clientes.png"); // Clientes
        aplicarIconoAMenu(jMenuItem10, "/img/ventas.png");  // Ventas

        // --- Menú Mantenimiento ---
        aplicarIconoAMenu(jMenuItem5, "/img/usuarios.png"); // Usuarios

        // --- Menú Administración ---
        aplicarIconoAMenu(jMenuItem7, "/img/categorias.png");   // Categorías
        aplicarIconoAMenu(jMenuItem8, "/img/productos.png");    // Productos
        aplicarIconoAMenu(jMenuItem12, "/img/estadisticas.png");// Estadísticas
        aplicarIconoAMenu(jMenuItem11, "/img/reportes.png");    // Reportes
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        lblFondoLogo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(0, 52, 89));

        jDesktopPane1.setLayer(lblFondoLogo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(lblFondoLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblFondoLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        jMenu3.setText("Sistema");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem6.setText("Cerrar Sesión");
        jMenuItem6.addActionListener(this::jMenuItem6ActionPerformed);
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Comercial");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem9.setText("Clientes");
        jMenuItem9.addActionListener(this::jMenuItem9ActionPerformed);
        jMenu5.add(jMenuItem9);

        jMenuItem10.setText("Ventas");
        jMenuItem10.addActionListener(this::jMenuItem10ActionPerformed);
        jMenu5.add(jMenuItem10);

        jMenuBar1.add(jMenu5);

        jMenu1.setText("Mantenimiento");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem5.setText("Usuarios");
        jMenuItem5.addActionListener(this::jMenuItem5ActionPerformed);
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Administración");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem7.setText("Categorías");
        jMenuItem7.addActionListener(this::jMenuItem7ActionPerformed);
        jMenu4.add(jMenuItem7);

        jMenuItem8.setText("Productos");
        jMenuItem8.addActionListener(this::jMenuItem8ActionPerformed);
        jMenu4.add(jMenuItem8);

        jMenuItem12.setText("Estadísticas");
        jMenuItem12.addActionListener(this::jMenuItem12ActionPerformed);
        jMenu4.add(jMenuItem12);

        jMenuItem11.setText("Reportes");
        jMenuItem11.addActionListener(this::jMenuItem11ActionPerformed);
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
// 1. Crear una instancia de tu pantalla de usuarios
        FrmUsuarios crudUsuarios = new FrmUsuarios();
        
        // 2. Agregarla al fondo gris gigante (JDesktopPane)
        jDesktopPane1.add(crudUsuarios);
        
        
        
        // Forzar el tamaño de la ventana (Ancho, Alto) para que no corte la tabla
        crudUsuarios.setSize(950, 650);
        
        
        // 4. Mostrarla
        crudUsuarios.setVisible(true);    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed


// 1. Cierra y destruye la ventana actual (MDI)
        this.dispose();
        
        // 2. Abre nuevamente la pantalla de Login
        new FrmLogin().setVisible(true);



        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        FrmCategorias categorias = new FrmCategorias();
        categorias.setVisible(true);
        jDesktopPane1.add(categorias);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        FrmProductos p = new FrmProductos();
        p.setVisible(true);
        jDesktopPane1.add(p);
        
        
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        FrmClientes clientes = new FrmClientes(this.rolUsuario);
        clientes.setVisible(true);
        jDesktopPane1.add(clientes);
                                      
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        FrmVentas ventas = new FrmVentas(this.rolUsuario);
        ventas.setVisible(true);
        jDesktopPane1.add(ventas);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        FrmReportes reportes = new FrmReportes();
        reportes.setVisible(true);
        jDesktopPane1.add(reportes);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        
        FrmDashboard estadisticas = new FrmDashboard();
        estadisticas.setVisible(true);
        jDesktopPane1.add(estadisticas);
        
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    /**
     * @param args the command line arguments
     */

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JLabel lblFondoLogo;
    // End of variables declaration//GEN-END:variables


    /**
     * Aplica un icono redimensionado a un elemento del menu (JMenuItem o JMenu).
     * * @param menu El componente de menu al que se le aplicara el icono.
     * @param ruta La ruta interna del recurso dentro de la carpeta /img/
     */
    private void aplicarIconoAMenu(javax.swing.JMenuItem menu, String ruta) {
        try {
            java.net.URL url = getClass().getResource(ruta);
            if (url != null) {
                javax.swing.ImageIcon iconoOriginal = new javax.swing.ImageIcon(url);
                // Se escala a 20x20 pixeles para una optima visualizacion corporativa
                java.awt.Image imgEscalada = iconoOriginal.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
                menu.setIcon(new javax.swing.ImageIcon(imgEscalada));
            } else {
                System.out.println("Advertencia de Interfaz: No se encontró el recurso gráfico en " + ruta);
            }
        } catch (Exception e) {
            System.err.println("Error al aplicar icono en el menu: " + e.getMessage());
        }
    }
    
    

}
