package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.MenuPagoDao;
import entidad.Cuota;
import entidad.Prestamos;

public class MenuPagoDaoImpl implements MenuPagoDao {
    private static final String SQL_OBTENER_PRESTAMO =
        "SELECT * FROM Prestamos WHERE id = ?";
    private static final String SQL_OBTENER_CUOTAS_PENDIENTES =
        "SELECT * FROM Cuotas WHERE idPrestamo = ? AND pago = 0";
    private static final String SQL_REALIZAR_PAGO =
        "UPDATE Cuotas SET pago = 1 WHERE idPrestamo = ? AND pago = 0 LIMIT ?";
    private static final String SQL_OBTENER_SALDO =
        "SELECT saldo FROM Cuentas WHERE id = ?";
    private static final String SQL_ACTUALIZAR_ESTADO_PRESTAMO =
        "UPDATE Prestamos SET estado = 'pagado' WHERE id = ? AND " +
        "(SELECT COUNT(*) FROM Cuotas WHERE idPrestamo = ? AND pago = 0) = 0";

    @Override
    public Prestamos obtenerPrestamoPorId(int idPrestamo) {
        Prestamos prestamo = null;
        try (Connection conn = Conexion.getConexion().getSQLConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_OBTENER_PRESTAMO)) {
            ps.setInt(1, idPrestamo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    prestamo = new Prestamos();
                    prestamo.setId(rs.getInt("id"));
                    prestamo.setImportePedido(rs.getDouble("importePedido"));
                    prestamo.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamo;
    }

    @Override
    public List<Cuota> obtenerCuotasPendientesPorPrestamo(int idPrestamo) {
        List<Cuota> cuotas = new ArrayList<>();
        try (Connection conn = Conexion.getConexion().getSQLConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_OBTENER_CUOTAS_PENDIENTES)) {
            ps.setInt(1, idPrestamo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cuota cuota = new Cuota();
                    cuota.setId(rs.getInt("id"));
                    cuota.setIdPrestamo(rs.getInt("idPrestamo"));
                    cuota.setNumCuota(rs.getInt("numCuota"));
                    cuota.setMonto(rs.getDouble("monto"));
                    cuota.setPago(rs.getBoolean("pago"));
                    cuotas.add(cuota);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuotas;
    }

    @Override
    public boolean realizarPagoCuotas(int idPrestamo, int cuotasSeleccionadas) {
        try (Connection conn = Conexion.getConexion().getSQLConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_REALIZAR_PAGO)) {
            ps.setInt(1, idPrestamo);
            ps.setInt(2, cuotasSeleccionadas);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public double obtenerSaldoPorCuenta(int idCuenta) {
        try (Connection conn = Conexion.getConexion().getSQLConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_OBTENER_SALDO)) {
            ps.setInt(1, idCuenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("saldo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void actualizarEstadoPrestamo(int idPrestamo) {
        try (Connection conn = Conexion.getConexion().getSQLConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_ACTUALIZAR_ESTADO_PRESTAMO)) {
            ps.setInt(1, idPrestamo);
            ps.setInt(2, idPrestamo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
