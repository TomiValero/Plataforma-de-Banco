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
import entidad.TipoDeCuenta;
import negocio.CuentasService;
import negocio.TipoDeCuentaService;
import negocioImpl.CuentasServiceImpl;
import negocioImpl.TipoDeCuentaServiceImpl;

/**
 * Servlet implementation class ListaMisCuentas
 */
@WebServlet("/ListaMisCuentas")
public class ListaMisCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaMisCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  Clientes cliente = (Clientes) request.getSession().getAttribute("Cliente");
	        
	        if (cliente == null) {
	            response.sendRedirect("Principal.jsp");
	            return;
	        }
	        request.setAttribute("MostrarModal", false);
		  List<Cuentas> cuentas = null ;
			List<TipoDeCuenta> listaTiposCuenta=new ArrayList<>();
			TipoDeCuentaService tipoCuentaService=new TipoDeCuentaServiceImpl();
			listaTiposCuenta=tipoCuentaService.ListarTipos();
		  
		  	  
		  boolean exito = false;
	  		String mensaje = "";
			int idCuenta=0;

	        CuentasService cuentasServ= new CuentasServiceImpl();
	        cuentas=cuentasServ.ObtenerByIdCliente(cliente.getId());

	        if(request.getParameter("idCuentaBaja")!=null )
		     {
				 idCuenta=Integer.parseInt(request.getParameter("idCuentaBaja"));
				 
				 if(request.getParameter("Modal")!= null )
			     {	 				 
					 //Cuentas cuenta =cuentasServ.ObtenerById(idCuenta);	    			  
	    			  //cuenta.setBaja(true);
					  for(Cuentas cuenta : cuentas)
			    	  {
			    		  if(cuenta.getId() == idCuenta )
			    		  	{	    			  
			    			  cuenta.setBaja(true);					 
			    			  if(cuentasServ.ActualizarCuenta(cuenta)) {
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
	        
	        if(request.getParameter("idCuentaHistorial")!=null )
	        {
	        	idCuenta=Integer.parseInt(request.getParameter("idCuentaHistorial"));
	        	request.getSession().setAttribute("idCuentaHistorial", idCuenta);	
	        	response.sendRedirect("HistorialCuentaServlet");
	        	return;
	        }
	        
	        request.setAttribute("TipoCuentas",listaTiposCuenta);
	        request.setAttribute("Mensaje", mensaje);
			request.setAttribute("Exito", exito);
	        request.setAttribute("cuentasCliente", cuentas);
			   RequestDispatcher rd = request.getRequestDispatcher("/ListaDeMisCuentas.jsp");
				rd.forward(request, response);
	        
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Clientes cliente = (Clientes) request.getSession().getAttribute("Cliente");
		 
		List<Cuentas> cuentas = null ;
		 
		CuentasService cuentasServ= new CuentasServiceImpl();
		 
		cuentas=cuentasServ.ObtenerByIdCliente(cliente.getId());
	        
		 Date fechaMinima = null;
		Date fechaMaxima = null;
	    if (request.getParameter("txtFechaMin") != null && !request.getParameter("txtFechaMin").isEmpty()) {
	        fechaMinima = Date.valueOf(request.getParameter("txtFechaMin"));
	        request.setAttribute("FechaMinima", fechaMinima);
	    }
	    if (request.getParameter("txtFechaMax") != null && !request.getParameter("txtFechaMax").isEmpty()) {
	        fechaMaxima = Date.valueOf(request.getParameter("txtFechaMax"));
	        request.setAttribute("FechaMaxima", fechaMaxima);
	    }
		
	    List<Cuentas> listaCuentasFiltradas = new ArrayList<>();
	    
	    
	    
	    for (Cuentas cuenta : cuentas) {
            boolean cumpleFechaMin = (fechaMinima == null) || (cuenta.getFechaAlta().compareTo(fechaMinima) >= 0);
            boolean cumpleFechaMax = (fechaMaxima == null) || (cuenta.getFechaAlta().compareTo(fechaMaxima) <= 0);			          

            if (cumpleFechaMin && cumpleFechaMax) {
                listaCuentasFiltradas.add(cuenta);
            }
        }

	    List<TipoDeCuenta> listaTiposCuenta=new ArrayList<>();
		TipoDeCuentaService tipoCuentaService=new TipoDeCuentaServiceImpl();
		listaTiposCuenta=tipoCuentaService.ListarTipos();
	  
	    request.setAttribute("TipoCuentas",listaTiposCuenta);
	    request.setAttribute("listaFiltrada", listaCuentasFiltradas);
		RequestDispatcher rd = request.getRequestDispatcher("/ListaDeMisCuentas.jsp");
		rd.forward(request, response);
	}

}
