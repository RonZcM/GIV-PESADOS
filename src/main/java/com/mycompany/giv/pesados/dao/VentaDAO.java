/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giv.pesados.dao;

import com.mycompany.giv.pesados.config.Conexion;
import com.mycompany.giv.pesados.modelos.DetalleVenta;
import com.mycompany.giv.pesados.modelos.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class VentaDAO {

    /**
     * Registra la cabecera, los detalles y descuenta el stock en una sola transacción.
     * Retorna el ID de la venta generada para usarlo luego en el PDF y el JSON.
     */
    public int registrarVenta(Venta venta, List<DetalleVenta> listaDetalles) {
        int idVentaGenerada = 0;
        Connection con = null;
        PreparedStatement psVenta = null;
        PreparedStatement psDetalle = null;
        PreparedStatement psActualizarStock = null;
        ResultSet rs = null;

        // 1. Sentencias SQL preparadas
        String sqlVenta = "INSERT INTO VENTAS (fecha_venta, total_pagar, tipo_comprobante, metodo_pago, estado, id_cliente, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO DETALLE_VENTAS (id_venta, id_producto, cantidad, descuento, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlStock = "UPDATE PRODUCTOS SET stock_actual = stock_actual - ? WHERE id_producto = ?";

        try {
            // Obtenemos la conexión del Singleton
            con = Conexion.getInstancia().conectar();
            
            // ¡CRUCIAL PARA EL ROL 4! Desactivar el autocommit para iniciar la transacción manual
            con.setAutoCommit(false);

            // 2. Inserción de la cabecera (Tabla VENTAS) pidiendo que nos devuelva el ID generado
            psVenta = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            psVenta.setTimestamp(1, venta.getFechaVenta());
            psVenta.setDouble(2, venta.getTotalPagar());
            psVenta.setString(3, venta.getTipoComprobante());
            psVenta.setString(4, venta.getMetodoPago());
            psVenta.setInt(5, venta.getEstado());
            psVenta.setInt(6, venta.getIdCliente());
            psVenta.setInt(7, venta.getIdUsuario()); // Vinculando el usuario en sesión
            
            int filasAfectadas = psVenta.executeUpdate();
            
            // Capturamos el ID autoincrementable que MySQL le asignó a esta venta
            if (filasAfectadas > 0) {
                rs = psVenta.getGeneratedKeys();
                if (rs.next()) {
                    idVentaGenerada = rs.getInt(1);
                }
            }

            // Si se generó el ID correctamente, procedemos a insertar el carrito
            if (idVentaGenerada > 0) {
                psDetalle = con.prepareStatement(sqlDetalle);
                psActualizarStock = con.prepareStatement(sqlStock);

                // 3. Recorrido de los productos comprados
                for (DetalleVenta detalle : listaDetalles) {
                    
                    // a) Insertar en DETALLE_VENTAS
                    psDetalle.setInt(1, idVentaGenerada); // Llave foránea de la venta principal
                    psDetalle.setInt(2, detalle.getIdProducto());
                    psDetalle.setInt(3, detalle.getCantidad());
                    psDetalle.setDouble(4, detalle.getDescuento());
                    psDetalle.setDouble(5, detalle.getPrecioUnitario());
                    psDetalle.setDouble(6, detalle.getSubtotal());
                    psDetalle.executeUpdate();

                    // b) Ejecución del UPDATE para restar stock en PRODUCTOS
                    psActualizarStock.setInt(1, detalle.getCantidad()); // Lo que se lleva
                    psActualizarStock.setInt(2, detalle.getIdProducto()); // A qué producto se lo restamos
                    psActualizarStock.executeUpdate();
                }

                // 4. Si el ciclo termina sin explotar, CONFIRMAMOS LA TRANSACCIÓN
                con.commit();
                System.out.println("Transacción Exitosa: Venta y Detalles guardados.");
                
            } else {
                throw new SQLException("Fallo al insertar la cabecera, no se generó ID.");
            }

        } catch (SQLException e) {
            try {
                if (con != null) {
                    // ¡EL SALVAVIDAS! Si cualquier INSERT o UPDATE falla, deshacemos todo
                    con.rollback();
                    System.out.println("ROLLBACK EJECUTADO: " + e.getMessage());
                }
            } catch (SQLException ex) {
                System.out.println("Error fatal al hacer rollback: " + ex.getMessage());
            }
            idVentaGenerada = 0; // Indicamos a la vista que falló
        } finally {
            try {
                // Restauramos el comportamiento normal de la base de datos
                if (con != null) {
                    con.setAutoCommit(true);
                }
            } catch (SQLException e) {
                System.out.println("Error al restaurar autocommit: " + e.getMessage());
            }
        }

        return idVentaGenerada; 
    }
}
