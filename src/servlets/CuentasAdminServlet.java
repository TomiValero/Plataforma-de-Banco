package servlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Clientes;
import entidad.Cuentas;
import negocio.CuentasService;
import negocioImpl.CuentasServiceImpl;

/**
 * Servlet implementation class CuentasAdminServlet
 */
@WebServlet("/CuentasAdminServlet")
public class CuentasAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    private CuentasService cuentasService = new CuentasServiceImpl();

    public CuentasAdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Clientes clienteLog = (Clientes) request.getSession().getAttribute("Cliente");
        
        if (clienteLog == null) {
            response.sendRedirect("Principal.jsp");
            return;
        }
		
		String idClienteStr = request.getParameter("id");
		int idCliente = idClienteStr != null ? Integer.parseInt(idClienteStr) : 0;
		
		
		String filtroCBU = request.getParameter("filtroClientes");
		
		List<Cuentas> listaCuentas;
		
		if (filtroCBU != null && !filtroCBU.isEmpty()) {
		
		    listaCuentas = cuentasService.ListarCuentas().stream()
		        .filter(cuenta -> cuenta.getCliente() == idCliente && 
		                         String.valueOf(cuenta.getCbu()).contains(filtroCBU))
		        .collect(Collectors.toList());
		} else {
	
		    listaCuentas = cuentasService.ListarCuentas().stream()
		        .filter(cuenta -> cuenta.getCliente() == idCliente)
		        .collect(Collectors.toList());
		}

	
		request.setAttribute("listaCuentas", listaCuentas);
		request.getRequestDispatcher("ListaDeCuentas.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
