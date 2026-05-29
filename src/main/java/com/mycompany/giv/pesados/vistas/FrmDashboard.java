/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.giv.pesados.vistas;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author ElRon
 */
public class FrmDashboard extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmDashboard
     */
    public FrmDashboard() {
        initComponents();
        cargarEstadisticas();
    }
    
    /**
     * Captura el contenido visual del Dashboard y lo exporta a un documento PDF.
     */
    private void exportarDashboardAPDF() {
        try {
            // 1. Extraer el contenedor maestro de los graficos desde el ScrollPane
            java.awt.Component componente = ScrollPaneGraficos.getViewport().getView();
            if (componente == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "No hay datos cargados para exportar.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 2. Renderizar el componente visual a una imagen en memoria (BufferedImage)
            java.awt.image.BufferedImage imagenDashboard = new java.awt.image.BufferedImage(
                    componente.getWidth(), componente.getHeight(), java.awt.image.BufferedImage.TYPE_INT_RGB);
            java.awt.Graphics2D g2d = imagenDashboard.createGraphics();
            componente.paint(g2d);
            g2d.dispose();

            // 3. Preparar la estructura de carpetas
            java.io.File directorio = new java.io.File("Comprobantes_GIV/Reportes_Gerenciales/Estadisticas");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // 4. Generar nombre de archivo unico basado en la fecha y hora
            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
            String rutaFinal = directorio.getAbsolutePath() + "/Captura_Estadisticas_" + timestamp + ".pdf";

            // 5. Inicializar el documento PDF en formato A4 Horizontal (Apaisado)
            com.itextpdf.text.Document documento = new com.itextpdf.text.Document(com.itextpdf.text.PageSize.A4.rotate());
            com.itextpdf.text.pdf.PdfWriter.getInstance(documento, new java.io.FileOutputStream(rutaFinal));
            documento.open();

            // --- NUEVO: 6. Agregar Titulo y Fecha al Documento ---
            // Configuracion de fuentes tipograficas
            com.itextpdf.text.Font fuenteTitulo = com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA_BOLD, 18, com.itextpdf.text.BaseColor.BLACK);
            com.itextpdf.text.Font fuenteFecha = com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA, 12, com.itextpdf.text.BaseColor.DARK_GRAY);

            // Insercion del Titulo
            com.itextpdf.text.Paragraph titulo = new com.itextpdf.text.Paragraph("REPORTE GERENCIAL - DASHBOARD ESTADÍSTICO - GIV PESADOS", fuenteTitulo);
            titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            documento.add(titulo);

            // Insercion de la Fecha
            String fechaFormateada = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
            com.itextpdf.text.Paragraph fechaEmision = new com.itextpdf.text.Paragraph("Fecha de impresión: " + fechaFormateada, fuenteFecha);
            fechaEmision.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            fechaEmision.setSpacingAfter(20f); // Se agrega un margen inferior de 20 puntos para separar del grafico
            documento.add(fechaEmision);
            // -----------------------------------------------------

            // 7. Convertir la imagen al formato de iText y escalar preservando proporciones
            com.itextpdf.text.Image pdfImagen = com.itextpdf.text.Image.getInstance(imagenDashboard, null);
            
            // Calculo de margenes (Restamos 110 al alto para dejar espacio suficiente al titulo y la fecha)
            float anchoDisponible = documento.getPageSize().getWidth() - 40;
            float altoDisponible = documento.getPageSize().getHeight() - 110;
            
            pdfImagen.scaleToFit(anchoDisponible, altoDisponible);
            pdfImagen.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

            // 8. Insertar la imagen en el PDF y cerrar flujos
            documento.add(pdfImagen);
            documento.close();

            javax.swing.JOptionPane.showMessageDialog(this, "¡Dashboard exportado con éxito!\nRuta: " + rutaFinal, "Exportación Exitosa", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al generar el PDF:\n" + e.getMessage(), "Error crítico", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarEstadisticas() {
        com.mycompany.giv.pesados.dao.EstadisticasDAO dao = new com.mycompany.giv.pesados.dao.EstadisticasDAO();
        com.mycompany.giv.pesados.config.GeneradorGraficos graficos = new com.mycompany.giv.pesados.config.GeneradorGraficos();

        // 1. Cargar Indicadores Rapidos (KPIs)
        lblIngresosHoy2.setText("$ " + String.format(java.util.Locale.US, "%.2f", dao.obtenerIngresosHoy()));
        lblVentasMes2.setText(String.valueOf(dao.obtenerVentasMesActual()));
        lblCriticos2.setText(String.valueOf(dao.obtenerRepuestosCriticos()));
        lblValorInventario2.setText("$ " + String.format(java.util.Locale.US, "%.2f", dao.obtenerValorTotalInventario()));

        // 2. Crear el contenedor principal que ira dentro del ScrollPane
        javax.swing.JPanel pnlContenedorMaster = new javax.swing.JPanel(new java.awt.BorderLayout(15, 15));
        pnlContenedorMaster.setBackground(java.awt.Color.WHITE);
        pnlContenedorMaster.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 3. Generar y dimensionar el Grafico de Lineas (Parte Superior)
        org.jfree.chart.ChartPanel panelLineas = graficos.crearGraficoLineas(dao.obtenerVentasUltimos7Dias(), "Tendencia de Ingresos (Últimos 7 Días)");
        // Obligamos al grafico a tener una altura minima para que la curva se dibuje correctamente
        panelLineas.setPreferredSize(new java.awt.Dimension(800, 300)); 
        pnlContenedorMaster.add(panelLineas, java.awt.BorderLayout.NORTH);

        // 4. Crear un sub-panel para la cuadricula inferior (3 graficos)
        javax.swing.JPanel pnlCuadriculaInferior = new javax.swing.JPanel(new java.awt.GridLayout(1, 3, 15, 0));
        pnlCuadriculaInferior.setBackground(java.awt.Color.WHITE);
        pnlCuadriculaInferior.setPreferredSize(new java.awt.Dimension(800, 300));

        org.jfree.chart.ChartPanel panelBarras = graficos.crearGraficoBarras(dao.obtenerTop5Repuestos(), "Top 5 Repuestos");
        org.jfree.chart.ChartPanel panelPastel = graficos.crearGraficoPastel(dao.obtenerIngresosPorCategoria(), "Ingresos por Categoría");
        org.jfree.chart.ChartPanel panelAnillo = graficos.crearGraficoAnillo(dao.obtenerIngresosPorMetodoPago(), "Método de Pago");

        pnlCuadriculaInferior.add(panelBarras);
        pnlCuadriculaInferior.add(panelPastel);
        pnlCuadriculaInferior.add(panelAnillo);

        // Ensamblar la cuadricula inferior al master
        pnlContenedorMaster.add(pnlCuadriculaInferior, java.awt.BorderLayout.CENTER);

        // 5. Inyectar todo el bloque en el ScrollPane (Misma tecnica de DlgBuscarProducto)
        ScrollPaneGraficos.setViewportView(pnlContenedorMaster);
        
        // Forzar repintado para asegurar la renderizacion de los datos
        ScrollPaneGraficos.revalidate();
        ScrollPaneGraficos.repaint();
    }
    
    
    /**
     * Invoca el DAO y el Generador de Gráficos para poblar el Dashboard.
     */
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblIngresosHoy2 = new javax.swing.JLabel();
        lblVentasMes2 = new javax.swing.JLabel();
        lblCriticos2 = new javax.swing.JLabel();
        lblValorInventario2 = new javax.swing.JLabel();
        ScrollPaneGraficos = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        lblIngresosHoy = new javax.swing.JLabel();
        lblVentasMes = new javax.swing.JLabel();
        lblCriticos = new javax.swing.JLabel();
        lblValorInventario = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIngresosHoy2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblIngresosHoy2.setForeground(new java.awt.Color(255, 255, 255));
        lblIngresosHoy2.setText("INGRESOS HOY:");
        jPanel1.add(lblIngresosHoy2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 140, -1));

        lblVentasMes2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblVentasMes2.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasMes2.setText("VENTAS DEL MES:");
        jPanel1.add(lblVentasMes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));

        lblCriticos2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCriticos2.setForeground(new java.awt.Color(255, 255, 255));
        lblCriticos2.setText("STOCKS CRITICOS:");
        jPanel1.add(lblCriticos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, -1, -1));

        lblValorInventario2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblValorInventario2.setForeground(new java.awt.Color(255, 255, 255));
        lblValorInventario2.setText("VALOR DE INVENTARIO:");
        jPanel1.add(lblValorInventario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 40, -1, -1));
        jPanel1.add(ScrollPaneGraficos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1310, 660));

        jButton1.setBackground(new java.awt.Color(255, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Imprimir PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblIngresosHoy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblIngresosHoy.setForeground(new java.awt.Color(255, 255, 255));
        lblIngresosHoy.setText("INGRESOS HOY");
        jPanel1.add(lblIngresosHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 140, -1));

        lblVentasMes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblVentasMes.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasMes.setText("VENTAS DEL MES");
        jPanel1.add(lblVentasMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, -1));

        lblCriticos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCriticos.setForeground(new java.awt.Color(255, 255, 255));
        lblCriticos.setText("STOCKS CRITICOS");
        jPanel1.add(lblCriticos, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, -1, -1));

        lblValorInventario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblValorInventario.setForeground(new java.awt.Color(255, 255, 255));
        lblValorInventario.setText("VALOR DE INVENTARIO");
        jPanel1.add(lblValorInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 70, 210, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        exportarDashboardAPDF();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPaneGraficos;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCriticos;
    private javax.swing.JLabel lblCriticos2;
    private javax.swing.JLabel lblIngresosHoy;
    private javax.swing.JLabel lblIngresosHoy2;
    private javax.swing.JLabel lblValorInventario;
    private javax.swing.JLabel lblValorInventario2;
    private javax.swing.JLabel lblVentasMes;
    private javax.swing.JLabel lblVentasMes2;
    // End of variables declaration//GEN-END:variables
}
