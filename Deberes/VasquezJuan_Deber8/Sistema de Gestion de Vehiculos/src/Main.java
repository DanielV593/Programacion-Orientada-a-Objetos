public class Main {
    public static void main(String[] args) {
        System.out.println("--- 1. Pruebas de Excepciones en Constructores (IllegalArgumentException) ---");

        try {
            new Auto("Ford", "T", 1899, 5000.0,4);
        } catch (IllegalArgumentException e) {
            System.out.println(" ERROR ESPERADO (Año < 1900): " + e.getMessage());
        }

        try {
            new Moto("Yamaha", "Cruiser", 2020, -1000.0, 500);
        } catch (IllegalArgumentException e) {
            System.out.println(" ERROR ESPERADO (Precio Negativo): " + e.getMessage());
        }

        System.out.println("\n--- 2. Agregando 6 Vehículos Válidos ---");

        Concesionaria concesionaria = new Concesionaria();

        try {
            concesionaria.agregarVehiculo(new Auto("Toyota", "Corolla", 2023, 25000.0, 5));
            concesionaria.agregarVehiculo(new Auto("Honda", "CRV", 2024, 35000.0,3));
            concesionaria.agregarVehiculo(new Moto("Suzuki", "GSX", 2021, 8000.0, 150));
            concesionaria.agregarVehiculo(new Moto("Kawasaki", "Ninja", 2022, 12000.0, 600));
            concesionaria.agregarVehiculo(new Camion("Volvo", "FH16", 2019, 90000.0, 15.5)); // El más caro
            concesionaria.agregarVehiculo(new Camion("MAN", "TGX", 2020, 75000.0, 10.0));


            System.out.println("Inventario cargado con éxito.");

        } catch (Exception e) {
            System.out.println("Error durante la carga: " + e.getMessage());
        }

        System.out.println("\n--- 3. Pruebas de Excepciones en Concesionaria (IllegalStateException) ---");

        Concesionaria concesionariaVacia = new Concesionaria();
        try {
            concesionariaVacia.calcularTotalImpuestos();
        } catch (IllegalStateException e) {
            System.out.println(" ERROR ESPERADO (Lista Vacía): " + e.getMessage());
        }

        System.out.println("\n--- 4. Operaciones con Lista Llena ---");

        double totalImpuestos = concesionaria.calcularTotalImpuestos();
        System.out.println(" Total de Impuestos de Circulación: " + String.format("$%.2f", totalImpuestos));

        Vehiculo masCaro = concesionaria.obtenerVehiculoMasCaro();
        System.out.println("\n Vehículo Más Caro (Precio: " + String.format("$%.2f", masCaro.getPrecio()) + "):");
        System.out.println("  -> " + masCaro);

        concesionaria.ordenarPorPrecio();
        System.out.println("\n Inventario Ordenado por Precio (Menor a Mayor):");
        System.out.println("--------------------------------------------------");
        for (Vehiculo v : concesionaria.getInventario()) {
            System.out.println(v);
        }
    }
}