package negocioImpl;

import java.util.List;

import dao.IGenericDAO;
import daoImpl.ProvinciaDaoImpl;
import entidad.Provincia;
import negocio.ProvinciaService;

public class ProvinciaServiceImpl implements ProvinciaService{

	private IGenericDAO<Provincia> ProvinciaDao = new ProvinciaDaoImpl();
	
 	public List<Provincia> ListarProvincias() {
        return ProvinciaDao.list();
    }

    public boolean AgregarProvincia(Provincia provincia) {
        return ProvinciaDao.insert(provincia);
    }

    public boolean ActualizarProvincia(Provincia provincia) {
        return ProvinciaDao.update(provincia);
    }

    public boolean EliminarProvincia(Provincia provincia) {
        return ProvinciaDao.delete(provincia);
    }
    
    public Provincia ObtenerById(int id) {
        return ProvinciaDao.findById(id);
    }
}
