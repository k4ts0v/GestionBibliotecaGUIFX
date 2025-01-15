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
import java.security.Key;
import java.util.Locale;
import java.util.ResourceBundle;

import com.lvg.modelo.dto.Libro;
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

public class ControladorVistaLibros {

    @FXML
    private ComboBox<String> idioma;

    @FXML
    private Label lblTitulo;
    @FXML
    private ListView<Libro> listaLibros;
    @FXML
    private Button btnEditarLibro;
    @FXML
    private Button btnBorrarLibro;
    @FXML
    private Button btnAnhadirLibro;

    private ResourceBundle bundle;

    @FXML
    private Tooltip ttHlMenuPrincipal;
    @FXML
    private Tooltip ttBtnAnhadirLibro;
    @FXML
    private Tooltip ttBtnBorrarLibro;
    @FXML
    private Tooltip ttBtnEditarLibro;
    @FXML
    private Tooltip ttComboBox;

    @FXML
    private VBox rootVBox;

    @FXML
    private Hyperlink hlMenuPrincipal;

    private BibliotecaService modeloBiblioteca = new BibliotecaService();

    String lastFXML = App.getLastLoadedFXML();

    Libro libroSeleccionado = null;


    /**
     * Método que inicializa la aplicación.
     */
    @FXML
    private void initialize() {
        initComboBox();
        setTooltips();
        setAtajos();
        seleccionarIdioma();
        initListaLibros();
        initListaLibrosListener();
    }

    /**
     * Método que inicializa el combobox de idiomas.
     */
    private void initComboBox() {
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
        lblTitulo.setText(bundle.getString("vistaLibros.lbl.titulo"));
        btnAnhadirLibro.setText(bundle.getString("libro.btn.anhadirLibro"));
        btnEditarLibro.setText(bundle.getString("libro.btn.editarLibro"));
        btnBorrarLibro.setText(bundle.getString("libro.btn.borrarLibro"));
        hlMenuPrincipal.setText(bundle.getString("login.hl.menuPrincipal"));
    }
    
    /**
     * Método que establece el idioma seleccionado.
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
        ttBtnAnhadirLibro.setText(bundle.getString("tt.vistaLibros.btn.anhadirLibro"));
        ttBtnBorrarLibro.setText(bundle.getString("tt.vistaLibros.btn.borrarLibro"));
        ttBtnEditarLibro.setText(bundle.getString("tt.vistaLibros.btn.editarLibro"));
    }

    /**
     * Método que establece los tooltips de los botones y combobox.
     */
    private void setTooltips() {
        idioma.setTooltip(ttComboBox);
        hlMenuPrincipal.setTooltip(ttHlMenuPrincipal);
        btnAnhadirLibro.setTooltip(ttBtnAnhadirLibro);
        btnBorrarLibro.setTooltip(ttBtnBorrarLibro);
        btnEditarLibro.setTooltip(ttBtnEditarLibro);
    }

    /**
     * Método que inicializa la lista de libros.
     */
    private void initListaLibros() {
        // Obtener la lista de libros.
        ObservableList<Libro> libros = FXCollections.observableArrayList(modeloBiblioteca.getListaLibros());
        
        listaLibros.setItems(libros);
    }
    
    /**
     * Método que inicializa el listener de la lista de libros.
     */
    private void initListaLibrosListener() {
        if (listaLibros != null) {
            // Listener for item selection
            listaLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldLibro, newLibro) -> {
                if (newLibro != null) {
                    // Store the selected Libro object
                    libroSeleccionado = newLibro;
                }
            });
    
            // Optional: Add double-click event handling
            listaLibros.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() == 2) {
                    // Action on double-click
                    Libro selectedLibro = listaLibros.getSelectionModel().getSelectedItem();
                    if (selectedLibro != null) {
                        try {
                            editarLibro();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    /**
     * Método que muestra el formulario de edición de un libro.
     * @throws IOException Excepción lanzada cuando ocurre un error al cargar el formulario.
     */
    @FXML
    private void volverMenuPrincipal() throws IOException {
        // No se pone lastFXML porque se puede haber accedido al formulario de los libros.
        App.setRoot("fxml/menuGestion");
    }

    /**
     * Método que muestra el formulario de edición de un libro.
     * @throws IOException Excepción lanzada cuando ocurre un error al cargar el formulario.
     */
    @FXML
    private void editarLibro() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/formLibro.fxml"));
        loader.setResources(ResourceBundle.getBundle("com.lvg.i18n.LB", Locale.getDefault()));
        Parent root = loader.load();

        ControladorFormLibro controladorFormLibro = loader.getController();
        controladorFormLibro.precargarDatos(getLibroSeleccionado());

        // Replace the scene root
        App.getPrimaryStage().getScene().setRoot(root);
    }


    /**
     * Método que muestra el formulario de añadido de un libro.
     * @throws IOException Excepción lanzada cuando ocurre un error al cargar el formulario.
     */
    @FXML
    private void anhadirLibro() throws IOException {
        App.setRoot("fxml/formLibro");
    }

    /**
     * Método que borra un libro.
     * @throws IOException Excepción lanzada cuando ocurre un error al cargar el formulario.
     */
    @FXML 
    private void borrarLibro() throws IOException {
        modeloBiblioteca.borrarLibro(getLibroSeleccionado());
        initListaLibros();
    }

    /**
     * Método que devuelve el libro seleccionado.
     * @return Libro seleccionado.
     */
    private Libro getLibroSeleccionado() {
        return libroSeleccionado;
    }

    /**
     * Método que devuelve la primera letra en mayúscula de una cadena de texto.
     * @param input cadena de texto.
     * @return cadena de texto con la primera letra en mayúscula.
     */
    private static String primeraLetraMayuscula(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * Método que establece los atajos de teclado.
     */
    private void setAtajos() {
        // Evento de teclado para añadir un libro.
        KeyCombination kcAnhadirLibro = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcAnhadirLibro.match(event)) {
                try {
                    anhadirLibro();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Evento de teclado para editar un libro.
        KeyCombination kcEditarLibro = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcEditarLibro.match(event)) {
                try {
                    editarLibro();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Evento de teclado para borrar un libro.
        KeyCombination kcBorrarLibro = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN);
        rootVBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (kcBorrarLibro.match(event)) {
                try {
                    borrarLibro();
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
