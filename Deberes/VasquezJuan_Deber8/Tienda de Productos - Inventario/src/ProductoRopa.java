public class ProductoRopa extends Producto{
    private String talla;
    private String material;

    public ProductoRopa(String codigo, String nombre, double precioBase, int stock, String talla, String material) {
        super(codigo, nombre, precioBase, stock);
        if(talla.trim().isEmpty()||material.trim().isEmpty()){
            throw new IllegalArgumentException("La talla o el Material no pueden estar vacios");
        }
        this.talla = talla;
        this.material = material;
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase()*1.12;
    }

}
