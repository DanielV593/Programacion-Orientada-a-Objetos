public class PagoTarjeta implements MetodoPago, Reembolsable, Verificable {
    private String numeroTarjeta;
    private String cvv;
    private String titular;
    private String ultimoComprobante;

    public PagoTarjeta(String numeroTarjeta, String cvv, String titular) {
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.titular = titular;
    }

    @Override
    public boolean validar() {
        if (cvv.length() == 3) {
            System.out.println("✓ Validacion exitosa");
            return true;
        }
        return false;
    }

    @Override
    public boolean procesarPago(double monto, String referencia) {
        if (validar()) {
            System.out.println("💳 Pago procesado: $" + monto + " - Ref: " + referencia);
            ultimoComprobante = "TARJETA-" + numeroTarjeta.substring(numeroTarjeta.length() - 4) + "-" + referencia;
            return true;
        }
        return false;
    }

    @Override
    public String generarComprobante() {
        return ultimoComprobante;
    }

    @Override
    public boolean procesarDevolucion(double monto, String motivo) {
        System.out.println("Devolucion procesada: $" + monto);
        System.out.println("Motivo: " + motivo);
        return true;
    }

    @Override
    public int diasParaDevolucion() {
        return 30;
    }

    @Override
    public boolean verificarIdentidad(String documento) {
        System.out.println("Identidad verificada: CI " + documento);
        return true;
    }

    @Override
    public boolean esSeguro() {
        System.out.println("Metodo de pago seguro");
        return true;
    }
}