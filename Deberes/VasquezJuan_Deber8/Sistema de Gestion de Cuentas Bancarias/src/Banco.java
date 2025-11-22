import java.util.ArrayList;
import java.util.Random;

public class Banco {
    public ArrayList<CuentaBancaria>cuentas;
    public String nombre;
    public Banco(String nombre){
        if(nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("EL nombre del banco no puede estar vacio");
        }
        this.nombre = nombre;
        this.cuentas = new ArrayList<>();
    }
    public static String generarNumeroCuenta(){
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(10);
        for(int i = 0; i <10; i++){
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public void abrirCuenta(CuentaBancaria cuenta){
        if(cuenta == null){
            throw new NullPointerException("La cuenta a abrir no puede ser nula");
        }
        this.cuentas.add(cuenta);
    }

    private CuentaBancaria buscarCuenta(String numeroCuenta){
        for (CuentaBancaria c : cuentas){
            if(c.getNumeroCuenta().equals(numeroCuenta)){
                return c;
            }
        }
        return null;
    }

    public void transferir(String origen, String destino, double monto) throws SaldoInsuficienteException{
        if (monto <=0){
            throw new MontoInvalidoException("El monto a transferir no puede ser menor o igual a 0");
        }
        CuentaBancaria cOrigen = buscarCuenta(origen);
        CuentaBancaria cDestino = buscarCuenta(destino);
        if(cOrigen == null || cDestino == null){
            throw new IllegalArgumentException("Alguna de las dos cuentas no existen por favor revisa nuevamente");
        }
        cOrigen.retirar(monto);
        cDestino.depositar(monto);

        System.out.println("Transferencia exitosa: $"+monto +" de"+origen+" a "+destino);
    }

    public void aplicarInteresesAhorros(){
        if(cuentas.isEmpty()){
            throw new IllegalArgumentException("El banco no tiene cuentas disponibles");
        }

        System.out.println("\n--- Aplicando Intereses a Cuentas de Ahorro ---");
        for(CuentaBancaria c : cuentas){
            if(c instanceof CuentaAhorros){
                CuentaAhorros ca= (CuentaAhorros) c;

                if (ca.getSaldo()==0){
                    throw new CuentaInactivaException("Cuenta "+ ca.getNumeroCuenta()+ " inactiva (saldo de $0)");
                }
                double rendimiento = ca.getSaldo()*ca.getTasaInteres();
                ca.aplicarInteres();
                System.out.println("Interes aplicado a ahorros: "+ca.getNumeroCuenta()+ ": $"+rendimiento);
            }
        }
    }
    public double obtenerSaldoTotal() {
        if (cuentas.isEmpty()) {
            throw new IllegalStateException("No hay cuentas, el saldo total no se puede calcular.");
        }
        double total = 0;
        for (CuentaBancaria c : cuentas) {
            total += c.getSaldo();
        }
        return total;
    }

    public void ordenarPorSaldo() {
        int n = cuentas.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (cuentas.get(j).getSaldo() < cuentas.get(minIdx).getSaldo()) {
                    minIdx = j;
                }
            }
            CuentaBancaria temp = cuentas.get(minIdx);
            cuentas.set(minIdx, cuentas.get(i));
            cuentas.set(i, temp);
        }
    }

    public ArrayList<CuentaBancaria> getCuentas() {
        return cuentas;
    }
}
