import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Conectar conexion = new Conectar();
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            stm.executeUpdate("CREATE TABLE Invitados (CIF varchar(12) NOT NULL, nombre" +
                    " VARCHAR(45), pais VARCHAR(45), PRIMARY KEY (CIF))");
            stm.execute("ALTER TABLE baseDeDatos.Invitados ADD COLUMN tipo INT;");
            stm.execute("INSERT INTO Invitados VALUES ('2222', 'HECTOR', 'SPAIN', 55);");
            rs = stm.executeQuery("SELECT * FROM Invitados");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String CIF = rs.getString("CIF");
                int tipo = rs.getInt("tipo");
                System.out.println(nombre + " - " + CIF + " - " + tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
