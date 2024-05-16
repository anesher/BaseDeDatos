import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {
    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3309/baseDeDatos?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USUARIO = "root";
    private static final String CLAVE = "1234";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = (Connection) DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexión OK");
        } catch (SQLException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }
        return conexion;
    }
}
