import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Conectar conexion = new Conectar();
        Connection cn = null;
        try {
            // Conectar a la base de datos
            cn = conexion.conectar();
           Interfaz base = new Implementacion(cn);

            // Crear la tabla usuarios si no existe
            base.crearTabla();

            Scanner scanner = new Scanner(System.in);
            boolean ejecutar = true;

            // Menú interactivo para realizar operaciones CRUD
            while (ejecutar) {
                System.out.println("\nMenú:");
                System.out.println("1. Crear nuevo usuario");
                System.out.println("2. Modificar usuario");
                System.out.println("3. Borrar usuario");
                System.out.println("4. Mostrar usuarios por país");
                System.out.println("5. Mostrar todos los usuarios");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        // Crear un nuevo usuario
                        System.out.print("Ingrese DNI: ");
                        String dni = scanner.nextLine();
                        System.out.print("Ingrese nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese país: ");
                        String pais = scanner.nextLine();
                        try {
                            base.insertarUsuario(dni, nombre, pais);
                            System.out.println("Usuario creado exitosamente.");
                        } catch (SQLException e) {
                            System.out.println("Error al crear el usuario.");
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        // Modificar un usuario existente
                        System.out.print("Ingrese DNI del usuario a modificar: ");
                        dni = scanner.nextLine();
                        System.out.print("Ingrese nuevo nombre: ");
                        nombre = scanner.nextLine();
                        System.out.print("Ingrese nuevo país: ");
                        pais = scanner.nextLine();
                        try {
                            base.modificarUsuario(dni, nombre, pais);
                            System.out.println("Usuario modificado exitosamente.");
                        } catch (SQLException e) {
                            System.out.println("Error al modificar el usuario.");
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        // Borrar un usuario
                        System.out.print("Ingrese DNI del usuario a borrar: ");
                        dni = scanner.nextLine();
                        try {
                            base.borrarUsuario(dni);
                            System.out.println("Usuario borrado exitosamente.");
                        } catch (SQLException e) {
                            System.out.println("Error al borrar el usuario.");
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        // Mostrar usuarios por país
                        System.out.print("Ingrese país: ");
                        pais = scanner.nextLine();
                        try {
                            List<String> usuarios = base.obtenerUsuariosPorPais(pais);
                            usuarios.forEach(System.out::println);
                        } catch (SQLException e) {
                            System.out.println("Error al obtener los usuarios.");
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        // Mostrar todos los usuarios
                        try {
                            List<String> usuarios = base.obtenerTodosLosUsuarios();
                            usuarios.forEach(System.out::println);
                        } catch (SQLException e) {
                            System.out.println("Error al obtener los usuarios.");
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        // Salir de la aplicación
                        ejecutar = false;
                        System.out.println("Cerrando la aplicación.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión a la base de datos
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

