package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidad.Clientes;
import entidad.Localidad;
import entidad.Provincia;
import negocio.LocalidadService;
import negocio.ProvinciaService;
import negocioImpl.LocalidadServiceImpl;
import negocioImpl.ProvinciaServiceImpl;

@WebServlet("/PerfilServlet")
public class PerfilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PerfilServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Clientes cliente = (Clientes) request.getSession().getAttribute("Cliente");
        
        if (cliente == null) {
            response.sendRedirect("Principal.jsp");
            return;
        }
       
        ProvinciaService provinciaNegocio = new ProvinciaServiceImpl();
		LocalidadService localidadNegocio = new LocalidadServiceImpl();
		
		Provincia provincia = provinciaNegocio.ObtenerById(cliente.getProvincia());
		Localidad localidad = localidadNegocio.ObtenerById(cliente.getLocalidad());
		
		request.setAttribute("Provincia", provincia);
		request.setAttribute("Localidad", localidad);
        
        request.setAttribute("Cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Perfil.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
