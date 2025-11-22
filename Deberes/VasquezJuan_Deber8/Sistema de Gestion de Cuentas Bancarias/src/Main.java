public class Main {
    public static void main(String[] args) {
        System.out.println("--- 1. Pruebas de Excepciones en Constructores (IllegalArgumentException) ---");

        try {
            new CuentaAhorros(Banco.generarNumeroCuenta(), "", 100.0, 0.05);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR ESPERADO (Titular Vacío): " + e.getMessage());
        }

        try {
            new CuentaCorriente(Banco.generarNumeroCuenta(), "Ana Lopez", -50.0, 500.0);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR ESPERADO (Saldo Negativo): " + e.getMessage());
        }

        System.out.println("\n--- 2. Agregar 6 Cuentas Válidas ---");

        Banco banco = new Banco("MiBanco Global");

        CuentaAhorros ca1 = new CuentaAhorros("AH1001", "Juan Vasquez", 1000.0, 0.05);
        CuentaAhorros ca2 = new CuentaAhorros("AH1002", "Daniel Mera", 60.0, 0.03);
        CuentaCorriente cc1 = new CuentaCorriente("CR2001", "Britany Chuma", 500.0, 100.0);
        CuentaCorriente cc2 = new CuentaCorriente("CR2002", "Karolina Davila", 10.0, 200.0);
        CuentaInversion ci1 = new CuentaInversion("IN3001", "Alejandro Lopez", 2000.0, 100.0, 0.08);
        CuentaInversion ci2 = new CuentaInversion("IN3002", "Jose Gallegos", 150.0, 500.0, 0.10);

        banco.abrirCuenta(ca1);
        banco.abrirCuenta(ca2);
        banco.abrirCuenta(cc1);
        banco.abrirCuenta(cc2);
        banco.abrirCuenta(ci1);
        banco.abrirCuenta(ci2);
        System.out.println("Cuentas agregadas con éxito.");

        System.out.println("\n--- 3. Pruebas de Operaciones y Excepciones ---");

        System.out.println("\n-> Depósito en CR2001");
        cc1.depositar(200.0);
        System.out.println(cc1);

        System.out.println("\n-> Retiro en IN3001");
        try {
            ci1.retirar(500.0);
            System.out.println(ci1);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n-> Retiro con saldo insuficiente (AH1002)");
        try {
            ca2.retirar(15.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("ERROR ESPERADO (Saldo Insuficiente): " + e.getMessage());
        }

        System.out.println("\n-> Depósito con monto negativo");
        try {
            ca1.depositar(-10.0);
        } catch (MontoInvalidoException e) {
            System.out.println("ERROR ESPERADO (Monto Negativo): " + e.getMessage());
        }

        try {
            banco.transferir("AH1001", "CR2001", 100.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Transferencia Fallida: " + e.getMessage());
        }

        try {
            banco.transferir("CR2001", "IN3001", 1000.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("TRANSFERENCIA FALLIDA (Saldo Insuficiente): " + e.getMessage());
        }

        System.out.println("\n--- 4. Cálculos y Ordenamiento Finales ---");

        double saldoTotal = banco.obtenerSaldoTotal();
        System.out.println("Saldo Total del Banco: $" + String.format("%.2f", saldoTotal));

        CuentaAhorros caInactiva = new CuentaAhorros(Banco.generarNumeroCuenta(), "Inactivo", 0.0, 0.05);
        banco.abrirCuenta(caInactiva);
        try {
            banco.aplicarInteresesAhorros();
        } catch (CuentaInactivaException e) {
            System.out.println("ERROR ESPERADO (Cuenta Inactiva): " + e.getMessage());
        }
        banco.getCuentas().remove(caInactiva);

        banco.aplicarInteresesAhorros();

        banco.ordenarPorSaldo();
        System.out.println("\nCuentas Ordenadas por Saldo (Menor a Mayor - Algoritmo Manual):");
        System.out.println("-------------------------------------------------");
        for (CuentaBancaria c : banco.getCuentas()) {
            System.out.println(c);
        }
    }
}