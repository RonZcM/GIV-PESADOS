package com.mycompany.giv.pesados.dao;

import com.mycompany.giv.pesados.config.Conexion;
import com.mycompany.giv.pesados.modelos.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    
    // 1. MÉTODO PARA CREAR (INSERT)
    public boolean registrarCategoria(Categoria categoria) {
        String sql = "INSERT INTO CATEGORIAS (nombre_categoria, descripcion, estado) VALUES (?, ?, 1)";
        Connection con = Conexion.getInstancia().conectar(); // Conexión fuera del try-with-resources
        
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, categoria.getNombreCategoria());
            pst.setString(2, categoria.getDescripcion());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar categoría: " + e.getMessage());
            return false;
        }
    }

    // 2. MÉTODO PARA LEER (SELECT)
    public List<Categoria> listarCategoriasActivas() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIAS WHERE estado = 1";
        Connection con = Conexion.getInstancia().conectar(); // Conexión fuera del try-with-resources
        
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombreCategoria(rs.getString("nombre_categoria"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setEstado(rs.getInt("estado"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar categorías: " + e.getMessage());
        }
        return lista;
    }

    // 3. MÉTODO PARA ACTUALIZAR (UPDATE)
    public boolean actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE CATEGORIAS SET nombre_categoria = ?, descripcion = ? WHERE id_categoria = ?";
        Connection con = Conexion.getInstancia().conectar(); // Conexión fuera del try-with-resources
        
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, categoria.getNombreCategoria());
            pst.setString(2, categoria.getDescripcion());
            pst.setInt(3, categoria.getIdCategoria());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar categoría: " + e.getMessage());
            return false;
        }
    }

    // 4. MÉTODO PARA ELIMINAR LÓGICAMENTE (UPDATE ESTADO)
    public boolean eliminarCategoria(int idCategoria) {
        String sql = "UPDATE CATEGORIAS SET estado = 0 WHERE id_categoria = ?";
        Connection con = Conexion.getInstancia().conectar(); // Conexión fuera del try-with-resources
        
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idCategoria);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar categoría: " + e.getMessage());
            return false;
        }
    }
    
    // 5. MÉTODO PARA OBTENER CATEGORIAS POR PRODUCTO
    public List<Integer> obtenerIdCategoriasPorProducto(int idProducto) {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id_categoria FROM CATEGORIA_PRODUCTO WHERE id_producto = ?";
        try {
            java.sql.Connection con = com.mycompany.giv.pesados.config.Conexion.getInstancia().conectar();
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idProducto);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("id_categoria"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener categorías: " + e.getMessage());
        }
        return ids;
    }
    
    // 6. MÉTODO PARA VERIFICAR SI EL NOMBRE YA EXISTE
    public boolean existeCategoria(String nombre, int idIgnorar) {
        // Si idIgnorar es 0, es un registro nuevo. Si es > 0, estamos editando.
        String sql = "SELECT COUNT(*) FROM CATEGORIAS WHERE nombre_categoria = ? AND id_categoria != ? AND estado = 1";
        Connection con = Conexion.getInstancia().conectar();
        
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nombre);
            pst.setInt(2, idIgnorar);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Devuelve true si existe
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia: " + e.getMessage());
        }
        return false;
    }
}