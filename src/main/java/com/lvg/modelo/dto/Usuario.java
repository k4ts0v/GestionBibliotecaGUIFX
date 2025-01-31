package com.lvg.modelo.dto;

import java.util.Objects;

/**
 * Clase que representa un usuario en la biblioteca.
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @since 1.0
 */
public class Usuario {
    private int id; // ID del usuario.
    private String nombre; // Nombre del usuario.
    private String email; // Email del usuario.
    private String password; // Contraseña del usuario.
    private String rol; // Rol del usuario.

    /**
     * Constructor del usuario.
     *
     * @param id ID del usuario.
     * @param nombre Nombre del usuario.
     * @param email Email del usuario.
     * @param password Contraseña del usuario.
     * @param rol Rol del usuario.
     * @since 1.0
     */
    public Usuario(int id, String nombre, String email, String password, String rol) {
        setId(id);
        setNombre(nombre);
        setEmail(email);
        setPassword(password);
        setRol(rol);
    }

    /**
     * Constructor del usuario.
     *
     * @param nombre Nombre del usuario.
     * @param email Email del usuario.
     * @param password Contraseña del usuario.
     * @param rol Rol del usuario.
     * @since 1.0
     */
    public Usuario(String nombre, String email, String password, String rol) {
        setNombre(nombre);
        setEmail(email);
        setPassword(password);
        setRol(rol);
    }

    /**
     * Constructor del usuario.
     *
     * @param nombre Nombre del usuario.
     * @param password Contraseña del usuario.
     * @param rol Rol del usuario.
     * @since 1.0
     */
    public Usuario(String nombre, String password, String rol) {
        setNombre(nombre);
        setPassword(password);
        setRol(rol);
    }

    // Getters y setters

    /**
     * Getter que devuelve el ID del usuario.
     *
     * @return ID del usuario.
     * @since 1.0
     */
    public int getId() {
        return id;
    }

    /**
     * Setter que asigna el ID a una instancia de usuario.
     *
     * @param id ID a asignar.
     * @since 1.0
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter que devuelve el nombre del usuario.
     *
     * @return Nombre del usuario.
     * @since 1.0
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter que asigna el nombre a una instancia de usuario.
     *
     * @param nombre Nombre a asignar.
     * @since 1.0
     */
    public void setNombre(String nombre) {
        if (nombre.matches("[a-zA-Z0-9.]{6,}")) {
            this.nombre = nombre;
        }
    }

    /**
     * Constructor del usuario con solo el nombre.
     *
     * @param nombre Nombre del usuario.
     * @since 1.0
     */
    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter que devuelve el email del usuario.
     *
     * @return Email del usuario.
     * @since 1.0
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter que asigna el email a una instancia de usuario.
     *
     * @param email Email a asignar.
     * @since 1.0
     */
    public void setEmail(String email) {
        if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            this.email = email;
        }
    }

    /**
     * Getter que devuelve la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     * @since 1.0
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter que asigna la contraseña a una instancia de usuario.
     *
     * @param password Contraseña a asignar.
     * @since 1.0
     */
    public void setPassword(String password) {
        if (password.matches(".{6,}$")) {
            this.password = password;
        }
    }

    /**
     * Getter que devuelve el rol del usuario.
     *
     * @return Rol del usuario.
     * @since 1.0
     */
    public String getRol() {
        return rol;
    }

    /**
     * Setter que asigna el rol a una instancia de usuario.
     *
     * @param rol Rol a asignar.
     * @since 1.0
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Método toString que genera una String con toda la información del objeto.
     *
     * @return String formateada con toda la información de la instancia de la clase Usuario.
     * @since 1.0
     */
    @Override
    public String toString() {
        return String.format("Usuario [id=%s, nombre=%s, email=%s, password=%s, rol=%s]", id, nombre, email, password, rol);
    }

    /**
     * Este método genera el hashcode, que es un integer generado por un algoritmo de hashing.
     * Para ello, ha usado el nombre y la contraseña.
     *
     * @return int Hashcode generado en base al nombre y la contraseña del usuario.
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, password);
    }

    /**
     * Compara dos objetos para ver si son iguales.
     * En este caso, solo son iguales si son dos instancias de Usuario y poseen el mismo nombre y contraseña.
     *
     * @param obj La otra instancia de usuario a usar para la comparación.
     * @return boolean Si son iguales, devuelve verdadero; de lo contrario, falso.
     * @since 1.0
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Usuario))
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(nombre, other.nombre) && Objects.equals(password, other.password);
    }
}