/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giv.pesados.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class GeneradorReportes {

    private final String RUTA_BASE = "Comprobantes_GIV/Reportes_Gerenciales";

    /**
     * Prepara la estructura de directorios para organizar los reportes generados.
     */
    private void prepararDirectorios() {
        String[] carpetas = {
            RUTA_BASE + "/Excel",
            RUTA_BASE + "/PDF"
        };
        
        for (String ruta : carpetas) {
            File directorio = new File(ruta);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
        }
    }

    /**
     * Genera un timestamp actual para nombrar los archivos sin sobreescribirlos.
     */
    private String obtenerFechaHoraActual() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }

    /**
     * Exporta los datos visuales de un JTable a un archivo Excel (.xlsx) usando Apache POI.
     */
    public void exportarTablaAExcel(JTable tabla, String nombreReporte) {
        prepararDirectorios();
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Datos de Reporte");
            TableModel modelo = tabla.getModel();

            // Estilo para la cabecera (Negrita y fondo azul claro)
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            // 1. Crear la fila de cabeceras
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < modelo.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(modelo.getColumnName(i));
                cell.setCellStyle(headerStyle);
            }

            // 2. Volcar los datos fila por fila
            for (int i = 0; i < modelo.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < modelo.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    Object valor = modelo.getValueAt(i, j);
                    if (valor != null) {
                        // Intentar parsear a numero para que Excel lo reconozca como tal
                        try {
                            double num = Double.parseDouble(valor.toString());
                            cell.setCellValue(num);
                        } catch (NumberFormatException e) {
                            cell.setCellValue(valor.toString());
                        }
                    } else {
                        cell.setCellValue("");
                    }
                }
            }

            // 3. Ajustar el tamano de las columnas automaticamente
            for (int i = 0; i < modelo.getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }

            // 4. Guardar el archivo
            String nombreArchivo = nombreReporte.replace(" ", "_") + "_" + obtenerFechaHoraActual() + ".xlsx";
            String rutaCompleta = RUTA_BASE + "/Excel/" + nombreArchivo;
            
            try (FileOutputStream fileOut = new FileOutputStream(rutaCompleta)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(null, "¡Reporte Excel generado con éxito!\nRuta: " + rutaCompleta);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el archivo Excel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Genera un reporte PDF utilizando plantillas predefinidas de JasperReports.
     */
    public void exportarReportePDF(int tipoReporte, String nombreReporte, String paramFechaInicio, String paramFechaFin) {
        prepararDirectorios();
        Connection con = null;
        
        // Mapeo de la plantilla segun el indice seleccionado en el ComboBox
        String rutaPlantilla = "";
        switch (tipoReporte) {
            case 0: rutaPlantilla = "/reportes/Rep_Vendedores.jasper"; break;
            case 1: rutaPlantilla = "/reportes/Rep_TopRepuestos.jasper"; break;
            case 2: rutaPlantilla = "/reportes/Rep_InventarioCritico.jasper"; break;
            case 3: rutaPlantilla = "/reportes/Rep_IngresosFechas.jasper"; break;
            case 4: rutaPlantilla = "/reportes/Rep_RankingClientes.jasper"; break;
            case 5: rutaPlantilla = "/reportes/Rep_MovimientoCategorias.jasper"; break;
            default: return;
        }

        try (InputStream stream = getClass().getResourceAsStream(rutaPlantilla)) {
            if (stream == null) {
                throw new Exception("No se encontró la plantilla Jasper para este reporte (" + rutaPlantilla + "). Asegúrese de haberla diseñado y compilado.");
            }

            con = Conexion.getInstancia().conectar();
            Map<String, Object> parametros = new HashMap<>();
            
            // Inyectar parametros de fecha solo si es el reporte que lo requiere (Indice 3)
            if (tipoReporte == 3) {
                parametros.put("param_fecha_inicio", paramFechaInicio);
                parametros.put("param_fecha_fin", paramFechaFin);
            }

            // --- NUEVO: Cargar el logo desde los recursos y enviarlo como parametro ---
            InputStream logoStream = getClass().getResourceAsStream("/img/logo.png");
            if (logoStream != null) {
                parametros.put("param_logo", logoStream);
            } else {
                System.err.println("Advertencia: No se encontro el logo en la ruta /img/logo.png");
            }
            // --------------------------------------------------------------------------

            JasperPrint impresion = JasperFillManager.fillReport(stream, parametros, con);
            
            String nombreArchivo = nombreReporte.replace(" ", "_") + "_" + obtenerFechaHoraActual() + ".pdf";
            String rutaDestinoPDF = RUTA_BASE + "/PDF/" + nombreArchivo;
            
            JasperExportManager.exportReportToPdfFile(impresion, rutaDestinoPDF);
            
            JOptionPane.showMessageDialog(null, "¡Reporte PDF generado con éxito!\nRuta: " + rutaDestinoPDF);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF (JasperReports):\n" + e.getMessage(), "Error de Reporte", JOptionPane.ERROR_MESSAGE);
        }
    }
}
