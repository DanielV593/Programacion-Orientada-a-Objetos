public abstract class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    protected double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        if(numeroCuenta == null || numeroCuenta.trim().isEmpty()){
            throw new IllegalArgumentException("El numero de cuenta no puede ser nula ni vacia");
        }
        if(titular == null || titular.trim().isEmpty()){
            throw new IllegalArgumentException("El titular no puede ser nulo ni estar vacio");
        }
        if(saldo <0){
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double monto){
        if(monto<=0){
            throw new MontoInvalidoException("ERROR: El monto a depositar debe ser mayor a 0");
        }
        this.saldo += monto;
    }
    public abstract void retirar(double monto) throws SaldoInsuficienteException;

    @Override
    public String toString() {
        return

                "\nTitular de la cuenta: " + titular +
                "\nSaldo: " + saldo;
    }
}

