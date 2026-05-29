/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giv.pesados.config;

import java.awt.Color;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class GeneradorGraficos {

    /**
     * Crea un gráfico de líneas para mostrar tendencias a lo largo del tiempo.
     */
    public ChartPanel crearGraficoLineas(Map<String, Double> datos, String titulo) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : datos.entrySet()) {
            dataset.addValue(entry.getValue(), "Ingresos", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                titulo,
                "Fecha",
                "Ingresos ($)",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        return new ChartPanel(chart);
    }

    /**
     * Crea un gráfico de barras horizontales o verticales para comparativas.
     */
    public ChartPanel crearGraficoBarras(Map<String, Double> datos, String titulo) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : datos.entrySet()) {
            dataset.addValue(entry.getValue(), "Unidades Vendidas", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                titulo,
                "Repuesto",
                "Cantidad",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        return new ChartPanel(chart);
    }

    /**
     * Crea un gráfico de pastel tradicional.
     */
    public ChartPanel crearGraficoPastel(Map<String, Double> datos, String titulo) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Double> entry : datos.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                titulo,
                dataset,
                true, // Mostrar leyenda
                true, // Tooltips
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);

        return new ChartPanel(chart);
    }

    /**
     * Crea un gráfico de anillo (donut chart) para distribución porcentual.
     */
    public ChartPanel crearGraficoAnillo(Map<String, Double> datos, String titulo) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Double> entry : datos.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createRingChart(
                titulo,
                dataset,
                true,
                true,
                false
        );

        RingPlot plot = (RingPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);

        return new ChartPanel(chart);
    }
}
