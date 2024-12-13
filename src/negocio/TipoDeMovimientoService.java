package negocio;

import java.util.List;


import entidad.TipoDeMovimiento;

public interface TipoDeMovimientoService {
	
 	public List<TipoDeMovimiento> ListarTipos();

    public boolean AgregarTipo(TipoDeMovimiento tipo);

    public boolean ActualizarTipo(TipoDeMovimiento tipo);

    public boolean EliminarTipo(TipoDeMovimiento tipo);
    
    public TipoDeMovimiento ObtenerById(int id);
}
