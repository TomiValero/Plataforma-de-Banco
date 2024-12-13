package negocio;

import java.util.List;


import entidad.TipoDeCuenta;

public interface TipoDeCuentaService {
		
	 	public List<TipoDeCuenta> ListarTipos();	
	    public boolean AgregarTipo(TipoDeCuenta tipo);
	    public boolean ActualizarTipo(TipoDeCuenta tipo);
	    public boolean EliminarTipo(TipoDeCuenta tipo);
	    public TipoDeCuenta ObtenerById(int id);
}
