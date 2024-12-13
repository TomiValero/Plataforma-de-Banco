package negocioImpl;

import java.util.List;

import dao.IGenericDAO;
import daoImpl.LocalidadDaoImpl;
import entidad.Localidad;
import negocio.LocalidadService;

public class LocalidadServiceImpl implements LocalidadService {
	
	private IGenericDAO<Localidad> LocalidadDao = new LocalidadDaoImpl();
		
	 	public List<Localidad> ListarLocalidad() {
	        return LocalidadDao.list();
	    }
	
	    public boolean AgregarLocalidad(Localidad localidad) {
	        return LocalidadDao.insert(localidad);
	    }
	
	    public boolean ActualizarLocalidad(Localidad localidad) {
	        return LocalidadDao.update(localidad);
	    }
	
	    public boolean EliminarLocalidad(Localidad localidad) {
	        return LocalidadDao.delete(localidad);
	    }
	    
	    public Localidad ObtenerById(int id) {
	        return LocalidadDao.findById(id);
	    }
}
