package com.mycompany.giv.pesados.config;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

public class EstiloUI {
    // Nueva Paleta de Colores "Dashboard Moderno"
    public static final Color AZUL_OSCURO = new Color(0, 43, 73);     // Banner superior, botón Limpiar y cabecera de tabla
    public static final Color FONDO_APP = new Color(243, 244, 246);   // Gris clarito para el fondo de la ventana
    public static final Color FONDO_TARJETA = Color.WHITE;            // Blanco puro para el contenedor del formulario
    
    // Colores específicos de botones
    public static final Color BTN_GUARDAR = new Color(25, 135, 84);   // Verde
    public static final Color BTN_ACTUALIZAR = new Color(0, 170, 228);// Celeste brillante
    public static final Color BTN_ELIMINAR = new Color(220, 53, 69);  // Rojo
    
    public static final Color TEXTO_PRINCIPAL = new Color(51, 51, 51);
    public static final Color TEXTO_SECUNDARIO = new Color(108, 117, 125); // Para placeholders visuales
    
    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FUENTE_GENERAL = new Font("Segoe UI", Font.PLAIN, 14);

    // Estilo de botones
    public static void aplicarEstiloBoton(JButton btn, Color colorFondo) {
        btn.setBackground(colorFondo);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        // Padding interno para que los botones se vean más "gorditos"
        btn.setBorder(new EmptyBorder(8, 15, 8, 15)); 
    }
    
    // Estilo de campos de texto
    public static void aplicarEstiloCampo(JComponent campo) {
        // Borde gris sutil con esquinas ligeramente redondeadas (true)
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
            new EmptyBorder(6, 10, 6, 10) 
        )); 
        campo.setFont(FUENTE_GENERAL);
        campo.setForeground(TEXTO_PRINCIPAL);
    }
}