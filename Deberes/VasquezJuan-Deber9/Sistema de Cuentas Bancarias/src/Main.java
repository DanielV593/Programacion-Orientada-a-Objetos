public class Main{
    public static void main(String[] args) {
        CuentaAhorros ahorros = new CuentaAhorros("AH-555", "Carlos Ruiz", 150);
        CuentaCorriente corriente = new CuentaCorriente("CC-777", "Ana Torres", 800);

        System.out.println("=== CUENTA DE AHORROS ===");
        ahorros.depositar(200);
        ahorros.retirar(300);

        System.out.println("\n=== CUENTA CORRIENTE ===");
        corriente.retirar(1000);
        corriente.transferir(150, "112233");

        System.out.println("\n=== SALDOS FINALES ===");
        System.out.println("Cuenta Ahorros: $" + ahorros.consultarSaldo());
        System.out.println("Cuenta Corriente: $" + corriente.consultarSaldo());
    }
}
