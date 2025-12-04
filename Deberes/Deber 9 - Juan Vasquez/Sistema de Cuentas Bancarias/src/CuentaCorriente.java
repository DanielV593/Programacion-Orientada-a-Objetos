public class CuentaCorriente implements OperacionesBancarias,Transferible{
    private String numeroCuenta;
    private  String titular;
    private double saldo;
    private final double LIMITE_SOBREGIRO = 500.00;

    public CuentaCorriente(String numeroCuenta, String titular, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    @Override
    public void depositar(double monto) {
        saldo += monto;
    }

    @Override
    public boolean retirar(double monto) {
    if((saldo + LIMITE_SOBREGIRO)>= monto){
        saldo -= monto;
        System.out.println("Retiro: $"+monto+ " - Saldo: $"+saldo+(saldo <0? "SOBREGIRO" : ""));
        return true;
    }
    return false;
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }

    @Override
    public boolean transferir(double monto, String cuentaDestino) {
        if(retirar(monto)){
            System.out.println("Transferencia: $"+monto+ " a cuenta "+cuentaDestino+" - Saldo: $"+saldo);
            return true;
        }
        return false;
    }
}
