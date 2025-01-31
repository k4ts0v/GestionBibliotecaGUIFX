package com.lvg.modelo.dto;

import java.util.Objects;

/**
 * Clase que representa un préstamo en la biblioteca.
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @since 1.0
 */
public class Prestamo {
    private int id; // ID del préstamo.
    private String fechaInicio; // Fecha de inicio del préstamo.
    private String fechaFin; // Fecha de fin del préstamo.
    private int idUsuario; // ID del usuario asociado al préstamo.
    private int idLibro; // ID del libro asociado al préstamo.

    /**
     * Constructor del préstamo.
     *
     * @param id ID del préstamo.
     * @since 1.0
     */
    public Prestamo(int id) {
        this.id = id;
    }

    /**
     * Constructor del préstamo.
     *
     * @param idPrestamo  ID del préstamo.
     * @param fechaInicio Fecha de inicio del préstamo.
     * @param fechaFin    Fecha de fin del préstamo.
     * @param idUsuario   ID del usuario asociado al préstamo.
     * @param idLibro     ID del libro asociado al préstamo.
     * @since 1.0
     */
    public Prestamo(Integer idPrestamo, String fechaInicio, String fechaFin, int idUsuario, int idLibro) {
        this.id = idPrestamo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
    }

    /**
     * Constructor del préstamo.
     *
     * @param fechaInicio Fecha de inicio del préstamo.
     * @param fechaFin    Fecha de fin del préstamo.
     * @param idUsuario   ID del usuario asociado al préstamo.
     * @param idLibro     ID del libro asociado al préstamo.
     * @since 1.0
     */
    public Prestamo(String fechaInicio, String fechaFin, int idUsuario, int idLibro) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
    }

    // Getters y setters

    /**
     * Getter que devuelve el ID del préstamo.
     *
     * @return ID del préstamo.
     * @since 1.0
     */
    public int getId() {
        return id;
    }

    /**
     * Setter que asigna el ID a una instancia de préstamo.
     *
     * @param id ID a asignar.
     * @since 1.0
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter que devuelve la fecha de inicio del préstamo.
     *
     * @return Fecha de inicio del préstamo.
     * @since 1.0
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Setter que asigna la fecha de inicio a una instancia de préstamo.
     *
     * @param fechaInicio Fecha de inicio a asignar.
     * @since 1.0
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Getter que devuelve la fecha de fin del préstamo.
     *
     * @return Fecha de fin del préstamo.
     * @since 1.0
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * Setter que asigna la fecha de fin a una instancia de préstamo.
     *
     * @param fechaFin Fecha de fin a asignar.
     * @since 1.0
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Getter que devuelve el ID del usuario asociado al préstamo.
     *
     * @return ID del usuario asociado al préstamo.
     * @since 1.0
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Setter que asigna el ID del usuario a una instancia de préstamo.
     *
     * @param idUsuario ID del usuario a asignar.
     * @since 1.0
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Getter que devuelve el ID del libro asociado al préstamo.
     *
     * @return ID del libro asociado al préstamo.
     * @since 1.0
     */
    public int getIdLibro() {
        return idLibro;
    }

    /**
     * Setter que asigna el ID del libro a una instancia de préstamo.
     *
     * @param idLibro ID del libro a asignar.
     * @since 1.0
     */
    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    /**
     * Compara dos objetos para ver si son iguales.
     * En este caso, solo son iguales si son dos instancias de Prestamo y poseen el
     * mismo ID.
     *
     * @param obj La otra instancia de préstamo a usar para la comparación.
     * @return boolean Si son iguales, devuelve verdadero; de lo contrario, falso.
     * @since 1.0
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Prestamo))
            return false;
        Prestamo other = (Prestamo) obj;
        return Objects.equals(id, other.id);
    }

    /**
     * Este método genera el hashcode, que es un integer generado por un algoritmo
     * de hashing.
     * Para ello, ha usado el ID.
     *
     * @return int Hashcode generado en base al ID del préstamo.
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Método toString que genera una String con toda la información del objeto.
     *
     * @return String formateada con toda la información de la instancia de la clase
     *         Prestamo.
     * @since 1.0
     */
    @Override
    public String toString() {
        return String.format("Préstamo Nº: %s, fecha de inicio: %s, fecha de fin: %s, Usuario Nº: %s, idLibro Nº: %s",
                id, fechaInicio, fechaFin, idUsuario, idLibro);
    }
}
