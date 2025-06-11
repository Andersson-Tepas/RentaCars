package PresentacionSwing;

import javax.swing.*;
import java.awt.event.*;

public class MenuPrincipalForm extends JFrame {

    public MenuPrincipalForm() {
        setTitle("Menú Principal");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crear barra de menú simple
        JMenuBar menuBar = new JMenuBar();

        // Ítems directos
        JMenuItem itemUsuarios = new JMenuItem("Usuarios");
        JMenuItem itemClientes = new JMenuItem("Clientes");
        JMenuItem itemCoches = new JMenuItem("Coches");
        JMenuItem itemReservas = new JMenuItem("Reservas");
        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesión");

        // Agregar ítems al menú bar
        menuBar.add(itemUsuarios);
        menuBar.add(itemClientes);
        menuBar.add(itemCoches);
        menuBar.add(itemReservas);
        menuBar.add(Box.createHorizontalGlue()); // Alinear el último a la derecha
        menuBar.add(itemCerrarSesion);

        setJMenuBar(menuBar);

        // Acciones de cada ítem
        itemUsuarios.addActionListener(e -> new UsuarioForm());
        itemClientes.addActionListener(e -> new ClienteForm());
        itemCoches.addActionListener(e -> new CocheForm());
        itemReservas.addActionListener(e -> new ReservaForm());

        itemCerrarSesion.addActionListener(e -> {
            dispose(); // Cerrar ventana actual
            new LoginForm(); // Volver al login
        });

        // Mensaje de bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenido al Sistema de Gestión de Reservas", SwingConstants.CENTER);
        add(lblBienvenida);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuPrincipalForm();
    }
}
