import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AutenticacionOTP implements Autenticable, MultiFactor, Auditable {
    private String usuarioValido;
    private String passwordValido;
    private String codigoOTP;
    private int intentosCodigo;
    private boolean sesionActiva;
    private List<String> historial;

    public AutenticacionOTP(String usuario, String password) {
        this.usuarioValido = usuario;
        this.passwordValido = password;
        this.historial = new ArrayList<>();
    }

    @Override
    public boolean autenticar(String usuario, String credencial) {
        if (this.usuarioValido.equals(usuario) && this.passwordValido.equals(credencial)) {
            return true;
        }
        return false;
    }

    @Override
    public String generarCodigoVerificacion() {
        codigoOTP = "784523";
        intentosCodigo = 3;
        System.out.println("Codigo OTP generado: " + codigoOTP);
        System.out.println("Codigo enviado por SMS");
        return codigoOTP;
    }

    @Override
    public boolean verificarCodigo(String codigo) {
        if (intentosCodigo <= 0) {
            System.out.println(" Sin intentos restantes");
            return false;
        }

        intentosCodigo--;
        System.out.println("Ingresando codigo: " + codigo);

        if (codigoOTP.equals(codigo)) {
            System.out.println(" Codigo verificado correctamente");
            sesionActiva = true;
            System.out.println(" Autenticacion OTP exitosa");
            registrarIntento(usuarioValido, true);
            return true;
        } else {
            System.out.println(" Codigo incorrecto - Intentos restantes: " + intentosCodigo);
            registrarIntento(usuarioValido, false);
            return false;
        }
    }

    @Override
    public int intentosRestantes() {
        return intentosCodigo;
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
        System.out.println("[AUDIT] Intento exitoso: " + usuario + " (OTP) - " + fecha);
        historial.add("[" + fecha + "] LOGIN OTP " + (exitoso ? "EXITOSO" : "FALLIDO"));
    }

    @Override
    public List<String> obtenerHistorial(String usuario) {
        return historial;
    }
}