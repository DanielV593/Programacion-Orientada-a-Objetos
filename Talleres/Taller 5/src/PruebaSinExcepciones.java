public class PruebaSinExcepciones {
    public static void main(String[] args) {
        GestorInventario gestor = new GestorInventario();
        System.out.println("=== PRUEBAS DEL SISTEMA (SIN EXCEPCIONES) ===\n");
        System.out.println("--- PRUEBA 1: Agregar productos validos ---");
        gestor.agregarProducto(new ProductoElectronico("Laptop",1200,5,24));
        gestor.agregarProducto(new ProductoAlimenticio("Leche",3.5,20,true));
        gestor.mostrarInventario();

        System.out.println("---PRUEBA 2: Calcular total ---");
        double total = gestor.calcularTotalInventario();
        System.out.println("Total inventario: $"+total+"\n");

    }
}