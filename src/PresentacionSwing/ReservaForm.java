package PresentacionSwing;

import AccesoDatos.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ReservaForm extends JFrame {
    private JTextField txtId, txtIdCliente, txtIdCoche, txtFechaInicio, txtFechaFin, txtEstado;
    private JButton btnGuardar, btnEditar, btnEliminar;
    private JTable tabla;
    private DefaultTableModel modelo;

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

        // Cargar reservas reales desde la base
        cargarReservas();

        setVisible(true);
    }

    private void cargarReservas() {
        modelo.setRowCount(0); // Limpiar tabla

        try (Connection con = Conexion.getConnection()) {
            String sql = "SELECT * FROM Reserva";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("IdReserva");
                int idCliente = rs.getInt("IdCliente");
                int idCoche = rs.getInt("IdCoche");
                Date fechaInicio = rs.getDate("FechaInicio");
                Date fechaFin = rs.getDate("FechaFin");
                int estado = rs.getInt("Estado");

                modelo.addRow(new Object[]{id, idCliente, idCoche, fechaInicio, fechaFin, estado});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar reservas: " + e.getMessage());
        }
    }
}
