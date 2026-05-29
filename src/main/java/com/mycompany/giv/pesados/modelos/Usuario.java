/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giv.pesados.modelos;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String dui;
    private String correo;
    private String telefono;
    private int estado;
    private String clave;
    private int rol;

    public Usuario() {}

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getDui() { return dui; }
    public void setDui(String dui) { this.dui = dui; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public int getRol() { return rol; }
    public void setRol(int rol) { this.rol = rol; }
}