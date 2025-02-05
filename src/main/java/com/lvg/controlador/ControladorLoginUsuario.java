/*
 * File: ControladorLoginUsuario.java
 * Project: gestionbibliotecagui
 * File Created: Friday, 29th November 2024 9:02:11 AM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Friday, 6th December 2024 10:27:23 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvg.controlador;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.lvg.App;
import com.lvg.modelo.dto.Usuario;
import com.lvg.modelo.servicio.BibliotecaService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Esta clase representa el controlador del login de los usuarios miembros en la aplicación de biblioteca.
 * Permite al usuario iniciar sesión como miembro,
 * así como cambiar el idioma de la aplicación.
 *
 * @author Lucas V. (k4ts0v@protonmail.com)
 *  * @version 1.0
 *  * @since 1.0
 */
public class ControladorLoginUsuario {

    @FXML private ComboBox<String> idioma;

    @FXML private Label lblTitulo, lblUsuario, lblContrasenha, lblValidacionUsuario;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasenha;
    @FXML private Hyperlink hlRegistro, hlRecuperarContrasenha, hlMenuPrincipal;
    @FXML private Button btnIniciarSesion;
    @FXML private HBox hbValidacionUsuario;

    // Tooltips
    @FXML private Tooltip ttComboBox, ttTxtUsuario, ttTxtContrasenha, ttBtnIniciarSesion, ttHlMenuPrincipal, ttHlRegistro, ttHlRecuperarContrasenha;

    @FXML private VBox rootVBox;

    private ResourceBundle bundle;

    private BibliotecaService modeloBiblioteca = new BibliotecaService();

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
     * Actualiza los textos de los elementos de la interfaz en función del idioma.
     *
     * @since 1.0
     */
    private void actualizarLabels() {
        lblTitulo.setText(bundle.getString("loginUsuario.lbl.titulo"));
        lblUsuario.setText(bundle.getString("login.lbl.usuario"));
        lblContrasenha.setText(bundle.getString("login.lbl.contrasenha"));
        hlRegistro.setText(bundle.getString("login.hl.registro"));
        btnIniciarSesion.setText(bundle.getString("login.btn.iniciarSesion"));
        hlRecuperarContrasenha.setText(bundle.getString("login.hl.recuperarContrasenha"));
        hlMenuPrincipal.setText(bundle.getString("login.hl.menuPrincipal"));
        lblValidacionUsuario.setText(bundle.getString("login.UsuarioInvalido"));
    }

    /**
     * Actualiza los tooltips de los botones y campos de texto en función del
     * idioma.
     *
     * @since 1.0
     */
    private void actualizarTooltips() {
        ttComboBox.setText(bundle.getString("tt.comboBox"));
        ttTxtUsuario.setText(bundle.getString("tt.login.txt.usuario"));
        ttTxtContrasenha.setText(bundle.getString("tt.login.txt.contrasenha"));
        ttBtnIniciarSesion.setText(bundle.getString("tt.login.btn.iniciarSesion"));
        ttHlMenuPrincipal.setText(bundle.getString("tt.login.hl.menuPrincipal"));
        ttHlRegistro.setText(bundle.getString("tt.login.hl.registro"));
        ttHlRecuperarContrasenha.setText(bundle.getString("tt.login.hl.recuperarContrasenha"));
    }

    /**
     * Añade tooltips a los botones y campos de texto.
     *
     * @since 1.0
     */
    private void setTooltips() {
        idioma.setTooltip(ttComboBox);
        txtUsuario.setTooltip(ttTxtUsuario);
        txtContrasenha.setTooltip(ttTxtContrasenha);
        btnIniciarSesion.setTooltip(ttBtnIniciarSesion);
        hlMenuPrincipal.setTooltip(ttHlMenuPrincipal);
        hlRegistro.setTooltip(ttHlRegistro);
        hlRecuperarContrasenha.setTooltip(ttHlRecuperarContrasenha);
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
     * Muestra el formulario de registro de usuario.
     * 
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     * @since 1.0
     */
    @FXML
    private void registro() throws IOException {
        App.setRoot("fxml/registroUsuario");
    }

    /**
     * Inicia sesión como miembro de la biblioteca.
     * 
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     * @since 1.0
     */
    @FXML
    private void login() throws IOException {
        Usuario usuario = new Usuario(txtUsuario.getText(), txtContrasenha.getText(), "Miembro");
        if (modeloBiblioteca.verificarUsuarioMiembro(usuario) && App.getFxmlActual().equals("fxml/loginUsuario")) {
            Usuario usuarioByData = modeloBiblioteca.getUsuarioByData(txtUsuario.getText(), txtContrasenha.getText(),
                    "Miembro");
            ContextoApp.setUsuario(usuarioByData);
            ContextoApp.setUltimoPanelCargado("LoginUsuario");
            App.setRoot("fxml/menuUsuario");
        } else {
            hbValidacionUsuario.setVisible(true);
        }
    }

    /**
     * Muestra el formulario de recuperación de contraseña.
     * 
     * @throws IOException Excepción lanzada si no se puede mostrar el formulario.
     * @since 1.0
     */
    @FXML
    private void recuperarContrasenha() throws IOException {
        App.setRoot("fxml/recuperarContrasenha");
    }

    /**
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
        // Añadir evento de teclado para iniciar sesión.
        btnIniciarSesion.setDefaultButton(true);

        // Añadir evento de teclado para volver al menú anterior.
        KeyCombination kcVolverMenuAnterior = new KeyCodeCombination(KeyCode.ESCAPE);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcVolverMenuAnterior.match(event)) {
                try {
                    volverMenuPrincipal();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Añadir evento de teclado para registrarse.
        KeyCombination kcRegistro = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcRegistro.match(event)) {
                try {
                    registro();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Añadir evento de teclado para recuperar la contraseña.
        KeyCombination kcRecuperacionContrasenha = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcRecuperacionContrasenha.match(event)) {
                try {
                    recuperarContrasenha();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}