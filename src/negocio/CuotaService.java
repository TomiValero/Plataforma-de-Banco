package negocio;

import java.util.List;
import entidad.Cuota;

public interface CuotaService {
    public List<Cuota> listarTodasLasCuotas();
    public Cuota obtenerCuotaPorId(int id);
    public boolean agregarCuota(Cuota cuota);
    public boolean actualizarCuota(Cuota cuota);
    public boolean eliminarCuota(int id);
    public List<Cuota> listarCuotasPendientesPorCliente(int clienteId);
    public List<Cuota> listarCuotasByPrestamoId(int idPrestamo);
    public boolean pagarCuota(int cuotaId, int cuentaId);
}
