import java.util.ArrayList;

public class GestorInventario {
    private ArrayList<Producto> inventario;

    public GestorInventario() {
        this.inventario = new ArrayList<>();
    }

    // NUEVO: Validar producto nulo
    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        inventario.add(producto);
        System.out.println("✓ Producto agregado: " + producto.getNombre());
    }

    // NUEVO: Validar lista vacía e índice
    public Producto obtenerProducto(int indice) throws ListaVaciaException {
        if (inventario.isEmpty()) {
            throw new ListaVaciaException("No hay productos en el inventario");
        }

        if (indice < 0 || indice >= inventario.size()) {
            throw new IndexOutOfBoundsException(
                "Índice inválido: " + indice + ". Rango válido: 0-" + (inventario.size() - 1)
            );
        }

        return inventario.get(indice);
    }

    // NUEVO: Validar lista vacía
    public double calcularTotalInventario() throws ListaVaciaException {
        if (inventario.isEmpty()) {
            throw new ListaVaciaException("No se puede calcular el total de un inventario vacío");
        }

        double total = 0;
        for (Producto producto : inventario) {
            total += producto.calcularTotal();
        }
        return total;
    }

    // NUEVO: Validar lista vacía e índice
    public void eliminarProducto(int indice) throws ListaVaciaException {
        if (inventario.isEmpty()) {
            throw new ListaVaciaException("No hay productos para eliminar");
        }

        if (indice < 0 || indice >= inventario.size()) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
        }

        Producto eliminado = inventario.remove(indice);
        System.out.println("✓ Producto eliminado: " + eliminado.getNombre());
    }

    // NUEVO: Validar lista vacía
    public void mostrarInventario() throws ListaVaciaException {
        if (inventario.isEmpty()) {
            throw new ListaVaciaException("El inventario está vacío");
        }

        System.out.println("\n=== INVENTARIO COMPLETO ===");
        for (int i = 0; i < inventario.size(); i++) {
            System.out.println("[" + i + "] " + inventario.get(i));
        }
        System.out.println("================================\n");
    }

    public int cantidadProductos() {
        return inventario.size();
    }

    public boolean estaVacio() {
        return inventario.isEmpty();
    }
}