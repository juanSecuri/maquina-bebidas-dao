package maquina_bebidas.datos;

import maquina_bebidas.dominio.Bebida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static maquina_bebidas.conexion.Conexion.getConexion;

public class BebidaDAO implements IBebidaDAO{
    //cumpliendo el contrato de la interface
    @Override
    public List<Bebida> listarBebidas() {
        List<Bebida> bebidas = new ArrayList<>();
        //conectar a la bd y usar SELECT
        //permite ejecutar la sentencia que se hace a la bd
        PreparedStatement ps;
        ResultSet rs; //recibe la info de la consulta que se realiza
        Connection con = getConexion();
        var sql = "SELECT * FROM bebida ORDER BY id";
        try{
            ps = con.prepareStatement(sql); //preparar la sentencia sql
            rs = ps.executeQuery(); //alamacenar el resultado y da el paso a ejecutar
            //iterar los resultados
            while (rs.next()){//hay registros para iterar?
                var bebida = new Bebida();
                //recuperando cada valor de registro que se esta iterando
                bebida.setId(rs.getInt("id"));
                bebida.setNombre(rs.getString("nombre"));
                bebida.setPrecio(rs.getDouble("precio"));
                bebidas.add(bebida);
            }
        } catch (Exception e) {
            System.out.println("Error a listar las bebidas: " + e.getMessage());
        }
        //cerra conexion a bd, limpiando recursos
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return bebidas;
    }

    @Override
    public boolean agregarBebida(Bebida bebida) {
        PreparedStatement ps;
        Connection con = getConexion();
        //cuando se usa insert, no se recupera info, sino que se inserta
        String sql = "INSERT INTO bebida(nombre, precio) " + "VALUES(?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, bebida.getNombre());
            ps.setDouble(2, bebida.getPrecio());
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error al agregar bebida: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarBebida(Bebida bebida) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "UPDATE bebida SET nombre = ?, precio = ? " + " WHERE id = ?";
        try{
            ps = con.prepareStatement(sql); //preparando la sentencia
            //recuperar los parametros
            ps.setString(1, bebida.getNombre());
            ps.setDouble(2, bebida.getPrecio());
            ps.setInt(3, bebida.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar bebida: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarBebida(Bebida bebida) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM bebida WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, bebida.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al eliminar bebida: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return false;
    }


    //prueba local
    public static void main(String[] args) {
        IBebidaDAO bebidaDAO = new BebidaDAO();
        //agregar bebida
//        var nuevaBebida = new Bebida("Capucchino Vainilla 400ml",170.90);
//        var agregada = bebidaDAO.agregarBebida(nuevaBebida);
//        //validar si se agregó la bebida
//        if(agregada){
//            System.out.println("Bebida agregada: " + nuevaBebida);
//        } else {
//            System.out.println("No se agregó la bebida: " + nuevaBebida);
//        }

        //modificar bebida
//        var modificarBebida = new Bebida(1, "Pepsi 350ml", 21.5);
//        var modificada = bebidaDAO.modificarBebida(modificarBebida);
//        //validar si se modificó la bebida
//        if (modificada) {
//            System.out.println("Bebida modificada: " + modificarBebida);
//        } else {
//            System.out.println("No se modificó la bebida: " + modificarBebida);
//        }

        //eliminar bebida
        var bebidaEliminar = new Bebida(4);
        var eliminada = bebidaDAO.eliminarBebida(bebidaEliminar);
        //validar si se eliminó la bebida
        if (eliminada) {
            System.out.println("Bebida eliminada: " + bebidaEliminar);
        } else {
            System.out.println("No se eliminó la bebida: " + bebidaEliminar);
        }

        //listar bebidas
        System.out.println("--- Listar Bebidas ---");
        var bebidas = bebidaDAO.listarBebidas();
        bebidas.forEach(System.out::println);


    }
}
