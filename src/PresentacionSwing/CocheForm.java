package PresentacionSwing;

import AccesoDatos.CocheDAO;
import Entidades.Coche;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CocheForm extends JFrame {
    private JTextField txtId, txtMarca, txtModelo, txtPlaca, txtAnio, txtColor;
    private JButton btnGuardar, btnEditar, btnEliminar;
    private JTable tabla;
    private DefaultTableModel modelo;
    private CocheDAO cocheDAO = new CocheDAO();

    public CocheForm() {
        setTitle("Gestión de Coches");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 80, 25);
        add(lblId);
        txtId = new JTextField();
        txtId.setBounds(100, 20, 200, 25);
        add(txtId);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(20, 60, 80, 25);
        add(lblMarca);
        txtMarca = new JTextField();
        txtMarca.setBounds(100, 60, 200, 25);
        add(txtMarca);

        JLabel lblModelo = new JLabel("Modelo:");
        lblModelo.setBounds(20, 100, 80, 25);
        add(lblModelo);
        txtModelo = new JTextField();
        txtModelo.setBounds(100, 100, 200, 25);
        add(txtModelo);

        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setBounds(20, 140, 80, 25);
        add(lblPlaca);
        txtPlaca = new JTextField();
        txtPlaca.setBounds(100, 140, 200, 25);
        add(txtPlaca);

        JLabel lblAnio = new JLabel("Año:");
        lblAnio.setBounds(20, 180, 80, 25);
        add(lblAnio);
        txtAnio = new JTextField();
        txtAnio.setBounds(100, 180, 200, 25);
        add(txtAnio);

        JLabel lblColor = new JLabel("Color:");
        lblColor.setBounds(20, 220, 80, 25);
        add(lblColor);
        txtColor = new JTextField();
        txtColor.setBounds(100, 220, 200, 25);
        add(txtColor);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(330, 20, 120, 30);
        add(btnGuardar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(330, 60, 120, 30);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(330, 100, 120, 30);
        add(btnEliminar);

        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        modelo.setColumnIdentifiers(new Object[]{"ID", "Marca", "Modelo", "Placa", "Año", "Color"});
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 260, 700, 100);
        add(scroll);

        // Acciones de los botones
        btnGuardar.addActionListener(e -> {
            try {
                Coche c = new Coche(
                        0,
                        txtMarca.getText(),
                        txtModelo.getText(),
                        txtPlaca.getText(),
                        Integer.parseInt(txtAnio.getText()),
                        txtColor.getText()
                );

                if (cocheDAO.guardar(c)) {
                    JOptionPane.showMessageDialog(this, "Coche guardado correctamente");
                    cargarCoches();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar coche");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos: " + ex.getMessage());
            }
        });

        btnEditar.addActionListener(e -> {
            try {
                Coche c = new Coche(
                        Integer.parseInt(txtId.getText()),
                        txtMarca.getText(),
                        txtModelo.getText(),
                        txtPlaca.getText(),
                        Integer.parseInt(txtAnio.getText()),
                        txtColor.getText()
                );

                if (cocheDAO.editar(c)) {
                    JOptionPane.showMessageDialog(this, "Coche actualizado correctamente");
                    cargarCoches();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar coche");
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
                    if (cocheDAO.eliminar(id)) {
                        JOptionPane.showMessageDialog(this, "Coche eliminado correctamente");
                        cargarCoches();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar coche");
                    }
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ID inválido: " + ex.getMessage());
            }
        });

        // Evento al seleccionar fila
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                    int fila = tabla.getSelectedRow();
                    txtId.setText(tabla.getValueAt(fila, 0).toString());
                    txtMarca.setText(tabla.getValueAt(fila, 1).toString());
                    txtModelo.setText(tabla.getValueAt(fila, 2).toString());
                    txtPlaca.setText(tabla.getValueAt(fila, 3).toString());
                    txtAnio.setText(tabla.getValueAt(fila, 4).toString());
                    txtColor.setText(tabla.getValueAt(fila, 5).toString());
                }
            }
        });

        cargarCoches();
        setVisible(true);
    }

    private void cargarCoches() {
        modelo.setRowCount(0);
        for (Coche c : cocheDAO.listar()) {
            modelo.addRow(new Object[]{
                    c.getIdCoche(),
                    c.getMarca(),
                    c.getModelo(),
                    c.getPlaca(),
                    c.getAño(),
                    c.getColor()
            });
        }
    }
}
