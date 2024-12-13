package negocio;

import java.util.List;


import entidad.Movimientos;

public interface MovimientosService {

		
	 	public List<Movimientos> ListarMovimientos();
	
	    public boolean AgregarMovimiento(Movimientos movimientos);
	
	    public boolean ActualizarMovimiento(Movimientos movimientos);
	
	    public boolean EliminarMovimiento(Movimientos movimientos);
	    
	    public Movimientos ObtenerById(int id);
	    
	    public List<Movimientos> ListarMovimientosByIdCuenta(int idCuenta);
}
