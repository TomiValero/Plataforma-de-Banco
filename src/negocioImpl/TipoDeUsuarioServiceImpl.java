package negocioImpl;

import java.util.List;

import dao.IGenericDAO;
import daoImpl.TipoDeUsuarioDaoImpl;
import entidad.TipoDeUsuarios;
import negocio.TipoDeUsuarioService;


public class TipoDeUsuarioServiceImpl implements TipoDeUsuarioService {
	private IGenericDAO<TipoDeUsuarios> TipoDao = new TipoDeUsuarioDaoImpl();
		
	 	public List<TipoDeUsuarios> ListarTipos() {
	        return TipoDao.list();
	    }
	
	    public boolean AgregarTipo(TipoDeUsuarios tipo) {
	        return TipoDao.insert(tipo);
	    }
	
	    public boolean ActualizarTipo(TipoDeUsuarios tipo) {
	        return TipoDao.update(tipo);
	    }
	
	    public boolean EliminarTipo(TipoDeUsuarios tipo) {
	        return TipoDao.delete(tipo);
	    }
	    
	    public TipoDeUsuarios ObtenerById(int id) {
	        return TipoDao.findById(id);
	    }
}
