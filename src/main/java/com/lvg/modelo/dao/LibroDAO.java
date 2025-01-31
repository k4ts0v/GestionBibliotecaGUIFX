/*
 * File: LibroDAO.java
 * Project: biblioteca_jdbc
 * File Created: Friday, 18th October 2024 11:48:45 AM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Friday, 18th October 2024 12:32:12 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */

package com.lvg.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import com.lvg.modelo.dto.Libro;


/**
 * Esta clase representa el DAO (Data Access Object) para la entidad Libro.
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @since 1.0
 */
public class LibroDAO implements DAO<Libro> {
    private Connection conexion = JDBC.getConexion();
    private final String CREATE = "INSERT INTO Libro VALUES(?,?,?,?)";
    private final String READ = "SELECT * FROM Libro WHERE id = ?";
    private final String READALL = "SELECT * FROM Libro";
    private final String UPDATE = "UPDATE Libro SET titulo=?, autor=?, isbn=? WHERE id = ?";
    private final String DELETE = "DELETE FROM Libro WHERE id = ?";

    /**
     * Este método añade un libro a la BD.
     *
     * @param j Objeto libro a añadir.
     * @return Devuelve 1 si la operación se ha realizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido crear el libro en la BD.
     * @since 1.0
     */
        @Override
    public Integer create(Libro l) throws SQLException {
        try (PreparedStatement ps = conexion.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, 0);
            ps.setString(2, l.getTitulo());
            ps.setString(3, l.getAutor());
            ps.setString(4, l.getIsbn());
            System.out.println(l.getTitulo() + " " + l.getAutor() + " " + l.getIsbn());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("la inserción del libro ha fallado."); 
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    l.setId(rs.getInt(1));
                    return l.getId();
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error creando el Libro: " + e.getMessage(), e);
        }
        return -1;
    }


    /**
     * Este método lee el libro especificado (por ID) de la BD.
     *
     * @param id ID del libro.
     * @return El objeto libro que ha sido leído.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el libro de la BD.
     * @since 1.0
     */
    @Override
    public Libro read(Libro l) throws SQLException {
        try  {
            PreparedStatement ps = conexion.prepareStatement(READ);
            ps.setInt(1, l.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return getLibroRS(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Este método lee todos los libros que existen en la BD.
     *
     * @return ArrayList<Libro> Lista de los libros existentes en la BD.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el contenido de la BD.
     * @since 1.0
     */
    @Override
    public ArrayList<Libro> readAll() throws SQLException {
        ArrayList<Libro> listaLibros = new ArrayList<>();
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(READALL);
            while (rs.next()) {
                listaLibros.add(getLibroRS(rs));
            }
            return listaLibros;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Este método actualiza un libro de la BD en base a su ID.
     *
     * @param l Libro a actualizar.
     * @return Devuelve 1 si se ha actualizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido actualizar el libro.
     * @since 1.0
     */
    @Override
public Integer update(Libro l) throws SQLException {
    try {
        PreparedStatement ps = conexion.prepareStatement(UPDATE);
        ps.setString(1, l.getTitulo());
        ps.setString(2, l.getAutor());
        ps.setString(3, l.getIsbn());
        ps.setInt(4, l.getId());
        
        // Debugging: Log the query and parameters
        System.out.println("Executing Update: " + UPDATE);
        System.out.println("Parameters: titulo=" + l.getTitulo() + ", autor=" + l.getAutor() + ", isbn=" + l.getIsbn() + ", id=" + l.getId());

        int rowsAffected = ps.executeUpdate(); // Use executeUpdate for updates
        return rowsAffected;
    } catch (SQLException e) {
        System.out.println("Error executing update: " + e.getMessage());
        throw e;
    }
}


    /**
     * Este método borra un libro de la BD en base a su ID.
     *
     * @param l Libro a borrar.
     * @return Devuelve 1 si el libro se ha borrado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido borrar el libro.
     * @since 1.0
     */
    @Override
    public Integer delete(Libro l) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(DELETE);
            ps.setInt(1, l.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Este método genera un libro en base al ResultSet devuelto por una consulta.
     *
     * @param rs - ResultSet de la consulta.
     * @return Objeto libro con los datos de la consulta.
     * @throws SQLException Lanza una SQLException si no se ha podido crear el libro.
     * @since 1.0
     */
    public Libro getLibroRS(ResultSet rs) throws SQLException {
        return new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
    }
}