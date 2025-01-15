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

import com.lvg.modelo.dto.Prestamo;
import com.lvg.modelo.servicio.BibliotecaService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ControladorVistaPrestamos {

    @FXML
    private ComboBox<String> idioma;

    @FXML
    private Label lblTitulo;
    @FXML
    private ListView<Prestamo> listaPrestamos;
    @FXML
    private Button btnEditarPrestamo;
    @FXML
    private Button btnBorrarPrestamo;
    @FXML
    private Button btnAnhadirPrestamo;

    private ResourceBundle bundle;

    @FXML
    private Hyperlink hlMenuPrincipal;

    @FXML
    private Tooltip ttHlMenuPrincipal;
    @FXML
    private Tooltip ttBtnAnhadirPrestamo;
    @FXML
    private Tooltip ttBtnBorrarPrestamo;
    @FXML
    private Tooltip ttBtnEditarPrestamo;
    @FXML
    private Tooltip ttComboBox;
    @FXML
    private VBox rootVBox;

    private BibliotecaService modeloBiblioteca = new BibliotecaService();

    String lastFXML = App.getLastLoadedFXML();

    Prestamo prestamoseleccionado = null;

    /**
     * Método llamado al iniciar la aplicación.
     */
    @FXML
    private void initialize() {
        initComboBox();
        setTooltips();
        setAtajos();
        seleccionarIdioma();
        initListaPrestamos();
        initListaPrestamosListener();
    }

    /**
     * Método que inicializa el combobox de idiomas.
     */
    public void initComboBox() {
        idioma.getItems().addAll("Español", "English");
        idioma.setValue(idioma.getValue() == null ? primeraLetraMayuscula(Locale.getDefault().getDisplayLanguage()) : "Español");
    }

    /**
     * Método que establece el idioma de la aplicación.
     * @param localeStr cadena de texto que representa el idioma a establecer.
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
     * Método que actualiza los textos de las etiquetas y botones.
     */
    private void actualizarLabels() {
        lblTitulo.setText(bundle.getString("vistaPrestamos.lbl.titulo"));
        btnAnhadirPrestamo.setText(bundle.getString("vistaPrestamos.btn.anhadirPrestamo"));
        btnEditarPrestamo.setText(bundle.getString("vistaPrestamos.btn.editarPrestamo"));
        btnBorrarPrestamo.setText(bundle.getString("vistaPrestamos.btn.borrarPrestamo"));
        hlMenuPrincipal.setText(bundle.getString("login.hl.menuPrincipal"));
    }

    /**
     * Método que establece los tooltips de los botones y combobox.
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
     * Método que actualiza los tooltips de los botones y combobox.
     */
    private void actualizarTooltips() {
        ttComboBox.setText(bundle.getString("tt.comboBox"));
        ttHlMenuPrincipal.setText(bundle.getString("tt.login.hl.menuPrincipal"));
        ttBtnAnhadirPrestamo.setText(bundle.getString("tt.vistaPrestamos.btn.anhadirPrestamo"));
        ttBtnBorrarPrestamo.setText(bundle.getString("tt.vistaPrestamos.btn.borrarPrestamo"));
        ttBtnEditarPrestamo.setText(bundle.getString("tt.vistaPrestamos.btn.editarPrestamo"));
    }

    /**
     * Método que establece los atajos de teclado.
     */
    private void setTooltips() {
        idioma.setTooltip(ttComboBox);
        hlMenuPrincipal.setTooltip(ttHlMenuPrincipal);
        btnAnhadirPrestamo.setTooltip(ttBtnAnhadirPrestamo);
        btnBorrarPrestamo.setTooltip(ttBtnBorrarPrestamo);
        btnEditarPrestamo.setTooltip(ttBtnEditarPrestamo);
    }

    /**
     * Método que inicializa la lista de préstamos.
     */
    private void initListaPrestamos() {
        System.out.println(ContextoApp.getUsuario().toString());
        if (ContextoApp.getUsuario().getRol().equals("Administrador")) {
            // Obtener la lista de préstamos.
            ObservableList<Prestamo> prestamosAdm = FXCollections.observableArrayList(modeloBiblioteca.getListaPrestamos());
            
            listaPrestamos.setItems(prestamosAdm);
        } else {
            // Obtener la lista de préstamos.
            ObservableList<Prestamo> prestamosUsu = FXCollections.observableArrayList(modeloBiblioteca.getListaPrestamosUsuario(ContextoApp.getUsuario().getId()));
            
            listaPrestamos.setItems(prestamosUsu);
        }
    }

    /**
     * Método que inicializa el listener de la lista de préstamos.
     */
    private void initListaPrestamosListener() {
        if (listaPrestamos != null) {
            // Listener de selección de un elemento de la lista.
            listaPrestamos.getSelectionModel().selectedItemProperty().addListener((obs, oldPrestamo, newPrestamo) -> {
                if (newPrestamo != null) {
                    // Guardar el objeto Prestamo seleccionado.
                    prestamoseleccionado = newPrestamo;
                    System.out.println("Selected Prestamo: " + prestamoseleccionado);
                }
            });

            // Listener en click doble (para editar un préstamo)
            listaPrestamos.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() == 2) {
                    // Acción en click doble.
                    Prestamo selectedPrestamo = listaPrestamos.getSelectionModel().getSelectedItem();
                    if (selectedPrestamo != null) {
                        try {
                            editarPrestamo();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    /**
     * Método que muestra el formulario de edición de un préstamo.
     * @throws IOException Excepción lanzada cuando ocurre un error al cargar el formulario.
     */
    @FXML
    private void volverMenuPrincipal() throws IOException {
        // No se pone lastFXML porque se puede haber accedido al formulario de los
        // prestamos.
        if (ContextoApp.getUsuario().getRol().equals("Administrador")) {
            App.setRoot("fxml/menuGestion");
        } else {
            App.setRoot("fxml/menuUsuario");
        }
    }

    /**
     * Método que muestra el formulario de edición de un préstamo.
     * @throws IOException Excepción lanzada cuando ocurre un error al cargar el formulario.
     */
    @FXML
    private void editarPrestamo() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/formPrestamo.fxml"));
        loader.setResources(ResourceBundle.getBundle("com.lvg.i18n.LB", Locale.getDefault()));
        Parent root = loader.load();

        ControladorFormPrestamo controladorFormPrestamo = loader.getController();
        controladorFormPrestamo.precargarDatos(getPrestamoseleccionado());

        // Cambiar la escena.
        App.getPrimaryStage().getScene().setRoot(root);
    }

    /**
     * Método que muestra el formulario de añadido de un préstamo.
     * @throws IOException Excepción lanzada cuando ocurre un error al cargar el formulario.
     */
    @FXML
    private void anhadirPrestamo() throws IOException {
        App.setRoot("fxml/formPrestamo");
    }

    /**
     * Método que borra un préstamo.
     * @throws IOException Excepción lanzada cuando ocurre un error al cargar el formulario.
     */
    @FXML
    private void borrarPrestamo() throws IOException {
        modeloBiblioteca.borrarPrestamo(getPrestamoseleccionado());
        initListaPrestamos();
    }

    /**
     * Método que devuelve el préstamo seleccionado.
     * @return Prestamo seleccionado.
     */
    public Prestamo getPrestamoseleccionado() {
        return prestamoseleccionado;
    }

    /**
     * Método que devuelve la primera letra en mayúscula de una cadena de texto.
     * @param input cadena de texto.
     * @return cadena de texto con la primera letra en mayúscula.
     */
    public static String primeraLetraMayuscula(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * Método que establece los atajos de teclado.
     */
    private void setAtajos() {
        // Evento de teclado para añadir un préstamo.
        KeyCombination kcAnhadirPrestamo = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcAnhadirPrestamo.match(event)) {
                try {
                    anhadirPrestamo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // Evento de teclado para editar un préstamo.
        KeyCombination kcEditarPrestamo = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcEditarPrestamo.match(event)) {
                try {
                    editarPrestamo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Evento de teclado para borrar un préstamo.
        KeyCombination kcBorrarPrestamo = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcBorrarPrestamo.match(event)) {
                try {
                    borrarPrestamo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Evento de teclado para volver al menú principal.
        KeyCombination kcVolverMenuPrincipal = new KeyCodeCombination(KeyCode.ESCAPE);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcVolverMenuPrincipal.match(event)) {
                try {
                    volverMenuPrincipal();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
