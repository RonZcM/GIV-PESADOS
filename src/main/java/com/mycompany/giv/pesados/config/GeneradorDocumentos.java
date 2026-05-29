/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giv.pesados.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.giv.pesados.modelos.DetalleVenta;
import com.mycompany.giv.pesados.modelos.Venta;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class GeneradorDocumentos {

    // ==========================================
    // 0. RUTAS Y CREACIÓN DE CARPETAS
    // ==========================================
    private final String RUTA_BASE = "Comprobantes_GIV";

    private void prepararDirectorios() {
        // Definimos las 4 subcarpetas
        String[] carpetas = {
            RUTA_BASE + "/JSON",
            RUTA_BASE + "/Tickets",
            RUTA_BASE + "/Facturas",
            RUTA_BASE + "/Creditos_Fiscales"
        };
        
        for (String ruta : carpetas) {
            File directorio = new File(ruta);
            if (!directorio.exists()) {
                directorio.mkdirs(); // El mkdirs crea todo el árbol de carpetas de un solo
            }
        }
    }

    // ==========================================
    // 1. GENERACIÓN DE JSON (GSON)
    // ==========================================
    public void exportarVentaJSON(Venta venta, List<DetalleVenta> listaDetalles) {
        try {
            prepararDirectorios(); // Nos aseguramos de que la carpeta exista antes de guardar

            Map<String, Object> estructuraFactura = new HashMap<>();
            estructuraFactura.put("cabecera", venta);
            estructuraFactura.put("repuestos", listaDetalles);

            Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String jsonSalida = gson.toJson(estructuraFactura);

            // Armamos la ruta completa apuntando a la subcarpeta JSON
            String rutaDestino = RUTA_BASE + "/JSON/Comprobante_Venta_" + venta.getIdVenta() + ".json";
            
            try (FileWriter file = new FileWriter(rutaDestino)) {
                file.write(jsonSalida);
                file.flush();
                System.out.println("JSON exportado exitosamente: " + rutaDestino);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el archivo JSON: " + e.getMessage());
        }
    }

    // ==========================================
    // 2. GENERACIÓN DE PDF (JASPER REPORTS)
    // ==========================================
    public void generarComprobantePDF(int idVentaGenerada, String tipoComprobante) {
        Connection con = null;
        String tipoLimpio = tipoComprobante.trim();
        
        prepararDirectorios(); // Preparamos el terreno
        
        String rutaPlantilla = "";
        String subCarpeta = ""; // Variable para saber en cuál de las 3 carpetas lo vamos a tirar
        
        // Asignamos la plantilla correcta y su carpeta de destino
        if (tipoLimpio.equals("Ticket")) {
            rutaPlantilla = "/reportes/Ticket.jasper";
            subCarpeta = "/Tickets/";
        } else if (tipoLimpio.equals("Factura Consumidor Final")) {
            rutaPlantilla = "/reportes/Factura.jasper";
            subCarpeta = "/Facturas/";
        } else {
            rutaPlantilla = "/reportes/CreditoFiscal.jasper";
            subCarpeta = "/Creditos_Fiscales/";
        }

        try (InputStream stream = getClass().getResourceAsStream(rutaPlantilla)) {
            
            if (stream == null) {
                throw new Exception("No se encontró el archivo " + rutaPlantilla + " en la carpeta de recursos. Revisa que hayas pegado el .jasper correcto.");
            }

            con = Conexion.getInstancia().conectar();
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("param_id_venta", idVentaGenerada);
            
            // Si tenés el logo activo, dejale estas líneas, si no las tenés, borralas:
            // InputStream logoStream = getClass().getResourceAsStream("/img/logo.png");
            // parametros.put("param_logo", logoStream);

            JasperPrint impresion = JasperFillManager.fillReport(stream, parametros, con);
            
            // Armamos la ruta final: Comprobantes_GIV / Tickets / Ticket_8.pdf
            String nombreArchivo = tipoLimpio.replace(" ", "_") + "_" + idVentaGenerada + ".pdf";
            String rutaDestinoPDF = RUTA_BASE + subCarpeta + nombreArchivo;
            
            JasperExportManager.exportReportToPdfFile(impresion, rutaDestinoPDF);
            
            JOptionPane.showMessageDialog(null, "¡Comprobante PDF generado con éxito en " + subCarpeta + "!\nArchivo: " + nombreArchivo);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en JasperReports al generar PDF:\n" + e.getMessage(), "Error de Reporte", JOptionPane.ERROR_MESSAGE);
        }
    }
}