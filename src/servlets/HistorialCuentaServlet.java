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
import entidad.Localidad;
import entidad.Movimientos;
import entidad.Prestamos;
import entidad.Provincia;
import entidad.TipoDeMovimiento;
import negocio.CuentasService;
import negocio.LocalidadService;
import negocio.MovimientosService;
import negocio.ProvinciaService;
import negocio.TipoDeMovimientoService;
import negocioImpl.CuentasServiceImpl;
import negocioImpl.LocalidadServiceImpl;
import negocioImpl.MovimientosServiceImpl;
import negocioImpl.ProvinciaServiceImpl;
import negocioImpl.TipoDeMovimientoServiceImpl;

@WebServlet("/HistorialCuentaServlet")
public class HistorialCuentaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HistorialCuentaServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Clientes cliente = (Clientes) request.getSession().getAttribute("Cliente");
    	
    	if (cliente == null) {
            response.sendRedirect("Principal.jsp");
            return;
        }
    	
		List<Movimientos> movimientos = null;
		List<TipoDeMovimiento> tiposMovimientos = null;
		request.setAttribute("Tipo", 0);
		
		
		if(request.getSession().getAttribute("idCuentaHistorial") != null) {
			int idCuenta= (int) request.getSession().getAttribute("idCuentaHistorial");
			
			
			MovimientosService movimientosServ= new MovimientosServiceImpl();
			movimientos=movimientosServ.ListarMovimientosByIdCuenta(idCuenta);
			
			TipoDeMovimientoService tipoService = new TipoDeMovimientoServiceImpl();
			tiposMovimientos = tipoService.ListarTipos();
			
			//Filtros
	        String txtTipo = request.getParameter("tipoSelect");
	        if(txtTipo != null && !txtTipo.isEmpty()) {
		        int idTipo = Integer.parseInt(txtTipo);
				
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
				
			    List<Movimientos> listaMovimientosFiltrada = new ArrayList<>();
			    
			   
	
			    request.setAttribute("Tipo", idTipo);
			    request.setAttribute("MinImporte", minImporte);
			    request.setAttribute("MaxImporte", maxImporte);
			    request.setAttribute("FechaMinima", fechaMinima);
			    request.setAttribute("FechaMaxima", fechaMaxima);
			    
		        for (Movimientos movimiento : movimientos) {
		        	
		            boolean cumpleEstado = (idTipo == -1) || movimiento.getTipoMovimiento() == idTipo;
		            boolean cumpleMinImporte = (minImporte == null) || (movimiento.getMonto() >= minImporte);
		            boolean cumpleMaxImporte = (maxImporte == null) || (movimiento.getMonto() <= maxImporte);
		            boolean cumpleFechaMin = (fechaMinima == null) || (movimiento.getFecha().compareTo(fechaMinima) >= 0);
		            boolean cumpleFechaMax = (fechaMaxima == null) || (movimiento.getFecha().compareTo(fechaMaxima) <= 0);			          
	
		            if (cumpleEstado && cumpleMinImporte && cumpleMaxImporte && cumpleFechaMin && cumpleFechaMax) {
		            	listaMovimientosFiltrada.add(movimiento);
		            }
		        }
			    
	
			        
			    if(listaMovimientosFiltrada!= null) {
			    	movimientos = listaMovimientosFiltrada;
			    }
	        }

			request.setAttribute("TiposMovimientos", tiposMovimientos);
			request.setAttribute("historialCuenta", movimientos);
			RequestDispatcher rd = request.getRequestDispatcher("/HistorialCuenta.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("ListaMisCuentas");
			rd.forward(request, response);
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
}
