import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class HojaVida extends JFrame {
    private JPanel panelPrincipal;

    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtGithub;

    private JComboBox<String> cbCargo;
    private JComboBox<String> cbEstudios;
    private JComboBox<String> cbIngles;

    private JTextArea txtPerfil;
    private JTextArea txtExperiencia;

    private JButton btnGuardar;

    private JLabel lblNombre, lblTelefono, lblCorreo, lblCargo, lblPerfil, lblExp, lblEst, lblIng;

    public HojaVida() {
        setTitle("Generador de CV Profesional - ESFOT");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        if (txtPerfil != null) {
            txtPerfil.setLineWrap(true);
            txtPerfil.setWrapStyleWord(true);
        }
        if (txtExperiencia != null) {
            txtExperiencia.setLineWrap(true);
            txtExperiencia.setWrapStyleWord(true);
        }

        llenarListas();

        setSize(600, 750);
        setLocationRelativeTo(null);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
    }

    private void llenarListas() {
        if (cbCargo != null) {
            cbCargo.addItem("Desarrollador de Software Junior");
            cbCargo.addItem("Analista de Sistemas");
            cbCargo.addItem("Backend Developer");
            cbCargo.addItem("Supervisor");
        }

        if (cbEstudios != null) {
            cbEstudios.addItem("Estudiante");
            cbEstudios.addItem("Egresado");
            cbEstudios.addItem("Graduado");
        }

        if (cbIngles != null) {
            cbIngles.addItem("Básico");
            cbIngles.addItem("Intermedio");
            cbIngles.addItem("Técnico");
            cbIngles.addItem("Avanzado");
        }
    }

    private void guardarDatos() {
        String nombre = txtNombre.getText().trim();
        String tel = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();
        String github = txtGithub.getText().trim();

        String cargo = cbCargo.getSelectedItem() != null ? cbCargo.getSelectedItem().toString() : "";
        String perfil = txtPerfil.getText().trim();
        String experiencia = txtExperiencia.getText().trim();
        String estudios = cbEstudios.getSelectedItem() != null ? cbEstudios.getSelectedItem().toString() : "";
        String ingles = cbIngles.getSelectedItem() != null ? cbIngles.getSelectedItem().toString() : "";

        if (nombre.isEmpty() || tel.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Faltan datos personales básicos");
            return;
        }

        if (!tel.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El teléfono debe contener solo números");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Ingrese un correo válido");
            return;
        }

        try {
            FileWriter archivo = new FileWriter("BaseDatos_HojasDeVida.txt", true);
            PrintWriter escritor = new PrintWriter(archivo);

            escritor.println("=================================================");
            escritor.println("                 HOJA DE VIDA                    ");
            escritor.println("=================================================");
            escritor.println("DATOS PERSONALES");
            escritor.println("Nombre:   " + nombre);
            escritor.println("Teléfono: +593 " + tel);
            escritor.println("Correo:   " + email);
            escritor.println("GitHub:   " + github);
            escritor.println("Cargo:    " + cargo);
            escritor.println("\nPERFIL PROFESIONAL");
            escritor.println(perfil);
            escritor.println("\nEXPERIENCIA LABORAL");
            escritor.println(experiencia);
            escritor.println("\nEDUCACIÓN E IDIOMAS");
            escritor.println("Estudios: " + estudios);
            escritor.println("Inglés:   " + ingles);
            escritor.println("=================================================\n");

            escritor.close();
            archivo.close();

            JOptionPane.showMessageDialog(this, "CV Generado y Guardado Exitosamente");
            txtNombre.setText("");
            txtTelefono.setText("");
            txtEmail.setText("");
            txtGithub.setText("");
            txtPerfil.setText("");
            txtExperiencia.setText("");

            if (cbCargo != null) cbCargo.setSelectedIndex(0);
            if (cbEstudios != null) cbEstudios.setSelectedIndex(0);
            if (cbIngles != null) cbIngles.setSelectedIndex(0);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HojaVida().setVisible(true));
    }
}