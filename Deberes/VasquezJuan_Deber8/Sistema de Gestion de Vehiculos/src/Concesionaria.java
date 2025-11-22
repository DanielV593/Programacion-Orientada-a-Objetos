import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Concesionaria {
    private ArrayList <Vehiculo> inventario = new ArrayList<>();
    public void agregarVehiculo(Vehiculo v){
        if (v == null){
            throw new NullPointerException("El vehiculo a agregar no puede ser nulo");
        }
        inventario.add(v);
    }
    public double calcularTotalImpuestos(){
        if(inventario.isEmpty()){
            throw new IllegalStateException("El inventario esta vacio ");
        }
        double totalImpuestos = 0;
        for(Vehiculo v : inventario){
            totalImpuestos += v.calcularImpuestoCirculacion();
        }
        return totalImpuestos;
    }
    public Vehiculo obtenerVehiculoMasCaro(){
        if(inventario.isEmpty()){
            throw new IllegalStateException("El inventario esta vacio ");
        }
        Vehiculo masCaro = inventario.get(0);
        for (Vehiculo v : inventario){
            if(v.getPrecio()> masCaro.getPrecio()){
                masCaro = v;
            }
        }
        return masCaro;
    }
    public void ordenarPorPrecio(){
        Collections.sort(inventario, Comparator.comparingDouble(Vehiculo::getPrecio));
    }
    public ArrayList<Vehiculo> getInventario(){
        return inventario;
    }
}

