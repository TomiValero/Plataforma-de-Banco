package dao;

import java.util.List;
import entidad.Cuota;

public interface CuotaDao {
    List<Cuota> listarTodasLasCuotas();
    Cuota obtenerCuotaPorId(int id);
    boolean insertarCuota(Cuota cuota);
    boolean actualizarCuota(Cuota cuota);
    boolean eliminarCuota(int id);
    List<Cuota> obtenerCuotasPendientesPorCliente(int clienteId);
    List<Cuota> listarCuotasByPrestamoId(int idPrestamo);
}
