package com.lvg.modelo.dto;

import java.util.Objects;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String password;
    private String rol;

    public Usuario(int id, String nombre, String email, String password, String rol) {
        setId(id);
        setNombre(nombre);
        setEmail(email);
        setPassword(password);
        setRol(rol);
    }

    public Usuario(String nombre, String email, String password, String rol) {
        setNombre(nombre);
        setEmail(email);
        setPassword(password);
        setRol(rol);
    }

    public Usuario(String nombre, String password, String rol) {
        setNombre(nombre);
        setPassword(password);
        setRol(rol);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        if (nombre.matches("[a-zA-Z0-9.]{6,}")) {
            this.nombre = nombre;
        }
    }
    public Usuario(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            this.email = email;
        }
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.matches(".{6,}$")) {
            this.password = password;
        }
    }
    

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return String.format("Usuario [id=%s, nombre=%s, email=%s, password=%s, rol=%s]", id, nombre, email, password, rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, password);
    }

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