public class PagoPayPal implements MetodoPago, Reembolsable {
    private String email;
    private String ultimoComprobante;

    public PagoPayPal(String email) {
        this.email = email;
    }

    @Override
    public boolean validar() {
        if (email.contains("@")) {
            System.out.println("Validacion exitosa");
            return true;
        }
        return false;
    }

    @Override
    public boolean procesarPago(double monto, String referencia) {
        if (validar()) {
            System.out.println("Pago procesado: $" + monto + " - Ref: " + referencia);
            ultimoComprobante = "PAYPAL-" + email + "-" + referencia;
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
        return 180;
    }
}