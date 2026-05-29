/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giv.pesados.dao;

import com.mycompany.giv.pesados.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class EstadisticasDAO {

    // ========================================================
    // 1. METODOS PARA TARJETAS KPI (INDICADORES RAPIDOS)
    // ========================================================

    public double obtenerIngresosHoy() {
        double total = 0;
        String sql = "SELECT IFNULL(SUM(total_pagar), 0) AS ingresos FROM VENTAS WHERE DATE(fecha_venta) = CURDATE() AND estado = 1";
        // La conexion se declara fuera del try-with-resources para evitar que se cierre el Singleton
        Connection con = Conexion.getInstancia().conectar();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble("ingresos");
            }
        } catch (Exception e) {
            System.err.println("Error en obtenerIngresosHoy: " + e.getMessage());
        }
        return total;
    }

    public int obtenerVentasMesActual() {
        int cantidad = 0;
        String sql = "SELECT COUNT(id_venta) AS conteo FROM VENTAS WHERE MONTH(fecha_venta) = MONTH(CURDATE()) AND YEAR(fecha_venta) = YEAR(CURDATE()) AND estado = 1";
        Connection con = Conexion.getInstancia().conectar();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                cantidad = rs.getInt("conteo");
            }
        } catch (Exception e) {
            System.err.println("Error en obtenerVentasMesActual: " + e.getMessage());
        }
        return cantidad;
    }

    public int obtenerRepuestosCriticos() {
        int criticos = 0;
        String sql = "SELECT COUNT(id_producto) AS criticos FROM PRODUCTOS WHERE stock_actual <= stock_minimo AND estado = 1";
        Connection con = Conexion.getInstancia().conectar();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                criticos = rs.getInt("criticos");
            }
        } catch (Exception e) {
            System.err.println("Error en obtenerRepuestosCriticos: " + e.getMessage());
        }
        return criticos;
    }

    public double obtenerValorTotalInventario() {
        double valor = 0;
        String sql = "SELECT IFNULL(SUM(stock_actual * precio_venta), 0) AS valor_total FROM PRODUCTOS WHERE estado = 1";
        Connection con = Conexion.getInstancia().conectar();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                valor = rs.getDouble("valor_total");
            }
        } catch (Exception e) {
            System.err.println("Error en obtenerValorTotalInventario: " + e.getMessage());
        }
        return valor;
    }

    // ========================================================
    // 2. METODOS PARA ALIMENTAR GRAFICOS (JFREECHART)
    // ========================================================

    public Map<String, Double> obtenerVentasUltimos7Dias() {
        Map<String, Double> datos = new LinkedHashMap<>();
        String sql = "SELECT DATE_FORMAT(fecha_venta, '%d/%m') AS dia, SUM(total_pagar) AS total " +
                     "FROM VENTAS WHERE fecha_venta >= DATE_SUB(CURDATE(), INTERVAL 6 DAY) AND estado = 1 " +
                     "GROUP BY DATE(fecha_venta) ORDER BY DATE(fecha_venta) ASC";
        Connection con = Conexion.getInstancia().conectar();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                datos.put(rs.getString("dia"), rs.getDouble("total"));
            }
        } catch (Exception e) {
            System.err.println("Error en obtenerVentasUltimos7Dias: " + e.getMessage());
        }
        return datos;
    }

    public Map<String, Double> obtenerTop5Repuestos() {
        Map<String, Double> datos = new LinkedHashMap<>();
        String sql = "SELECT p.nombre_repuesto, SUM(d.cantidad) AS cantidad_vendida " +
                     "FROM PRODUCTOS p INNER JOIN DETALLE_VENTAS d ON p.id_producto = d.id_producto " +
                     "INNER JOIN VENTAS v ON d.id_venta = v.id_venta WHERE v.estado = 1 " +
                     "GROUP BY p.id_producto ORDER BY cantidad_vendida DESC LIMIT 5";
        Connection con = Conexion.getInstancia().conectar();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String nombre = rs.getString("nombre_repuesto");
                if (nombre.length() > 15) nombre = nombre.substring(0, 15) + "...";
                datos.put(nombre, rs.getDouble("cantidad_vendida"));
            }
        } catch (Exception e) {
            System.err.println("Error en obtenerTop5Repuestos: " + e.getMessage());
        }
        return datos;
    }

    public Map<String, Double> obtenerIngresosPorCategoria() {
        Map<String, Double> datos = new LinkedHashMap<>();
        String sql = "SELECT cat.nombre_categoria, SUM(d.subtotal) AS ingresos " +
                     "FROM CATEGORIAS cat INNER JOIN CATEGORIA_PRODUCTO cp ON cat.id_categoria = cp.id_categoria " +
                     "INNER JOIN PRODUCTOS p ON cp.id_producto = p.id_producto " +
                     "INNER JOIN DETALLE_VENTAS d ON p.id_producto = d.id_producto " +
                     "INNER JOIN VENTAS v ON d.id_venta = v.id_venta WHERE v.estado = 1 " +
                     "GROUP BY cat.id_categoria ORDER BY ingresos DESC";
        Connection con = Conexion.getInstancia().conectar();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                datos.put(rs.getString("nombre_categoria"), rs.getDouble("ingresos"));
            }
        } catch (Exception e) {
            System.err.println("Error en obtenerIngresosPorCategoria: " + e.getMessage());
        }
        return datos;
    }

    public Map<String, Double> obtenerIngresosPorMetodoPago() {
        Map<String, Double> datos = new LinkedHashMap<>();
        String sql = "SELECT metodo_pago, SUM(total_pagar) AS total FROM VENTAS WHERE estado = 1 GROUP BY metodo_pago";
        Connection con = Conexion.getInstancia().conectar();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                datos.put(rs.getString("metodo_pago"), rs.getDouble("total"));
            }
        } catch (Exception e) {
            System.err.println("Error en obtenerIngresosPorMetodoPago: " + e.getMessage());
        }
        return datos;
    }
}