package AccesoDatos;

import Entidades.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Obtener todos los clientes
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("IdCliente"),
                        rs.getString("Nombre"),
                        rs.getString("Correo"),
                        rs.getString("Telefono"),
                        rs.getString("DUI"),
                        rs.getString("Direccion"),
                        rs.getInt("IdUsuario")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Guardar nuevo cliente
    public boolean guardar(Cliente cliente) {
        String sql = "INSERT INTO Cliente (Nombre, Correo, Telefono, DUI, Direccion, IdUsuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCorreo());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDUI());
            ps.setString(5, cliente.getDireccion());
            ps.setInt(6, cliente.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Editar cliente existente
    public boolean editar(Cliente cliente) {
        String sql = "UPDATE Cliente SET Nombre=?, Correo=?, Telefono=?, DUI=?, Direccion=?, IdUsuario=? WHERE IdCliente=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCorreo());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDUI());
            ps.setString(5, cliente.getDireccion());
            ps.setInt(6, cliente.getIdUsuario());
            ps.setInt(7, cliente.getIdCliente());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar cliente por ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Cliente WHERE IdCliente=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
