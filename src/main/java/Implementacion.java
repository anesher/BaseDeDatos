import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Implementacion implements Interfaz{

    // Implementación de la interfaz UsuarioDAO
        private final Connection conexion;

        // Constructor que recibe una conexión a la base de datos
        public Implementacion(Connection conexion) {
            this.conexion = conexion;
        }

        // Crear la tabla usuarios si no existe
        @Override
        public void crearTabla() throws SQLException {
            String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                    + "DNI VARCHAR(12) NOT NULL PRIMARY KEY, "
                    + "nombre VARCHAR(45), "
                    + "pais VARCHAR(45))";
            try (Statement stm = conexion.createStatement()) {
                stm.executeUpdate(sql);
            }
        }

        // Insertar un nuevo usuario en la tabla usuarios
        @Override
        public void insertarUsuario(String dni, String nombre, String pais) throws SQLException {
            // Definimos la consulta SQL para insertar un nuevo usuario en la tabla 'usuarios'.
            String sql = "INSERT INTO usuarios (DNI, nombre, pais) VALUES (?, ?, ?)";

            // Usamos un PreparedStatement para ejecutar la consulta de manera segura.
            // Esto previene ataques de inyección SQL y nos permite establecer los valores de los parámetros de manera segura.
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                // Asignamos el valor del parámetro 'dni' al primer placeholder (?) en la consulta SQL.
                pstmt.setString(1, dni);

                // Asignamos el valor del parámetro 'nombre' al segundo placeholder (?) en la consulta SQL.
                pstmt.setString(2, nombre);

                // Asignamos el valor del parámetro 'pais' al tercer placeholder (?) en la consulta SQL.
                pstmt.setString(3, pais);

                // Ejecutamos la consulta. 'executeUpdate' se utiliza para consultas SQL que modifican la base de datos
                // (como INSERT, UPDATE, DELETE), y devuelve el número de filas afectadas.
                pstmt.executeUpdate();
            }
        }

        // Modificar un usuario existente en la tabla usuarios
        @Override
        public void modificarUsuario(String dni, String nombre, String pais) throws SQLException {
            String sql = "UPDATE usuarios SET nombre = ?, pais = ? WHERE DNI = ?";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, pais);
                pstmt.setString(3, dni);
                pstmt.executeUpdate();
            }
        }

        // Borrar un usuario de la tabla usuarios
        @Override
        public void borrarUsuario(String dni) throws SQLException {
            String sql = "DELETE FROM usuarios WHERE DNI = ?";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, dni);
                pstmt.executeUpdate();
            }
        }

        // Obtener usuarios por país
        @Override
        public List<String> obtenerUsuariosPorPais(String pais) throws SQLException {
            String sql = "SELECT * FROM usuarios WHERE pais = ?";
            List<String> usuarios = new ArrayList<>();
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, pais);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        usuarios.add(rs.getString("nombre") + " - " + rs.getString("DNI"));
                    }
                }
            }
            return usuarios;
        }

        // Obtener todos los usuarios
        @Override
        public List<String> obtenerTodosLosUsuarios() throws SQLException {
            String sql = "SELECT * FROM usuarios";
            List<String> usuarios = new ArrayList<>();
            try (Statement stm = conexion.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
                while (rs.next()) {
                    usuarios.add(rs.getString("nombre") + " - " + rs.getString("DNI"));
                }
            }
            return usuarios;
        }
    }


