package negocioImpl;

import java.util.List;

import dao.IGenericDAO;
import daoImpl.TipoDeMovimientoDaoImpl;
import entidad.TipoDeMovimiento;
import negocio.TipoDeMovimientoService;

public class TipoDeMovimientoServiceImpl implements TipoDeMovimientoService {
	private IGenericDAO<TipoDeMovimiento> TipoDao = new TipoDeMovimientoDaoImpl();
	
 	public List<TipoDeMovimiento> ListarTipos() {
        return TipoDao.list();
    }

    public boolean AgregarTipo(TipoDeMovimiento tipo) {
        return TipoDao.insert(tipo);
    }

    public boolean ActualizarTipo(TipoDeMovimiento tipo) {
        return TipoDao.update(tipo);
    }

    public boolean EliminarTipo(TipoDeMovimiento tipo) {
        return TipoDao.delete(tipo);
    }
    
    public TipoDeMovimiento ObtenerById(int id) {
        return TipoDao.findById(id);
    }
}
