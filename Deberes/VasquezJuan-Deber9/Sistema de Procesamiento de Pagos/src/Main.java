public class Main {
    public static void main(String[] args) {
        PagoTarjeta pagoTarjeta = new PagoTarjeta("4500123456789010", "123", "Pedro Silva");
        PagoPayPal pagoPayPal = new PagoPayPal("lucia.gomez@email.com");
        PagoTransferencia pagoTransferencia = new PagoTransferencia("1234567890", "Banco Pichincha");

        System.out.println("=== PROCESAMIENTO DE PAGOS ===");

        System.out.println("--- Pago con Tarjeta de Credito ---");
        pagoTarjeta.verificarIdentidad("1712345678");
        pagoTarjeta.esSeguro();
        pagoTarjeta.procesarPago(125.50, "REF-881");
        System.out.println("Comprobante: " + pagoTarjeta.generarComprobante());

        System.out.println("--- Pago con PayPal ---");
        pagoPayPal.procesarPago(60.00, "REF-882");
        System.out.println("Comprobante: " + pagoPayPal.generarComprobante());

        System.out.println("--- Pago con Transferencia ---");
        pagoTransferencia.verificarIdentidad("1798765432");
        pagoTransferencia.esSeguro();
        pagoTransferencia.procesarPago(450.00, "REF-883");
        System.out.println("Comprobante: " + pagoTransferencia.generarComprobante());

        System.out.println("=== PROCESAMIENTO DE DEVOLUCIONES ===");

        System.out.println("--- Devolucien Tarjeta ---");
        pagoTarjeta.procesarDevolucion(25.50, "Cobro duplicado");
        System.out.println("Dias permitidos: " + pagoTarjeta.diasParaDevolucion() + " dias");

        System.out.println("--- Devolucion Transferencia ---");
        System.out.println("Este metodo de pago no permite devoluciones");

        double totalProcesado = 125.50 + 60.00 + 450.00;
        double totalDevuelto = 25.50;

        System.out.println("TOTAL PROCESADO: $" + totalProcesado);
        System.out.println("TOTAL DEVUELTO: $" + totalDevuelto);
        System.out.println("NETO: $" + (totalProcesado - totalDevuelto));
    }
}