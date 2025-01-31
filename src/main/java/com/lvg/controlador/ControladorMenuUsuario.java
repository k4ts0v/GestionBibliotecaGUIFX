/*
 * File: ControladorMenuUsuario.java
 * Project: gestionbibliotecagui
 * File Created: Friday, 29th November 2024 3:12:10 PM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Friday, 6th December 2024 10:32:30 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvg.controlador;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.lvg.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * Esta clase representa el controlador del menú de los usuarios en la aplicación de biblioteca.
 * Permite al usuario acceder a la gestión de sus préstamos,
 * así como cambiar el idioma de la aplicación.
 *
 * @author Lucas V. (k4ts0v@protonmail.com)
 *  * @version 1.0
 *  * @since 1.0
 */
public class ControladorMenuUsuario {

    @FXML private ComboBox<String> idioma;
    @FXML private Button btnVerPrestamos;
    @FXML private Hyperlink hlCerrarSesion;
    @FXML private Label lblIntro, lblBienvenido;
    @FXML private Tooltip ttComboBox, ttBtnVerPrestamos, ttHlCerrarSesion;

    @FXML private VBox rootVBox;

    private ResourceBundle bundle;

    /**
     * Método llamado al iniciar la aplicación.
     *
     * @since 1.0
     */
    @FXML
    private void initialize() {
        initComboBox();
        setAtajos();
        setTooltips();
        seleccionarIdioma();
    }

    /**
     * Inicializa el combobox con los idiomas disponibles.
     *
     * @since 1.0
     */
    public void initComboBox() {
        idioma.getItems().addAll("Español", "English");
        idioma.setValue(idioma.getValue() == null ? primeraLetraMayuscula(Locale.getDefault().getDisplayLanguage())
                : "Español");
    }

    /**
     * Cambia el idioma de la aplicación.
     * 
     * @param localeStr String con el idioma a cambiar.
     * @since 1.0
     */
    private void setLocale(String localeStr) {
        // Cambiar el idioma de la aplicación.
        Locale locale = Locale.forLanguageTag(localeStr);
        Locale.setDefault(locale);
        // Cargar un nuevo bundle de recursos.
        bundle = ResourceBundle.getBundle("com.lvg.i18n.LB", locale);

        // Actualizar los elementos de la interfaz con el texto localizado.
        actualizarLabels();
        actualizarTooltips();
    }

    /**
     * Actualiza los textos de los elementos de la interfaz en función del idioma
     * seleccionado.
     *
     * @since 1.0
     */
    private void actualizarLabels() {
        btnVerPrestamos.setText(bundle.getString("menuUsuario.btn.verPrestamos"));
        hlCerrarSesion.setText(bundle.getString("menuUsuario.hl.cerrarSesion"));
        lblIntro.setText(bundle.getString("menuUsuario.lbl.intro"));
        lblBienvenido.setText(
                bundle.getString("menuUsuario.lbl.bienvenido") + ", " + ContextoApp.getUsuario().getNombre() + ".");
    }

    /**
     * Actualiza los tooltips de los botones y campos de texto en función del idioma
     * seleccionado.
     *
     * @since 1.0
     */
    private void actualizarTooltips() {
        ttComboBox.setText(bundle.getString("tt.comboBox"));
        ttBtnVerPrestamos.setText(bundle.getString("tt.menuUsuario.btn.verPrestamos"));
        ttHlCerrarSesion.setText(bundle.getString("tt.menuUsuario.btn.cerrarSesion"));
        ;
    }

    /**
     * Añade tooltips a los botones y campos de texto.
     *
     * @since 1.0
     */
    private void setTooltips() {
        idioma.setTooltip(ttComboBox);
        btnVerPrestamos.setTooltip(ttBtnVerPrestamos);
        hlCerrarSesion.setTooltip(ttHlCerrarSesion);
    }

    /**
     * Selecciona el idioma en base al contenido del combobox.
     *
     * @since 1.0
     */
    @FXML
    private void seleccionarIdioma() {
        // Determinar el idioma seleccionado.
        String idiomaSeleccionado = idioma.getValue();
        String localeStr;

        // Devolver el idioma seleccionado.
        localeStr = switch (idiomaSeleccionado) {
            case "Español" -> "es";
            case "English" -> "en";
            default -> "es";
        };

        // Establecer el nuevo idioma.
        ContextoApp.setIdioma(localeStr);
        setLocale(localeStr);
    }

    /**
     * Muestra el formulario de vista de préstamos.
     * 
     * @throws IOException Excepción lanzada si no se puede mostrar el formulario.
     * @since 1.0
     */
    @FXML
    private void verPrestamos() throws IOException {
        App.setRoot("fxml/vistaPrestamos");
    }

    /**
     * Vuelve al menú anterior.
     * 
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     * @since 1.0
     */
    @FXML
    private void volverMenuPrincipal() throws IOException {
        App.setRoot("fxml/login");
    }

    /**
     * Devuelve la primera letra en mayúscula de un string.
     * 
     * @param input String a convertir.
     * @return String con la primera letra en mayúscula.
     * @since 1.0
     */
    public static String primeraLetraMayuscula(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * Añade los atajos de teclado.
     *
     * @since 1.0
     */
    public void setAtajos() {
        // Añadir atajo de teclado para ir a la vista de préstamos.
        btnVerPrestamos.setDefaultButton(true);

        // Añadir evento de teclado para cerrar la sesión.
        KeyCombination kcCerrarSesion = new KeyCodeCombination(KeyCode.ESCAPE);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcCerrarSesion.match(event)) {
                try {
                    volverMenuPrincipal();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}