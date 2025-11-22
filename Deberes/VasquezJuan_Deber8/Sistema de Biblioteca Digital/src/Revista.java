public class Revista extends MaterialBiblioteca{
    private int numeroEdicion;
    private String mesPublicacion;
    private boolean esESpecializada;

    public Revista(String codigo, String titulo, String autor, int anioPublicacion, boolean estaPrestado, int numeroEdicion, String mesPublicacion, boolean esESpecializada) {
        super(codigo, titulo, autor, anioPublicacion, estaPrestado);
        if(numeroEdicion<0){
            throw new IllegalArgumentException("El numero de edicion debe ser mayor a 0");
        }
        if(mesPublicacion==null||mesPublicacion.isEmpty()){
            throw new IllegalArgumentException("El mes de publicacion no puede ser nulo ni vacio");
        }
        this.numeroEdicion = numeroEdicion;
        this.mesPublicacion = mesPublicacion;
        this.esESpecializada = esESpecializada;
    }

    @Override
    public double calcularMultaPorRetraso(int diasRetraso) {
        if(diasRetraso<0){
            throw new IllegalArgumentException("Los dias de retraso deben ser mayores a 0");
        }
        if(esESpecializada){
            return diasRetraso*2.00;
        }else{
            return diasRetraso*0.75;
        }
    }

    @Override
    public int calcularTiempoMaximoPrestamo() {
        if(esESpecializada){
            return 5;
        }else{
            return 7;
        }
    }
}
