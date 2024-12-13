package negocio;

import java.util.List;


import entidad.Cuentas;

public interface CuentasService {
	
 	public List<Cuentas> ListarCuentas();
    public boolean AgregarCuenta(Cuentas cuenta);
    public boolean ActualizarCuenta(Cuentas cuenta);
    public boolean EliminarCuenta(Cuentas cuenta);
    public Cuentas ObtenerById(int id);
    public Cuentas ObtenerByCbu(int cbu);
    public List<Cuentas> ObtenerByIdCliente(int id);
}

