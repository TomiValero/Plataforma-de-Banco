package negocioImpl;

import java.util.List;
import dao.IPrestamosDAO;
import daoImpl.PrestamoDaoImpl;
import entidad.Prestamos;
import negocio.PrestamoService;

public class PrestamoServiceImpl implements PrestamoService {

    private IPrestamosDAO<Prestamos> prestamosDao = new PrestamoDaoImpl();

    public List<Prestamos> listarPrestamos() {
        return prestamosDao.list();
    }

    public Prestamos obtenerPrestamoPorId(int id) {
        return prestamosDao.findById(id);
    }

    public boolean agregarPrestamo(Prestamos prestamo) {
        return prestamosDao.insert(prestamo);
    }

    public boolean actualizarPrestamo(Prestamos prestamo) {
        return prestamosDao.update(prestamo);
    }

    public List<Prestamos> listarPrestamosPorEstado(String estado) {
        return  prestamosDao.findByEstado(estado);
    }

	@Override
	public List<Prestamos> listarPrestamosPorIdCliente(int idCliente) {
		return  prestamosDao.findByIdCliente(idCliente);
	}
}
