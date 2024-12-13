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
import negocio.CuentasService;
import negocio.PrestamoService;
import negocioImpl.CuentasServiceImpl;
import negocioImpl.PrestamoServiceImpl;

/**
 * Servlet implementation class PagoServlet
 */
@WebServlet("/PagoServlet")
public class PagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagoServlet() {
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
		
		if (request.getParameter("PrestId") != null) {
		    String id = request.getParameter("PrestId");
		    int idPrestamo = Integer.parseInt(id);
		    request.getSession().setAttribute("IdPrestamo", idPrestamo);
		    response.sendRedirect("MenuPagoServlet");
		}

		else {
			Clientes cliente = (Clientes) request.getSession().getAttribute("Cliente");
	        
	        if (cliente == null) {
	            response.sendRedirect("Principal.jsp");
	            return;
	        }
			
	        
	        PrestamoService prestamoService = new PrestamoServiceImpl();
	        List<Prestamos> listaPrestamos = prestamoService.listarPrestamosPorIdCliente(cliente.getId());
	        
	        //Filtros
		        String estado = request.getParameter("estadoSelect");
				
				Double minImporte = null;
				Double maxImporte = null;
			    if (request.getParameter("txtMinImporte") != null && !request.getParameter("txtMinImporte").isEmpty()) {
			        minImporte = Double.parseDouble(request.getParameter("txtMinImporte"));
			    }
			    if (request.getParameter("txtMaxImporte") != null && !request.getParameter("txtMaxImporte").isEmpty()) {
			        maxImporte = Double.parseDouble(request.getParameter("txtMaxImporte"));
			    }
	
	
				Date fechaMinima = null;
				Date fechaMaxima = null;
			    if (request.getParameter("txtFechaMin") != null && !request.getParameter("txtFechaMin").isEmpty()) {
			        fechaMinima = Date.valueOf(request.getParameter("txtFechaMin"));
			    }
			    if (request.getParameter("txtFechaMax") != null && !request.getParameter("txtFechaMax").isEmpty()) {
			        fechaMaxima = Date.valueOf(request.getParameter("txtFechaMax"));
			    }
				
			    List<Prestamos> listaPrestamosFiltrada = new ArrayList<>();
			    
			    if(estado == null) {
			    	estado = "Todos";
			    }
			    
			    request.setAttribute("Estado", estado);
			    request.setAttribute("MinImporte", minImporte);
			    request.setAttribute("MaxImporte", maxImporte);
			    request.setAttribute("FechaMinima", fechaMinima);
			    request.setAttribute("FechaMaxima", fechaMaxima);
			    
			    
			    if(estado != null) {
				    for (Prestamos prestamo : listaPrestamos) {
				        boolean cumpleEstado = estado.equalsIgnoreCase("Todos") || prestamo.getEstado().equalsIgnoreCase(estado);
				        boolean cumpleMinImporte = minImporte == null || prestamo.getImportePedido() >= minImporte;
				        boolean cumpleMaxImporte = maxImporte == null || prestamo.getImportePedido() <= maxImporte;
				        boolean cumpleFechaMin = fechaMinima == null || prestamo.getFechaAlta().compareTo(fechaMinima) >= 0;
				        boolean cumpleFechaMax = fechaMaxima == null || prestamo.getFechaAlta().compareTo(fechaMaxima) <= 0;
		
				        if (cumpleEstado && cumpleMinImporte && cumpleMaxImporte && cumpleFechaMin && cumpleFechaMax) {
				            listaPrestamosFiltrada.add(prestamo);
				        }
				    }
				}
			    if(listaPrestamosFiltrada!= null) {
			    	listaPrestamos = listaPrestamosFiltrada;
			    }
		    	        	        
	       	
				request.setAttribute("ListaPrestamos", listaPrestamos);
			
			
	        CuentasService cuentasService = new CuentasServiceImpl();
	        List<Cuentas> listaCuentas = cuentasService.ObtenerByIdCliente(cliente.getId());
	        request.setAttribute("listMisCuentas", listaCuentas);
	          
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pago.jsp");
	        dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
