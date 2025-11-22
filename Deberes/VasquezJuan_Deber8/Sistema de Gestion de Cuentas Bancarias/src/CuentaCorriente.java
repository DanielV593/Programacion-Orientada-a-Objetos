public class CuentaCorriente extends CuentaBancaria{
    private double limiteCredito;

    public CuentaCorriente(String numeroCuenta, String titular, double saldo, double limiteCredito) {
        super(numeroCuenta, titular, saldo);
        if(limiteCredito<=0){
            throw new IllegalArgumentException("El limite de credito no puede ser menor o igual a 0");
        }
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException {
        if(monto <=0){
            throw new MontoInvalidoException("El monto debe ser mayor a 0");
        }
        double saldoDisponible = this.saldo + this.limiteCredito;

        if(monto>saldoDisponible){
            throw new SaldoInsuficienteException("Retiro excede el limite al saldo disponible: "+saldoDisponible);
        }
        this.saldo -= monto;
    }

    @Override
    public String toString() {
        return "Cuenta corriente No: "+getNumeroCuenta()+super.toString();
    }
}
