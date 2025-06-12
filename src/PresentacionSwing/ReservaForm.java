package PresentacionSwing;

import AccesoDatos.ReservaDAO;
import Entidades.Reserva;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ReservaForm extends JFrame {
    private JTextField txtId, txtIdCliente, txtIdCoche, txtFechaInicio, txtFechaFin, txtEstado;
    private JButton btnGuardar, btnEditar, btnEliminar;
    private JTable tabla;
    private DefaultTableModel modelo;
    private ReservaDAO reservaDAO = new ReservaDAO();

    public ReservaForm() {
        setTitle("Gestión de Reservas");
        setSize(800, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 100, 25);
        add(lblId);
        txtId = new JTextField();
        txtId.setBounds(120, 20, 200, 25);
        add(txtId);

        JLabel lblIdCliente = new JLabel("ID Cliente:");
        lblIdCliente.setBounds(20, 60, 100, 25);
        add(lblIdCliente);
        txtIdCliente = new JTextField();
        txtIdCliente.setBounds(120, 60, 200, 25);
        add(txtIdCliente);

        JLabel lblIdCoche = new JLabel("ID Coche:");
        lblIdCoche.setBounds(20, 100, 100, 25);
        add(lblIdCoche);
        txtIdCoche = new JTextField();
        txtIdCoche.setBounds(120, 100, 200, 25);
        add(txtIdCoche);

        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaInicio.setBounds(20, 140, 100, 25);
        add(lblFechaInicio);
        txtFechaInicio = new JTextField("YYYY-MM-DD");
        txtFechaInicio.setBounds(120, 140, 200, 25);
        add(txtFechaInicio);

        JLabel lblFechaFin = new JLabel("Fecha Fin:");
        lblFechaFin.setBounds(20, 180, 100, 25);
        add(lblFechaFin);
        txtFechaFin = new JTextField("YYYY-MM-DD");
        txtFechaFin.setBounds(120, 180, 200, 25);
        add(txtFechaFin);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(20, 220, 100, 25);
        add(lblEstado);
        txtEstado = new JTextField();
        txtEstado.setBounds(120, 220, 200, 25);
        add(txtEstado);

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
        modelo.setColumnIdentifiers(new Object[]{
                "ID", "ID Cliente", "ID Coche", "Fecha Inicio", "Fecha Fin", "Estado"
        });
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 260, 740, 100);
        add(scroll);

        // Evento de selección de tabla
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                    int fila = tabla.getSelectedRow();
                    txtId.setText(tabla.getValueAt(fila, 0).toString());
                    txtIdCliente.setText(tabla.getValueAt(fila, 1).toString());
                    txtIdCoche.setText(tabla.getValueAt(fila, 2).toString());
                    txtFechaInicio.setText(tabla.getValueAt(fila, 3).toString());
                    txtFechaFin.setText(tabla.getValueAt(fila, 4).toString());
                    txtEstado.setText(tabla.getValueAt(fila, 5).toString());
                }
            }
        });

        // Botones
        btnGuardar.addActionListener(e -> {
            try {
                Reserva r = new Reserva(
                        0,
                        Integer.parseInt(txtIdCliente.getText()),
                        Integer.parseInt(txtIdCoche.getText()),
                        Date.valueOf(txtFechaInicio.getText()),
                        Date.valueOf(txtFechaFin.getText()),
                        Integer.parseInt(txtEstado.getText())
                );
                if (reservaDAO.guardar(r)) {
                    JOptionPane.showMessageDialog(this, "Reserva guardada.");
                    cargarReservas();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
        });

        btnEditar.addActionListener(e -> {
            try {
                Reserva r = new Reserva(
                        Integer.parseInt(txtId.getText()),
                        Integer.parseInt(txtIdCliente.getText()),
                        Integer.parseInt(txtIdCoche.getText()),
                        Date.valueOf(txtFechaInicio.getText()),
                        Date.valueOf(txtFechaFin.getText()),
                        Integer.parseInt(txtEstado.getText())
                );
                if (reservaDAO.editar(r)) {
                    JOptionPane.showMessageDialog(this, "Reserva actualizada.");
                    cargarReservas();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                int confirm = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar la reserva?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (reservaDAO.eliminar(id)) {
                        JOptionPane.showMessageDialog(this, "Reserva eliminada.");
                        cargarReservas();
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
            }
        });

        cargarReservas();
        setVisible(true);
    }

    private void cargarReservas() {
        modelo.setRowCount(0);
        for (Reserva r : reservaDAO.listar()) {
            modelo.addRow(new Object[]{
                    r.getIdReserva(),
                    r.getIdCliente(),
                    r.getIdCoche(),
                    r.getFechaInicio(),
                    r.getFechaFin(),
                    r.getEstado()
            });
        }
    }
}
