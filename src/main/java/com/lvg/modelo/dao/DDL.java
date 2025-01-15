package com.lvg.modelo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DDL {

    private final Connection conexion;

    public DDL() {
        this.conexion = JDBC.getConexion();
    }

    /**
     * Verifica si una tabla contiene datos.
     *
     * @param nombreTabla Nombre de la tabla a verificar.
     * @return true si la tabla tiene datos, false en caso contrario.
     * @throws SQLException Si ocurre un error al consultar la tabla.
     */
    private boolean tablaTieneDatos(String nombreTabla) throws SQLException {
        String consulta = "SELECT EXISTS (SELECT 1 FROM " + nombreTabla + ")";
        try (Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(consulta)) {
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        }
        return false;
    }

    /**
     * Lee un archivo de script SQL y ejecuta sus sentencias, omitiendo
     * inserciones si las tablas correspondientes ya tienen datos.
     *
     * @param rutaArchivo La ruta al archivo de script SQL.
     * @throws IOException  Si hay un error al leer el archivo.
     * @throws SQLException Si hay un error al ejecutar las sentencias SQL.
     */
    public void ejecutarScript(String rutaArchivo) throws IOException, SQLException {
        if (conexion == null || conexion.isClosed()) {
            throw new IllegalStateException("La conexión a la base de datos no está establecida.");
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
                Statement statement = conexion.createStatement()) {

            StringBuilder acumuladorSQL = new StringBuilder();
            String linea;

            while ((linea = lector.readLine()) != null) {
                // Ignorar comentarios y líneas vacías
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("--")) {
                    continue;
                }

                // Detectar si es un bloque de inserción y omitirlo si ya hay datos
                if (linea.startsWith("INSERT INTO")) {
                    String nombreTabla = linea.split(" ")[2]; // Obtener el nombre de la tabla
                    if (tablaTieneDatos(nombreTabla)) {
                        System.out.println("Omitiendo inserciones para la tabla: " + nombreTabla + " (ya tiene datos)");
                        continue;
                    }
                }

                // Agregar la línea actual al comando SQL
                acumuladorSQL.append(linea).append(" ");

                // Si se detecta un comando SQL completo (termina con ";"), ejecutarlo
                if (linea.endsWith(";")) {
                    String sql = acumuladorSQL.toString();
                    statement.execute(sql); // Ejecutar la sentencia
                    acumuladorSQL.setLength(0); // Reiniciar el acumulador
                    System.out.println("Ejecutado: " + sql);
                }
            }

            // Ejecutar cualquier comando SQL restante sin punto y coma
            if (acumuladorSQL.length() > 0) {
                String sql = acumuladorSQL.toString();
                statement.execute(sql);
                System.out.println("Ejecutado: " + sql);
            }

            System.out.println("El script SQL se ejecutó correctamente.");
        }
    }

    public void run() {
        try {
            ejecutarScript("src/main/java/com/lvg/modelo/datos/bdBiblioteca.sql");
        } catch (IOException e) {
            System.out.println("Ocurrió un error leyendo el script." + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ocurrió un error ejecutando el script." + e.getMessage());
        }
    }
}
