public class ProductoElectronico extends Producto{
    private int mesGarantia;

    public ProductoElectronico(String nombre, double precio, int cantidad, int mesGarantia) {
        super(nombre, precio, cantidad);
        this.mesGarantia = mesGarantia;
    }

    @Override
    public double calcularDescuento() {
        return (mesGarantia>12)? getPrecio() * 0.10 : 0;
    }
    public int getMesGarantia(){
        return mesGarantia;
    }
}
