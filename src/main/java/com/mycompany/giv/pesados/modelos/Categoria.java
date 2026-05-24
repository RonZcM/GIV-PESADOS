package com.mycompany.giv.pesados.modelos;

public class Categoria {
    private int idCategoria;
    private String nombreCategoria;
    private String descripcion;
    private int estado;

    public Categoria() {}

    public Categoria(int idCategoria, String nombreCategoria, String descripcion, int estado) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public String getNombreCategoria() { return nombreCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    
    // Crucial para que el componente visual (JList o JComboBox) muestre el nombre y no el espacio de memoria
    @Override
    public String toString() {
        return this.nombreCategoria;
    }
}