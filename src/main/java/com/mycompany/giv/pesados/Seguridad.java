package com.mycompany.giv.pesados;

import java.security.MessageDigest;

public class Seguridad {
    
    // Método estático para encriptar cualquier texto a SHA-256
    public static String encriptarSHA256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error al encriptar la contraseña", ex);
        }
    }
}