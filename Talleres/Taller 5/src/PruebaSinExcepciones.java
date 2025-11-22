public class PruebaSinExcepciones {

    public static void main(String[] args) {
        GestorInventario gestor = new GestorInventario();

        System.out.println("=== TALLER CON MANEJO DE EXCEPCIONES ===\n");

        ejemplo1_ValoresNegativos(gestor);
        ejemplo2_ListaVacia(gestor);
        ejemplo3_UsoCorrecto(gestor);
        ejemplo4_IndicesInvalidos(gestor);
        ejemplo5_Finally(gestor);
    }

    // ========== EJEMPLO 1: Valores Negativos ==========
    private static void ejemplo1_ValoresNegativos(GestorInventario gestor) {
        System.out.println("--- EJEMPLO 1: Validación de Valores Negativos ---");

        try {
            Producto p1 = new ProductoElectronico("Laptop", -1200, 5, 24);
            gestor.agregarProducto(p1);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error capturado: " + e.getMessage());
        }

        try {
            Producto p2 = new ProductoAlimenticio("Manzanas", 2.5, -10, true);
            gestor.agregarProducto(p2);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error capturado: " + e.getMessage());
        }

        System.out.println("✓ El programa continúa ejecutándose sin terminaciones abruptas\n");
    }

    // ========== EJEMPLO 2: Lista Vacía ==========
    private static void ejemplo2_ListaVacia(GestorInventario gestor) {
        System.out.println("--- EJEMPLO 2: Operaciones con Lista Vacía ---");

        try {
            gestor.mostrarInventario();
        } catch (ListaVaciaException e) {
            System.out.println("✗ Error capturado: " + e.getMessage());
        }

        try {
            double total = gestor.calcularTotalInventario();
            System.out.println("Total: $" + total);
        } catch (ListaVaciaException e) {
            System.out.println("✗ Error capturado: " + e.getMessage());
        }

        System.out.println("✓ Ahora sabemos cuándo la lista está vacía\n");
    }

    // ========== EJEMPLO 3: Uso Correcto ==========
    private static void ejemplo3_UsoCorrecto(GestorInventario gestor) {
        System.out.println("--- EJEMPLO 3: Uso Correcto ---");

        try {
            gestor.agregarProducto(new ProductoElectronico("Laptop Dell", 1200, 5, 24));
            gestor.agregarProducto(new ProductoElectronico("Mouse", 25.50, 10, 6));
            gestor.agregarProducto(new ProductoAlimenticio("Leche", 3.50, 20, true));
            gestor.agregarProducto(new ProductoAlimenticio("Arroz", 15, 30, false));

            gestor.mostrarInventario();

            double total = gestor.calcularTotalInventario();
            System.out.println("💰 Total del inventario: $" + String.format("%.2f", total));

        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error en datos: " + e.getMessage());
        } catch (ListaVaciaException e) {
            System.out.println("✗ Lista vacía: " + e.getMessage());
        }
    }

    // ========== EJEMPLO 4: Índices Inválidos ==========
    private static void ejemplo4_IndicesInvalidos(GestorInventario gestor) {
        System.out.println("\n--- EJEMPLO 4: Manejo de Índices Inválidos ---");

        try {
            Producto p = gestor.obtenerProducto(100);
            System.out.println(p);
        } catch (IndexOutOfBoundsException | ListaVaciaException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        try {
            Producto p = gestor.obtenerProducto(-5);
            System.out.println(p);
        } catch (IndexOutOfBoundsException | ListaVaciaException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        System.out.println("✓ Los errores se manejan sin terminaciones abruptas\n");
    }

    // ========== EJEMPLO 5: Finally ==========
    private static void ejemplo5_Finally(GestorInventario gestor) {
        System.out.println("--- EJEMPLO 5: Bloque Finally ---");

        try {
            System.out.println("Intentando eliminar producto en índice 1...");
            gestor.eliminarProducto(1);
            System.out.println("✓ Eliminación exitosa");
        } catch (ListaVaciaException | IndexOutOfBoundsException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            System.out.println("→ Finally: Productos en inventario: " + gestor.cantidadProductos());
        }

        System.out.println("\n=== FIN DEL TALLER ===");
    }
}
