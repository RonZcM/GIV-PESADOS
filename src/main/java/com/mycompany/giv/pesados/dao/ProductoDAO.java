package com.mycompany.giv.pesados.dao;

import com.mycompany.giv.pesados.config.Conexion;
import com.mycompany.giv.pesados.modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // 1. MÉTODO PARA REGISTRAR CON TRANSACCIÓN
    public boolean registrarProductoConCategorias(Producto producto, List<Integer> idCategorias) {
        String sqlProducto = "INSERT INTO PRODUCTOS (nombre_repuesto, num_serie, marca, descripcion, precio_venta, stock_actual, stock_minimo, ruta_imagen, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
        String sqlIntermedia = "INSERT INTO CATEGORIA_PRODUCTO (id_producto, id_categoria) VALUES (?, ?)";
        
        Connection con = null;
        try {
            con = Conexion.getInstancia().conectar();
            con.setAutoCommit(false);

            PreparedStatement pstProd = con.prepareStatement(sqlProducto, Statement.RETURN_GENERATED_KEYS);
            pstProd.setString(1, producto.getNombreRepuesto());
            pstProd.setString(2, producto.getNumSerie());
            pstProd.setString(3, producto.getMarca());
            pstProd.setString(4, producto.getDescripcion());
            pstProd.setFloat(5, producto.getPrecioVenta());
            pstProd.setInt(6, producto.getStockActual());
            pstProd.setInt(7, producto.getStockMinimo());
            pstProd.setString(8, producto.getRutaImagen());
            pstProd.executeUpdate();

            ResultSet rs = pstProd.getGeneratedKeys();
            int idProductoGenerado = 0;
            if (rs.next()) {
                idProductoGenerado = rs.getInt(1);
            }

            if (idProductoGenerado > 0 && idCategorias != null && !idCategorias.isEmpty()) {
                PreparedStatement pstInter = con.prepareStatement(sqlIntermedia);
                for (int idCat : idCategorias) {
                    pstInter.setInt(1, idProductoGenerado);
                    pstInter.setInt(2, idCat);
                    pstInter.addBatch();
                }
                pstInter.executeBatch();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: " + ex.getMessage());
            }
            System.err.println("Error en transacción de producto: " + e.getMessage());
            return false;
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // 2. MÉTODO PARA LEER (SELECT) - ¡Corregido!
    public List<Producto> listarProductosActivos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTOS WHERE estado = 1";
        
        // ¡LA CONEXIÓN SE SACA DEL TRY PARA QUE JAVA NO LA CIERRE!
        Connection con = Conexion.getInstancia().conectar();
        
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombreRepuesto(rs.getString("nombre_repuesto"));
                p.setNumSerie(rs.getString("num_serie"));
                p.setMarca(rs.getString("marca"));
                p.setPrecioVenta(rs.getFloat("precio_venta"));
                p.setStockActual(rs.getInt("stock_actual"));
                p.setStockMinimo(rs.getInt("stock_minimo"));
                p.setEstado(rs.getInt("estado"));
                p.setDescripcion(rs.getString("descripcion")); 
                p.setRutaImagen(rs.getString("ruta_imagen"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
        return lista;
    }

    // 3. MÉTODO PARA ACTUALIZAR (UPDATE) CON TRANSACCIÓN
    public boolean actualizarProductoConCategorias(Producto producto, List<Integer> idCategorias) {
        String sqlProducto = "UPDATE PRODUCTOS SET nombre_repuesto=?, num_serie=?, marca=?, descripcion=?, precio_venta=?, stock_actual=?, stock_minimo=?, ruta_imagen=? WHERE id_producto=?";
        String sqlDeleteCategorias = "DELETE FROM CATEGORIA_PRODUCTO WHERE id_producto = ?";
        String sqlInsertCategorias = "INSERT INTO CATEGORIA_PRODUCTO (id_producto, id_categoria) VALUES (?, ?)";
        
        Connection con = null;
        try {
            con = Conexion.getInstancia().conectar();
            con.setAutoCommit(false); // Iniciamos transacción

            // 1. Actualizar datos base del producto
            PreparedStatement pstProd = con.prepareStatement(sqlProducto);
            pstProd.setString(1, producto.getNombreRepuesto());
            pstProd.setString(2, producto.getNumSerie());
            pstProd.setString(3, producto.getMarca());
            pstProd.setString(4, producto.getDescripcion());
            pstProd.setFloat(5, producto.getPrecioVenta());
            pstProd.setInt(6, producto.getStockActual());
            pstProd.setInt(7, producto.getStockMinimo());
            pstProd.setString(8, producto.getRutaImagen());
            pstProd.setInt(9, producto.getIdProducto());
            pstProd.executeUpdate();

            // 2. Limpiar las categorías viejas asociadas a este producto
            PreparedStatement pstDel = con.prepareStatement(sqlDeleteCategorias);
            pstDel.setInt(1, producto.getIdProducto());
            pstDel.executeUpdate();

            // 3. Insertar las nuevas categorías seleccionadas
            if (idCategorias != null && !idCategorias.isEmpty()) {
                PreparedStatement pstInter = con.prepareStatement(sqlInsertCategorias);
                for (int idCat : idCategorias) {
                    pstInter.setInt(1, producto.getIdProducto());
                    pstInter.setInt(2, idCat);
                    pstInter.addBatch();
                }
                pstInter.executeBatch();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: " + ex.getMessage());
            }
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // 4. MÉTODO PARA ELIMINAR LÓGICAMENTE (UPDATE ESTADO) - ¡Corregido!
    public boolean eliminarProducto(int idProducto) {
        String sql = "UPDATE PRODUCTOS SET estado = 0 WHERE id_producto = ?";
        
        // ¡LA CONEXIÓN SE SACA DEL TRY PARA QUE JAVA NO LA CIERRE!
        Connection con = Conexion.getInstancia().conectar();
        
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idProducto);
            return pst.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
    
    
    
   // =========================================================
    // 5. NUEVO MÉTODO PARA FILTRAR CATÁLOGO VISUAL (POR CATEGORÍA)
    // =========================================================
    public List<Producto> filtrarCatalogo(String busqueda, String categoria) {
        List<Producto> lista = new ArrayList<>();
        
        String sql = "SELECT DISTINCT p.* FROM PRODUCTOS p " +
                     "LEFT JOIN CATEGORIA_PRODUCTO cp ON p.id_producto = cp.id_producto " +
                     "LEFT JOIN CATEGORIAS c ON cp.id_categoria = c.id_categoria " +
                     "WHERE p.estado = 1 AND (p.nombre_repuesto LIKE ? OR p.num_serie LIKE ?) ";
        
        if (!categoria.equals("Todos")) {
            sql += " AND c.nombre_categoria = ?";
        }

        try {
            Connection con = Conexion.getInstancia().conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            
            String filtroTexto = "%" + busqueda + "%";
            ps.setString(1, filtroTexto);
            ps.setString(2, filtroTexto);
            
            if (!categoria.equals("Todos")) {
                ps.setString(3, categoria);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombreRepuesto(rs.getString("nombre_repuesto"));
                p.setNumSerie(rs.getString("num_serie"));
                p.setMarca(rs.getString("marca"));
                p.setDescripcion(rs.getString("descripcion")); // ¡LÍNEA AGREGADA!
                p.setPrecioVenta(rs.getFloat("precio_venta"));
                p.setStockActual(rs.getInt("stock_actual"));
                p.setStockMinimo(rs.getInt("stock_minimo"));
                p.setEstado(rs.getInt("estado"));
                p.setRutaImagen(rs.getString("ruta_imagen")); 
                
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar catálogo: " + e.toString());
        }
        return lista;
    }
    
    
}