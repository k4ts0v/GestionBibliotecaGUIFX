/*
 * File: ControladorRecuperarContrasenha.java
 * Project: gestionbibliotecagui
 * File Created: Monday, 2nd December 2024 11:45:46 AM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Friday, 6th December 2024 10:39:08 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvg;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

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

public class ControladorRecuperarContrasenha {

    @FXML
    private ComboBox<String> idioma;

    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblUsuario;
    @FXML
    private TextField txtUsuario;
    @FXML
    private Label lblContrasenha;
    @FXML
    private PasswordField txtContrasenha;
    @FXML
    private Hyperlink hlIniciarSesion;
    @FXML
    private Button btnRecuperarContrasenha;
    @FXML
    private Label lblRecuperacionExitosa;
    @FXML
    private HBox hbRecuperacionExitosa;

    private ResourceBundle bundle;

    @FXML
    private Hyperlink hlMenuPrincipal;
    @FXML
    private Tooltip ttComboBox;
    @FXML
    private Tooltip ttHlMenuPrincipal;
    @FXML
    private Tooltip ttbtnIniciarSesion;
    @FXML
    private Tooltip ttRecuperarContrasenhaBtnRecuperarContrasenha;
    @FXML
    private Tooltip ttRecuperarContrasenhaTxtContrasenha;
    @FXML
    private Tooltip ttRecuperarContrasenhaTxtUsuario;
    @FXML
    private VBox rootVBox;

    private BibliotecaService modeloBiblioteca = new BibliotecaService();

    String lastFXML = App.getLastLoadedFXML();

    /**
     * Método llamado al iniciar la aplicación.
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
     */
    public void initComboBox() {
        idioma.getItems().addAll("Español", "English");
        idioma.setValue(idioma.getValue() == null ? primeraLetraMayuscula(Locale.getDefault().getDisplayLanguage())
                : "Español");
    }

    /**
     * Cambia el idioma de la aplicación.
     * @param localeStr String con el idioma a cambiar.
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
     * Actualiza los textos de los elementos de la interfaz en función del idioma seleccionado.
     */
    private void actualizarLabels() {
        lblTitulo.setText(bundle.getString("recuperarContrasenha.lbl.titulo"));
        lblUsuario.setText(bundle.getString("login.lbl.usuario"));
        lblContrasenha.setText(bundle.getString("recuperarContrasenha.lbl.contrasenha"));
        hlIniciarSesion.setText(bundle.getString("login.btn.iniciarSesion"));
        btnRecuperarContrasenha.setText(bundle.getString("recuperarContrasenha.lbl.contrasenha"));
        hlMenuPrincipal.setText(bundle.getString("login.hl.menuPrincipal"));
        lblRecuperacionExitosa.setText(bundle.getString("recuperarContrasenha.lbl.recuperacionExitosa"));
    }

    /**
     * Actualiza los tooltips de los botones y campos de texto en función del idioma seleccionado.
     */
    private void actualizarTooltips() {
        ttComboBox.setText(bundle.getString("tt.comboBox"));
        ttHlMenuPrincipal.setText(bundle.getString("tt.login.hl.menuPrincipal"));
        ttbtnIniciarSesion.setText(bundle.getString("tt.login.btn.iniciarSesion"));
        ttRecuperarContrasenhaBtnRecuperarContrasenha.setText(bundle.getString("tt.recuperarContrasenha.btn.recuperarContrasenha"));
        ttRecuperarContrasenhaTxtContrasenha.setText(bundle.getString("tt.recuperarContrasenha.txt.contrasenha"));
        ttRecuperarContrasenhaTxtUsuario.setText(bundle.getString("tt.recuperarContrasenha.txt.usuario"));
    }

    /**
     * Añade tooltips a los botones y campos de texto.
     */
    private void setTooltips() {
        idioma.setTooltip(ttComboBox);
        hlMenuPrincipal.setTooltip(ttHlMenuPrincipal);
        hlIniciarSesion.setTooltip(ttbtnIniciarSesion);
        btnRecuperarContrasenha.setTooltip(ttRecuperarContrasenhaBtnRecuperarContrasenha);
        txtContrasenha.setTooltip(ttRecuperarContrasenhaTxtContrasenha);
        txtUsuario.setTooltip(ttRecuperarContrasenhaTxtUsuario);
    }

    /**
     * Selecciona el idioma en base al contenido del combobox.
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
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     */
    @FXML
    private void volverMenuPrincipal() throws IOException {
        App.setRoot("fxml/login");
    }

    /**
     * Muestra el formulario de inicio de sesión de usuario.
     * Dependiendo del último formulario cargado, muestra el formulario de inicio de sesión de usuario o de administrador.
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     */
    @FXML
    private void iniciarSesion() throws IOException {
        if (lastFXML.equals("fxml/loginUsuario")) {
            App.setRoot("fxml/loginUsuario");
        } else {
            App.setRoot("fxml/loginGestion");
        }
    }

    /**
     * Actualiza la contraseña del usuario.
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     */
    @FXML
    private void actualizarContrasenha() throws IOException {
        if (lastFXML.equals("fxml/loginUsuario")) {
            modeloBiblioteca.updateUsuario(new Usuario(txtUsuario.getText(), "", txtContrasenha.getText(), "Miembro"));
        } else {
            modeloBiblioteca
                    .updateUsuario(new Usuario(txtUsuario.getText(), "", txtContrasenha.getText(), "Administrador"));
        }
        hbRecuperacionExitosa.setVisible(true);
    }

    /**
     * Devuelve la primera letra en mayúscula de un string.
     * @param input String a convertir.
     * @return String con la primera letra en mayúscula.
     */
    public static String primeraLetraMayuscula(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * Añade los atajos de teclado.
     */
    public void setAtajos() {
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

        // Añadir evento de teclado para iniciar sesión.
        KeyCombination kcIniciarSesion = new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcIniciarSesion.match(event)) {
                try {
                    iniciarSesion();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Añadir evento de teclado para actualizar contraseña.
        KeyCombination kcActualizarContrasenha = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcActualizarContrasenha.match(event)) {
                try {
                    actualizarContrasenha();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
