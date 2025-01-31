/*
 * File: DAO.java
 * Project: crudBiblioteca
 * File Created: Thursday, 17th October 2024 2:07:33 PM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Thursday, 17th October 2024 5:16:56 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvg.modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interfaz genérica que sirve para hacer las operaciones CRUD sobre la base de
 * datos.
 *
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @since 1.0
 */
public interface DAO<T> {
    /**
     * Crea un objeto T en la base de datos.
     *
     * @param t Objeto a añadir a la base de datos.
     * @return Entero que simboliza el resultado de la operación:
     *         1 -> Realizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido crear el objeto en la BD.
     * @since 1.0
     */
    Integer create(T t) throws SQLException;

    /**
     * Lee un objeto T de la base de datos.
     *
     * @param id Id del objeto a leer de la base de datos.
     * @return Objeto leído de la base de datos.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el objeto de la BD.
     * @since 1.0
     */
    T read(T t) throws SQLException;

    /**
     * Lee todos los objetos de la base de datos.
     *
     * @return Arraylist con el contenido de la tabla de la base de datos del objeto.
     *         T.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el contenido de la BD.
     * @since 1.0
     */
    ArrayList<T> readAll() throws SQLException;

    /**
     * Actualiza el objeto T de la base de datos.
     *
     * @param T Objeto a actualizar en la base de datos.
     * @return Entero que simboliza el resultado de la operación:
     *         1 -> Realizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido actualizar el objeto.
     * @since 1.0
     */
    Integer update(T t) throws SQLException;

    /**
     * Borra un objeto T de la base de datos.
     *
     * @param id Id del objeto a borrar en la base de datos.
     * @return Entero que simboliza el resultado de la operación:
     *         1 -> Realizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido borrar el objeto.
     * @since 1.0
     */
    Integer delete(T t) throws SQLException;
}
