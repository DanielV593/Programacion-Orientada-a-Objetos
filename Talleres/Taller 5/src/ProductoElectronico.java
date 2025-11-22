public class ProductoElectronico extends Producto {
    private int mesesGarantia;

    public ProductoElectronico(String nombre, double precio, int cantidad, int mesesGarantia) {
        super(nombre, precio, cantidad); // Se validan precio y cantidad aquí

        // NUEVO: Validar meses de garantía
        if (mesesGarantia < 0) {
            throw new IllegalArgumentException("Los meses de garantía no pueden ser negativos: " + mesesGarantia);
        }

        this.mesesGarantia = mesesGarantia;
    }

    @Override
    public double calcularDescuento() {
        // Aplica un 10% de descuento si la garantía es mayor a 12 meses.
        return (mesesGarantia > 12) ? getPrecio() * 0.10 : 0;
    }

    public int getMesesGarantia() {
        return mesesGarantia;
    }
}
