package PresentacionSwing;

import AccesoDatos.ClienteDAO;
import AccesoDatos.Conexion;
import Entidades.Cliente;

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
    private ClienteDAO clienteDAO = new ClienteDAO();

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

        // Eventos de los botones
        btnGuardar.addActionListener(e -> {
            try {
                Cliente c = new Cliente(
                        0,
                        txtNombre.getText(),
                        txtCorreo.getText(),
                        txtTelefono.getText(),
                        txtDUI.getText(),
                        txtDireccion.getText(),
                        Integer.parseInt(txtIdUsuario.getText())
                );

                if (clienteDAO.guardar(c)) {
                    JOptionPane.showMessageDialog(this, "Cliente guardado correctamente");
                    cargarClientes();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar cliente");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos: " + ex.getMessage());
            }
        });

        btnEditar.addActionListener(e -> {
            try {
                Cliente c = new Cliente(
                        Integer.parseInt(txtId.getText()),
                        txtNombre.getText(),
                        txtCorreo.getText(),
                        txtTelefono.getText(),
                        txtDUI.getText(),
                        txtDireccion.getText(),
                        Integer.parseInt(txtIdUsuario.getText())
                );

                if (clienteDAO.editar(c)) {
                    JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente");
                    cargarClientes();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar cliente");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos: " + ex.getMessage());
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());

                int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (clienteDAO.eliminar(id)) {
                        JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
                        cargarClientes();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar cliente");
                    }
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ID inválido: " + ex.getMessage());
            }
        });

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

        cargarClientes();
        setVisible(true);
    }

    private void cargarClientes() {
        modelo.setRowCount(0); // Limpiar tabla
        for (Cliente c : clienteDAO.listar()) {
            modelo.addRow(new Object[]{
                    c.getIdCliente(),
                    c.getNombre(),
                    c.getCorreo(),
                    c.getTelefono(),
                    c.getDUI(),
                    c.getDireccion(),
                    c.getIdUsuario()
            });
        }
    }
}
