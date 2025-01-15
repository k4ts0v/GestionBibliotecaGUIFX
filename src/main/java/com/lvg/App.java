/*
 * File: App.java
 * Project: gestionbibliotecagui
 * File Created: Friday, 29th November 2024 9:02:11 AM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Friday, 6th December 2024 10:25:35 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */

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

public class App extends Application {

    private static Scene scene;
    private static String lastLoadedFXML = ""; // To track the last loaded FXML
    private static String previousLoadedFXML = ""; // To track the last loaded FXML
    private static Stage primaryStage;
    private static String fxmlActual = "";

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        scene = new Scene(loadFXML("fxml/login"), 798, 431);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    // This method sets the root and tracks the last loaded FXML
    static void setRoot(String fxml) throws IOException {
        previousLoadedFXML = lastLoadedFXML;
        lastLoadedFXML = fxml;
        scene.setRoot(loadFXML(fxml));
        fxmlActual = fxml;
    }

    public static void setRootWithData(String fxml, Consumer<Object> initializer) throws IOException {
        Locale localeDefecto = ContextoApp.getIdioma() == null ? Locale.getDefault() : Locale.of(ContextoApp.getIdioma());
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        loader.setResources(ResourceBundle.getBundle("com.lvg.i18n.LB", localeDefecto));
        Parent root = loader.load();
    
        // Pass data to the controller if initializer is provided
        if (initializer != null) {
            initializer.accept(loader.getController());
        }
    
        // Switch the scene
        Stage primaryStage = (Stage) getPrimaryStage().getScene().getWindow();
        primaryStage.getScene().setRoot(root);
        fxmlActual = fxml;
    }
    

    // Load the FXML file using FXMLLoader, and load the ResourceBundle for localization
    private static Parent loadFXML(String fxml) throws IOException {
        Locale localeDefecto = ContextoApp.getIdioma() == null ? Locale.getDefault() : Locale.of(ContextoApp.getIdioma());
        ResourceBundle bundle = ResourceBundle.getBundle("com.lvg.i18n.LB", localeDefecto);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"), bundle);
        return fxmlLoader.load();
    }

    // Get the last loaded FXML name (for conditional actions)
    public static String getLastLoadedFXML() {
        return previousLoadedFXML;
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static String getFxmlActual() {
        return fxmlActual;
    }   
    
}
