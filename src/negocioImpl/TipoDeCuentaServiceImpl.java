package negocioImpl;

import java.util.List;

import dao.IGenericDAO;
import daoImpl.TipoDeCuentaDaoImpl;
import entidad.TipoDeCuenta;
import negocio.TipoDeCuentaService;

public class TipoDeCuentaServiceImpl implements TipoDeCuentaService {
	private IGenericDAO<TipoDeCuenta> TipoDao = new TipoDeCuentaDaoImpl();
		
	 	public List<TipoDeCuenta> ListarTipos() {
	        return TipoDao.list();
	    }
	
	    public boolean AgregarTipo(TipoDeCuenta tipo) {
	        return TipoDao.insert(tipo);
	    }
	
	    public boolean ActualizarTipo(TipoDeCuenta tipo) {
	        return TipoDao.update(tipo);
	    }
	
	    public boolean EliminarTipo(TipoDeCuenta tipo) {
	        return TipoDao.delete(tipo);
	    }
	    
	    public TipoDeCuenta ObtenerById(int id) {
	        return TipoDao.findById(id);
	    }
}
