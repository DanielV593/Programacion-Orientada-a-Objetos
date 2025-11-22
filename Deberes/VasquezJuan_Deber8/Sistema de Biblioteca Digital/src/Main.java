import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- 1. PRUEBAS DE VALIDACIÓN (Excepciones) ---");

        try {
            new Libro("LIB-001", "", "Autor X", 2020, false, 100, "Editorial", false);
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPCIÓN CAPTURADA: " + e.getMessage());
        }

        try {
            new DVD("DVD-001", "Pelicula", "Director", 900, false, 120, "Accion", true);
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPCIÓN CAPTURADA: " + e.getMessage());
        }

        System.out.println("\n--- 2. INICIALIZANDO BIBLIOTECA ---");

        ArrayList<MaterialBiblioteca> listaInicial = new ArrayList<>();
        Biblioteca biblioteca = new Biblioteca(listaInicial, "Biblioteca Nacional");

        System.out.println("Biblioteca creada exitosamente.");

        System.out.println("\n--- 3. AGREGANDO MATERIALES ---");

        try {
            biblioteca.agregarMaterial(new Libro("LIB-100", "Java Básico", "Gosling", 2010, false, 500, "Oracle", true));
            biblioteca.agregarMaterial(new Libro("LIB-101", "El Quijote", "Cervantes", 1605, false, 800, "Alfaguara", false));

            biblioteca.agregarMaterial(new Revista("REV-200", "Science", "Varios", 2023, false, 50, "Marzo", true));
            biblioteca.agregarMaterial(new Revista("REV-201", "Hola", "Varios", 2024, false, 10, "Abril", false));

            biblioteca.agregarMaterial(new DVD("DVD-300", "Inception", "Nolan", 2010, false, 148, "Sci-Fi", true));
            biblioteca.agregarMaterial(new DVD("DVD-301", "Matrix", "Wachowski", 1999, false, 136, "Sci-Fi", true));

            biblioteca.agregarMaterial(new Libro("XXX-999", "Error", "Nadie", 2020, false, 100, "N/A", true));

        } catch (CodigoInvalidoException e) {
            System.out.println("EXCEPCIÓN CAPTURADA (Código): " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        System.out.println("\n--- 4. PRUEBAS DE PRÉSTAMOS ---");

        try {
            biblioteca.prestarMaterial("LIB-100");
            biblioteca.prestarMaterial("DVD-300");

            // Intentar prestar algo ya prestado
            biblioteca.prestarMaterial("LIB-100");
        } catch (MaterialNoDisponibleException e) {
            System.out.println("EXCEPCIÓN CAPTURADA (No disponible): " + e.getMessage());
        } catch (MaterialNoEncontradoException e) {
            System.out.println("EXCEPCIÓN CAPTURADA (No encontrado): " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Otro error en préstamos: " + e.getMessage());
        }

        try {
            biblioteca.prestarMaterial("LIB-9999");
        } catch (MaterialNoEncontradoException e) {
            System.out.println("EXCEPCIÓN CAPTURADA (No encontrado): " + e.getMessage());
        } catch (Exception e) {
            // Captura genérica por si acaso
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- 5. PRUEBAS DE DEVOLUCIÓN Y MULTAS ---");

        try {
            double multa1 = biblioteca.devolverMaterial("LIB-100", 5);
            System.out.println("Devolución 'Java Básico' (5 días retraso). Multa: $" + multa1);

            biblioteca.devolverMaterial("DVD-300", 0);

            // --- AQUI ESTABA EL POSIBLE ERROR ---
            // Esta línea lanza una excepción, debe estar dentro del try
            biblioteca.prestarMaterial("DVD-301");

            double multa2 = biblioteca.devolverMaterial("DVD-301", 2);
            System.out.println("Devolución 'Matrix' (2 días retraso). Multa: $" + multa2);

        } catch (Exception e) {
            // Este catch capturará MaterialNoDisponibleException y cualquier otra
            System.out.println("Error en proceso de devolución: " + e.getMessage());
        }

        System.out.println("\n--- 6. LISTADO Y ORDENAMIENTO ---");

        biblioteca.listarMaterialesDisponibles();

        System.out.println("\n--> Ordenando por año de publicación...");
        biblioteca.ordenarPorAnio();
        biblioteca.listarMaterialesDisponibles();
    }
}