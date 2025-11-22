public class DVD extends MaterialBiblioteca{
    private int duracionMinutos;
    private String genero;
    private boolean tieneSubtitulos;

    public DVD(String codigo, String titulo, String autor, int anioPublicacion, boolean estaPrestado, int duracionMinutos, String genero, boolean tieneSubtitulos) {
        super(codigo, titulo, autor, anioPublicacion, estaPrestado);
        if(duracionMinutos<0){
            throw new IllegalArgumentException("Error: la pelicula no puede tener menor a 0 minutos");
        }
        if(genero==null||genero.isEmpty()){
            throw new IllegalArgumentException("Error: el genero no puede ser nulo ni vacio");
        }
        this.duracionMinutos = duracionMinutos;
        this.genero = genero;
        this.tieneSubtitulos = tieneSubtitulos;
    }

    @Override
    public double calcularMultaPorRetraso(int diasRetraso) {
        if(diasRetraso<0){
            throw new IllegalArgumentException("Los dias de retraso deben ser mayores a 0");
        }
        return diasRetraso*1.50;
    }

    @Override
    public int calcularTiempoMaximoPrestamo() {
        return 3;
    }
    public double calcularMultaPorRetraso(int diasRetraso, boolean esEstreno){
        double multaBase = calcularMultaPorRetraso(diasRetraso);
        if (esEstreno){
            return  multaBase*2;
        }
        return multaBase;
    }
}
