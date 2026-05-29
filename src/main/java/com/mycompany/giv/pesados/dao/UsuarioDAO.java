/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giv.pesados.dao;

import com.mycompany.giv.pesados.config.Conexion;
import com.mycompany.giv.pesados.modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // 1. VERIFICAR SI EL USUARIO YA EXISTE (Para evitar duplicados)
    public boolean existeUsuario(String nombreUsuario) {
        String sql = "SELECT nombre_usuario FROM USUARIOS WHERE nombre_usuario = ?";
        Connection con = Conexion.getInstancia().conectar();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Retorna true si encuentra un registro
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar usuario: " + e.getMessage());
            return false;
        }
    }

    // 2. REGISTRAR NUEVO USUARIO
    public boolean registrarUsuario(Usuario u) {
        String sql = "INSERT INTO USUARIOS (nombre, apellido, nombre_usuario, DUI, correo, telefono, estado, clave, rol) VALUES (?, ?, ?, ?, ?, ?, 1, ?, ?)";
        Connection con = Conexion.getInstancia().conectar();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getNombreUsuario());
            ps.setString(4, u.getDui());
            ps.setString(5, u.getCorreo());
            ps.setString(6, u.getTelefono());
            ps.setString(7, u.getClave());
            ps.setInt(8, u.getRol());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    // 3. ACTUALIZAR USUARIO (Maneja si se actualiza o no la contraseña)
    public boolean actualizarUsuario(Usuario u, boolean actualizarClave) {
        String sql;
        if (actualizarClave) {
            sql = "UPDATE USUARIOS SET nombre=?, apellido=?, DUI=?, telefono=?, correo=?, rol=?, clave=? WHERE id_usuario=?";
        } else {
            sql = "UPDATE USUARIOS SET nombre=?, apellido=?, DUI=?, telefono=?, correo=?, rol=? WHERE id_usuario=?";
        }

        Connection con = Conexion.getInstancia().conectar();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getDui());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getCorreo());
            ps.setInt(6, u.getRol());
            
            if (actualizarClave) {
                ps.setString(7, u.getClave());
                ps.setInt(8, u.getIdUsuario());
            } else {
                ps.setInt(7, u.getIdUsuario());
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // 4. ELIMINACIÓN LÓGICA (Baja de sistema)
    public boolean eliminarUsuario(int idUsuario) {
        String sql = "UPDATE USUARIOS SET estado = 0 WHERE id_usuario = ?";
        Connection con = Conexion.getInstancia().conectar();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
    
    // 5. BÚSQUEDA AVANZADA (Query by Example)
    public java.util.List<Usuario> buscarUsuariosAvanzado(Usuario filtro) {
        java.util.List<Usuario> lista = new java.util.ArrayList<>();
        
        // Si un campo de texto viene vacío, el LIKE '%%' lo ignora.
        // Si el rol viene en 0, el "OR ? = 0" hace que traiga todos los roles.
        String sql = "SELECT id_usuario, nombre, apellido, nombre_usuario, DUI, correo, telefono, rol " +
                     "FROM USUARIOS WHERE estado = 1 " +
                     "AND nombre LIKE ? " +
                     "AND apellido LIKE ? " +
                     "AND DUI LIKE ? " +
                     "AND telefono LIKE ? " +
                     "AND correo LIKE ? " +
                     "AND nombre_usuario LIKE ? " +
                     "AND (rol = ? OR ? = 0)";
                     
        java.sql.Connection con = Conexion.getInstancia().conectar();
        try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, "%" + filtro.getNombre() + "%");
            ps.setString(2, "%" + filtro.getApellido() + "%");
            ps.setString(3, "%" + filtro.getDui() + "%");
            ps.setString(4, "%" + filtro.getTelefono() + "%");
            ps.setString(5, "%" + filtro.getCorreo() + "%");
            ps.setString(6, "%" + filtro.getNombreUsuario() + "%");
            
            // Inyectamos el rol dos veces para la validación lógica
            ps.setInt(7, filtro.getRol());
            ps.setInt(8, filtro.getRol());
            
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setApellido(rs.getString("apellido"));
                    u.setNombreUsuario(rs.getString("nombre_usuario"));
                    u.setDui(rs.getString("DUI"));
                    u.setCorreo(rs.getString("correo"));
                    u.setTelefono(rs.getString("telefono"));
                    u.setRol(rs.getInt("rol"));
                    lista.add(u);
                }
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Error al realizar búsqueda avanzada: " + e.getMessage());
        }
        return lista;
    }
    
    
    
}