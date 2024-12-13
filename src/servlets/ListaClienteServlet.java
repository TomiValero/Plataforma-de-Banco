package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Clientes;
import entidad.Cuentas;
import entidad.Prestamos;
import exceptions.DniYaExiste;
import exceptions.EmailYaExiste;
import exceptions.UsuarioYaExiste;
import negocio.ClientesService;
import negocio.LocalidadService;
import negocioImpl.ClientesServiceImpl;
import negocioImpl.LocalidadServiceImpl;

/**
 * Servlet implementation class ListaClienteServlet
 */
@WebServlet("/ListaClienteServlet")
public class ListaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Clientes clienteLog = (Clientes) request.getSession().getAttribute("Cliente");
        
        if (clienteLog == null) {
            response.sendRedirect("Principal.jsp");
            return;
        } 
        request.setAttribute("MostrarModal", false);
        
		List<Clientes> listaClientes=null;
		   ClientesService clienteService= new ClientesServiceImpl();     
		    List<Clientes> listaFiltradaXDNI = new ArrayList<>();
		    List<Clientes> listaFiltradaXApellido = new ArrayList<>();
		    listaClientes=clienteService.ListarCliente();
		    boolean exito = false;
	  		String mensaje = "";
	String dni="";
	 String Apellido="";
	    	
	 if(request.getParameter("btnFiltros")!=null)
{
			  
	
    if (request.getParameter("txtDNI") != null ) {
        dni =request.getParameter("txtDNI");
for( Clientes cliente : listaClientes)
{
	if(cliente.getDni().equals(dni))
	{
		 listaFiltradaXDNI.add(cliente);
	}
	}

     	}
    if (request.getParameter("txtApellido") != null )
     	{
     		Apellido=request.getParameter("txtApellido");
     		for( Clientes cliente : listaClientes)
     		{
     			
     			if(cliente.getApellido().equals(Apellido))
     			{
     				 listaFiltradaXApellido.add(cliente);
     			}
     			}		
     	}
    listaClientes=null;
    }
	 
	 int idCliente = 0;
	 if(request.getParameter("IdClienteBaja")!=null )
     {
		 idCliente=Integer.parseInt(request.getParameter("IdClienteBaja"));
		 
		 if(request.getParameter("Modal")!= null )
	     {	    
	     	  for(Clientes cliente : listaClientes)
		    	  {
		    		  if(cliente.getId() == idCliente )
		    		  	{	    			  
		    			  cliente.setBaja(true);
						if(clienteService.ActualizarCliente(cliente)) {
							  mensaje = "Cliente dado de baja con exito";
							  exito = true;
						  } else {
							  mensaje = "Error al dar de baja cliente";
							  exito = false;
						  }
		    		  }
		    	  } 
	     } 
		 else {
	    	 
			 request.setAttribute("MostrarModal", true);
			 request.setAttribute("IdClienteBajaModal", idCliente);
	     }
     }
	 
	 if(request.getParameter("IdClienteAlta")!=null )
     {
		 idCliente=Integer.parseInt(request.getParameter("IdClienteAlta"));
     		    
     	  for(Clientes cliente : listaClientes)
	    	  {
	    		  if(cliente.getId() == idCliente )
	    		  	{	    			  
	    			  cliente.setBaja(false);
					if(clienteService.ActualizarCliente(cliente)) {
						  mensaje = "Cliente dado de alta con exito";
						  exito = true;
					  } else {
						  mensaje = "Error al dar de alta cliente";
						  exito = false;
					  }
				
	    		  }
	    	  }        	
     }
     

 
     	request.setAttribute("Mensaje", mensaje);
		request.setAttribute("Exito", exito);
	 
	 
	 request.setAttribute("ListaClientes", listaClientes);
		request.setAttribute("DNI", dni);
		request.setAttribute("Apellido", Apellido);
		 request.setAttribute("ListaFiltroXApellido", listaFiltradaXApellido);
		  request.setAttribute("ListaFiltroXDNI", listaFiltradaXDNI);
		   RequestDispatcher rd = request.getRequestDispatcher("/ListaDeClientesAdmin.jsp");
			rd.forward(request, response);
   
	

    
   




	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 
	
	}

}
