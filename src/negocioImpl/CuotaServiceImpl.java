package negocioImpl;

import java.util.List;

import dao.CuotaDao;
import daoImpl.CuotaDaoImpl;
import entidad.Cuota;
import negocio.CuotaService;

public class CuotaServiceImpl implements CuotaService {

    private CuotaDao cuotaDao = new CuotaDaoImpl();

    @Override
    public List<Cuota> listarTodasLasCuotas() {
        return cuotaDao.listarTodasLasCuotas();
    }

    @Override
    public Cuota obtenerCuotaPorId(int id) {
        return cuotaDao.obtenerCuotaPorId(id);
    }

    @Override
    public boolean agregarCuota(Cuota cuota) {
        return cuotaDao.insertarCuota(cuota);
    }

    @Override
    public boolean actualizarCuota(Cuota cuota) {
        return cuotaDao.actualizarCuota(cuota);
    }

    @Override
    public boolean eliminarCuota(int id) {
        return cuotaDao.eliminarCuota(id);
    }

    @Override
    public List<Cuota> listarCuotasPendientesPorCliente(int clienteId) {
        return cuotaDao.obtenerCuotasPendientesPorCliente(clienteId);
    }

    @Override
    public boolean pagarCuota(int cuotaId, int cuentaId) {
        return cuotaDao.actualizarCuota(new Cuota(cuotaId, true));
    }

	@Override
	public List<Cuota> listarCuotasByPrestamoId(int idPrestamo) {
		return cuotaDao.listarCuotasByPrestamoId(idPrestamo);
	}
}
