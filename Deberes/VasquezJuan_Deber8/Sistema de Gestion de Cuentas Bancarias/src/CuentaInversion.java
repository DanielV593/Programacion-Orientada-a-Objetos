public class CuentaInversion extends CuentaBancaria{
    private double montoMinimo;
    private double rendimientoAnual;

    public CuentaInversion(String numeroCuenta, String titular, double saldo, double montoMinimo, double rendimientoAnual) {
        super(numeroCuenta, titular, saldo);
        if(montoMinimo<0||rendimientoAnual<=0){
            throw new IllegalArgumentException("El monto minimo y el rendimiento anual no deben ser menores a 0");
        }
        this.montoMinimo = montoMinimo;
        this.rendimientoAnual = rendimientoAnual;
    }

    @Override
    public void depositar(double monto) {
        if(monto<montoMinimo){
            throw new MontoInvalidoException("El deposito minimo de inversion debe ser mayor a: $"+montoMinimo);
        }
        super.depositar(monto);
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0){
            throw new MontoInvalidoException("El monto a retirar debe ser mayor a 0");
        }
        if (monto > this.saldo){
            throw new SaldoInsuficienteException("Saldo insuficiente para poder realizar el retiro");
        }
        this.saldo -= monto;
    }
    public double calcularRendimiento(){
        return (saldo *(rendimientoAnual/12));
    }
    public double calcularRendimiento(int meses){
        if(meses <= 0) return 0;
        return (calcularRendimiento()*meses);
    }

    @Override
    public String toString() {
        return "Cuenta de Inversion No: "+getNumeroCuenta()+ super.toString();
    }
}
