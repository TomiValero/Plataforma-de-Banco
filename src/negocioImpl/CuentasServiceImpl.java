package negocioImpl;

import java.util.List;

import dao.ICuentasDAO;
import daoImpl.CuentasDaoImpl;
import entidad.Cuentas;
import negocio.CuentasService;

public class CuentasServiceImpl implements CuentasService {
	private ICuentasDAO<Cuentas> CuentasDao = new CuentasDaoImpl();
	
 	public List<Cuentas> ListarCuentas() {
        return CuentasDao.list();
    }

    public boolean AgregarCuenta(Cuentas cuenta) {
        return CuentasDao.insert(cuenta);
    }

    public boolean ActualizarCuenta(Cuentas cuenta) {
        return CuentasDao.update(cuenta);
    }

    public boolean EliminarCuenta(Cuentas cuenta) {
        return CuentasDao.delete(cuenta);
    }
    
    public Cuentas ObtenerById(int id) {
        return CuentasDao.findById(id);
    }

	@Override
	public List<Cuentas> ObtenerByIdCliente(int id) {
		return CuentasDao.findByIdCliente(id);
	}

	@Override
	public Cuentas ObtenerByCbu(int cbu) {
		return CuentasDao.findByCbu(cbu);
	}
}
