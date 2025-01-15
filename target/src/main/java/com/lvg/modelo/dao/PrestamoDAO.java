package com.lvg.modelo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import com.lvg.modelo.dto.Prestamo;
public class PrestamoDAO implements DAO<Prestamo> {
    private Connection conexion = JDBC.getConexion();
    private final String CREATE = "INSERT INTO Prestamo VALUES(?, ?, ?, ?, ?)";
    private final String READ = "SELECT * FROM Prestamo WHERE id = ?";
    private final String READUSUARIO = "SELECT * FROM Prestamo WHERE usuarioID = ?";
    private final String READALL = "SELECT * FROM Prestamo";
    private final String UPDATE = "UPDATE Prestamo SET fechaInicio=?, fechaFin=?, usuarioId=?, LibroId=? WHERE id = ?";
    private final String DELETE = "DELETE FROM Prestamo WHERE id = ?";

    public Integer create(Prestamo p) throws SQLException {
        try (PreparedStatement ps = conexion.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, 0);
            ps.setString(2, p.getFechaInicio());
            ps.setString(3, p.getFechaFin());
            ps.setInt(4, p.getIdUsuario());
            ps.setInt(5, p.getIdLibro());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("la inserción del prestamo ha fallado."); 
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                    return p.getId();
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error creando el Prestamo: " + e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public Prestamo read(Prestamo p) throws SQLException {
        try {
        PreparedStatement ps = conexion.prepareStatement(READ);
        ps.setInt(1, p.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return crearPrestamo(rs);
        }
        } catch(SQLException e) {
            throw new SQLException();
        }
        return null;
    }
    @Override
    public ArrayList<Prestamo> readAll() throws SQLException {
        ArrayList<Prestamo> listaPrestamo= new ArrayList<>();
        try {
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(READALL);
        while (rs.next()) {
            listaPrestamo.add(crearPrestamo(rs));
        }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return listaPrestamo;
    }

    public ArrayList<Prestamo> readUsuario(Integer idUsuario) throws SQLException {
        ArrayList<Prestamo> listaPrestamo= new ArrayList<>();
        try {
        PreparedStatement ps = conexion.prepareStatement(READUSUARIO);
        ps.setInt(1, idUsuario);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(crearPrestamo(rs));
            listaPrestamo.add(crearPrestamo(rs));
        }
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new SQLException();
        }
        return listaPrestamo;
    }
    
    @Override
    public Integer update(Prestamo p) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE);
            ps.setString(1, p.getFechaInicio());
            ps.setString(2, p.getFechaFin());
            ps.setInt(3, p.getIdUsuario());
            ps.setInt(4, p.getIdLibro());
            ps.setInt(5, p.getId());

            System.out.println(String.format("UPDATE Prestamo SET fechaInicio=%s, fechaFin=%s, usuarioId=%s LibroId=%s WHERE id = %s", p.getFechaInicio(), p.getFechaFin(), p.getIdUsuario(), p.getIdLibro(), p.getId()));
            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println("Se produjo un error al actualizar el prestamo: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException();
        }
    }

    @Override
    public Integer delete(Prestamo p) throws SQLException {
        try {
        PreparedStatement ps = conexion.prepareStatement(DELETE);
        ps.setInt(1, p.getId());
        return ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Se produjo un error al borrar el prestamo: " + e.getMessage());
            throw new SQLException();
        }
    }
    public Prestamo crearPrestamo(ResultSet rs) throws SQLException {
        return new Prestamo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
    }
}