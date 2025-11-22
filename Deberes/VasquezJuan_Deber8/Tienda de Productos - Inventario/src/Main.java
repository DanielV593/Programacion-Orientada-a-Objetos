public class Main {
    public static void main(String[] args) {
        System.out.println("--- 1. PRUEBAS DE EXCEPCIONES EN CONSTRUCTORES ---");

        try {
            new ProductoAlimenticio("TEST1", "Leche", -5.0, 10, "2025", true);
        } catch (PrecioInvalidoException e) {
            System.out.println("EXCEPCIÓN CAPTURADA: " + e.getMessage());
        }

        try {
            new ProductoElectronico("", "TV", 500.0, 5, "Sony", 12);
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPCIÓN CAPTURADA: " + e.getMessage());
        }

        System.out.println("\n--- 2. LLENANDO INVENTARIO ---");
        Inventario tienda = new Inventario("Supermercado POO");

        tienda.agregarProducto(new ProductoElectronico(Inventario.generarCodigo("ELEC"), "Laptop", 1000.0, 10, "Dell", 24));
        tienda.agregarProducto(new ProductoElectronico("ELEC-002", "Mouse", 20.0, 50, "Genius", 6));
        tienda.agregarProducto(new ProductoAlimenticio("ALIM-001", "Arroz", 1.50, 100, "2026", false));
        tienda.agregarProducto(new ProductoAlimenticio("ALIM-002", "Yogurt", 0.80, 20, "2024", true));
        tienda.agregarProducto(new ProductoRopa("ROPA-001", "Camiseta", 15.0, 3, "M", "Algodon"));
        tienda.agregarProducto(new ProductoRopa("ROPA-002", "Jean", 40.0, 30, "32", "Mezclilla"));
        tienda.agregarProducto(new ProductoElectronico("ELEC-003", "Teclado", 45.0, 2, "HP", 12));
        tienda.agregarProducto(new ProductoAlimenticio("ALIM-003", "Pan", 0.15, 500, "2024", false));
        tienda.agregarProducto(new ProductoRopa("ROPA-003", "Medias", 2.0, 150, "U", "Lana"));
        tienda.agregarProducto(new ProductoElectronico("ELEC-004", "Monitor", 200.0, 8, "LG", 12));

        System.out.println("Productos agregados: " + tienda.getProductos().size());

        System.out.println("\n--- 3. PRUEBAS DE VENTAS ---");

        try {
            double total = tienda.venderProducto("ELEC-002", 5);
            System.out.println("Venta Exitosa (Mouse x5). Total: $" + total);
        } catch (StockInsuficienteException e) {
            System.out.println(e.getMessage());
        }

        try {
            tienda.venderProducto("ALIM-001", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR ESPERADO VENTA: " + e.getMessage());
        } catch (StockInsuficienteException e) {
            System.out.println("Error de stock");
        }

        try {
            tienda.venderProducto("ROPA-001", 10);
        } catch (StockInsuficienteException e) {
            System.out.println("EXCEPCIÓN CHECKED CAPTURADA: " + e.getMessage());
        }

        try {
            tienda.venderProducto("XYZ-999", 1);
        } catch (StockInsuficienteException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- 4. BÚSQUEDAS Y REPORTES ---");

        try {
            Producto p = tienda.buscarPorCodigo("XYZ-000");
            System.out.println(p);
        } catch (ProductoNoEncontradoException e) {
            System.out.println("EXCEPCIÓN CAPTURADA (Búsqueda): " + e.getMessage());
        }

        System.out.println("\n-> Productos con Stock < 5:");
        for(Producto p : tienda.listarProductosBajoStock(5)) {
            System.out.println(" - " + p.getNombre() + " (Stock: " + p.getStock() + ")");
        }

        System.out.println("\n-> Valor Total del Inventario: $" + tienda.calcularValorInventario());

        System.out.println("\n--- 5. ORDENAR POR PRECIO (Algoritmo Manual) ---");
        tienda.ordenarPorPrecio();
        for(Producto p : tienda.getProductos()) {
            System.out.println(p);
        }
    }
}