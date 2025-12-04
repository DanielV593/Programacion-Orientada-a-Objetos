public class PagoTransferencia implements MetodoPago, Verificable {
    private String numeroCuenta;
    private String banco;
    private String ultimoComprobante;

    public PagoTransferencia(String numeroCuenta, String banco) {
        this.numeroCuenta = numeroCuenta;
        this.banco = banco;
    }

    @Override
    public boolean validar() {
        if (numeroCuenta.length() == 10) {
            System.out.println("Validacion exitosa");
            return true;
        }
        return false;
    }

    @Override
    public boolean procesarPago(double monto, String referencia) {
        if (validar()) {
            System.out.println("Pago procesado: $" + monto + " - Ref: " + referencia);
            ultimoComprobante = "TRANSFER-" + numeroCuenta + "-" + referencia;
            return true;
        }
        return false;
    }

    @Override
    public String generarComprobante() {
        return ultimoComprobante;
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