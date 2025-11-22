import java.util.ArrayList;

public class Biblioteca {
    public ArrayList<MaterialBiblioteca>catalogo;
    private String nombre;

    public Biblioteca(ArrayList<MaterialBiblioteca> catalogo, String nombre) {
        if(nombre==null||nombre.isEmpty()){
            throw new IllegalArgumentException("Error: el nombre no puede ser nulo ni vacio");
        }
        this.catalogo = catalogo;
        this.nombre = nombre;
    }

    public static void validarCodigo(String codigo) throws CodigoInvalidoException{
        if (!codigo.startsWith("LIB-")&&!codigo.startsWith("REV-")&&!codigo.startsWith("DVD-")){
            throw new CodigoInvalidoException("El codigo: "+codigo+ " no cumple con el formato (LIB-, REV-, DVD)");
        }
    }
    public static String generarCodigoAleatorio(String tipo) {
        return tipo.toUpperCase() + "-" + ((int)(Math.random() * 900) + 100);
    }

    public void agregarMaterial(MaterialBiblioteca m) throws CodigoInvalidoException{
        if(m==null){
            throw new NullPointerException("No se puede agregar material nulo");
        }
        validarCodigo(m.getCodigo());
        catalogo.add(m);
    }
    public MaterialBiblioteca buscarMaterial(String codigo) throws MaterialNoEncontradoException {
        for (MaterialBiblioteca m : catalogo) {
            if (m.getCodigo().equals(codigo)) {
                return m;
            }
        }
        throw new MaterialNoEncontradoException("Material con código " + codigo + " no encontrado");
    }

    public void prestarMaterial(String codigo) throws MaterialNoEncontradoException, MaterialNoDisponibleException {
        MaterialBiblioteca m = buscarMaterial(codigo);
        if (m.isEstaPrestado()) {
            throw new MaterialNoDisponibleException("El material " + m.getTitulo() + " ya está prestado");
        }
        m.prestar();
        System.out.println("Préstamo exitoso: " + m.getTitulo() + ". Devolver en " + m.calcularTiempoMaximoPrestamo() + " días.");
    }

    public double devolverMaterial(String codigo, int diasRetraso) throws MaterialNoEncontradoException {
        if (diasRetraso < 0) {
            throw new IllegalArgumentException("Los días de retraso no pueden ser negativos");
        }

        MaterialBiblioteca m = buscarMaterial(codigo);
        m.devolver();

        double multa = m.calcularMultaPorRetraso(diasRetraso);
        return multa;
    }

    public void listarMaterialesDisponibles() {
        System.out.println("--- Materiales Disponibles ---");
        for (MaterialBiblioteca m : catalogo) {
            if (!m.isEstaPrestado()) {
                System.out.println(m);
            }
        }
    }

    public void ordenarPorAnio() {
        int n = catalogo.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (catalogo.get(j).getAnioPublicacion() > catalogo.get(j + 1).getAnioPublicacion()) {
                    MaterialBiblioteca temp = catalogo.get(j);
                    catalogo.set(j, catalogo.get(j + 1));
                    catalogo.set(j + 1, temp);
                }
            }
        }
    }
}
