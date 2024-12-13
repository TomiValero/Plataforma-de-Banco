package dao;

import java.util.List;
import entidad.Cuota;
import entidad.Prestamos;

public interface MenuPagoDao {
    Prestamos obtenerPrestamoPorId(int idPrestamo);
    List<Cuota> obtenerCuotasPendientesPorPrestamo(int idPrestamo);
    boolean realizarPagoCuotas(int idPrestamo, int cuotasSeleccionadas);
    double obtenerSaldoPorCuenta(int idCuenta);
    void actualizarEstadoPrestamo(int idPrestamo);
}
