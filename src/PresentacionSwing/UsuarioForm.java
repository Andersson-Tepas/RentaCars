package PresentacionSwing;

import AccesoDatos.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class UsuarioForm extends JFrame {
    private JTextField txtId, txtNombre, txtCorreo, txtContrasena, txtEstado;
    private JButton btnGuardar, btnEditar, btnEliminar;
    private JTable tabla;
    private DefaultTableModel modelo;

    public UsuarioForm() {
        setTitle("Gestión de Usuarios");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 80, 25);
        add(lblId);
        txtId = new JTextField();
        txtId.setBounds(100, 20, 200, 25);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 80, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(100, 60, 200, 25);
        add(txtNombre);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(20, 100, 80, 25);
        add(lblCorreo);
        txtCorreo = new JTextField();
        txtCorreo.setBounds(100, 100, 200, 25);
        add(txtCorreo);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(20, 140, 80, 25);
        add(lblContrasena);
        txtContrasena = new JTextField();
        txtContrasena.setBounds(100, 140, 200, 25);
        add(txtContrasena);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(20, 180, 80, 25);
        add(lblEstado);
        txtEstado = new JTextField();
        txtEstado.setBounds(100, 180, 200, 25);
        add(txtEstado);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(320, 20, 120, 30);
        add(btnGuardar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(320, 60, 120, 30);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(320, 100, 120, 30);
        add(btnEliminar);

        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        modelo.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Correo", "Contraseña", "Estado"});
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 220, 650, 120);
        add(scroll);

        // Cargar registros al iniciar
        cargarUsuarios();

        setVisible(true);
    }

    private void cargarUsuarios() {
        modelo.setRowCount(0); // Limpiar tabla

        try (Connection con = Conexion.getConnection()) {
            String sql = "SELECT * FROM Usuarios";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("IdUsuario");
                String nombre = rs.getString("Nombre");
                String correo = rs.getString("Correo");
                String contrasena = rs.getString("ContrasenaHash");
                int estado = rs.getInt("Estado");

                modelo.addRow(new Object[]{id, nombre, correo, contrasena, estado});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + e.getMessage());
        }
    }
}
