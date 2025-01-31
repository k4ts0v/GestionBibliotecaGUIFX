package com.lvg.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lvg.modelo.dto.Libro;
import com.lvg.modelo.dto.Prestamo;

/**
 * Esta clase representa el DAO (Data Access Object) para la entidad Prestamo.
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @since 1.0
 */
public class PrestamoDAO implements DAO<Prestamo> {
    private Connection conexion = JDBC.getConexion();
    private final String CREATE = "INSERT INTO Prestamo VALUES(?, ?, ?, ?, ?)";
    private final String READ = "SELECT * FROM Prestamo WHERE id = ?";
    private final String READUSUARIO = "SELECT * FROM Prestamo WHERE usuarioID = ?";
    private final String READALL = "SELECT * FROM Prestamo";
    private final String UPDATE = "UPDATE Prestamo SET fechaInicio=?, fechaFin=?, usuarioId=?, LibroId=? WHERE id = ?";
    private final String DELETE = "DELETE FROM Prestamo WHERE id = ?";

    /**
     * Este método añade un préstamo a la base de datos.
     *
     * @param p Objeto Prestamo a añadir.
     * @return Devuelve 1 si la operación se ha realizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido crear el préstamo en la base de datos.
     * @since 1.0
     */
    public Integer create(Prestamo p) throws SQLException {
        try (PreparedStatement ps = conexion.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, 0);
            ps.setString(2, p.getFechaInicio());
            ps.setString(3, p.getFechaFin());
            ps.setInt(4, p.getIdUsuario());
            ps.setInt(5, p.getIdLibro());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("La inserción del préstamo ha fallado."); 
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

    /**
     * Este método lee el préstamo especificado (por ID) de la base de datos.
     *
     * @param p Objeto Prestamo con el ID a buscar.
     * @return El objeto Prestamo que ha sido leído.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el préstamo de la base de datos.
     * @since 1.0
     */
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
            throw new SQLException("Error leyendo el Prestamo: " + e.getMessage(), e);
        }
        return null;
    }

    /**
     * Este método lee todos los préstamos que existen en la base de datos.
     *
     * @return ArrayList<Prestamo> Lista de los préstamos existentes en la base de datos.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el contenido de la base de datos.
     * @since 1.0
     */
    @Override
    public ArrayList<Prestamo> readAll() throws SQLException {
        ArrayList<Prestamo> listaPrestamo = new ArrayList<>();
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(READALL);
            while (rs.next()) {
                listaPrestamo.add(crearPrestamo(rs));
            }
        } catch (SQLException e) {
            throw new SQLException("Error leyendo todos los Prestamos: " + e.getMessage(), e);
        }
        return listaPrestamo;
    }

    /**
     * Este método lee todos los préstamos asociados a un usuario específico.
     *
     * @param idUsuario ID del usuario.
     * @return ArrayList<Prestamo> Lista de los préstamos del usuario.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el contenido de la base de datos.
     * @since 1.0
     */
    public ArrayList<Prestamo> readUsuario(Integer idUsuario) throws SQLException {
        ArrayList<Prestamo> listaPrestamo = new ArrayList<>();
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

    /**
     * Este método actualiza un préstamo de la base de datos en base a su ID.
     *
     * @param p Préstamo a actualizar.
     * @return Devuelve 1 si se ha actualizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido actualizar el préstamo.
     * @since 1.0
     */
    @Override
    public Integer update(Prestamo p) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE);
            ps.setString(1, p.getFechaInicio());
            ps.setString(2, p.getFechaFin());
            ps.setInt(3, p.getIdUsuario());
            ps.setInt(4, p.getIdLibro());
            ps.setInt(5, p.getId());

            System.out.println(String.format("UPDATE Prestamo SET fechaInicio=%s, fechaFin=%s, usuarioId=%s, LibroId=%s WHERE id = %s", p.getFechaInicio(), p.getFechaFin(), p.getIdUsuario(), p.getIdLibro(), p.getId()));
            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println("Se produjo un error al actualizar el préstamo: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Error actualizando el Prestamo: " + e.getMessage(), e);
        }
    }

    /**
     * Este método borra un préstamo de la base de datos en base a su ID.
     *
     * @param p Préstamo a borrar.
     * @return Devuelve 1 si el préstamo se ha borrado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido borrar el préstamo.
     * @since 1.0
     */
    @Override
    public Integer delete(Prestamo p) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(DELETE);
            ps.setInt(1, p.getId());
            return ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Se produjo un error al borrar el préstamo: " + e.getMessage());
            throw new SQLException("Error borrando el Prestamo: " + e.getMessage(), e);
        }
    }

    /**
     * Este método genera un préstamo en base al ResultSet devuelto por una consulta.
     *
     * @param rs ResultSet de la consulta.
     * @return Objeto Prestamo con los datos de la consulta.
     * @throws SQLException Lanza una SQLException si no se ha podido crear el préstamo.
     * @since 1.0
     */
    public Prestamo crearPrestamo(ResultSet rs) throws SQLException {
        return new Prestamo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
    }
}