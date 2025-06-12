package PresentacionSwing;

import AccesoDatos.Conexion;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ClienteForm extends JFrame {
    private JTextField txtId, txtNombre, txtCorreo, txtTelefono, txtDUI, txtDireccion, txtIdUsuario;
    private JButton btnGuardar, btnEditar, btnEliminar;
    private JTable tabla;
    private DefaultTableModel modelo;

    public ClienteForm() {
        setTitle("Gestión de Clientes");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 100, 25);
        add(lblId);
        txtId = new JTextField();
        txtId.setBounds(120, 20, 200, 25);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 100, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(120, 60, 200, 25);
        add(txtNombre);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(20, 100, 100, 25);
        add(lblCorreo);
        txtCorreo = new JTextField();
        txtCorreo.setBounds(120, 100, 200, 25);
        add(txtCorreo);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 140, 100, 25);
        add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 140, 200, 25);
        add(txtTelefono);

        JLabel lblDUI = new JLabel("DUI:");
        lblDUI.setBounds(20, 180, 100, 25);
        add(lblDUI);
        txtDUI = new JTextField();
        txtDUI.setBounds(120, 180, 200, 25);
        add(txtDUI);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(20, 220, 100, 25);
        add(lblDireccion);
        txtDireccion = new JTextField();
        txtDireccion.setBounds(120, 220, 200, 25);
        add(txtDireccion);

        JLabel lblIdUsuario = new JLabel("ID Usuario:");
        lblIdUsuario.setBounds(20, 260, 100, 25);
        add(lblIdUsuario);
        txtIdUsuario = new JTextField();
        txtIdUsuario.setBounds(120, 260, 200, 25);
        add(txtIdUsuario);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(350, 20, 120, 30);
        add(btnGuardar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(350, 60, 120, 30);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(350, 100, 120, 30);
        add(btnEliminar);

        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        modelo.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Correo", "Teléfono", "DUI", "Dirección", "ID Usuario"});
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 300, 740, 100);
        add(scroll);

        cargarClientes();

        // Mostrar datos al seleccionar una fila
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                    int fila = tabla.getSelectedRow();
                    txtId.setText(tabla.getValueAt(fila, 0).toString());
                    txtNombre.setText(tabla.getValueAt(fila, 1).toString());
                    txtCorreo.setText(tabla.getValueAt(fila, 2).toString());
                    txtTelefono.setText(tabla.getValueAt(fila, 3).toString());
                    txtDUI.setText(tabla.getValueAt(fila, 4).toString());
                    txtDireccion.setText(tabla.getValueAt(fila, 5).toString());
                    txtIdUsuario.setText(tabla.getValueAt(fila, 6).toString());
                }
            }
        });

        setVisible(true);
    }

    private void cargarClientes() {
        modelo.setRowCount(0); // Limpiar tabla

        try (Connection con = Conexion.getConnection()) {
            String sql = "SELECT * FROM Cliente";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getInt("IdCliente"),
                        rs.getString("Nombre"),
                        rs.getString("Correo"),
                        rs.getString("Telefono"),
                        rs.getString("DUI"),
                        rs.getString("Direccion"),
                        rs.getInt("IdUsuario")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes: " + e.getMessage());
        }
    }
}
