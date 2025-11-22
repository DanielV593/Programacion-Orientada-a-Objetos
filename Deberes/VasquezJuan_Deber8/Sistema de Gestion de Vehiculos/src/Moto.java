public class Moto extends Vehiculo {
    private int cilindrada;

    public Moto(String marca, String modelo, int anio, double precio, int cilindrada) {
        super(marca, modelo, anio, precio);
        if(cilindrada<0){
            throw new IllegalArgumentException("El cilindraje de la moto no puede ser menor o igual a 0");
        }
        this.cilindrada = cilindrada;
    }

    @Override
    public double calcularImpuestoCirculacion() {
        if( cilindrada <= 250){
            return (getPrecio()*0.02);
        } else if (cilindrada > 250) {
            return (getPrecio()*0.04);

        }
        return calcularImpuestoCirculacion();
    }

    @Override
    public String toString() {
        return super.toString()+"\nCilindrada: "+cilindrada + "\nPrecio: "+getPrecio()+
                "Impuesto por circulacion: "+ calcularImpuestoCirculacion();
    }
}
