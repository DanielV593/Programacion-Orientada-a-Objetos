public class Auto extends Vehiculo{
    private int numeroPuertas;

    public Auto(String marca, String modelo, int anio, double precio, int numeroPuertas) {
        super(marca, modelo, anio, precio);
        if(numeroPuertas < 0){
            throw new IllegalArgumentException("El numero de puertas de un auto no puede ser 0 ni negativo");
        }
        this.numeroPuertas = numeroPuertas;
    }

    @Override
    public double calcularImpuestoCirculacion() {
        return (getPrecio() * 0.05);
    }

    @Override
    public String toString() {
        return super.toString()+"\nNumero de puertas: "+numeroPuertas+"\nImpuesto por circulacion: "+calcularImpuestoCirculacion();
    }
}
