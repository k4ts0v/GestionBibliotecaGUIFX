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
 */
public interface DAO<T> {
    /**
     * @param t Objeto a añadir a la base de datos.
     * @return Entero que simboliza el resultado de la operación:
     *         1 -> Realizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido crear el objeto en la BD.
     */
    Integer create(T t) throws SQLException;

    /**
     * @param id Id del objeto a leer de la base de datos.
     * @return Objeto leído de la base de datos.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el objeto de la BD.
     */
    T read(T t) throws SQLException;

    /**
     * @return Arraylist con el contenido de la tabla de la base de datos del objeto.
     *         T.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el contenido de la BD.
     */
    ArrayList<T> readAll() throws SQLException;

    /**
     * @param T Objeto a actualizar en la base de datos.
     * @return Entero que simboliza el resultado de la operación:
     *         1 -> Realizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido actualizar el objeto.
     */
    Integer update(T t) throws SQLException;

    /**
     * @param id Id del objeto a borrar en la base de datos.
     * @return Entero que simboliza el resultado de la operación:
     *         1 -> Realizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido borrar el objeto.
     */
    Integer delete(T t) throws SQLException;
}
