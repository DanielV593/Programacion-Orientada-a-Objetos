public interface MetodoPago {
    boolean validar();
    boolean procesarPago(double monto, String referencia);
    String generarComprobante();
}