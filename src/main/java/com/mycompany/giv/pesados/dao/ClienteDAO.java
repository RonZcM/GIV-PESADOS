/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giv.pesados.dao;
import com.mycompany.giv.pesados.config.Conexion;
import com.mycompany.giv.pesados.modelos.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class ClienteDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // ==========================================
    // MÉTODOS DE VALIDACIÓN (Expresiones Regulares)
    // ==========================================
    
    private boolean validarDUI(String dui) {
        // Formato salvadoreño: 00000000-0
        return Pattern.matches("^\\d{8}-\\d$", dui);
    }

    private boolean validarCorreo(String correo) {
        // Formato estándar de email
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", correo);
    }

    private boolean validarTelefono(String telefono) {
        // Acepta 8 dígitos seguidos o con guion (ej. 0000-0000 o 00000000)
        return Pattern.matches("^\\d{4}-?\\d{4}$", telefono);
    }

    // ==========================================
    // MÉTODOS CRUD
    // ==========================================

    public boolean registrarCliente(Cliente cl) {
        if (!validarDUI(cl.getDocumentoIdentidad())) {
            JOptionPane.showMessageDialog(null, "El formato del DUI es incorrecto. Debe ser 00000000-0");
            return false;
        }
        if (!validarCorreo(cl.getCorreo())) {
            JOptionPane.showMessageDialog(null, "El formato del correo electrónico es inválido.");
            return false;
        }
        if (!validarTelefono(cl.getTelefono())) {
            JOptionPane.showMessageDialog(null, "El número de teléfono es inválido. Ingrese 8 dígitos.");
            return false;
        }

        String sql = "INSERT INTO CLIENTES (nombre_cliente, correo, direccion, estado, tipo_cliente, documento_identidad, telefono) VALUES (?,?,?,?,?,?,?)";
        try {
            con = Conexion.getInstancia().conectar(); 
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getNombreCliente());
            ps.setString(2, cl.getCorreo());
            ps.setString(3, cl.getDireccion());
            ps.setInt(4, cl.getEstado());
            ps.setString(5, cl.getTipoCliente());
            ps.setString(6, cl.getDocumentoIdentidad());
            ps.setString(7, cl.getTelefono());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar cliente: " + e.toString());
            return false;
        }
    }

    public List<Cliente> listarClientes(int rol) {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql;
        
        // Si es Administrador (rol 1), trae TODOS los clientes
        // Si es Vendedor (rol 2), filtra solo los activos (estado = 1)
        if (rol == 1) {
            sql = "SELECT * FROM CLIENTES";
        } else {
            sql = "SELECT * FROM CLIENTES WHERE estado = 1";
        }
        
        try {
            con = Conexion.getInstancia().conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setIdCliente(rs.getInt("id_cliente"));
                cl.setNombreCliente(rs.getString("nombre_cliente"));
                cl.setCorreo(rs.getString("correo"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setEstado(rs.getInt("estado"));
                cl.setTipoCliente(rs.getString("tipo_cliente"));
                cl.setDocumentoIdentidad(rs.getString("documento_identidad"));
                cl.setTelefono(rs.getString("telefono"));
                listaClientes.add(cl);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.toString());
        }
        return listaClientes;
    }

    public boolean actualizarCliente(Cliente cl) {
        if (!validarDUI(cl.getDocumentoIdentidad()) || !validarCorreo(cl.getCorreo()) || !validarTelefono(cl.getTelefono())) {
            JOptionPane.showMessageDialog(null, "Verifique el formato de DUI, Correo o Teléfono antes de actualizar.");
            return false;
        }

        String sql = "UPDATE CLIENTES SET nombre_cliente=?, correo=?, direccion=?, estado=?, tipo_cliente=?, documento_identidad=?, telefono=? WHERE id_cliente=?";
        try {
            con = Conexion.getInstancia().conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getNombreCliente());
            ps.setString(2, cl.getCorreo());
            ps.setString(3, cl.getDireccion());
            ps.setInt(4, cl.getEstado());
            ps.setString(5, cl.getTipoCliente());
            ps.setString(6, cl.getDocumentoIdentidad());
            ps.setString(7, cl.getTelefono());
            ps.setInt(8, cl.getIdCliente());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.toString());
            return false;
        }
    }

    public boolean eliminarCliente(int id){
        // Ejecutamos un DELETE real en lugar de un UPDATE
        String sql = "DELETE FROM CLIENTES WHERE id_cliente = ?";
        try {
            con = Conexion.getInstancia().conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar al cliente: " + e.toString());
            // Mostramos un mensaje amigable si falla por culpa de la llave foránea con VENTAS
            javax.swing.JOptionPane.showMessageDialog(null, "No se puede eliminar este cliente porque ya tiene ventas asociadas en el sistema.", "Error de Integridad", javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    
    // ==========================================
    // MÉTODO DE BÚSQUEDA
    // ==========================================
    
  public List<Cliente> buscarClientes(String busqueda, int rol) {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql;
        
        // Si es Admin (1), busca en toda la base de datos.
        // Si es Vendedor (2), busca solo entre los clientes activos (estado = 1).
        if (rol == 1) {
            sql = "SELECT * FROM CLIENTES WHERE nombre_cliente LIKE ? OR correo LIKE ? OR documento_identidad LIKE ?";
        } else {
            sql = "SELECT * FROM CLIENTES WHERE estado = 1 AND (nombre_cliente LIKE ? OR correo LIKE ? OR documento_identidad LIKE ?)";
        }
        
        try {
            con = Conexion.getInstancia().conectar();
            ps = con.prepareStatement(sql);
            
            // Los comodines '%' permiten buscar el texto en cualquier parte del campo
            String filtro = "%" + busqueda + "%";
            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setIdCliente(rs.getInt("id_cliente"));
                cl.setNombreCliente(rs.getString("nombre_cliente"));
                cl.setCorreo(rs.getString("correo"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setEstado(rs.getInt("estado"));
                cl.setTipoCliente(rs.getString("tipo_cliente"));
                cl.setDocumentoIdentidad(rs.getString("documento_identidad"));
                cl.setTelefono(rs.getString("telefono"));
                listaClientes.add(cl);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar clientes: " + e.toString());
        }
        return listaClientes;
    }
}