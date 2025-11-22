public class Libro extends MaterialBiblioteca{
    private int numPaginas;
    private String editorial;
    private boolean esDigital;

    public Libro(String codigo, String titulo, String autor, int anioPublicacion, boolean estaPrestado, int numPaginas, String editorial, boolean esDigital) {
        super(codigo, titulo, autor, anioPublicacion, estaPrestado);
        if(numPaginas<=0){
            throw new IllegalArgumentException("Error: no puede haber 0 paginas en el libro");
        }
        if(editorial==null||editorial.isEmpty()){
            throw new IllegalArgumentException("Error: La editorial no puede estar vacia");
        }
        this.numPaginas = numPaginas;
        this.editorial = editorial;
        this.esDigital = esDigital;
    }

    @Override
    public double calcularMultaPorRetraso(int diasRetraso) {
        if(diasRetraso<0){
            throw new IllegalArgumentException("No hay multa porque no hay dias de retraso");
        }
        if(esDigital){
            return diasRetraso*0.50;
        }else {
            return diasRetraso*1.00;
        }
    }

    @Override
    public int calcularTiempoMaximoPrestamo() {
        if(esDigital){
            return 7;
        }else{
            return 15;
        }
    }
}
