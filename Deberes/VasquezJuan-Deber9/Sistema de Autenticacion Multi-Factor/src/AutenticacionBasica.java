import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AutenticacionBasica implements Autenticable, Auditable {
    private String usuarioValido;
    private String passwordValido;
    private boolean sesionActiva;
    private List<String> historial;
    private int intentosFallidos;

    public AutenticacionBasica(String usuario, String password) {
        this.usuarioValido = usuario;
        this.passwordValido = password;
        this.historial = new ArrayList<>();
        this.intentosFallidos = 0;
    }

    @Override
    public boolean autenticar(String usuario, String credencial) {
        if (intentosFallidos >= 3) {
            System.out.println("CUENTA BLOQUEADA - Contacte al administrador");
            return false;
        }

        if (this.usuarioValido.equals(usuario) && this.passwordValido.equals(credencial)) {
            sesionActiva = true;
            System.out.println("✓ Autenticacion exitosa");
            registrarIntento(usuario, true);
            intentosFallidos = 0;
            return true;
        } else {
            intentosFallidos++;
            System.out.println("✗ Autenticacion fallida");
            if (intentosFallidos < 3) {
                System.out.println("Intento " + intentosFallidos + ": Fallido - Intentos restantes: " + (3 - intentosFallidos));
            }
            registrarIntento(usuario, false);
            return false;
        }
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
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String estado = exitoso ? "LOGIN EXITOSO" : "LOGIN FALLIDO";
        String log = "[" + fecha + "] " + estado;
        historial.add(log);

        String tipo = exitoso ? "Intento exitoso" : "Intento fallido";
        System.out.println("[AUDIT] " + tipo + ": " + usuario + " - " + fecha);
    }

    @Override
    public List<String> obtenerHistorial(String usuario) {
        return historial;
    }
}