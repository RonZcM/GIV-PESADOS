/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giv.pesados.modelos;

import java.sql.Timestamp;

public class Venta {
    private int idVenta;
    private Timestamp fechaVenta;
    private double totalPagar;
    private String tipoComprobante;
    private String metodoPago;
    private int estado;
    private int idCliente;
    private int idUsuario;

    public Venta() {}

    public Venta(int idVenta, Timestamp fechaVenta, double totalPagar, String tipoComprobante, String metodoPago, int estado, int idCliente, int idUsuario) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.totalPagar = totalPagar;
        this.tipoComprobante = tipoComprobante;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public Timestamp getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(Timestamp fechaVenta) { this.fechaVenta = fechaVenta; }

    public double getTotalPagar() { return totalPagar; }
    public void setTotalPagar(double totalPagar) { this.totalPagar = totalPagar; }

    public String getTipoComprobante() { return tipoComprobante; }
    public void setTipoComprobante(String tipoComprobante) { this.tipoComprobante = tipoComprobante; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
}