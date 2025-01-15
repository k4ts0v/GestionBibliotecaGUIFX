/*
 * File: ContextoApp.java
 * Project: gestionbibliotecagui
 * File Created: Tuesday, 3rd December 2024 9:35:51 PM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Friday, 6th December 2024 10:25:55 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */


package com.lvg;

import com.lvg.modelo.dto.Usuario;

public class ContextoApp {
    private static Usuario usuario; // Guarda el usuario actual
    private static String ultimoPanelCargado; // Guarda el nombre del último panel cargado.
    private static String idioma; // Guarda el idioma seleccionado.

    public static String getIdioma() {
        return idioma;
    }

    public static void setIdioma(String idioma) {
        ContextoApp.idioma = idioma;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        ContextoApp.usuario = usuario;
    }

    public static String getUltimoPanelCargado() {
        return ultimoPanelCargado;
    }

    public static void setUltimoPanelCargado(String panel) {
        ultimoPanelCargado = panel;
    }

    public static void clearContext() {
        usuario = null;
        ultimoPanelCargado = null;
        idioma = null;
    }
}