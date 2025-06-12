package AccesoDatos;

import Entidades.Reserva;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    // Listar reservas
    public List<Reserva> listar() {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM Reserva";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reserva r = new Reserva(
                        rs.getInt("IdReserva"),
                        rs.getInt("IdCliente"),
                        rs.getInt("IdCoche"),
                        rs.getDate("FechaInicio"),
                        rs.getDate("FechaFin"),
                        rs.getInt("Estado")
                );
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Guardar nueva reserva
    public boolean guardar(Reserva reserva) {
        String sql = "INSERT INTO Reserva (IdCliente, IdCoche, FechaInicio, FechaFin, Estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reserva.getIdCliente());
            ps.setInt(2, reserva.getIdCoche());
            ps.setDate(3, new java.sql.Date(reserva.getFechaInicio().getTime()));
            ps.setDate(4, new java.sql.Date(reserva.getFechaFin().getTime()));
            ps.setInt(5, reserva.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Editar reserva
    public boolean editar(Reserva reserva) {
        String sql = "UPDATE Reserva SET IdCliente=?, IdCoche=?, FechaInicio=?, FechaFin=?, Estado=? WHERE IdReserva=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reserva.getIdCliente());
            ps.setInt(2, reserva.getIdCoche());
            ps.setDate(3, new java.sql.Date(reserva.getFechaInicio().getTime()));
            ps.setDate(4, new java.sql.Date(reserva.getFechaFin().getTime()));
            ps.setInt(5, reserva.getEstado());
            ps.setInt(6, reserva.getIdReserva());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar reserva
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Reserva WHERE IdReserva=?";

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
