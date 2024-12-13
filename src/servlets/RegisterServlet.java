package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Provincia;
import exceptions.DniYaExiste;
import exceptions.EmailYaExiste;
import exceptions.UsuarioYaExiste;
import entidad.Clientes;
import entidad.Localidad;
import negocio.ClientesService;
import negocio.LocalidadService;
import negocio.ProvinciaService;
import negocioImpl.ClientesServiceImpl;
import negocioImpl.LocalidadServiceImpl;
import negocioImpl.ProvinciaServiceImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
			ProvinciaService provinciaNegocio = new ProvinciaServiceImpl();
			LocalidadService localidadNegocio = new LocalidadServiceImpl();
			
			List<Provincia> provincias = provinciaNegocio.ListarProvincias();
			List<Localidad> localidades = localidadNegocio.ListarLocalidad();
			
			request.setAttribute("listProvincias", provincias);
			request.setAttribute("listLocalidades", localidades);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Registrarse.jsp");
    		rd.forward(request, response);
  		
    		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String dni = request.getParameter("txtdni");
		String cuil = request.getParameter("txtcuil");
		String nombre = request.getParameter("txtnombre");
		String apellido = request.getParameter("txtapellido");
		String sexo = request.getParameter("ddlSexo");
		String fechaNacimiento = request.getParameter("txtfechaNacimiento");
		String direccion = request.getParameter("txtdireccion");
		String provincia = request.getParameter("ddlProvincia");
		String localidad = request.getParameter("ddlLocalidad");
		String correo = request.getParameter("txtcorreo");
		String telefono = request.getParameter("txttelefono");
		String usuario = request.getParameter("txtusuario");
		String contrasena = request.getParameter("txtcontrasena");
		String contrasenaRepetida = request.getParameter("txtcontrasenaRepetida");
		String nacionalidad = request.getParameter("txtnacionalidad");
		
		if(request.getParameter("btnAceptar")!=null)
		{
			int provinciaId = Integer.parseInt(provincia);
			int localidadId = Integer.parseInt(localidad);
			
			Date fechaNac = Date.valueOf(fechaNacimiento);
			
			Clientes cliente = new Clientes(
					0,
				    dni,
				    cuil,
				    nombre,
				    apellido,
				    sexo,
				    nacionalidad,
				    fechaNac,
				    direccion,
				    localidadId,
				    provinciaId,
				    correo,
				    telefono,
				    usuario,
				    contrasena,
				    false,                
				    1                    
				);
			ClientesService clienteNegocio = new ClientesServiceImpl();
			
			
			String mensaje = "";
			boolean registrado = false;
			
			
			
			try {
				if(!contrasena.equals(contrasenaRepetida)) {
					mensaje = "Las contraseñas no coinciden";
				}else {
					registrado = clienteNegocio.AgregarCliente(cliente);
					if(registrado) {
						mensaje = "Cliente registrado correctamente";
					}else {
						mensaje = "Ocurrio un error al registar cliente";
					}
				}
			} catch (DniYaExiste e) {
				mensaje = e.getMessage();
				//e.printStackTrace();
			} catch (EmailYaExiste e) {
				mensaje = e.getMessage();
				//e.printStackTrace();
			} catch (UsuarioYaExiste e) {
				mensaje = e.getMessage();
				//e.printStackTrace();
			}
			finally {
				request.setAttribute("Mensaje", mensaje);
				request.setAttribute("Registrado", registrado);
				
				ProvinciaService provinciaNegocio = new ProvinciaServiceImpl();
				LocalidadService localidadNegocio = new LocalidadServiceImpl();
				
				List<Provincia> provincias = provinciaNegocio.ListarProvincias();
				List<Localidad> localidades = localidadNegocio.ListarLocalidad();
				
				request.setAttribute("listProvincias", provincias);
				request.setAttribute("listLocalidades", localidades);
				
				RequestDispatcher rd = request.getRequestDispatcher("/Registrarse.jsp");
				rd.forward(request, response);
			}
			
			
		}
		else {
			
			
			
			request.setAttribute("dni", dni);
			request.setAttribute("cuil", cuil);
			request.setAttribute("nombre", nombre);
			request.setAttribute("apellido", apellido);
			request.setAttribute("sexo", sexo);
			request.setAttribute("fechaNacimiento", fechaNacimiento);
			request.setAttribute("direccion", direccion);			
			
			request.setAttribute("pSelecionada", provincia);
			
			request.setAttribute("correo", correo);
			request.setAttribute("telefono", telefono);
			request.setAttribute("usuario", usuario);
			request.setAttribute("contrasena", contrasena);
			request.setAttribute("contrasenaRepetida", contrasenaRepetida);
			request.setAttribute("nacionalidad", nacionalidad);
			
			ProvinciaService provinciaNegocio = new ProvinciaServiceImpl();
			LocalidadService localidadNegocio = new LocalidadServiceImpl();
			
			List<Provincia> provincias = provinciaNegocio.ListarProvincias();
			List<Localidad> localidades = localidadNegocio.ListarLocalidad();
			
			request.setAttribute("listProvincias", provincias);
			request.setAttribute("listLocalidades", localidades);

			RequestDispatcher rd = request.getRequestDispatcher("/Registrarse.jsp");
			rd.forward(request, response);
		}
		
		
		

	}

}
