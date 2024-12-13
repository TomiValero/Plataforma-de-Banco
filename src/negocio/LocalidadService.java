package negocio;

import java.util.List;


import entidad.Localidad;

public interface LocalidadService {
			
	 	public List<Localidad> ListarLocalidad();	
	    public boolean AgregarLocalidad(Localidad localidad);
	    public boolean ActualizarLocalidad(Localidad localidad);
	    public boolean EliminarLocalidad(Localidad localidad);
	    public Localidad ObtenerById(int id);
}
