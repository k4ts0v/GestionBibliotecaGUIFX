package com.lvg.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lvg.modelo.dto.Usuario;

/**
 * Esta clase representa el DAO (Data Access Object) para la entidad Usuario.
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @since 1.0
 */
public class UsuarioDAO implements DAO<Usuario> {
    private Connection conexion = JDBC.getConexion();
    private final String CREATE = "INSERT INTO Usuario VALUES(?, ?, ?, ? ,?)";
    private final String READ = "SELECT * FROM Usuario WHERE id = ?";
    private final String READALL = "SELECT * FROM Usuario ORDER BY id";
    private final String VERIFICARUSUARIOMIEMBRO = "SELECT COUNT(*) AS total FROM Usuario WHERE nombre = ? AND contrasena = ? AND rol = 'Miembro'";
    private final String VERIFICARUSUARIOADMINISTRADOR = "SELECT COUNT(*) AS total FROM Usuario WHERE nombre = ? AND contrasena = ? AND rol = 'Administrador'"; 
    private final String UPDATE = "UPDATE Usuario SET contrasena=? WHERE nombre = ?";
    private final String DELETE = "DELETE * FROM Usuario WHERE id = ?";

    /**
     * Este método añade un usuario a la base de datos.
     *
     * @param u Objeto Usuario a añadir.
     * @return Devuelve 1 si la operación se ha realizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido crear el usuario en la base de datos.
     * @since 1.0
     */
    @Override
    public Integer create(Usuario u) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 0);
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getRol());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                u.setId(rs.getInt(1));
                return 1;
            }
        } catch (SQLException e) {
            throw new SQLException("Error creando el Usuario: " + e.getMessage(), e);
        }
        return -1;
    }

    /**
     * Este método lee el usuario especificado (por ID) de la base de datos.
     *
     * @param u Objeto Usuario con el ID a buscar.
     * @return El objeto Usuario que ha sido leído.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el usuario de la base de datos.
     * @since 1.0
     */
    @Override
    public Usuario read(Usuario u) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(READ);
            ps.setInt(1, u.getId());
            ResultSet rs = ps.executeQuery();
            Usuario usuario = crearUsuario(rs);
            return usuario;
        } catch(SQLException e) {
            throw new SQLException("Error leyendo el Usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Este método lee todos los usuarios que existen en la base de datos.
     *
     * @return ArrayList<Usuario> Lista de los usuarios existentes en la base de datos.
     * @throws SQLException Lanza una SQLException si no se ha podido leer el contenido de la base de datos.
     * @since 1.0
     */
    @Override
    public ArrayList<Usuario> readAll() throws SQLException {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(READALL);
            while(rs.next()) {
                listaUsuarios.add(crearUsuario(rs));
            }
        } catch (SQLException e) {
            throw new SQLException("Error leyendo todos los Usuarios: " + e.getMessage(), e);
        }
        return listaUsuarios;
    }

    /**
     * Este método actualiza un usuario de la base de datos en base a su nombre.
     *
     * @param u Usuario a actualizar.
     * @return Devuelve 1 si se ha actualizado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido actualizar el usuario.
     * @since 1.0
     */
    @Override
    public Integer update(Usuario u) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE);
            ps.setString(1, u.getPassword());
            ps.setString(2, u.getNombre());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error actualizando el Usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Este método borra un usuario de la base de datos en base a su ID.
     *
     * @param u Usuario a borrar.
     * @return Devuelve 1 si el usuario se ha borrado correctamente.
     * @throws SQLException Lanza una SQLException si no se ha podido borrar el usuario.
     * @since 1.0
     */
    @Override
    public Integer delete(Usuario u) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(DELETE);
            ps.setInt(1, u.getId());
            return ps.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException("Error borrando el Usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Este método genera un usuario en base al ResultSet devuelto por una consulta.
     *
     * @param rs ResultSet de la consulta.
     * @return Objeto Usuario con los datos de la consulta.
     * @throws SQLException Lanza una SQLException si no se ha podido crear el usuario.
     * @since 1.0
     */
    public Usuario crearUsuario(ResultSet rs) throws SQLException {
        return new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
    }

    /**
     * Este método verifica si un usuario con rol 'Miembro' existe en la base de datos.
     *
     * @param u Objeto Usuario a verificar.
     * @return Devuelve true si el usuario existe, false en caso contrario.
     * @throws SQLException Lanza una SQLException si no se ha podido verificar el usuario.
     * @since 1.0
     */
    public boolean verificarUsuarioMiembro(Usuario u) throws SQLException {
        try (PreparedStatement ps = conexion.prepareStatement(VERIFICARUSUARIOMIEMBRO)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getPassword());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("total");
                    System.out.println("Usuario encontrado: " + count);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar usuario: " + e.getMessage());
            throw new SQLException("Error verificando el Usuario: " + e.getMessage(), e);
        }
        return false;
    }

    /**
     * Este método verifica si un usuario con rol 'Administrador' existe en la base de datos.
     *
     * @param u Objeto Usuario a verificar.
     * @return Devuelve true si el usuario existe, false en caso contrario.
     * @throws SQLException Lanza una SQLException si no se ha podido verificar el usuario.
     * @since 1.0
     */
    public boolean verificarUsuarioAdministrador(Usuario u) throws SQLException {
        try (PreparedStatement ps = conexion.prepareStatement(VERIFICARUSUARIOADMINISTRADOR)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getPassword());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("total");
                    System.out.println("Usuario encontrado: " + count);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar usuario: " + e.getMessage());
            throw new SQLException("Error verificando el Usuario: " + e.getMessage(), e);
        }
        return false;
    }
}