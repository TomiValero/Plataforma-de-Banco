package negocio;

import java.util.List;


import entidad.Clientes;
import exceptions.DniYaExiste;
import exceptions.EmailYaExiste;
import exceptions.UsuarioYaExiste;

public interface ClientesService {
		
 	public List<Clientes> ListarCliente();	
    public boolean AgregarCliente(Clientes cliente) throws DniYaExiste, EmailYaExiste, UsuarioYaExiste;	
    public boolean ActualizarCliente(Clientes cliente);	
    public boolean EliminarCliente(Clientes cliente);	    
    public Clientes ObtenerById(int id);	   
    public Clientes ObtenerByDNI(String id);
    public Clientes ObtenerByLogin(String usuario, String contra);
}
