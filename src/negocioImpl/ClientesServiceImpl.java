package negocioImpl;

import java.util.List;

import dao.IClientesDAO;
import daoImpl.ClientesDaoImpl;
import entidad.Clientes;
import exceptions.DniYaExiste;
import exceptions.EmailYaExiste;
import exceptions.UsuarioYaExiste;
import negocio.ClientesService;

public class ClientesServiceImpl implements ClientesService {
	private IClientesDAO<Clientes> ClientesDao = new ClientesDaoImpl();
		
	 	public List<Clientes> ListarCliente() {
	        return ClientesDao.list();
	    }
	
	    public boolean AgregarCliente(Clientes cliente) throws DniYaExiste, EmailYaExiste, UsuarioYaExiste {
	    	
	    	if(ClientesDao.findByDni(cliente.getDni()) != null) {
	    		throw new DniYaExiste();
	    	}
	    	else
	    	if(ClientesDao.findByEmail(cliente.getCorreo()) != null) {
	    		throw new EmailYaExiste();
	    	}
	    	else
	    	if(ClientesDao.findByUser(cliente.getUsuario()) != null) {
	    		throw new UsuarioYaExiste();
	    	}
	    	
	        return ClientesDao.insert(cliente);
	    }
	
	    public boolean ActualizarCliente(Clientes cliente) {	    	
	        return ClientesDao.update(cliente);
	    }
	
	    public boolean EliminarCliente(Clientes cliente) {
	        return ClientesDao.delete(cliente);
	    }
	    
	    public Clientes ObtenerById(int id) {
	        return ClientesDao.findById(id);
	    }
	    
	    public Clientes ObtenerByLogin(String usuario, String contra) {
	        return ClientesDao.login(usuario, contra);
	    }

		@Override
		public Clientes ObtenerByDNI(String dni) {
			// TODO Auto-generated method stub
			return ClientesDao.findByDni(dni);
		}

}
