package maquina_bebidas.dominio;
import java.util.Objects;

public class Bebida {
    //atributos de la clase dominio
    private int id;
    private String nombre;
    private Double precio;

    //se crea un constructor vacio de la clase de nuestro dominio
    public Bebida(){}

    //constructor con parametros id
    public Bebida(int id){
        this.id = id;
    }

    //constructor para la insercion de objetos de tipo bebida
    public Bebida(String nombre, Double precio){
        this.nombre = nombre;
        this.precio = precio;
    }

    //constructor con todos los parametros
    public Bebida(int id, String nombre, double precio){
        this(nombre, precio);
        this.id = id;
    }

    //encapsulamiento getters y setters
    public int getId() {
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public Double getPrecio(){
        return this.precio;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }

    //sobreescribir toString
    @Override
    public String toString() {
        return "Bebida{" +
                "id=" + this.id +
                ", nombre='" + this.nombre + '\'' +
                ", precio=" + this.precio +
                '}';
    }

    //aqui puede ser el metodo de escribir como parseo de archivos
    /*code*/

    //se va a agregar en una lista de bebidas, se recomienda:
    //esto es para agilizar la busqueda de tipo bebida
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bebida bebida = (Bebida) o;
        return id == bebida.id && Objects.equals(this.nombre, bebida.nombre) && Objects.equals(this.precio, bebida.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nombre, this.precio);
    }
}
