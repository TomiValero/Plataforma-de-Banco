package servlets;

import java.io.IOException;
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
import negocio.ClientesService;
import negocio.CuentasService;
import negocio.MovimientosService;
import negocioImpl.ClientesServiceImpl;
import negocioImpl.CuentasServiceImpl;
import negocioImpl.MovimientosServiceImpl;

/**
 * Servlet implementation class InformeServlet
 */
@WebServlet("/InformeServlet")
public class InformeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformeServlet() {
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
	        
	    Cuentas cuenta = new Cuentas();
        String id = request.getParameter("idCuenta") ;	
        if( id != null) {
        	int idCuenta = Integer.parseInt(id);    	       
        	 cuenta = cuentasService.ObtenerById(idCuenta);
        }
        else {
        	 cuenta = (Cuentas) request.getSession().getAttribute("CuentaInforme");
        }
	        
		int idCliente = cuenta.getCliente();
		
		ClientesService clienteService = new ClientesServiceImpl();
		Clientes clienteInforme = clienteService.ObtenerById(idCliente);
		
			    
	    request.setAttribute("ClienteInforme", clienteInforme);
	    
	    //request.getSession().setAttribute("ClienteInforme", clienteInforme);
	    request.setAttribute("MiCuenta", cuenta);
	    request.getSession().setAttribute("CuentaInforme", cuenta);
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/InformeAdmin.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Clientes clienteInforme = (Clientes) request.getSession().getAttribute("ClienteInformeente");
		Cuentas cuentaInforme = (Cuentas) request.getSession().getAttribute("CuentaInforme");
		
		int idCuenta = cuentaInforme.getId();
		
		//String ddlCuenta = request.getParameter("ddlCuenta");
		
		//int idCuenta = Integer.parseInt(ddlCuenta);
		//CuentasService cuentasService = new CuentasServiceImpl(); 	
		//Cuentas cuenta = cuentasService.ObtenerById(idCuenta);	
		
		int mesInicio = 0;
		int mesFin = 11;

		if (request.getParameter("mesInicio") != null && !request.getParameter("mesInicio").isEmpty()) {
		    mesInicio = Integer.valueOf(request.getParameter("mesInicio")); 
		    request.setAttribute("MesInicioSelect", mesInicio); 
		}

		if (request.getParameter("mesFin") != null && !request.getParameter("mesFin").isEmpty()) {
		    mesFin = Integer.valueOf(request.getParameter("mesFin")); 
		    request.setAttribute("MesFinSelect", mesFin); 
		}
		
		int txtAnio = -1;

		if (request.getParameter("txtAnio") != null && !request.getParameter("txtAnio").isEmpty()) {
			txtAnio = Integer.valueOf(request.getParameter("txtAnio")); 
		}

		
	    MovimientosService movimientoService = new MovimientosServiceImpl();
	    List<Movimientos> movimientos = movimientoService.ListarMovimientosByIdCuenta(idCuenta);
	    
	    
	    double[] extracionesMes = new double[12];
	    double[] entranteMes = new double[12];
	    int[] cantExtracionesMes = new int[12];
	    int[] cantEntranteMes = new int[12];
	    int cantExtraido = 0;
	    int cantEntrante = 0;
	    double extraidoTotal = 0;
	    double entranteTotal = 0;
	    
	    
	    if(movimientos != null){
			for(Movimientos movi :movimientos) {
				boolean cumpleAnio = (txtAnio == -1) || (movi.getFecha().getYear() + 1900 == txtAnio);
				
				int mes = movi.getFecha().getMonth();			
				boolean cumpleFechaMin = (mes >= mesInicio);
	            boolean cumpleFechaMax = (mes <= mesFin);
	            
	            if(cumpleAnio && cumpleFechaMin && cumpleFechaMax) {
	            	double monto = movi.getMonto();
            		if(monto < 0) {
            			monto = monto * (-1);
            		}
            		
	            	if(movi.getTipoMovimiento() == 4 || movi.getTipoMovimiento() == 3) {	            		
	            		extracionesMes[mes] += monto;
	            		extraidoTotal += monto;
	            		
	            		cantExtracionesMes[mes]++;
	            		cantExtraido++;
	            		
					} else {
						entranteMes[mes] += monto;
						entranteTotal += monto;
						
						cantEntranteMes[mes]++;
						cantEntrante++;
					}
	            }
			}
	    }
	    
	    
	    request.setAttribute("MostrarInforme", true);
	    request.setAttribute("ExtraidoMes", extracionesMes);
	    request.setAttribute("EntranteMes", entranteMes);
	    request.setAttribute("ExtraidoTotal", extraidoTotal);
	    request.setAttribute("EntranteTotal", entranteTotal);
	    request.setAttribute("CantExtraidoMes", cantExtracionesMes);
	    request.setAttribute("CantEntranteMes", cantEntranteMes);
	    request.setAttribute("CantExtraidoTotal", cantExtraido);
	    request.setAttribute("CantEntranteTotal", cantEntrante);
	    
	    doGet(request,response);
	}

}
