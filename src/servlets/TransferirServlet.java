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

import entidad.Clientes;
import entidad.Cuentas;
import entidad.Movimientos;
import negocio.CuentasService;
import negocio.MovimientosService;
import negocioImpl.CuentasServiceImpl;
import negocioImpl.MovimientosServiceImpl;

/**
 * Servlet implementation class TransferirServlet
 */
@WebServlet("/TransferirServlet")
public class TransferirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferirServlet() {
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
	        
	       CuentasService cuentasService = new CuentasServiceImpl();
	       List<Cuentas> listaCuentas = cuentasService.ObtenerByIdCliente(cliente.getId());
	       request.setAttribute("listMisCuentas", listaCuentas);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Transferencia.jsp");
			rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String monto = request.getParameter("montoATransferir");
		String idMiCuenta = request.getParameter("cuenta");
		String cbu = request.getParameter("numCBU");
		
		boolean exito = false;
		String mensaje = "";		
		
		double Monto = Double.parseDouble(monto);
		int idCuentaExt = Integer.parseInt(idMiCuenta);
		int cbuDep = Integer.parseInt(cbu);
		
		if(Monto <= 0) {
			exito = false;
			mensaje = "Monto incorrecto";
			request.setAttribute("Mensaje", mensaje);
			request.setAttribute("Exito", exito);
			
			doGet(request, response);
			return;
		}
		
		CuentasService cuentasService = new CuentasServiceImpl(); 
		
		Cuentas cuentaExtracion = cuentasService.ObtenerById(idCuentaExt);	
		Cuentas cuentaDeposito = cuentasService.ObtenerByCbu(cbuDep);
		
		if(cuentaDeposito == null) {
			exito = false;
			mensaje = "CBU incorrecto";
			request.setAttribute("Mensaje", mensaje);
			request.setAttribute("Exito", exito);
			
			doGet(request, response);
			return;
		}
		
		if(cuentaDeposito.isBaja()) {
			exito = false;
			mensaje = "La cuenta a la que intenta transferir esta dada de baja";
			request.setAttribute("Mensaje", mensaje);
			request.setAttribute("Exito", exito);
			
			doGet(request, response);
			return;
		}
		
		
		Movimientos movimientoExtraccion = new Movimientos(
			    -1,  
			    4,   
			    idCuentaExt, 
			    Monto * -1, 
			    Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
			);
		
		
		Movimientos movimientoDeposito = new Movimientos(
			    -1,  
			    5,   
			    cuentaDeposito.getId(), 
			    Monto, 
			    Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
			);
	
		
		
		double saldoViejoExt = cuentaExtracion.getSaldo();
		double saldoViejoDep = cuentaDeposito.getSaldo();
		
		if(saldoViejoExt < Monto) {
			exito = false;
			mensaje = "Saldo insuficiente";
		}
		else {
			cuentaExtracion.setSaldo(saldoViejoExt - Monto);
			cuentaDeposito.setSaldo(saldoViejoDep + Monto);
						
			if(cuentasService.ActualizarCuenta(cuentaExtracion)) {
				if(cuentasService.ActualizarCuenta(cuentaDeposito)) {
					MovimientosService movimientosService = new MovimientosServiceImpl();
					if(movimientosService.AgregarMovimiento(movimientoExtraccion) &&
						movimientosService.AgregarMovimiento(movimientoDeposito)) {
							exito = true;
							mensaje = "Transferencia exitosa";
					}else {
						cuentaExtracion.setSaldo(saldoViejoExt);
						cuentaDeposito.setSaldo(saldoViejoDep);
						cuentasService.ActualizarCuenta(cuentaDeposito);
						cuentasService.ActualizarCuenta(cuentaExtracion);
						exito = false;
						mensaje = "Error al regitrar movimiento";
					}
				}else {
					cuentaExtracion.setSaldo(saldoViejoExt);
					cuentasService.ActualizarCuenta(cuentaExtracion);
					exito = false;
					mensaje = "Error al depositar el dinero";
				}
			} else{
				exito = false;
				mensaje = "Error al extraer el dinero";
			}
		}
		
		request.setAttribute("Mensaje", mensaje);
		request.setAttribute("Exito", exito);
		
		doGet(request, response);
	}
	

}
