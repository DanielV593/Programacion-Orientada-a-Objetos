public class CuentaAhorros extends CuentaBancaria{
    private double tasaInteres;

    public CuentaAhorros(String numeroCuenta, String titular, double saldo, double tasaInteres) {
        super(numeroCuenta, titular, saldo);
        if (tasaInteres < 0 || tasaInteres > 1) {
            throw new IllegalArgumentException("La tasa de interes debe ser entre 0 y 1");
        }
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException {
        if(monto <=0){
            throw new MontoInvalidoException("El monto debe ser mayor a 0 para poder reitrar");
        }
        double nuevoSaldo = this.saldo - monto;
        if(nuevoSaldo<50){
            throw new SaldoInsuficienteException("Retiro denegado. El saldo minimo despues del retiro debe ser de $50. Tu saldo actual es: "+this.getSaldo());
        }
        this.saldo = nuevoSaldo;
    }
    public void aplicarInteres(){
        this.saldo += (this.saldo*tasaInteres);
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    @Override
    public String toString() {
        return "\nCuenta No: "+getNumeroCuenta()+super.toString();
    }
}
