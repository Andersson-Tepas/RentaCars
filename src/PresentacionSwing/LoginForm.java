package PresentacionSwing;

import AccesoDatos.Conexion;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnLogin;

    public LoginForm() {
        setTitle("Iniciar Sesión");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 30, 80, 25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(120, 30, 180, 25);
        add(txtCorreo);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(30, 70, 80, 25);
        add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(120, 70, 180, 25);
        add(txtContrasena);

        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(100, 110, 140, 30);
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        setVisible(true);
    }

    private void login() {
        String correo = txtCorreo.getText();
        String contrasena = String.valueOf(txtContrasena.getPassword());

        try (Connection con = Conexion.getConnection()) {
            String sql = "SELECT * FROM Usuarios WHERE Correo=? AND ContrasenaHash=? AND Estado=1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión correcto");
                dispose();
                new MenuPrincipalForm();
            } else {
                JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos o usuario inactivo");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
