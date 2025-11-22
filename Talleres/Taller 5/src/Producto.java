public abstract class Producto {
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        // NUEVO: Validar precio
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo: " + precio);
        }

        // NUEVO: Validar Cantidad
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa: " + cantidad);
        }

        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public abstract double calcularDescuento();

    public double calcularTotal() {
        return (precio - calcularDescuento()) * cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    // NUEVO: Validar en setter
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo: " + precio);
        }
        this.precio = precio;
    }

    // NUEVO: Validar en setter
    public void setCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa: " + cantidad);
        }
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return String.format("%s - Precio: %.2f - Cantidad: %d - Total: %.2f",
                nombre, precio, cantidad, calcularTotal());
    }
}