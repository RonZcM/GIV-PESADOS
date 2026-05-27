package com.mycompany.giv.pesados.modelos;

public class Producto {
    private int idProducto;
    private String nombreRepuesto;
    private String numSerie;
    private String rutaImagen;
    private String marca;
    private String descripcion;
    private float precioVenta;
    private int stockActual;
    private int stockMinimo;
    private int estado;

    public Producto() {}

   public Producto(int idProducto, String nombreRepuesto, String numSerie, String marca, String descripcion, float precioVenta, int stockActual, int stockMinimo, int estado, String rutaImagen) {
        this.idProducto = idProducto;
        this.nombreRepuesto = nombreRepuesto;
        this.numSerie = numSerie;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.estado = estado;
        this.rutaImagen = rutaImagen;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombreRepuesto() { return nombreRepuesto; }
    public void setNombreRepuesto(String nombreRepuesto) { this.nombreRepuesto = nombreRepuesto; }

    public String getNumSerie() { return numSerie; }
    public void setNumSerie(String numSerie) { this.numSerie = numSerie; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public float getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(float precioVenta) { this.precioVenta = precioVenta; }

    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }

    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    
    public String getRutaImagen() { return rutaImagen; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
}