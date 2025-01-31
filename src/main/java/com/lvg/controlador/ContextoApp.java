package com.lvg.controlador;

import com.lvg.modelo.dto.Usuario;

/**
 * Esta clase representa el contexto de la aplicación, almacenando el usuario actual,
 * el último panel cargado y el idioma seleccionado.
 *
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @since 1.0
 */
public class ContextoApp {
    private static Usuario usuario; // Guarda el usuario actual.
    private static String ultimoPanelCargado; // Guarda el nombre del último panel cargado.
    private static String idioma; // Guarda el idioma seleccionado.

    /**
     * Obtiene el idioma seleccionado actualmente.
     *
     * @return El idioma seleccionado.
     * @since 1.0
     */
    public static String getIdioma() {
        return idioma;
    }

    /**
     * Establece el idioma seleccionado.
     *
     * @param idioma El idioma a establecer.
     * @since 1.0
     */
    public static void setIdioma(String idioma) {
        ContextoApp.idioma = idioma;
    }

    /**
     * Obtiene el usuario actual.
     *
     * @return El usuario actual.
     * @since 1.0
     */
    public static Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario actual.
     *
     * @param usuario El usuario a establecer.
     * @since 1.0
     */
    public static void setUsuario(Usuario usuario) {
        ContextoApp.usuario = usuario;
    }

    /**
     * Obtiene el nombre del último panel cargado.
     *
     * @return El nombre del último panel cargado.
     * @since 1.0
     */
    public static String getUltimoPanelCargado() {
        return ultimoPanelCargado;
    }

    /**
     * Establece el nombre del último panel cargado.
     *
     * @param panel El nombre del panel a establecer.
     * @since 1.0
     */
    public static void setUltimoPanelCargado(String panel) {
        ultimoPanelCargado = panel;
    }
    /**
     * Limpia el contexto de la aplicación, restableciendo el usuario actual,
     * el último panel cargado y el idioma seleccionado.
     *
     * @since 1.0
     */
    public static void clearContext() {
        usuario = null;
        ultimoPanelCargado = null;
        idioma = null;
    }
}