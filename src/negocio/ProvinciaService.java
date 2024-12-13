package negocio;

import java.util.List;


import entidad.Provincia;

public interface ProvinciaService {

	
 	public List<Provincia> ListarProvincias();

    public boolean AgregarProvincia(Provincia provincia);

    public boolean ActualizarProvincia(Provincia provincia);

    public boolean EliminarProvincia(Provincia provincia);
    
    public Provincia ObtenerById(int id);
}
