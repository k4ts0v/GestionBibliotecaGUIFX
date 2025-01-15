package com.lvg.modelo.dto;

import java.util.Objects;

public class Prestamo {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    private int id;
    private String fechaInicio;
    private String fechaFin;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    private int idUsuario;
    private int idLibro;
    public Prestamo(int id) {
        this.id = id;
    }
    public Prestamo(Integer idPrestamo, String fechaInicio, String fechaFin, int idUsuario, int idLibro) {
        this.id = idPrestamo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
    }
    public Prestamo(String fechaInicio, String fechaFin, int idUsuario, int idLibro ) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Libro))
            return false;
        Libro other = (Libro) obj;
        return Objects.equals(id, other.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Préstamo Nº: %s, fecha de inicio: %s, fecha de fin: %s, Usuario Nº: %s, idLibro Nº: %s]", id, fechaInicio, 
                fechaFin, idUsuario, idLibro);
    }

}