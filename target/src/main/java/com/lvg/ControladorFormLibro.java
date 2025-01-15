/*
 * File: ControladorFormLibro.java
 * Project: gestionbibliotecagui
 * File Created: Tuesday, 3rd December 2024 9:23:39 AM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Friday, 6th December 2024 10:26:18 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */


package com.lvg;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.lvg.modelo.dto.Libro;
import com.lvg.modelo.servicio.BibliotecaService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class ControladorFormLibro {

    @FXML
    private ComboBox<String> idioma;

    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblIdLibro;
    @FXML
    private Label lblTituloLibro;
    @FXML
    private Label lblAutorLibro;
    @FXML
    private Label lblISBNLibro;
    @FXML
    private Label lblAnhadidoLibroExito;
    @FXML
    private HBox hbAnhadidoLibroExito;
    @FXML
    private Label lblAnhadidoLibroError;
    @FXML
    private HBox hbAnhadidoLibroError;
    @FXML
    private TextField txtIdLibro;
    @FXML
    private TextField txtTituloLibro;
    @FXML
    private TextField txtAutorLibro;
    @FXML
    private TextField txtISBNLibro;
    @FXML
    private Button btnDescartar;
    @FXML
    private Button btnAnhadirLibro;
    @FXML
    private Tooltip ttComboBox;
    @FXML
    private Tooltip ttBtnDescartar;
    @FXML
    private Tooltip ttHlMenuPrincipal;
    @FXML
    private Tooltip ttTxtIdLibro;
    @FXML
    private Tooltip ttBtnAnhadirLibro;
    @FXML
    private Tooltip ttTxtTituloLibro;
    @FXML
    private Tooltip ttTxtAutorLibro;
    @FXML
    private Tooltip ttTxtIsbnLibro;
    @FXML
    private VBox rootVBox;

    private boolean isPrecargado = false;

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
        // Inicializar el combobox y establecer el idioma predeterminado.
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
        lblTitulo.setText(bundle.getString("anhadidoLibro.lbl.titulo"));
        lblTituloLibro.setText(bundle.getString("libro.lbl.titulo"));
        lblIdLibro.setText(bundle.getString("libro.lbl.idLibro"));
        lblAutorLibro.setText(bundle.getString("libro.lbl.autor"));
        lblISBNLibro.setText(bundle.getString("libro.lbl.isbn"));
        btnAnhadirLibro.setText(bundle.getString("libro.btn.anhadirLibro"));
        btnDescartar.setText(bundle.getString("form.btn.descartar"));
        hlMenuPrincipal.setText(bundle.getString("login.hl.menuPrincipal"));
        lblAnhadidoLibroExito.setText(bundle.getString("anhadidoLibro.lbl.exito"));
        lblAnhadidoLibroError.setText(bundle.getString("anhadidoLibro.lbl.error"));
    }

    /**
     * Cambia el idioma en base al contenido del combobox.
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
     * Actualiza los tooltips de los botones y campos de texto en función del idioma seleccionado.
     */
    private void actualizarTooltips() {
        ttComboBox.setText(bundle.getString("tt.comboBox"));
        ttBtnAnhadirLibro.setText(bundle.getString("tt.libro.btn.anhadirLibro"));
        ttBtnDescartar.setText(bundle.getString("tt.btn.descartar"));
        ttHlMenuPrincipal.setText(bundle.getString("tt.login.hl.menuPrincipal"));
        ttTxtIdLibro.setText(bundle.getString("tt.libro.txt.idLibro"));
        ttTxtTituloLibro.setText(bundle.getString("tt.libro.txt.titulo"));
        ttTxtAutorLibro.setText(bundle.getString("tt.libro.txt.autor"));
        ttTxtIsbnLibro.setText(bundle.getString("tt.libro.txt.ISBN"));
    }

    /**
     * Añade tooltips a los botones y campos de texto.
     */
    private void setTooltips() {
        idioma.setTooltip(ttComboBox);
        btnDescartar.setTooltip(ttBtnDescartar);
        hlMenuPrincipal.setTooltip(ttHlMenuPrincipal);
        txtIdLibro.setTooltip(ttTxtIdLibro);
        btnAnhadirLibro.setTooltip(ttBtnAnhadirLibro);
        txtTituloLibro.setTooltip(ttTxtTituloLibro);
        txtAutorLibro.setTooltip(ttTxtAutorLibro);
        txtISBNLibro.setTooltip(ttTxtIsbnLibro);
    }

    /**
     * Vuelve al menú anterior.
     * @throws IOException
     */
    @FXML
    private void volverMenuPrincipal() throws IOException {
        App.setRoot("fxml/vistaLibros");
    }

    /**
     * Añade o actualiza un libro en la base de datos.
     * Si el campo ID del libro está vacío, se crea un nuevo libro.
     * Si el campo ID del libro no está vacío, se actualiza el libro.
     * Si se ha realizado correctamente, muestra el mensaje de exito.
     * Si se ha producido un error, muestra el mensaje de error.
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     */
    @FXML
    private void anhadirLibro() throws IOException {
        if (!validarDatos()) {
            return;
        }
        try {
            // Crea o actualiza el libro según el contenido del campo ID.
            Libro libro = txtIdLibro.getText().isEmpty()
                    ? new Libro(txtTituloLibro.getText(), txtAutorLibro.getText(), txtISBNLibro.getText())
                    : new Libro(Integer.parseInt(txtIdLibro.getText()), txtTituloLibro.getText(), txtAutorLibro.getText(), txtISBNLibro.getText());

            // Añade o actualiza el libro según el contenido del campo ID.
            if (txtIdLibro.getText().isEmpty()) {
                modeloBiblioteca.anhadirLibro(libro);
            } else {
                modeloBiblioteca.actualizarLibro(libro);
            }

            // Si se ha realizado correctamente, muestra el mensaje de exito.
            hbAnhadidoLibroExito.setVisible(true);
            hbAnhadidoLibroError.setVisible(false);
            volverMenuPrincipal();

        } catch (Exception e) {
            // Si se ha producido un error, muestra el mensaje de error.
            hbAnhadidoLibroExito.setVisible(false);
            hbAnhadidoLibroError.setVisible(true);
            e.printStackTrace();
        }
    }

    /**
     * Valida los datos del libro para que cumplan con los criterios de validación.
     * @return boolean True si los datos del libro son válidos, false en caso contrario.
     */
    private boolean validarDatos() {
            if (txtTituloLibro.getText().isEmpty() || txtAutorLibro.getText().isEmpty() || !validarISBN(txtISBNLibro.getText())) {
                // Si hay algún campo vacío, muestra el mensaje de error.
                hbAnhadidoLibroError.setVisible(true);
                return false;
            }
        return true;
    }

    /**
     * Valida un ISBN 13 para que cumpla con los criterios de validación.
     * @param isbn ISBN a validar.
     * @return boolean True si el ISBN es válido, false en caso contrario.
     */
    public boolean validarISBN(String isbn) {
        // Verificar si el ISBN solo contiene números y tiene 13 dígitos.
        if (isbn.matches("[0-9]{13}")) {
            int sumaTotal = 0;
            // Iterar sobre los 12 primeros caracteres del ISBN,
            for (int i = 0; i < 12; i++) {
                int digit = Character.getNumericValue(isbn.charAt(i));
                // Aplicar pesos: 1 para los números en posiciones impares y 3 para los de posición impar. Se va calculndo la suma total directamente.
                if (i % 2 == 0) {
                    sumaTotal += digit; // Posiciones pares con peso 1.
                } else {
                    sumaTotal += digit * 3; // Posiciones impares con peso 3.
                }
            }
            // Extraer eñ último dígito (de control)
            int digitoControl = Character.getNumericValue(isbn.charAt(12));
            // Verificar si la suma más el dígito son un múltiplo de 10.
            return (sumaTotal + digitoControl) % 10 == 0;
        }
        // Si algo de lo anterior no se cumple, devuelve falso.
        return false;
    }

    /**
     * Limpia los campos de texto de los libros.
     * @throws IOException Exception lanzada si no se puede mostrar el formulario.
     */
    @FXML
    private void descartar() throws IOException {
        txtIdLibro.clear();
        txtTituloLibro.clear();
        txtAutorLibro.clear();
        txtISBNLibro.clear();
    }

    /**
     * Precarga los datos de un libro en los campos correspondientes para facilitar la edición.
     * @param libro Libro a precargar.
     */
    public void precargarDatos(Libro libro) {
        this.isPrecargado = true;
        txtIdLibro.setText(String.valueOf(libro.getId()));
        txtTituloLibro.setText(String.valueOf(libro.getTitulo()));
        txtAutorLibro.setText(String.valueOf(libro.getAutor()));
        txtISBNLibro.setText(String.valueOf(libro.getIsbn()));
    }

    /**
     * Devuelve la primera letra en mayúscula de un string.
     * @param input
     * @return String con la primera letra en mayúscula.
     */
    public static String primeraLetraMayuscula(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * Añade botones de cancelación y aceptación por defecto.
     */
    public void setAtajos() {
        // Añadir evento de teclado para añadir o actualizar un libro.
        btnAnhadirLibro.setDefaultButton(true);

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
