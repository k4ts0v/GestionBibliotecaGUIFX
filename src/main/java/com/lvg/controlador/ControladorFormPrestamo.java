/*
 * File: ControladorFormPrestamo.java
 * Project: gestionbibliotecagui
 * File Created: Tuesday, 3rd December 2024 10:32:48 AM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Friday, 6th December 2024 10:26:35 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvg.controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import com.lvg.App;
import com.lvg.modelo.dto.Prestamo;
import com.lvg.modelo.servicio.BibliotecaService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Esta clase representa el formulario controlador para libros en la aplicación de biblioteca.
 * Maneja varias acciones como añadir, actualizar y descartar libros,
 * así como cambiar el idioma de la aplicación.
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @since 1.0
 */

public class ControladorFormPrestamo {

    @FXML private ComboBox<String> idioma;
    @FXML private Label lblTitulo, lblNombrePrestamo, lblFechaInicio, lblFechaFin, lblUsuario, lblLibroId, lblIdPrestamo, lblAnhadidoPrestamoExito, lblAnhadidoPrestamoError;
    @FXML private DatePicker dpFechaInicio, dpFechaFin;
    @FXML private TextField txtUsuario, txtLibroId;
    @FXML private Button btnAnhadirPrestamo, btnDescartar;
    @FXML private HBox hbAnhadidoPrestamoExito, hbAnhadidoPrestamoError;
    @FXML private TextField txtIdPrestamo;

    @FXML private Tooltip ttComboBox, ttTxtLibroId, ttTxtUsuarioId, ttTxtFechaInicio, ttTxtFechaFin, ttBtnAnhadirPrestamo, ttBtnDescartar, ttHlMenuPrincipal, ttTxtIdPrestamo;

    @FXML private VBox rootVBox;

    private boolean isPrecargado = false;

    private ResourceBundle bundle;

    @FXML private Hyperlink hlMenuPrincipal;

    private BibliotecaService modeloBiblioteca = new BibliotecaService();

    String lastFXML = App.getLastLoadedFXML();

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
        verificarUsuario();
    }

    /**
     * Verifica si el usuario es administrador o miembro de la biblioteca.
     * Si es administrador, permite editar el campo de usuario.
     * Si es miembro de la biblioteca, no permite editar el campo de usuario.
     *
     * @since 1.0
     */
    public void verificarUsuario() {
        if (ContextoApp.getUsuario().getRol().equals("Administrador")) {
            txtUsuario.setEditable(true);
        } else {
            txtUsuario.setEditable(false);
            txtUsuario.setText(String.valueOf(ContextoApp.getUsuario().getId()));
        }
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
        lblTitulo.setText(bundle.getString("anhadidoPrestamo.lbl.titulo"));
        lblIdPrestamo.setText(bundle.getString("prestamo.lbl.idPrestamo"));
        lblFechaInicio.setText(bundle.getString("prestamo.lbl.fechaInicio"));
        lblFechaFin.setText(bundle.getString("prestamo.lbl.fechaFin"));
        lblUsuario.setText(bundle.getString("prestamo.lbl.usuario"));
        lblLibroId.setText(bundle.getString("prestamo.lbl.libroId"));
        btnAnhadirPrestamo.setText(bundle.getString("vistaPrestamos.btn.anhadirPrestamo"));
        btnDescartar.setText(bundle.getString("form.btn.descartar"));
        hlMenuPrincipal.setText(bundle.getString("login.hl.menuPrincipal"));
        lblAnhadidoPrestamoExito.setText(bundle.getString("anhadidoPrestamo.lbl.exito"));
        lblAnhadidoPrestamoError.setText(bundle.getString("anhadidoPrestamo.lbl.error"));
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
     * Actualiza los tooltips de los botones y campos de texto en función del idioma
     * seleccionado.
     *
     * @since 1.0
     */
    private void actualizarTooltips() {
        ttComboBox.setText(bundle.getString("tt.comboBox"));
        ttTxtLibroId.setText(bundle.getString("tt.prestamo.txt.libroId"));
        ttTxtUsuarioId.setText(bundle.getString("tt.prestamo.txt.usuarioId"));
        ttTxtFechaInicio.setText(bundle.getString("tt.prestamo.txt.fechaInicio"));
        ttTxtFechaFin.setText(bundle.getString("tt.prestamo.txt.fechaFin"));
        ttBtnAnhadirPrestamo.setText(bundle.getString("tt.vistaPrestamos.btn.anhadirPrestamo"));
        ttBtnDescartar.setText(bundle.getString("tt.btn.descartar"));
        ttHlMenuPrincipal.setText(bundle.getString("tt.login.hl.menuPrincipal"));
        ttTxtIdPrestamo.setText(bundle.getString("tt.prestamo.txt.idPrestamo"));
    }

    /**
     * Añade tooltips a los botones y campos de texto.
     *
     * @since 1.0
     */
    private void setTooltips() {
        idioma.setTooltip(ttComboBox);
        txtLibroId.setTooltip(ttTxtLibroId);
        txtUsuario.setTooltip(ttTxtUsuarioId);
        dpFechaInicio.setTooltip(ttTxtFechaInicio);
        dpFechaFin.setTooltip(ttTxtFechaFin);
        btnAnhadirPrestamo.setTooltip(ttBtnAnhadirPrestamo);
        btnDescartar.setTooltip(ttBtnDescartar);
        hlMenuPrincipal.setTooltip(ttHlMenuPrincipal);
    }

    /**
     * Vuelve al menú anterior.
     * 
     * @throws IOException
     * @since 1.0
     */
    @FXML
    private void volverMenuPrincipal() throws IOException {
        App.setRoot("fxml/vistaPrestamos");
    }

    /**
     * Añade o actualiza un prestamo en la base de datos.
     * Si el campo ID del prestamo está vacío, se crea un nuevo prestamo.
     * Si el campo ID del prestamo no está vacío, se actualiza el prestamo.
     * Si se ha realizado correctamente, muestra el mensaje de exito.
     * Si se ha producido un error, muestra el mensaje de error.
     * 
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     * @since 1.0
     */
    @FXML
    private void anhadirPrestamo() throws IOException {
        if (!validarDatos()) {
            return;
        }
        try {
            // Crea o actualiza el prestamo según el contenido del campo ID.
            Prestamo prestamo = txtIdPrestamo.getText().isEmpty()
                    ? new Prestamo(dpFechaInicio.getValue().toString(), dpFechaFin.getValue().toString(),
                            Integer.parseInt(txtUsuario.getText()), Integer.parseInt(txtLibroId.getText()))
                    : new Prestamo(Integer.parseInt(txtIdPrestamo.getText()), dpFechaInicio.getValue().toString(),
                            dpFechaFin.getValue().toString(), Integer.parseInt(txtUsuario.getText()),
                            Integer.parseInt(txtLibroId.getText()));

            // Añade o actualiza el prestamo según el contenido del campo ID.
            if (txtIdPrestamo.getText().isEmpty()) {
                modeloBiblioteca.anhadirPrestamo(prestamo);
            } else {
                modeloBiblioteca.actualizarPrestamo(prestamo);
            }

            // Si se ha realizado correctamente, muestra el mensaje de exito.
            hbAnhadidoPrestamoExito.setVisible(true);
            hbAnhadidoPrestamoError.setVisible(false);
            volverMenuPrincipal();

        } catch (Exception e) {
            // Si se ha producido un error, muestra el mensaje de error.
            hbAnhadidoPrestamoExito.setVisible(false);
            hbAnhadidoPrestamoError.setVisible(true);
            e.printStackTrace();
        }
    }

    /**
     * Valida los datos del prestamo para que cumplan con los criterios de
     * validación.
     * 
     * @return boolean True si los datos del prestamo son válidos, false en caso
     *         contrario.
     * @since 1.0
     */
    private boolean validarDatos() {
        if (txtUsuario.getText().isEmpty() || txtLibroId.getText().isEmpty()
                || dpFechaInicio.getValue().isEqual(dpFechaFin.getValue())
                || dpFechaInicio.getValue().isAfter(dpFechaFin.getValue()) || dpFechaInicio.getValue().equals(null)
                || dpFechaFin.getValue().equals(null)) {
            // Si hay algún campo erróneo, muestra el mensaje de error.
            hbAnhadidoPrestamoError.setVisible(true);
            return false;
        }
        return true;
    }

    @FXML
    private void descartar() throws IOException {
        dpFechaInicio.getEditor().clear();
        dpFechaFin.getEditor().clear();
        txtUsuario.clear();
        txtLibroId.clear();
    }

    /**
     * Precarga los datos de un prestamo en los campos correspondientes para
     * facilitar la edición.
     * 
     * @param prestamo Prestamo a precargar.
     * @since 1.0
     */
    public void precargarDatos(Prestamo prestamo) {
        this.isPrecargado = true;
        txtIdPrestamo.setText(String.valueOf(prestamo.getId()));
        dpFechaInicio.setValue(LocalDate.parse(prestamo.getFechaInicio()));
        dpFechaFin.setValue(LocalDate.parse(prestamo.getFechaFin()));
        txtUsuario.setText(String.valueOf(prestamo.getIdUsuario()));
        txtLibroId.setText(String.valueOf(prestamo.getIdLibro()));
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
     * Añade botones de cancelación y aceptación por defecto.
     * @since 1.0
     */
    public void setAtajos() {
        btnAnhadirPrestamo.setDefaultButton(true);

        // Añadir evento de teclado para añadir o actualizar un libro.
        btnAnhadirPrestamo.setDefaultButton(true);

        // Añadir evento de teclado para volver al menú principal.
        KeyCombination kcSalir = new KeyCodeCombination(KeyCode.ESCAPE);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcSalir.match(event)) {
                try {
                    volverMenuPrincipal();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Añadir evento de teclado para limpiar el formulario.
        KeyCombination kcDescartar = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcDescartar.match(event)) {
                try {
                    descartar();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
