package negocio;

import java.util.List;


import entidad.TipoDeUsuarios;


public interface TipoDeUsuarioService {
		
	 	public List<TipoDeUsuarios> ListarTipos();	
	    public boolean AgregarTipo(TipoDeUsuarios tipo);
	    public boolean ActualizarTipo(TipoDeUsuarios tipo);
	    public boolean EliminarTipo(TipoDeUsuarios tipo);	    
	    public TipoDeUsuarios ObtenerById(int id);
}