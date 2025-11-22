public class ProductoAlimenticio extends Producto{
    private boolean esPerecible;

    public ProductoAlimenticio(String nombre, double precio, int cantidad, boolean esPerecible) {
        super(nombre, precio, cantidad);
        this.esPerecible = esPerecible;
    }

    @Override
    public double calcularDescuento() {
        return esPerecible? getPrecio()*0.05:0;
    }

    public boolean isEsPerecible(){
        return esPerecible;
    }
}
