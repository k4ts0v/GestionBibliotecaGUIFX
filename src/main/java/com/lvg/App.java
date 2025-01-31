package com.lvg;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.lvg.controlador.ContextoApp;

/**
 * Clase principal de la aplicación que extiende la clase Application de JavaFX.
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @since 1.0
 */
public class App extends Application {

    private static Scene scene; // Escena principal de la aplicación.
    private static String lastLoadedFXML = ""; // Para rastrear el último FXML cargado.
    private static String previousLoadedFXML = ""; // Para rastrear el FXML anterior cargado.
    private static Stage primaryStage; // El escenario principal de la aplicación.
    private static String fxmlActual = ""; // FXML actualmente cargado.

    /**
     * Método de inicio de la aplicación.
     *
     * @param stage El escenario principal.
     * @throws IOException Lanza una IOException si no se puede cargar el FXML.
     * @since 1.0
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        scene = new Scene(loadFXML("fxml/login"), 798, 431);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Este método establece la raíz y rastrea el último FXML cargado.
     *
     * @param fxml El nombre del archivo FXML a cargar.
     * @throws IOException Lanza una IOException si no se puede cargar el FXML.
     * @since 1.0
     */
    public static void setRoot(String fxml) throws IOException {
        previousLoadedFXML = lastLoadedFXML;
        lastLoadedFXML = fxml;
        scene.setRoot(loadFXML(fxml));
        fxmlActual = fxml;
    }

    /**
     * Este método establece la raíz y pasa datos al controlador si se proporciona un inicializador.
     *
     * @param fxml El nombre del archivo FXML a cargar.
     * @param initializer Función que inicializa el controlador con datos.
     * @throws IOException Lanza una IOException si no se puede cargar el FXML.
     * @since 1.0
     */
    public static void setRootWithData(String fxml, Consumer<Object> initializer) throws IOException {
        Locale localeDefecto = ContextoApp.getIdioma() == null ? Locale.getDefault() : Locale.of(ContextoApp.getIdioma());
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        loader.setResources(ResourceBundle.getBundle("com.lvg.i18n.LB", localeDefecto));
        Parent root = loader.load();
    
        // Pasar datos al controlador si se proporciona un inicializador
        if (initializer != null) {
            initializer.accept(loader.getController());
        }
    
        // Cambiar la escena
        Stage primaryStage = (Stage) getPrimaryStage().getScene().getWindow();
        primaryStage.getScene().setRoot(root);
        fxmlActual = fxml;
    }

    /**
     * Carga el archivo FXML usando FXMLLoader y carga el ResourceBundle para la localización.
     *
     * @param fxml El nombre del archivo FXML a cargar.
     * @return El objeto Parent cargado desde el archivo FXML.
     * @throws IOException Lanza una IOException si no se puede cargar el FXML.
     * @since 1.0
     */
    private static Parent loadFXML(String fxml) throws IOException {
        Locale localeDefecto = ContextoApp.getIdioma() == null ? Locale.getDefault() : Locale.of(ContextoApp.getIdioma());
        ResourceBundle bundle = ResourceBundle.getBundle("com.lvg.i18n.LB", localeDefecto);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"), bundle);
        return fxmlLoader.load();
    }

    /**
     * Obtiene el nombre del último archivo FXML cargado (para acciones condicionales).
     *
     * @return El nombre del último archivo FXML cargado.
     * @since 1.0
     */
    public static String getLastLoadedFXML() {
        return previousLoadedFXML;
    }

    /**
     * Método principal de la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     * @since 1.0
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Obtiene el escenario principal.
     *
     * @return El escenario principal.
     * @since 1.0
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Obtiene el nombre del archivo FXML actualmente cargado.
     *
     * @return El nombre del archivo FXML actualmente cargado.
     * @since 1.0
     */
    public static String getFxmlActual() {
        return fxmlActual;
    }
}