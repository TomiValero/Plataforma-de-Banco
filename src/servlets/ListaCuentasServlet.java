package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.RequestDispatcher;

import entidad.Clientes;
import entidad.Cuentas;
import entidad.TipoDeCuenta;
import negocio.ClientesService;
//import negocio.ClientesService;
import negocio.CuentasService;
import negocio.TipoDeCuentaService;
import negocioImpl.ClientesServiceImpl;
import negocioImpl.CuentasServiceImpl;
import negocioImpl.TipoDeCuentaServiceImpl;
//import servlets.RequestDispatcher;


/**
 * Servlet implementation class ListaCuentasServlet
 */
@WebServlet("/ListaCuentasServlet")
public class ListaCuentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaCuentasServlet() {
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
		List<TipoDeCuenta> listaTiposCuenta=new ArrayList<>();
		TipoDeCuentaService tipoCuentaService=new TipoDeCuentaServiceImpl();
		listaTiposCuenta=tipoCuentaService.ListarTipos();
		boolean exito = false;
  		String mensaje = "";
		
		        List<Cuentas> cuentas ;
				ArrayList<Cuentas> cuentasFiltrada = new ArrayList<>();
				String id = request.getParameter("IdCliente") ;			
				 CuentasService cuentaService=new CuentasServiceImpl();
				 cuentas=cuentaService.ListarCuentas();
				 if(id  !=null)
			 		{  
						 int idUsuario = Integer.parseInt(id);							 
						 cuentas = cuentaService.ObtenerByIdCliente(idUsuario);
						 ClientesService clienteService = new ClientesServiceImpl();
						 Clientes cliente = clienteService.ObtenerById(idUsuario);
						 request.setAttribute("UsuarioCuentas",cliente);
			 		}
			request.setAttribute("TipoCuentas",listaTiposCuenta);	   
				
		 	request.setAttribute("Cuentas",cuentas);	   
		   request.setAttribute("cuentasFiltrada", cuentasFiltrada);
		   
		   
		   
		   int idCliente = 0;
		   if(request.getParameter("idClienteNuevaCuenta")!=null )
		     {
			   idCliente=Integer.parseInt(request.getParameter("idClienteNuevaCuenta"));
		     		    				 
				 List<Cuentas> cuentasCliente = cuentaService.ObtenerByIdCliente(idCliente);
				 
				 int activas = 0;
				 for(Cuentas cuentaCliente : cuentasCliente)
		        	{
		        		if(!cuentaCliente.isBaja())
		        		{
		        			activas++;
		        		}
		        	}
				 if(activas >= 3) 
				 {
					 mensaje = "El cliente ya tiene 3 cuentas activas";
					 exito = false;
				 }
				 else {				 
					 request.getSession().setAttribute("IdClienteAlta", idCliente);
					 RequestDispatcher rd = request.getRequestDispatcher("/AltaCuentaServlet");
					 rd.forward(request, response);
					 return;
				 }
			  }
		   
		   
		   int idCuenta = 0;
			 if(request.getParameter("idCuentaBaja")!=null )
		     {
				 idCuenta=Integer.parseInt(request.getParameter("idCuentaBaja"));
				 
				 if(request.getParameter("Modal")!= null )
			     {	 				 

					  for(Cuentas cuenta : cuentas)
			    	  {
			    		  if(cuenta.getId() == idCuenta )
			    		  	{	    			  
			    			  cuenta.setBaja(true);					 
			    			  if(cuentaService.ActualizarCuenta(cuenta)) {
			    				  mensaje = "Cuenta dada de baja con exito";
			    				  exito = true;
			    			  } else {
			    				  mensaje = "Error al dar de baja cuenta";
			    				  exito = false;
			    			  }
			    		  	}
			    	  }
			     }else {
			    	 
					 request.setAttribute("MostrarModal", true);
					 request.setAttribute("idCuentaBajaModal", idCuenta);
			     }
   			  
	    	  }     	
		     
			 
			 if(request.getParameter("idCuentaAlta")!=null )
		     {
				 idCuenta=Integer.parseInt(request.getParameter("idCuentaAlta"));
		     		    
				 Cuentas cuenta =cuentaService.ObtenerById(idCuenta);
				 
				 List<Cuentas> cuentasCliente = cuentaService.ObtenerByIdCliente(cuenta.getCliente());
				 
				 int activas = 0;
				 for(Cuentas cuentaCliente : cuentasCliente)
		        	{
		        		if(!cuentaCliente.isBaja())
		        		{
		        			activas++;
		        		}
		        	}
				 if(activas >= 3) 
				 {
					 mensaje = "El cliente ya tiene 3 cuentas activas";
					 exito = false;
				 }
				 else {				 
					  cuenta.setBaja(false);
					  if(cuentaService.ActualizarCuenta(cuenta)) {
						  mensaje = "Cuenta dada de alta con exito";
						  exito = true;
					  } else {
						  mensaje = "Error al dar de alta cuenta";
						  exito = false;
					  }
				 }
			  }
	   
			 request.setAttribute("Mensaje", mensaje);
			request.setAttribute("Exito", exito);
		   RequestDispatcher rd = request.getRequestDispatcher("/ListaCuentasAdmin.jsp");
			rd.forward(request, response);
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cuentas> cuentas ;
		Cuentas cuentaAux=new Cuentas();
		CuentasService cuentaService=new CuentasServiceImpl();
		 cuentas=cuentaService.ListarCuentas();
	
	       
	        
	        if(request.getParameter("btnFiltros") !=null)
	        {
	        	String num = request.getParameter("txtNumeroCuenta");
	        	if(num !=null && !num.isEmpty())
	        	{
		        	int numCuenta=Integer.parseInt(request.getParameter("txtNumeroCuenta").toString());

		        	for(Cuentas cuenta:cuentas)
		        	{
		        		if(cuenta.getNumCuenta()== numCuenta)
		        		{
		        			cuentaAux=cuenta;
		        			request.setAttribute("NumeroCuenta", cuentaAux);
		        			request.setAttribute("CuentaNumero", cuentaAux.getNumCuenta());
		        		}
		        	}
		        	
		        	
	        	}
	        	String txtcbu = request.getParameter("txtCBU");
	        	if(txtcbu !=null && !txtcbu.isEmpty())
	        	{
	        		int cbu=Integer.parseInt(request.getParameter("txtCBU").toString());

		        	for(Cuentas cuenta:cuentas)
		        	{
		        		if(cuenta.getCbu()== cbu)
		        		{
		        			cuentaAux=cuenta;
		        			request.setAttribute("CBU", cuentaAux);
		        			request.setAttribute("CuentaCBU", cuentaAux.getCbu());
		        		}
		        	}
	        	}
	        		
	        }
	        
	        
	        RequestDispatcher rd = request.getRequestDispatcher("/ListaCuentasAdmin.jsp");
			rd.forward(request, response);
		
		
	}

}
