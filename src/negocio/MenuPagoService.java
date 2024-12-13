package negocio;

import java.util.List;
import entidad.Cuota;
import entidad.Prestamos;

public interface MenuPagoService {
    Prestamos obtenerPrestamoPorId(int idPrestamo);
    List<Cuota> listarCuotasPendientesPorPrestamo(int idPrestamo);
    double obtenerSaldoCliente(int idCuenta);
    boolean realizarPagoCuotas(int idPrestamo, int cuotasSeleccionadas);
    void actualizarEstadoPrestamo(int idPrestamo);
}
