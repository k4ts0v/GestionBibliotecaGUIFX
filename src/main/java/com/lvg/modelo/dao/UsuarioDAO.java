package com.lvg.modelo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import com.lvg.modelo.dto.Usuario;
public class UsuarioDAO implements DAO<Usuario> {
    private Connection conexion = JDBC.getConexion();
    private final String CREATE = "INSERT INTO Usuario VALUES(?, ?, ?, ? ,?)";
    private final String READ = "SELECT * FROM Usuario WHERE id = ?";
    private final String READALL = "SELECT * FROM Usuario ORDER BY id";
    private final String VERIFICARUSUARIOMIEMBRO = "SELECT COUNT(*) AS total FROM Usuario WHERE nombre = ? AND contrasena = ? AND rol = 'Miembro'";
    private final String VERIFICARUSUARIOADMINISTRADOR = "SELECT COUNT(*) AS total FROM Usuario WHERE nombre = ? AND contrasena = ? AND rol = 'Administrador'"; 
    private final String UPDATE = "UPDATE Usuario SET contrasena=? WHERE nombre = ?";
    private final String DELETE = "DELETE * FROM Usuario WHERE id = ?";

    @Override
    public Integer create(Usuario u) throws SQLException{
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
            throw new SQLException();
        }
        return -1;
    }

    @Override
    public Usuario read(Usuario u) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(READ);
            ps.setInt(1, u.getId());
            ResultSet rs = ps.executeQuery();
            Usuario usuario = crearUsuario(rs);
            return usuario;
        } catch(SQLException e) {
            throw new SQLException();
        }
    }
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
            throw new SQLException();
        }
        return listaUsuarios;

    }
    @Override
    public Integer update(Usuario u) throws SQLException{
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE);
            ps.setString(1, u.getPassword());
            ps.setString(2, u.getNombre());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
    @Override
    public Integer delete(Usuario u) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(DELETE);
            ps.setInt(1, u.getId());
            return ps.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException();
        }
    }
    public Usuario crearUsuario(ResultSet rs) throws SQLException {
        return new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
    }

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
            throw e; // Re-lanza la excepción para el manejo externo.
        }
        return false;
    }

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
            throw e; // Re-lanza la excepción para el manejo externo.
        }
        return false;
    }
    
}
