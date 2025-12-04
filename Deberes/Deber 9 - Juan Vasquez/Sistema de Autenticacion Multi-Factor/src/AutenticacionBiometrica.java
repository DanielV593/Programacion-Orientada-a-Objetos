import java.util.ArrayList;
import java.util.List;

public class AutenticacionBiometrica implements Autenticable, Auditable {
    private String usuarioValido;
    private String huellaRegistrada;
    private boolean sesionActiva;
    private List<String> historial;

    public AutenticacionBiometrica(String usuario, String huella) {
        this.usuarioValido = usuario;
        this.huellaRegistrada = huella;
        this.historial = new ArrayList<>();
    }

    @Override
    public boolean autenticar(String usuario, String huella) {
        System.out.println("Escaneando huella dactilar...");
        if (usuarioValido.equals(usuario) && huellaRegistrada.equals(huella)) {
            sesionActiva = true;
            System.out.println("Huella verificada - Confianza: ALTA");
            System.out.println("Autenticacion biometrica exitosa");
            registrarIntento(usuario, true);
            return true;
        }
        registrarIntento(usuario, false);
        return false;
    }

    @Override
    public void cerrarSesion() {
        sesionActiva = false;
    }

    @Override
    public boolean sesionActiva() {
        return sesionActiva;
    }

    @Override
    public void registrarIntento(String usuario, boolean exitoso) {
        String fecha = "2024-12-04 10:31:30";
        System.out.println("[AUDIT] Intento exitoso: " + usuario + " (Biométrica/Alta) - " + fecha);
        historial.add("[" + fecha + "] LOGIN BIOMETRICO " + (exitoso ? "EXITOSO" : "FALLIDO"));
    }

    @Override
    public List<String> obtenerHistorial(String usuario) {
        return historial;
    }
}