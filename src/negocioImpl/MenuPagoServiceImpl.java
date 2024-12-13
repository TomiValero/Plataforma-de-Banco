package negocioImpl;

import java.util.List;
import entidad.Cuota;
import entidad.Prestamos;
import dao.MenuPagoDao;
import daoImpl.MenuPagoDaoImpl;
import negocio.MenuPagoService;

public class MenuPagoServiceImpl implements MenuPagoService {
    private MenuPagoDao menuPagoDao;

    public MenuPagoServiceImpl() {
        this.menuPagoDao = new MenuPagoDaoImpl();
    }

    @Override
    public Prestamos obtenerPrestamoPorId(int idPrestamo) {
        return menuPagoDao.obtenerPrestamoPorId(idPrestamo);
    }

    @Override
    public List<Cuota> listarCuotasPendientesPorPrestamo(int idPrestamo) {
        return menuPagoDao.obtenerCuotasPendientesPorPrestamo(idPrestamo);
    }

    @Override
    public double obtenerSaldoCliente(int idCuenta) {
        return menuPagoDao.obtenerSaldoPorCuenta(idCuenta);
    }

    @Override
    public boolean realizarPagoCuotas(int idPrestamo, int cuotasSeleccionadas) {
        return menuPagoDao.realizarPagoCuotas(idPrestamo, cuotasSeleccionadas);
    }

    @Override
    public void actualizarEstadoPrestamo(int idPrestamo) {
        menuPagoDao.actualizarEstadoPrestamo(idPrestamo);
    }
}
