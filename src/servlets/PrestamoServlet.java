package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import entidad.Cuota;
import entidad.Movimientos;
import entidad.Prestamos;
import negocio.ClientesService;
import negocio.CuentasService;
import negocio.CuotaService;
import negocio.MovimientosService;
import negocio.PrestamoService;
import negocioImpl.ClientesServiceImpl;
import negocioImpl.CuentasServiceImpl;
import negocioImpl.CuotaServiceImpl;
import negocioImpl.MovimientosServiceImpl;
import negocioImpl.PrestamoServiceImpl;

@WebServlet("/PrestamoServlet")
public class PrestamoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Clientes cliente = (Clientes) request.getSession().getAttribute("Cliente");
        
        if (cliente == null) {
            response.sendRedirect("Principal.jsp");
            return;
        }
    	
    	boolean exito = false;
		String mensaje = "";
		PrestamoService prestamoService = new PrestamoServiceImpl();
		CuotaService cuotaService = new CuotaServiceImpl();
		CuentasService cuentasService = new CuentasServiceImpl(); 
		ClientesService clientesService = new ClientesServiceImpl();
		request.setAttribute("MostrarModalRechazo", false);
		request.setAttribute("MostrarModalAceptado", false);
		
    	if(request.getParameter("PrestAceptadoId") != null) {
			String id = request.getParameter("PrestAceptadoId");
			int idPrestamo = Integer.parseInt(id);
			
			if(request.getParameter("Modal")!= null )
		     {
				Prestamos prestamo = prestamoService.obtenerPrestamoPorId(idPrestamo);
				prestamo.setEstado("Aceptado");
				if(prestamoService.actualizarPrestamo(prestamo)) {
					exito = true;
					mensaje = "Prestamo aceptado con exito";
				}else {
					exito = false;
					mensaje = "Error al aceptar el prestamo";
				}
										
				Cuentas cuenta = cuentasService.ObtenerById(prestamo.getCuenta());
				
				double saldoViejo = cuenta.getSaldo();
				cuenta.setSaldo(saldoViejo + prestamo.getImportePedido());
				
				int cantCuotas = prestamo.getCantCuotas();
				
				for(int i=0; i<cantCuotas; i++) {
					int numCuota = i + 1;
					 Date fecha = Date.valueOf("0001-01-01");
					 Cuota cuota = new Cuota(
			                    -1, 
			                    idPrestamo, 
			                    numCuota, 
			                    fecha, 
			                    prestamo.getImportePorMes(), 
			                    false
			                );
					if(!cuotaService.agregarCuota(cuota)) {
						exito = true;
						mensaje = "Error al regitrar Cuotas";
					}
				}
				
				
				Movimientos movimientoAltaPrestamo = new Movimientos(
					    -1,  
					    2, //Alta prestamo  
					    cuenta.getId(), 
					    prestamo.getImportePedido(), 
					    Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
					);			
				
				if(cuentasService.ActualizarCuenta(cuenta) && exito) {				
					MovimientosService movimientosService = new MovimientosServiceImpl();
					if(movimientosService.AgregarMovimiento(movimientoAltaPrestamo)) {
							exito = true;
							mensaje = "Prestamo aceptado con exito";
					}else {
						cuenta.setSaldo(saldoViejo);
						cuentasService.ActualizarCuenta(cuenta);
						exito = false;
						mensaje = "Error al regitrar movimiento";
					}
				}else {
					exito = false;
					mensaje = "Error al depositar el dinero";
				}	
				
				request.setAttribute("Mensaje", mensaje);
				request.setAttribute("Exito", exito);
		     } else {
		    	 request.setAttribute("MostrarModalAceptado", true);
				 request.setAttribute("PrestAceptadoIdModal", idPrestamo);
		     }
		} 
		else if(request.getParameter("PrestRechazadoId") != null) {		
			String id = request.getParameter("PrestRechazadoId");
			int idPrestamo = Integer.parseInt(id);
			if(request.getParameter("Modal")!= null )
		     {
				Prestamos prestamo = prestamoService.obtenerPrestamoPorId(idPrestamo);
				prestamo.setEstado("Rechazado");
				if(prestamoService.actualizarPrestamo(prestamo)) {
					exito = true;
					mensaje = "Prestamo rechazado con exito";
				}else {
					exito = false;
					mensaje = "Error al rechazar el prestamo";
				}							

				request.setAttribute("Mensaje", mensaje);
				request.setAttribute("Exito", exito);
		     } else {
		    	 request.setAttribute("MostrarModalRechazo", true);
				 request.setAttribute("PrestRechazadoIdModal", idPrestamo);
		     }
		} 
    	
	        List<Prestamos> listaPrestamos = prestamoService.listarPrestamos();
	        
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
			    
			    List<Cuentas> listaCuentas = cuentasService.ListarCuentas(); 
			    List<Clientes> listaClientes = clientesService.ListarCliente();
			    
			    
			    
			    
			    request.setAttribute("ListaCuentas", listaCuentas);
			    request.setAttribute("ListaClientes", listaClientes);
			    request.setAttribute("Estado", estado);
			    request.setAttribute("MinImporte", minImporte);
			    request.setAttribute("MaxImporte", maxImporte);
			    request.setAttribute("FechaMinima", fechaMinima);
			    request.setAttribute("FechaMaxima", fechaMaxima);
			    
			    if (estado != null) {
			        for (Prestamos prestamo : listaPrestamos) {
			            boolean cumpleEstado = estado.equalsIgnoreCase("Todos") || prestamo.getEstado().equalsIgnoreCase(estado);
			            boolean cumpleMinImporte = (minImporte == null) || (prestamo.getImportePedido() >= minImporte);
			            boolean cumpleMaxImporte = (maxImporte == null) || (prestamo.getImportePedido() <= maxImporte);
			            boolean cumpleFechaMin = (fechaMinima == null) || (prestamo.getFechaAlta().compareTo(fechaMinima) >= 0);
			            boolean cumpleFechaMax = (fechaMaxima == null) || (prestamo.getFechaAlta().compareTo(fechaMaxima) <= 0);			          

			            if (cumpleEstado && cumpleMinImporte && cumpleMaxImporte && cumpleFechaMin && cumpleFechaMax) {
			                listaPrestamosFiltrada.add(prestamo);
			            }
			        }
			    }

			        
			    if(listaPrestamosFiltrada!= null) {
			    	listaPrestamos = listaPrestamosFiltrada;
			    }
		    	        	        	       
				request.setAttribute("ListaPrestamos", listaPrestamos);
			
			
			
	          
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListadoPrestamo.jsp");
	        dispatcher.forward(request, response);
			    

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }



}
