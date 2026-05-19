package com.mycompany.giv.pesados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    
    // Variables de conexión con tu servidor en la nube
    private static final String IP = "34.122.203.160"; 
    private static final String PUERTO = "3306";
    private static final String BD = "giv_pesados";
    private static final String USUARIO = "admin_giv";
    private static final String CLAVE = "catolica10";
    
    // Cadena de conexión
    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + BD + "?useSSL=false&serverTimezone=UTC";
    
    // Instancia única (Singleton)
    private static Conexion instancia;
    private Connection conexion;

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("¡Conexión exitosa al servidor GIV-PESADOS en Google Cloud!");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: No se encontró el driver.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection conectar() {
        return conexion;
    }

    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar: " + e.getMessage());
        }
    }
}