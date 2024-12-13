package negocioImpl;

import java.util.List;

import dao.IGenericDAO;
import dao.IMovimientosDAO;
import daoImpl.MovimientosDaoImpl;
import entidad.Movimientos;
import negocio.MovimientosService;

public class MovimientosServiceImpl implements MovimientosService {

	private IMovimientosDAO<Movimientos> MovimientosDao = new MovimientosDaoImpl();
		
	 	public List<Movimientos> ListarMovimientos() {
	        return MovimientosDao.list();
	    }
	
	    public boolean AgregarMovimiento(Movimientos movimientos) {
	        return MovimientosDao.insert(movimientos);
	    }
	
	    public boolean ActualizarMovimiento(Movimientos movimientos) {
	        return MovimientosDao.update(movimientos);
	    }
	
	    public boolean EliminarMovimiento(Movimientos movimientos) {
	        return MovimientosDao.delete(movimientos);
	    }
	    
	    public Movimientos ObtenerById(int id) {
	        return MovimientosDao.findById(id);
	    }
	    
	    public List<Movimientos> ListarMovimientosByIdCuenta(int idCuenta) {
	        return MovimientosDao.listByIdCuenta(idCuenta);
	    }
}
