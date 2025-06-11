package PresentacionSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CocheForm extends JFrame {
    private JTextField txtId, txtMarca, txtModelo, txtPlaca, txtAnio, txtColor;
    private JButton btnGuardar, btnEditar, btnEliminar;
    private JTable tabla;
    private DefaultTableModel modelo;

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

        setVisible(true);
    }
}
