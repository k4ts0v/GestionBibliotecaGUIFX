/*
 * File: JDBC.java
 * Project: biblioteca_jdbc
 * File Created: Wednesday, 16th October 2024 1:10:53 PM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvg.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
    private static final String USER = "root"; // Usuario de la BD.
    private static final String PASSWORD = ""; // Contraseña del usuario especificado arriba.
    private static final String URL_BASE = "jdbc:mariadb://localhost:3306/"; // URL de MariaDB.
    private static Connection conexion = null; // Objeto conexión para la BD.
    private static final String BD = "Biblioteca"; // Base de datos a utilizar.

    /**
     * Constructor privado que se conecta al SGBD usando los datos proporcionados.
     * Si falla, lanza un mensaje de error, pudiendo ser este por no encontrar la
     * librería necesaria o por no poder conectarse el gestor de base de datos pro
     * tener las credenciales incorrectas.
     */
    private JDBC() {
        try {
             // Intentar conectarse directamente a la base de datos
            String URL = URL_BASE + BD;
            System.out.println(URL);
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            try {
                // Crear la base de datos si no existe
                crearBaseDeDatos();
                // Reconectar a la base de datos recién creada
                String URL = URL_BASE + BD;
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e1) {
                System.out.println();
            }
        }
    } 

    /**
     * Crea la base de datos si no existe.
     *
     * @throws SQLException Si ocurre un error al crear la base de datos.
     */
    private static void crearBaseDeDatos() throws SQLException {
        try (Connection conexion = DriverManager.getConnection(URL_BASE, USER, PASSWORD);
            Statement statement = conexion.createStatement()) {
            statement.executeUpdate("CREATE DATABASE " + BD);
            System.out.println("Base de datos '" + BD + "' creada exitosamente.");
        }
    }

    /**
     * Genera una conexión al SGDB.
     * 
     * @return Connection - Objeto que permite la conexión a la base de datos.
     */
    public static Connection getConexion() {
        if (conexion == null) {
            new JDBC();
        }
        return conexion;
    }

    /**
     * Método que cierra la conexión al SGBD.
     * 
     * @throws SQLException Excepción lanzada en caso de que no se pueda cerrar la
     *                      conexión.
     */
    public static void close() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
