package PresentacionSwing;

import AccesoDatos.UsuarioDAO;
import Entidades.Usuario;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UsuarioForm extends JFrame {
    private JTextField txtId, txtNombre, txtCorreo, txtContrasena, txtEstado;
    private JButton btnGuardar, btnEditar, btnEliminar;
    private JTable tabla;
    private DefaultTableModel modelo;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

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

        // Eventos
        btnGuardar.addActionListener(e -> {
            try {
                Usuario u = new Usuario(0,
                        txtNombre.getText(),
                        txtContrasena.getText(),
                        txtCorreo.getText(),
                        Integer.parseInt(txtEstado.getText())
                );
                if (usuarioDAO.guardar(u)) {
                    JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente.");
                    cargarUsuarios();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos.");
            }
        });

        btnEditar.addActionListener(e -> {
            try {
                Usuario u = new Usuario(
                        Integer.parseInt(txtId.getText()),
                        txtNombre.getText(),
                        txtContrasena.getText(),
                        txtCorreo.getText(),
                        Integer.parseInt(txtEstado.getText())
                );
                if (usuarioDAO.editar(u)) {
                    JOptionPane.showMessageDialog(this, "Usuario actualizado.");
                    cargarUsuarios();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos.");
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                int confirm = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar este usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (usuarioDAO.eliminar(id)) {
                        JOptionPane.showMessageDialog(this, "Usuario eliminado.");
                        cargarUsuarios();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar.");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });

        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                    int fila = tabla.getSelectedRow();
                    txtId.setText(tabla.getValueAt(fila, 0).toString());
                    txtNombre.setText(tabla.getValueAt(fila, 1).toString());
                    txtCorreo.setText(tabla.getValueAt(fila, 2).toString());
                    txtContrasena.setText(tabla.getValueAt(fila, 3).toString());
                    txtEstado.setText(tabla.getValueAt(fila, 4).toString());
                }
            }
        });

        cargarUsuarios();
        setVisible(true);
    }

    private void cargarUsuarios() {
        modelo.setRowCount(0);
        for (Usuario u : usuarioDAO.listar()) {
            modelo.addRow(new Object[]{
                    u.getIdUsuario(),
                    u.getNombre(),
                    u.getCorreo(),
                    u.getContrasenaHash(),
                    u.getEstado()
            });
        }
    }
}
