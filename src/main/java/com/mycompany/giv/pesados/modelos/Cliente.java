/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giv.pesados.modelos;

/**
 *
 * @author bryan
 */
public class Cliente {
    private int idCliente;
    private String nombreCliente;
    private String correo;
    private String direccion;
    private int estado;
    private String tipoCliente;
    private String documentoIdentidad; // Manejará DUI o NIT
    private String telefono;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(int idCliente, String nombreCliente, String correo, String direccion, int estado, String tipoCliente, String documentoIdentidad, String telefono) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.correo = correo;
        this.direccion = direccion;
        this.estado = estado;
        this.tipoCliente = tipoCliente;
        this.documentoIdentidad = documentoIdentidad;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }

    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }

    public String getDocumentoIdentidad() { return documentoIdentidad; }
    public void setDocumentoIdentidad(String documentoIdentidad) { this.documentoIdentidad = documentoIdentidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}