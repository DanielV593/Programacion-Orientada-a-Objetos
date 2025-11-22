public class ProductoElectronico extends Producto{
    private String marca;
    private int garantiaMeses;

    public ProductoElectronico(String codigo, String nombre, double precioBase, int stock, String marca, int garantiaMeses) {
        super(codigo, nombre, precioBase, stock);
        if(marca==null||marca.trim().isEmpty()){
            throw new IllegalArgumentException("La marca no puede estar vacia");
        }
        if(garantiaMeses<=0){
            throw new IllegalArgumentException("La garantia en meses no puede ser menor o igual a 0");
        }
        this.marca = marca;
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase()*1.12;
    }

    public boolean aplicarGarantia(){
        return this.garantiaMeses == 12;
    }

    public double aplicarGarantia(int mesesExtras){
        if(mesesExtras<=0){
            throw new IllegalArgumentException("Los meses extras deben ser mayores a 0");
        }
        return mesesExtras*5.0;
    }
}
