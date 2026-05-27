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
}