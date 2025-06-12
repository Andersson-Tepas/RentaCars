package AccesoDatos;

import Entidades.Coche;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CocheDAO {

    // Listar todos los coches
    public List<Coche> listar() {
        List<Coche> lista = new ArrayList<>();
        String sql = "SELECT * FROM Coche";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Coche c = new Coche(
                        rs.getInt("IdCoche"),
                        rs.getString("Marca"),
                        rs.getString("Modelo"),
                        rs.getString("Placa"),
                        rs.getInt("Año"),
                        rs.getString("Color")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Guardar coche nuevo
    public boolean guardar(Coche coche) {
        String sql = "INSERT INTO Coche (Marca, Modelo, Placa, Año, Color) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, coche.getMarca());
            ps.setString(2, coche.getModelo());
            ps.setString(3, coche.getPlaca());
            ps.setInt(4, coche.getAño());
            ps.setString(5, coche.getColor());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Editar coche existente
    public boolean editar(Coche coche) {
        String sql = "UPDATE Coche SET Marca=?, Modelo=?, Placa=?, Año=?, Color=? WHERE IdCoche=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, coche.getMarca());
            ps.setString(2, coche.getModelo());
            ps.setString(3, coche.getPlaca());
            ps.setInt(4, coche.getAño());
            ps.setString(5, coche.getColor());
            ps.setInt(6, coche.getIdCoche());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar coche por ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Coche WHERE IdCoche=?";

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
