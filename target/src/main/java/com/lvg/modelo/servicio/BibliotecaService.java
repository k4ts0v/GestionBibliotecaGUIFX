package com.lvg.modelo.servicio;

import java.sql.SQLException;
import java.util.ArrayList;

import com.lvg.ContextoApp;
import com.lvg.modelo.dao.DDL;
import com.lvg.modelo.dao.LibroDAO;
import com.lvg.modelo.dao.PrestamoDAO;
import com.lvg.modelo.dao.UsuarioDAO;
import com.lvg.modelo.dto.Libro;
import com.lvg.modelo.dto.Prestamo;
import com.lvg.modelo.dto.Usuario;

public class BibliotecaService {
    private DDL ddl;

    private LibroDAO lDAO;
    private PrestamoDAO pDAO;
    private UsuarioDAO uDAO;
    private ArrayList<Libro> listaLibros;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Prestamo> listaPrestamos;
    private ArrayList<Prestamo> listaPrestamosUsuario;

    public BibliotecaService() {
        init();
    }

    /**
     * Este método inicializa las listas en memoria mediante el ReadAll de los DAOs.
     * 
     * @throws SQLException Lanza una excepción si no se ha podido leer el contenido
     *                      de la BD.
     */
    private void init() {
        ddl = new DDL();
        lDAO = new LibroDAO();
        pDAO = new PrestamoDAO();
        uDAO = new UsuarioDAO();
        try {
            ddl.run();
            listaLibros = lDAO.readAll();
            listaUsuarios = uDAO.readAll();
            listaPrestamos = pDAO.readAll();

        } catch (SQLException e) {
            System.out.println("Se ha producido un error cargando el contenido: " + e.getMessage());
        }
    }

    /**
     * Este método añade un libro de la lista y de la base de datos.
     * 
     * @param l Libro a añadir.
     * @return Integer - Resultado de la operación:
     *         1 -> Realizado correctamente.
     *         -1 -> No realizado, ha habido algún error.
     * @throws SQLException Lanza una excepción si no se ha añadido correctamente de
     *                      la BD.
     */
    public Integer anhadirLibro(Libro l) throws SQLException {
        try {
            if (lDAO.create(l) == 1) {
                listaLibros.add(l);
                return 1;
            }
        } catch (SQLException e) {
            throw e;
        }
        return -1;
    }

    /**
     * Este método lee a un libro de la lista y de la base de datos.
     * 
     * @param l Libro a leer.
     * @return Integer - Resultado de la operación:
     *         1 -> Realizado correctamente.
     *         -1 -> No realizado, ha habido algún error.
     * @throws SQLException Lanza una excepción si no se ha leído correctamente de
     *                      la BD.
     */
    public Integer mostrarLibro(Libro l) {
        try {
            Libro lBD = lDAO.read(l);
            if (listaLibros.contains(lBD)) {
                System.out.println(lBD.toString());
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("No se encontró el libro: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Este método lee todo el contenido de la lista.
     * 
     * @return 1 - Si se ha leído correctamente.
     */
    public Integer mostrarTodosLibros() {
        listaLibros.forEach(System.out::println);
        return 1;
    }

    /**
     * Este método actualiza a un libro de la lista y de la base de datos.
     * 
     * @param l Libro a actualizar.
     * @return Integer - Resultado de la operación:
     *         1 -> Realizado correctamente.
     *         -1 -> No realizado, ha habido algún error.
     * @throws SQLException Lanza una excepción si no se ha actualizado
     *                      correctamente de la BD.
     */
    public Integer actualizarLibro(Libro l) throws SQLException {
        try {
            if (lDAO.update(l) == 1) {
                listaLibros.remove(l);
                listaLibros.add(l);
                return 1;
            }
        } catch (SQLException e) {
            throw e;
        }
        return -1;
    }

    /**
     * Este método borra a un libro de la lista y de la base de datos.
     * 
     * @param l Libro a borrar.
     * @return Integer - Resultado de la operación:
     *         1 -> Realizado correctamente.
     *         -1 -> No realizado, ha habido algún error.
     * @throws SQLException Lanza una excepción si no se ha borrado correctamente de
     *                      la BD.
     */
    public Integer borrarLibro(Libro l) {
        try {
            if (lDAO.delete(l) == 1) {
                listaLibros.remove(l);
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("Se produjo un error borrando el libro: " + e.getMessage());
        }
        return -1;
    }

    public Integer anhadirUsuario(Usuario u) {
        try {
            if (uDAO.create(u) == 1) {
                listaUsuarios.add(u);
                return 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public Integer leerUsuario(Usuario u) {
        try {
            u = uDAO.read(u);
            if (listaUsuarios.contains(u)) {
                System.out.println(u.toString());
            }
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public void leerUsuarios() {
        for (Usuario u : listaUsuarios)
            System.out.println(u.toString());
    }

    public Integer updateUsuario(Usuario u) {
        try {
            if (uDAO.update(u) == 1) {
                listaUsuarios.remove(u);
                listaUsuarios.add(u);
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("Se produjo un error actualizando el usuario: " + e.getMessage());
        }
        return -1;
    }

    public Integer borrarUsuario(Usuario u) {
        try {
            if (uDAO.delete(u) == 1) {
                listaUsuarios.remove(u);
            }
            return 1;

        } catch (SQLException e) {
            {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }

    public Integer anhadirPrestamo(Prestamo p) throws SQLException{
        try {
            if (pDAO.create(p) == 1) {
                listaPrestamos.add(p);
                return 1;
            }
        } catch (SQLException e) {
            throw e;
        }
        return -1;
    }

    public Integer actualizarPrestamo(Prestamo p) throws SQLException {
        try {
            if (pDAO.update(p) == 1) {
                listaPrestamos.remove(p);
                listaPrestamos.add(p);
                return 1;
            }
        } catch (SQLException e) {
            throw e;
        }
        return -1;
    }

    public Integer borrarPrestamo(Prestamo p) {
        try {
            if (pDAO.delete(p) == 1) {
                listaPrestamos.remove(p);
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("Se produjo un error borrando el préstamo: " + e.getMessage());
        }
        return -1;
    }

    public boolean verificarUsuarioMiembro(Usuario usuario) {
        try {
            if (uDAO.verificarUsuarioMiembro(usuario)) {
                ContextoApp.setUsuario(usuario);
                System.out.println("Usuario verificado exitosamente.");
                return true;
            } else {
                System.out.println("Usuario no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar el usuario: " + e.getMessage());
        }
        return false;
    }

    public boolean verificarUsuarioAdministrador(Usuario usuario) {
        try {
            if (uDAO.verificarUsuarioAdministrador(usuario)) {
                ContextoApp.setUsuario(usuario);
                System.out.println("Usuario verificado exitosamente.");
                return true;
            } else {
                System.out.println("Usuario no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar el usuario: " + e.getMessage());
        }
        return false;
    }

    public ArrayList<Libro> getListaLibros() {
        return listaLibros;
    }

    public ArrayList<Prestamo> getListaPrestamos() {
        return listaPrestamos;
    }

    public ArrayList<Prestamo> getListaPrestamosUsuario(Integer idUsuario) {
        try {
            listaPrestamosUsuario = pDAO.readUsuario(idUsuario);
            System.out.println("Préstamos del usuario: " + idUsuario);
            listaPrestamosUsuario.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPrestamosUsuario;
    }

    public String getUsuarioById(int id) {
        for (Usuario u : listaUsuarios) {
            System.out.println(u.getId());
            if (u.getId() == id) {
                return u.getNombre();
            }
        }
        return null;
    }

    public Usuario getUsuarioByData(String nombre, String contransenha, String rol) {
        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equals(nombre) && u.getPassword().equals(contransenha) && u.getRol().equals(rol)) {
                return u;
            }
        }
        return null;
    }

    public String getLibroById(int id) {
        for (Libro l : listaLibros) {
            if (l.getId() == id) {
                return l.getTitulo();
            }
        }
        return null;
    }
}