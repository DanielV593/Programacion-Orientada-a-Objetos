public interface OperacionesBancarias {
    void depositar(double monto);
    boolean retirar(double monto);
    double consultarSaldo();
}
