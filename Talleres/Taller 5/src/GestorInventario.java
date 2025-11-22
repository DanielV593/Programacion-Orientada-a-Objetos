import java.util.ArrayList;
public class GestorInventario {
    private ArrayList<Producto> inventario;

    public GestorInventario(){
        this.inventario = new ArrayList<>();
    }

    public void agregarProducto(Producto producto){
        inventario.add(producto);
        System.out.println("Producto agregad: "+producto.getNombre());
    }

    public Producto obtenerProducto (int indice){
        return inventario.get(indice);
    }

    public double calcularTotalInventario(){
        double total = 0;
        for (Producto producto : inventario) {
            total+= producto.calcularTotal();
        }
        return total;
    }
    public void eliminarProducto(int indice){
        Producto eliminado = inventario.remove(indice);
        System.out.println("Producto eliminado: "+eliminado.getNombre());
    }

    public void mostrarInventario(){
        System.out.println("\n=== INVENTARIO ===");
        for (int i = 0; i <inventario.size(); i++){
            System.out.println("["+ i + "]"+ inventario.get(i));
        }
        System.out.println("".repeat(10)+"\n");
    }
    public int calcularProductos(){
        return inventario.size();
    }
    public boolean estaVacio(){
        return inventario.isEmpty();
    }
}

