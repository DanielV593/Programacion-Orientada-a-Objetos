public abstract class MaterialBiblioteca {
    private String codigo;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean estaPrestado;

    public MaterialBiblioteca(String codigo, String titulo, String autor, int anioPublicacion, boolean estaPrestado) {
        if(codigo==null || codigo.trim().isEmpty()){
            throw new IllegalArgumentException("El codigo no puede ser nulo ni vacio");
        }
        if(titulo==null || titulo.trim().isEmpty()){
            throw new IllegalArgumentException("El titulo no puede ser nulo ni vacio");
        }
        if(autor==null||autor.trim().isEmpty()){
            throw new IllegalArgumentException("El autor no puede ser nulo ni vacio");
        }
        if(anioPublicacion<1000||anioPublicacion>2025){
            throw new IllegalArgumentException("El año de publicacion debe ser entre 1000 y 2025");
        }
        this.titulo = titulo;
        this.codigo = codigo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.estaPrestado = estaPrestado;
    }
    public abstract double calcularMultaPorRetraso(int diasRetraso);
    public abstract int calcularTiempoMaximoPrestamo();

    public void prestar() {
        this.estaPrestado = true;
    }

    public void devolver() {
        this.estaPrestado = false;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public boolean isEstaPrestado() {
        return estaPrestado;
    }

    public void setEstaPrestado(boolean estaPrestado) {
        this.estaPrestado = estaPrestado;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + titulo + " (" + anioPublicacion + ") - Prestado: " + estaPrestado;
    }
}
