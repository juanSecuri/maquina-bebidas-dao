package maquina_bebidas.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        //crear el objeto llamado conexion
        Connection conexion = null;
        var baseDatos = "maquina_bebidas_db";
        //cadena de conexiona la BD
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "passwordAqui";
        try{
            //cargar la clase
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            System.out.println("Error al conectarnos a la Base de Datos: " + e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if (conexion != null) {
            System.out.println("Conexion exitosa: " + conexion);
        } else {
            System.out.println("Error al conectarse");
        }
    }
}
