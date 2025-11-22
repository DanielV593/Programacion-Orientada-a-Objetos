public class ListaVaciaException extends Exception{

    public ListaVaciaException(){
        super ("Error: La lista esta vacia");
    }
    public ListaVaciaException(String mensaje){
        super (mensaje);
    }
}
