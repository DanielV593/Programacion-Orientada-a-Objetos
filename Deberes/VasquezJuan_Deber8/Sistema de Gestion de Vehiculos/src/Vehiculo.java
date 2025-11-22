public abstract class Vehiculo {
    private String marca;
    private String modelo;
    private int anio;
    private double precio;

    public Vehiculo(String marca, String modelo, int anio, double precio) {
        if (marca == null || marca.trim().isEmpty()){
            throw new IllegalArgumentException("La marca no puede ser nula ni vacia");
        }
        if (modelo == null || modelo.trim().isEmpty()){
            throw new IllegalArgumentException("El modelo no puede ser mulo ni vacio");
        }
        if (anio< 1900 || anio > 2025){
            throw new IllegalArgumentException("El año debe estar entre 1900 y 2025");
        }
        if (precio <= 0){
            throw new IllegalArgumentException("EL precio debe ser mayor a 0");
        }
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;

    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public abstract double calcularImpuestoCirculacion();

    @Override
    public String toString() {
        return "\nVehiculo: " +
                "\nMarca: " + marca +
                "\nModelo: '" + modelo +
                "\nAño: " + anio +
                "\nPrecio: " + precio;
    }
}
