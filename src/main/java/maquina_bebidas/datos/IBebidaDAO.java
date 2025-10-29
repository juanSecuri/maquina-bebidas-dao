package maquina_bebidas.datos;

import maquina_bebidas.dominio.Bebida;

import java.util.List;

public interface IBebidaDAO {
    //definir metodos abstractos  y publicos
    List<Bebida> listarBebidas();
    //valores booleanos, si se encontró o agregó el registro correctamente
    boolean agregarBebida(Bebida bebida);
    boolean modificarBebida(Bebida bebida);
    boolean eliminarBebida(Bebida bebida);
}
