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
import entidad.Prestamos;
import negocio.CuentasService;
import negocio.PrestamoService;
import negocioImpl.CuentasServiceImpl;
import negocioImpl.PrestamoServiceImpl;

/**
 * Servlet implementation class PedirPrestamoServlet
 */
@WebServlet("/PedirPrestamoServlet")
public class PedirPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedirPrestamoServlet() {
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
		
		RequestDispatcher rd = request.getRequestDispatcher("/PedirPrestamos.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Clientes cliente = (Clientes) request.getSession().getAttribute("Cliente");
        
        if (cliente == null) {
            response.sendRedirect("login.jsp");
            return;
        }
		
		String importe = request.getParameter("importePedido");
		String idMiCuenta = request.getParameter("cuenta");
		String cuotas = request.getParameter("cantCuotas");
		
		boolean exito = false;
		String mensaje = "";		
		
		double Importe = Double.parseDouble(importe);
		int idCuenta = Integer.parseInt(idMiCuenta);
		int Cuotas = Integer.parseInt(cuotas);
		
		if(Importe <= 0) {
			exito = false;
			mensaje = "Monto incorrecto";
			request.setAttribute("Mensaje", mensaje);
			request.setAttribute("Exito", exito);
			
			doGet(request, response);
			return;
		}
		if(Cuotas > 24 || Cuotas < 1) {
			exito = false;
			mensaje = "La cantidad máxima de cuotas permitida es 24";
			request.setAttribute("Mensaje", mensaje);
			request.setAttribute("Exito", exito);
			
			doGet(request, response);
			return;
		}
		
		PrestamoService prestamoService = new PrestamoServiceImpl();
		
		//Calcular el importe por mes con intereses
		double ImporteMes = Importe/Cuotas;
		double ImporteMesConInteres = ImporteMes * 1.05; //Interes del 5%
		
		Prestamos prestamo = new Prestamos(
				-1, 
				cliente.getId(),
				idCuenta,
				Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()),
				Importe, 
				Cuotas,
				ImporteMesConInteres, 
				"Pendiente"
				);
			
		exito = prestamoService.agregarPrestamo(prestamo);
		if(exito) {
			mensaje = "Solicitud de prestamo enviada";
		} 
		else {
			mensaje = "Error al enviar la solicitud de prestamo";
		}
		
		request.setAttribute("Mensaje", mensaje);
		request.setAttribute("Exito", exito);
		
		doGet(request, response);
	}

}
