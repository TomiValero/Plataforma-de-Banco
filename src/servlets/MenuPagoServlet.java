package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CuentasService;
import negocio.CuotaService;
import negocio.MenuPagoService;
import negocio.MovimientosService;
import negocio.PrestamoService;
import negocioImpl.CuentasServiceImpl;
import negocioImpl.CuotaServiceImpl;
import negocioImpl.MenuPagoServiceImpl;
import negocioImpl.MovimientosServiceImpl;
import negocioImpl.PrestamoServiceImpl;
import entidad.Clientes;
import entidad.Cuentas;
import entidad.Cuota;
import entidad.Movimientos;
import entidad.Prestamos;

@WebServlet("/MenuPagoServlet")
public class MenuPagoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //private MenuPagoService menuPagoService = new MenuPagoServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 Clientes clienteLog = (Clientes) request.getSession().getAttribute("Cliente");
	        
	        if (clienteLog == null) {
	            response.sendRedirect("Principal.jsp");
	            return;
	        }
    	
    	Object idPrestamoObj = request.getSession().getAttribute("IdPrestamo");

        if (idPrestamoObj == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Préstamo no seleccionado.");
            return;
        }

        int idPrestamo = Integer.parseInt(idPrestamoObj.toString());
        
        PrestamoService prestamoService = new PrestamoServiceImpl();
        CuotaService cuotaService = new CuotaServiceImpl();
        
        Prestamos prestamo = prestamoService.obtenerPrestamoPorId(idPrestamo);
        List<Cuota> cuotas = cuotaService.listarCuotasByPrestamoId(idPrestamo);
        
        boolean prestamoPago = false;
        
        if (cuotas != null && !cuotas.isEmpty()) {
        	boolean cuotaPendiente = false;
            for (Cuota cuota : cuotas) {
            	if(!cuota.getPago()) {
            		cuotaPendiente = true;
            		break;
            	}
            }
            prestamoPago = !cuotaPendiente;
        }
        if(prestamoPago) {
        	prestamo.setEstado("Pago");
			prestamoService.actualizarPrestamo(prestamo);
        }

        CuentasService cuentasService = new CuentasServiceImpl();
        
       List<Cuentas> listaCuentas = cuentasService.ObtenerByIdCliente(prestamo.getCliente());
       request.setAttribute("listMisCuentas", listaCuentas);
       
       request.setAttribute("prestamo", prestamo);
        
       request.setAttribute("listaCuotas", cuotas);
                
       RequestDispatcher rd = request.getRequestDispatcher("/MenuPago.jsp");
       rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	boolean exito = false;
		String mensaje = "";
    	try {         	
            int idCuota = Integer.parseInt(request.getParameter("idCuota"));
            int idMiCuenta = Integer.parseInt(request.getParameter("cuenta"));
            
            CuentasService cuentasService = new CuentasServiceImpl();     		
    		Cuentas cuenta = cuentasService.ObtenerById(idMiCuenta);	
    		CuotaService cuotaService = new CuotaServiceImpl();
    		Cuota cuota = cuotaService.obtenerCuotaPorId(idCuota);
    		
    		
            double saldoCliente = cuenta.getSaldo();

            if (saldoCliente < cuota.getMonto()) {
            	exito = false;
            	mensaje = "Saldo insuficiente para realizar el pago.";
            	request.setAttribute("Mensaje", mensaje);
    			request.setAttribute("Exito", exito);
               
    			doGet(request, response);
    			return;
            }

            cuenta.setSaldo(saldoCliente - cuota.getMonto());
            
            cuota.setPago(true);
            cuota.setFechaPago(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            if(cuotaService.actualizarCuota(cuota)) {
				exito = true;
				mensaje = "Cuota pagada con exito";
			}else {
				exito = false;
				mensaje = "Error al actualizar el Cuota";
			}
                       
            Movimientos movimiento = new Movimientos(
    			    -1,  
    			    3, //Pago prestamo  
    			    idMiCuenta, 
    			    cuota.getMonto() * -1, 
    			    Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
    			);
            

            if(cuentasService.ActualizarCuenta(cuenta) && exito) {				
				MovimientosService movimientosService = new MovimientosServiceImpl();
				if(movimientosService.AgregarMovimiento(movimiento)) {
						exito = true;
						mensaje = "Cuota pagada con exito";
				}else {
					cuenta.setSaldo(saldoCliente);
					cuentasService.ActualizarCuenta(cuenta);
					exito = false;
					mensaje = "Error al regitrar movimiento";
				}
			}else {
				exito = false;
				mensaje = "Error al pagar cuota";
			}	
			
            
			request.setAttribute("Mensaje", mensaje);
			request.setAttribute("Exito", exito);
           
			doGet(request, response);


            
        } catch (Exception e) {
        	exito = false;
			mensaje = "Error al pagar cuota";
			
			request.setAttribute("Mensaje", mensaje);
			request.setAttribute("Exito", exito);
           
			doGet(request, response);
        }
    }
}
