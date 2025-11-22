import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> productos;
    private String nombreTienda;

    public Inventario(String nombreTienda) {
        if (nombreTienda == null || nombreTienda.isEmpty()) {
            throw new IllegalArgumentException("Nombre de tienda obligatorio");
        }
        this.nombreTienda = nombreTienda;
        this.productos = new ArrayList<>();
    }

    public static String generarCodigo(String categoria) {
        return categoria.substring(0, 4).toUpperCase() + "-" + (int)(Math.random() * 1000);
    }

    public void agregarProducto(Producto p) {
        if (p == null) {
            throw new NullPointerException("No se puede agregar un producto nulo");
        }
        productos.add(p);
    }

    public Producto buscarPorCodigo(String codigo) throws ProductoNoEncontradoException {
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        throw new ProductoNoEncontradoException("El producto " + codigo + " no existe en inventario.");
    }

    public double venderProducto(String codigo, int cantidad) throws StockInsuficienteException {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }

        try {
            Producto p = buscarPorCodigo(codigo);

            if (p.getStock() < cantidad) {
                throw new StockInsuficienteException("Stock insuficiente. Disponible: " + p.getStock());
            }

            p.reducirStock(cantidad);
            return p.calcularPrecioFinal() * cantidad;

        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error en venta: " + e.getMessage());
            return 0.0;
        }
    }

    public double calcularValorInventario() {
        if (productos.isEmpty()) {
            throw new IllegalStateException("El inventario está vacío, no se puede calcular valor.");
        }
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularPrecioFinal() * p.getStock();
        }
        return total;
    }

    public ArrayList<Producto> listarProductosBajoStock(int minimo) {
        ArrayList<Producto> bajoStock = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getStock() < minimo) {
                bajoStock.add(p);
            }
        }
        return bajoStock;
    }

    public void ordenarPorPrecio() {
        int n = productos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (productos.get(j).calcularPrecioFinal() > productos.get(j + 1).calcularPrecioFinal()) {
                    Producto temp = productos.get(j);
                    productos.set(j, productos.get(j + 1));
                    productos.set(j + 1, temp);
                }
            }
        }
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
}