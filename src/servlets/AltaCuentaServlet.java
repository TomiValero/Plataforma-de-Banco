package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
import entidad.TipoDeCuenta;
import negocio.CuentasService;
import negocio.MovimientosService;
import negocio.TipoDeCuentaService;
import negocioImpl.CuentasServiceImpl;
import negocioImpl.MovimientosServiceImpl;
import negocioImpl.TipoDeCuentaServiceImpl;

/**
 * Servlet implementation class AltaCuentaServlet
 */
@WebServlet("/AltaCuentaServlet")
public class AltaCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaCuentaServlet() {
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
        
		if(request.getSession().getAttribute("IdClienteAlta") != null) {
			
			int idCliente = (int)request.getSession().getAttribute("IdClienteAlta");
			
			//request.getSession().setAttribute("IdClienteAlta", idCliente);
				
	  		List<TipoDeCuenta> listaTiposCuenta=new ArrayList<>();
	  		TipoDeCuentaService tipoCuentaService=new TipoDeCuentaServiceImpl();
			listaTiposCuenta=tipoCuentaService.ListarTipos();
			
			request.setAttribute("TipoCuentas",listaTiposCuenta);
				
			RequestDispatcher rd = request.getRequestDispatcher("/AltaCuenta.jsp");
			rd.forward(request, response);
			 
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/ListaCuentasServlet");
			rd.forward(request, response);
		}
			  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idCliente = (int) request.getSession().getAttribute("IdClienteAlta");
		String ddlTipo = request.getParameter("txtTipoCuenta");		
		int idTipoCuenta = Integer.parseInt(ddlTipo);
		
		boolean exito = false;
  		String mensaje = "";
  		
		CuentasService cuentaService=new CuentasServiceImpl();
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
			 request.setAttribute("Mensaje", mensaje);
			 request.setAttribute("Exito", exito);
			 doGet(request, response);
			 return;
		 }
		 else {		
			 
			 int numCuenta = 1;
			 int cbu = 1;
			 List<Cuentas> todasCuentas = cuentaService.ListarCuentas();
			 if (!todasCuentas.isEmpty()) {
			     Cuentas ultimaCuenta = todasCuentas.get(todasCuentas.size() - 1);
			     numCuenta = ultimaCuenta.getNumCuenta() + 1;
			     cbu = ultimaCuenta.getCbu() + 1;
			 }
			 
			 Cuentas nuevaCuenta =  new Cuentas(
					 -1, 
					 idCliente,
					 Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()),
					 idTipoCuenta,
					 numCuenta, 
					 cbu,
					 10000,
					 false
					 );
			 
			 if(cuentaService.AgregarCuenta(nuevaCuenta)) {
				 
				 Cuentas idNuevaCuenta = cuentaService.ObtenerByCbu(cbu);

				 Movimientos movimiento = new Movimientos(
						    -1,  
						    1,   
						    idNuevaCuenta.getId(), 
						    10000, 
						    Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
						);
				 
				 MovimientosService movimientosService = new MovimientosServiceImpl();
					if(movimientosService.AgregarMovimiento(movimiento)) 
					{
						mensaje = "Cuenta creada con exito";
						exito = true;
						request.setAttribute("NuevaCuenta", idNuevaCuenta);
					}
					else {
						cuentaService.EliminarCuenta(idNuevaCuenta);
						mensaje = "Error al registar movimiento";
						exito = false;
					}
			 } 
			 else {
				 mensaje = "Error al crear nueva cuenta";
				 exito = false;
			 }
			 
					 
		request.setAttribute("Mensaje", mensaje);
		request.setAttribute("Exito", exito); 
		doGet(request, response);
		
		 }
	}
}


