import java.util.List;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE AUTENTICACIÓN ===");

        System.out.println("--- Autenticación Básica ---");
        AutenticacionBasica basicAuth = new AutenticacionBasica("sysadmin", "SuperClave99");

        System.out.println("Usuario: sysadmin / Password: SuperClave99");
        basicAuth.autenticar("sysadmin", "SuperClave99");

        System.out.println("Usuario: sysadmin / Password: claveIncorrecta");
        basicAuth.autenticar("sysadmin", "claveIncorrecta");

        System.out.println("--- Autenticación OTP ---");
        AutenticacionOTP otpAuth = new AutenticacionOTP("roberto.p", "pass1234");
        System.out.println("Usuario: roberto.p / Password: pass1234");

        if (otpAuth.autenticar("roberto.p", "pass1234")) {
            String codigo = otpAuth.generarCodigoVerificacion();
            otpAuth.verificarCodigo(codigo);
        }

        System.out.println("--- Autenticación Biométrica ---");
        AutenticacionBiometrica bioAuth = new AutenticacionBiometrica("elena.s", "face_id_data");
        System.out.println("Usuario: elena.s");
        bioAuth.autenticar("elena.s", "face_id_data");

        System.out.println("--- Historial de Usuario: sysadmin ---");
        List<String> historial = basicAuth.obtenerHistorial("sysadmin");
        for (String log : historial) {
            System.out.println(log);
        }

        System.out.println("--- Prueba de Bloqueo ---");
        basicAuth.autenticar("sysadmin", "error1");
        basicAuth.autenticar("sysadmin", "error2");
        basicAuth.autenticar("sysadmin", "error3");
    }
}