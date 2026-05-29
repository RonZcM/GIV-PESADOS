/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giv.pesados.dao;

import com.mycompany.giv.pesados.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {

    /**
     * Metodo centralizado para ejecutar las consultas de los reportes gerenciales.
     * Retorna una lista de arreglos genericos adaptable a cualquier estructura de tabla.
     */
    public List<Object[]> generarReporte(int tipoReporte, String fechaInicio, String fechaFin) {
        List<Object[]> listaDatos = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";

        try {
            con = Conexion.getInstancia().conectar();

            switch (tipoReporte) {
                case 0: // Rendimiento de Vendedores
                    sql = "SELECT u.nombre, COUNT(v.id_venta), SUM(v.total_pagar) " +
                          "FROM USUARIOS u INNER JOIN VENTAS v ON u.id_usuario = v.id_usuario " +
                          "GROUP BY u.id_usuario ORDER BY SUM(v.total_pagar) DESC";
                    ps = con.prepareStatement(sql);
                    break;
                case 1: // Top Repuestos
                    sql = "SELECT p.nombre_repuesto, p.marca, SUM(d.cantidad), SUM(d.subtotal) " +
                          "FROM PRODUCTOS p INNER JOIN DETALLE_VENTAS d ON p.id_producto = d.id_producto " +
                          "GROUP BY p.id_producto ORDER BY SUM(d.cantidad) DESC LIMIT 10";
                    ps = con.prepareStatement(sql);
                    break;
                case 2: // Inventario Critico
                    sql = "SELECT nombre_repuesto, marca, stock_actual, stock_minimo, (stock_minimo - stock_actual) AS deficit " +
                          "FROM PRODUCTOS WHERE stock_actual <= stock_minimo AND estado = 1 ORDER BY deficit DESC";
                    ps = con.prepareStatement(sql);
                    break;
                case 3: // Ingresos por Fechas
                    sql = "SELECT DATE(fecha_venta), COUNT(id_venta), SUM(total_pagar) " +
                          "FROM VENTAS WHERE DATE(fecha_venta) BETWEEN ? AND ? " +
                          "GROUP BY DATE(fecha_venta) ORDER BY DATE(fecha_venta) ASC";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, fechaInicio);
                    ps.setString(2, fechaFin);
                    break;
                case 4: // Ranking Clientes
                    sql = "SELECT c.nombre_cliente, c.tipo_cliente, COUNT(v.id_venta), SUM(v.total_pagar) " +
                          "FROM CLIENTES c INNER JOIN VENTAS v ON c.id_cliente = v.id_cliente " +
                          "GROUP BY c.id_cliente ORDER BY SUM(v.total_pagar) DESC LIMIT 10";
                    ps = con.prepareStatement(sql);
                    break;
                case 5: // Movimiento por Categorias
                    sql = "SELECT cat.nombre_categoria, SUM(d.cantidad), SUM(d.subtotal) " +
                          "FROM CATEGORIAS cat INNER JOIN CATEGORIA_PRODUCTO cp ON cat.id_categoria = cp.id_categoria " +
                          "INNER JOIN PRODUCTOS p ON cp.id_producto = p.id_producto " +
                          "INNER JOIN DETALLE_VENTAS d ON p.id_producto = d.id_producto " +
                          "GROUP BY cat.id_categoria ORDER BY SUM(d.subtotal) DESC";
                    ps = con.prepareStatement(sql);
                    break;
            }

            if (ps != null) {
                rs = ps.executeQuery();
                // Obtener el numero de columnas dinamicamente de la consulta
                int columnCount = rs.getMetaData().getColumnCount();

                while (rs.next()) {
                    Object[] fila = new Object[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        // El ResultSet en Java maneja indices desde 1
                        fila[i] = rs.getObject(i + 1); 
                    }
                    listaDatos.add(fila);
                }
            }

        } catch (Exception e) {
            System.err.println("Error al consultar la base de datos para el reporte: " + e.getMessage());
        } 
        return listaDatos;
    }
}