package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Clientes;
import entidad.Cuota;
import negocio.CuotaService;
import negocioImpl.CuotaServiceImpl;

@WebServlet("/CuotaServlet")
public class CuotaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CuotaService cuotaService = new CuotaServiceImpl();

    public CuotaServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Clientes clienteLog = (Clientes) request.getSession().getAttribute("Cliente");
        
        if (clienteLog == null) {
            response.sendRedirect("Principal.jsp");
            return;
        }
    	
    	String action = request.getParameter("action");
        try {
            switch (action) {
                case "listar":
                    listarCuotas(request, response);
                    break;
                case "pagar":
                    pagarCuota(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listarCuotas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clienteId = Integer.parseInt(request.getParameter("clienteId"));
        List<Cuota> cuotas = cuotaService.listarCuotasPendientesPorCliente(clienteId);
        request.setAttribute("cuotas", cuotas);
        request.getRequestDispatcher("menuDePagos.jsp").forward(request, response);
    }

    private void pagarCuota(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cuotaId = Integer.parseInt(request.getParameter("cuotaId"));
        int cuentaId = Integer.parseInt(request.getParameter("cuentaId"));
        
        boolean pagado = cuotaService.pagarCuota(cuotaId, cuentaId);
        if (pagado) {
            request.setAttribute("mensaje", "Cuota pagada exitosamente.");
        } else {
            request.setAttribute("error", "Error al pagar la cuota. Verifica el saldo o los datos ingresados.");
        }
        listarCuotas(request, response);
    }
}
