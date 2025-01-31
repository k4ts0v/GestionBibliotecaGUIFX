/*
 * File: Libro.java
 * Project: biblioteca_jdbc
 * File Created: Thursday, 17th October 2024 1:56:19 PM
 * Author: Lucas Villa (k4ts0v@protonmail.com)
 * -----
 * Last Modified: Thursday, 17th October 2024 6:49:20 PM
 * Modified By: Lucas Villa (k4ts0v@protonmail.com)
 */


package com.lvg.modelo.dto;

import java.util.Objects;

/**
 * Clase libro, que representa los libros de la biblioteca.
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @since 1.0
 */
public class Libro {
    Integer id; // Id del libro.
    private String titulo; // Título del libro.
    private String autor; // Autor del libro.

    private String isbn; // ISBN del libro.

    /**
     * Constructor del libro.
     *
     * @param id     Id del libro.
     * @param titulo Título del libro.
     * @param autor  Autor del libro.
     * @param isbn   ISBN del libro.
     * @since 1.0
     */
    public Libro(Integer id, String titulo, String autor, String isbn) {
        setId(id);
        setTitulo(titulo);
        setAutor(autor);
        setIsbn(isbn);
    }

    /**
     * Constructor del libro.
     *
     * @param titulo Título del libro.
     * @param autor  Autor del libro.
     * @param isbn   ISBN del libro.
     * @since 1.0
     */
    public Libro(String titulo, String autor,String isbn) {
        setTitulo(titulo);
        setAutor(autor);
        setIsbn(isbn);
    }

    // Getters y setters

    /**
     * Getter que devuelve el id del libro.
     *
     * @return Id del libro.
     * @since 1.0
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter que asigna el id a una instancia de libro.
     *
     * @param id Id a asignar.
     * @since 1.0
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter que devuelve el título del libro.
     *
     * @return Título del libro.
     * @since 1.0
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Setter que asigna el titulo a una instancia de libro.
     *
     * @param titulo Titulo a asignar.
     * @since 1.0
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Getter que devuelve el iSBN del libro.
     *
     * @return ISBN del libro.
     * @since 1.0
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Setter que asigna el isbn a una instancia de libro.
     *
     * @param isbn ISBN a asignar.
     * @since 1.0
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Este método genera el hashcode, que es un integer generado por un algoritmo
     * de hashing. Para ello, ha usado el ID.
     *
     * @return int - Hashcode generado en base al ID del libro.
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Compara dos objetos para ver si son iguales.
     * En este caso, solo son iguales si son dos instancias de Libro y poseen el
     * mismo ID.
     *
     * @param e0 - La otra instancia de libro a usar para la comparación.
     * @return boolean - Si son iguales, devuelve verdadero, si no, falso.
     * @since 1.0
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Libro))
            return false;
        Libro other = (Libro) obj;
        return Objects.equals(id, other.id);
    }

    /**
     * Método toString que genera una String con toda la información del objeto.
     *
     * @return String formateada con toda la información de la instancia de la clase
     *         Libro.
     * @since 1.0
     */
    @Override
    public String toString() {
        return String.format("Libro Nº%s: %s - %s (ISBN: %s)", id, titulo, autor, isbn);
    }
}