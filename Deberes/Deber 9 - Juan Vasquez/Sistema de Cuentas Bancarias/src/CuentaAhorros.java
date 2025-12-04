public class CuentaAhorros implements OperacionesBancarias{
    private String numeroCuenta;
    private  String titular;
    private double saldo;

    public CuentaAhorros(String numeroCuenta, String titular, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    @Override
    public void depositar(double monto) {
        saldo += monto;
        System.out.println("Deposito: $"+monto+" - Saldo: $"+saldo);
    }

    @Override
    public boolean retirar(double monto) {
        if(saldo>= monto){
            saldo -= monto;
            System.out.println("Retiro: $"+monto+" - Saldo: $"+saldo);

            if(saldo<100){
                saldo -= 5;
                System.out.println("Comision aplicada: $5.00  - Saldo: $"+saldo);
            }
            return true;
        }
        return false;
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }
}
