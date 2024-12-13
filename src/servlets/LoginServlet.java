package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Clientes;
import negocio.ClientesService;
import negocioImpl.ClientesServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String mensaje = "";
		boolean exito = false;
		
		ClientesService clienteNegocio = new ClientesServiceImpl();
		
		String username = request.getParameter("txtusuario");
        String password = request.getParameter("txtcontrasena");
        request.getSession().removeAttribute("Cliente");
        
        Clientes cliente = clienteNegocio.ObtenerByLogin(username, password);
        
        if(cliente != null) {
        	if(cliente.isBaja()) {
        		exito = false;
            	mensaje = "Usuario dado de baja";
            	request.setAttribute("Mensaje", mensaje);
    			request.setAttribute("Exito", exito);
            	RequestDispatcher rd = request.getRequestDispatcher("/Principal.jsp");
        		rd.forward(request, response);
        		return;
        	}
        	
        	request.getSession().setAttribute("Cliente", cliente);

        	response.sendRedirect("HomeServlet");
        }
        else {
        	exito = false;
        	mensaje = "Usuario o contraseña incorrectos";
        	request.setAttribute("Mensaje", mensaje);
			request.setAttribute("Exito", exito);
        	RequestDispatcher rd = request.getRequestDispatcher("/Principal.jsp");
    		rd.forward(request, response);
        }
		
		//RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
		//rd.forward(request, response);
	}

}
