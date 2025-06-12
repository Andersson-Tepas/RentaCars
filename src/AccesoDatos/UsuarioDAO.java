package AccesoDatos;

import Entidades.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Listar todos los usuarios
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nombre"),
                        rs.getString("ContrasenaHash"),
                        rs.getString("Correo"),
                        rs.getInt("Estado")
                );
                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Guardar nuevo usuario
    public boolean guardar(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (Nombre, ContrasenaHash, Correo, Estado) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getContrasenaHash());
            ps.setString(3, usuario.getCorreo());
            ps.setInt(4, usuario.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Editar usuario existente
    public boolean editar(Usuario usuario) {
        String sql = "UPDATE Usuarios SET Nombre=?, ContrasenaHash=?, Correo=?, Estado=? WHERE IdUsuario=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getContrasenaHash());
            ps.setString(3, usuario.getCorreo());
            ps.setInt(4, usuario.getEstado());
            ps.setInt(5, usuario.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar usuario por ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Usuarios WHERE IdUsuario=?";

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
