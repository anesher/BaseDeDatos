import java.sql.SQLException;
import java.util.List;

public interface Interfaz {
        void crearTabla() throws SQLException; // Método para crear la tabla usuarios si no existe
        void insertarUsuario(String dni, String nombre, String pais) throws SQLException; // Método para insertar un nuevo usuario
        void modificarUsuario(String dni, String nombre, String pais) throws SQLException; // Método para modificar un usuario existente
        void borrarUsuario(String dni) throws SQLException; // Método para borrar un usuario
        List<String> obtenerUsuariosPorPais(String pais) throws SQLException; // Método para obtener usuarios por país
        List<String> obtenerTodosLosUsuarios() throws SQLException; // Método para obtener todos los usuarios
    }


