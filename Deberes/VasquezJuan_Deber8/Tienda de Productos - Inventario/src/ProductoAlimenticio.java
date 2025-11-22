public class ProductoAlimenticio extends Producto{
    public String fechaVencimiento;
    public boolean requiereRefrigeracion;

    public ProductoAlimenticio(String codigo, String nombre, double precioBase, int stock, String fechaVencimiento, boolean requiereRefrigeracion) {
        super(codigo, nombre, precioBase, stock);
        if(fechaVencimiento.trim().isEmpty()){
            throw new IllegalArgumentException("La fecha de vencimiento no puede estar vacia");
        }
        this.fechaVencimiento = fechaVencimiento;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase();
    }
}
