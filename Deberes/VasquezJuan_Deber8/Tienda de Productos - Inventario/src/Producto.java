public abstract class Producto {
    private String codigo;
    private String nombre;
    private double precioBase;
    private int stock;

    public Producto(String codigo, String nombre, double precioBase, int stock) {
        if(codigo==null || codigo.trim().isEmpty()){
            throw new IllegalArgumentException("El codigo no puede ser nulo ni estar vacio");
        }
        if(nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacio");
        }
        if(precioBase<0){
            throw new PrecioInvalidoException("El precio no puede ser menor a 0");
        }
        if(stock <0){
            throw new IllegalArgumentException("El stock no puede ser menor a 0");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.stock = stock;
    }

    public abstract double calcularPrecioFinal();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public void reducirStock(int cantidad) {
        this.stock = this.stock - cantidad;
    }

    @Override
    public String toString() {
        return "Cod: " + codigo + " | " + nombre + " | Stock: " + stock + " | Base: $" + precioBase + " | Final: $" + calcularPrecioFinal();
    }
}
