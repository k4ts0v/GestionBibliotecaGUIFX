/*
 * File: ControladorLogin.java
 * Project: gestionbibliotecagui
 * File Created: Sunday, 1st December 2024 9:03:06 PM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Friday, 6th December 2024 10:26:47 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvg.controlador;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.lvg.App;
import com.lvg.modelo.dto.Usuario;

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
 * Esta clase representa el controlador del login en la aplicación de biblioteca.
 * Permite al usuario seleccionar el tipo de usuario con el que iniciar sesión,
 * así como cambiar el idioma de la aplicación.
 *
 * @author Lucas V. (k4ts0v@protonmail.com)
 *  * @version 1.0
 *  * @since 1.0
 */
public class ControladorLogin {
    @FXML private ComboBox<String> idioma;

    private ResourceBundle bundle;

    @FXML private Label lblRedDeBibliotecas, lblTxt;
    @FXML private Hyperlink hlSalir;
    @FXML private Button btnIniciarSesionGestion, btnIniciarSesionUsuario;

    @FXML private Tooltip ttComboBox, ttBtnIniciarSesionGestion, ttBtnIniciarSesionUsuario, ttHlSalir;
    @FXML private VBox rootVBox;

    /**
     * Método llamado al iniciar la aplicación.
     *
     * @since 1.0
     */
    @FXML
    private void initialize() {
        initComboBox();
        setTooltips();
        setAtajos();
        seleccionarIdioma();
        setAtajos();
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
        lblRedDeBibliotecas.setText(bundle.getString("login.lbl.redDeBibliotecas"));
        btnIniciarSesionUsuario.setText(bundle.getString("login.btn.iniciarSesionUsuario"));
        btnIniciarSesionGestion.setText(bundle.getString("login.btn.iniciarSesionGestion"));
        hlSalir.setText(bundle.getString("login.salir"));
        lblTxt.setText(bundle.getString("login.lbl.txt"));
    }

    /**
     * Actualiza los tooltips de los botones y campos de texto en función del idioma
     * seleccionado.
     *
     * @since 1.0
     */
    private void actualizarTooltips() {
        ttComboBox.setText(bundle.getString("tt.comboBox"));
        ttBtnIniciarSesionGestion.setText(bundle.getString("tt.login.btn.iniciarSesionGestion"));
        ttBtnIniciarSesionUsuario.setText(bundle.getString("tt.login.btn.iniciarSesionUsuario"));
        ttHlSalir.setText(bundle.getString("tt.login.hl.salir"));
    }

    /**
     * Añade tooltips a los botones y campos de texto.
     *
     * @since 1.0
     */
    private void setTooltips() {
        idioma.setTooltip(ttComboBox);
        btnIniciarSesionGestion.setTooltip(ttBtnIniciarSesionGestion);
        btnIniciarSesionUsuario.setTooltip(ttBtnIniciarSesionUsuario);
        hlSalir.setTooltip(ttHlSalir);
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
     * Muestra el formulario de inicio de sesión de usuario.
     * 
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     * @since 1.0
     */
    @FXML
    private void loginUsuario() throws IOException {
        App.setRoot("fxml/loginUsuario");
    }

    /**
     * Muestra el formulario de inicio de sesión de administrador.
     * 
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     * @since 1.0
     */
    @FXML
    private void loginGestion() throws IOException {
        ContextoApp.setUsuario(new Usuario("", "", "Administrador"));
        if (ContextoApp.getUsuario().getRol().equals("Administrador")) {
            App.setRoot("fxml/loginGestion");
        }
    }

    /**
     * Cierra la aplicación.
     * 
     * @throws IOException Exception lanzada si no se puede cerrar la aplicación.
     * @since 1.0
     */
    @FXML
    private void salir() throws IOException {
        System.exit(0);
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
        // Añadir evento de teclado para iniciar sesión de usuario.
        KeyCombination kcLoginUsuario = new KeyCodeCombination(KeyCode.U, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcLoginUsuario.match(event)) {
                btnIniciarSesionUsuario.fire();
            }
        });

        // Añadir evento de teclado para iniciar sesión de administrador.
        KeyCombination kcLoginGestion = new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcLoginGestion.match(event)) {
                btnIniciarSesionGestion.fire();
            }
        });

        // Añadir evento de teclado para cerrar la aplicación.
        KeyCombination kcSalir = new KeyCodeCombination(KeyCode.ESCAPE);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcSalir.match(event)) {
                try {
                    salir();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}