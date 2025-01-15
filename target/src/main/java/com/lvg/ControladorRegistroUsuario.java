/*
 * File: ControladorLoginUsuario copy.java
 * Project: lvg
 * File Created: Monday, 2nd December 2024 9:32:40 AM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Monday, 2nd December 2024 9:32:40 AM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvg;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.lvg.modelo.dto.Usuario;
import com.lvg.modelo.servicio.BibliotecaService;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
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

public class ControladorRegistroUsuario {

    @FXML
    private ComboBox<String> idioma;

    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblUsuario;
    @FXML
    private TextField txtUsuario;
    @FXML
    private Label lblEmail;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label lblContrasenha;
    @FXML
    private PasswordField txtContrasenha;
    @FXML
    private Hyperlink hlIniciarSesion;
    @FXML
    private Button btnRegistro;
    @FXML
    private HBox hbValidacionNombre;
    @FXML
    private HBox hbValidacionEmail;
    @FXML
    private HBox hbRegistroExitoso;
    @FXML
    private HBox hbValidacionContrasenha;
    @FXML
    private Label lblValidacionNombre;
    @FXML
    private Label lblValidacionEmail;
    @FXML
    private Label lblValidacionContrasenha;
    @FXML
    private Tooltip ttComboBox;
    @FXML
    private Tooltip ttHlMenuPrincipal;
    @FXML
    private Tooltip ttBtnIniciarSesion;
    @FXML
    private Tooltip ttTxtContrasenha;
    @FXML
    private Tooltip ttTxtUsuario;
    @FXML
    private Tooltip ttTxtEmail;
    @FXML
    private Tooltip ttHlRegistro;
    @FXML
    private VBox rootVBox;

    private ResourceBundle bundle;

    @FXML
    private Hyperlink hlMenuPrincipal;

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
        validacionesPeriodicas();
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
     * 
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
     * Actualiza los textos de los elementos de la interfaz en función del idioma
     * seleccionado.
     */
    private void actualizarLabels() {
        lblTitulo.setText(bundle.getString("registroUsuario.lbl.titulo"));
        lblUsuario.setText(bundle.getString("login.lbl.usuario"));
        lblContrasenha.setText(bundle.getString("login.lbl.contrasenha"));
        lblEmail.setText(bundle.getString("registro.lbl.email"));
        hlIniciarSesion.setText(bundle.getString("login.btn.iniciarSesion"));
        btnRegistro.setText(bundle.getString("login.hl.registro"));
        hlMenuPrincipal.setText(bundle.getString("login.hl.menuPrincipal"));
        lblValidacionNombre.setText(bundle.getString("registro.lbl.validacionNombre"));
        lblValidacionEmail.setText(bundle.getString("registro.lbl.validacionEmail"));
        lblValidacionContrasenha.setText(bundle.getString("registro.lbl.validacionContrasenha"));
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
     * Actualiza los tooltips de los botones y campos de texto en función del idioma
     * seleccionado.
     */
    private void actualizarTooltips() {
        ttComboBox.setText(bundle.getString("tt.comboBox"));
        ttHlMenuPrincipal.setText(bundle.getString("tt.login.hl.menuPrincipal"));
        ttBtnIniciarSesion.setText(bundle.getString("tt.login.btn.iniciarSesion"));
        ttHlRegistro.setText(bundle.getString("tt.login.hl.registro"));
        ttTxtContrasenha.setText(bundle.getString("tt.login.txt.contrasenha"));
        ttTxtUsuario.setText(bundle.getString("tt.login.txt.usuario"));
        ttTxtEmail.setText(bundle.getString("tt.registro.txt.Email"));
    }

    /**
     * Añade tooltips a los botones y campos de texto.
     */
    private void setTooltips() {
        idioma.setTooltip(ttComboBox);
        hlMenuPrincipal.setTooltip(ttHlMenuPrincipal);
        btnRegistro.setTooltip(ttBtnIniciarSesion);
        txtContrasenha.setTooltip(ttTxtContrasenha);
        txtUsuario.setTooltip(ttTxtUsuario);
        txtEmail.setTooltip(ttTxtEmail);
        btnRegistro.setTooltip(ttHlRegistro);
    }

    /**
     * Vuelve al menú anterior.
     * 
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     */
    @FXML
    private void volverMenuPrincipal() throws IOException {
        App.setRoot("fxml/login");
    }

    /**
     * Muestra el formulario de inicio de sesión de usuario.
     * 
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
     * Registra el usuario en la base de datos.
     * 
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     */
    @FXML
    private void registro() throws IOException {
        if (lastFXML.equals("fxml/loginUsuario")) {
            modeloBiblioteca.anhadirUsuario(
                    new Usuario(txtUsuario.getText(), txtEmail.getText(), txtContrasenha.getText(), "Miembro"));
        } else {
            modeloBiblioteca.anhadirUsuario(
                    new Usuario(txtUsuario.getText(), txtEmail.getText(), txtContrasenha.getText(), "Administrador"));
        }
        hbRegistroExitoso.setVisible(true);
    }

    /**
     * Valida el nombre del usuario.
     * 
     * @param txt TextField con el nombre del usuario.
     */
    public void validarNombre(TextField txt) {
        txt.setOnKeyReleased(evento -> {
            String contenidoActual = txt.getText();
            if (contenidoActual.matches("[a-zA-Z0-9.]{6,}")) {
                hbValidacionNombre.setVisible(false);
            } else {
                hbValidacionNombre.setVisible(true);
            }
        });
    }

    /**
     * Valida el email del usuario.
     * 
     * @param txt TextField con el email del usuario.
     */
    public void validarEmail(TextField txt) {
        txt.setOnKeyReleased(evento -> {
            String contenidoActual = txt.getText();
            if (contenidoActual.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                hbValidacionEmail.setVisible(false);
            } else {
                hbValidacionEmail.setVisible(true);
            }
        });
    }

    /**
     * Valida la contraseña del usuario.
     * 
     * @param pwd PasswordField con la contraseña del usuario.
     */
    public void validarContrasenha(PasswordField pwd) {
        pwd.setOnKeyReleased(evento -> {
            String contenidoActual = pwd.getText();
            if (contenidoActual.matches(".{6,}")) {
                hbValidacionContrasenha.setVisible(false);
            } else {
                hbValidacionContrasenha.setVisible(true);
            }
        });
    }

    /**
     * Validación periódica de los campos de texto.
     * Se realiza una validación periódica de los campos de texto para asegurarse de
     * que los campos estén completos y validos.
     * Se realiza mediante el uso de un Timeline que se ejecuta indefinidamente.
     * Cada campo de texto tiene un KeyFrame que se ejecuta cada dos segundos para
     * validar el contenido del campo.
     * Por último, se ejecuta el timeline para que se haga la validación periódica.
     */
    public void validacionesPeriodicas() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2), event -> {
            validarNombre(txtUsuario);
            validarEmail(txtEmail);
            validarContrasenha(txtContrasenha);
        }));
        timeline.play();
    }

    /**
     * Devuelve la primera letra en mayúscula de un string.
     * 
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
        // Añadir evento de teclado para registrarse.
        btnRegistro.setDefaultButton(true);
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
    }
}
