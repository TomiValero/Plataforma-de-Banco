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
import entidad.Cuentas;
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
@WebServlet("/ModUsuarioServlet")
public class ModUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModUsuarioServlet() {
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
	        
	        ClientesService clienteNegocio = new ClientesServiceImpl();
	        Clientes clienteMod = new Clientes();
	        
	        String id = request.getParameter("idCliente") ;	
	        if( id != null) {
	        	int idCliente = Integer.parseInt(id);    	       
	        	clienteMod = clienteNegocio.ObtenerById(idCliente);
	        }
	        else {
	        	response.sendRedirect("Principal.jsp");
	            return;
	        }

	        request.getSession().setAttribute("ClienteMod", clienteMod);
	        
	        
	        request.setAttribute("dni", clienteMod.getDni());
			request.setAttribute("cuil", clienteMod.getCuil());
			request.setAttribute("nombre", clienteMod.getNombre());
			request.setAttribute("apellido", clienteMod.getApellido());
			request.setAttribute("sexo", clienteMod.getSexo());
			request.setAttribute("fechaNacimiento", clienteMod.getFechaNac());
			request.setAttribute("direccion", clienteMod.getDireccion());						
			request.setAttribute("pSelecionada", clienteMod.getProvincia());			
			request.setAttribute("correo", clienteMod.getCorreo());
			request.setAttribute("telefono", clienteMod.getTelefono());
			request.setAttribute("usuario", clienteMod.getUsuario());
			request.setAttribute("nacionalidad", clienteMod.getNacionalidad());
	        
			ProvinciaService provinciaNegocio = new ProvinciaServiceImpl();
			LocalidadService localidadNegocio = new LocalidadServiceImpl();
			
			List<Provincia> provincias = provinciaNegocio.ListarProvincias();
			List<Localidad> localidades = localidadNegocio.ListarLocalidad();
			
			request.setAttribute("listProvincias", provincias);
			request.setAttribute("listLocalidades", localidades);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ModUsuario.jsp");
    		rd.forward(request, response);
  		
    		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Clientes clienteMod = (Clientes) request.getSession().getAttribute("ClienteMod");
		
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
		String nacionalidad = request.getParameter("txtnacionalidad");
		
		String contrasena = request.getParameter("txtcontrasena");
		String contrasenaRepetida = request.getParameter("txtcontrasenaRepetida");
		
		
		
		if(request.getParameter("btnAceptar")!=null)
		{
			if(contrasena == null || contrasena.isEmpty()) {
				contrasena = clienteMod.getContrasena();
			}
			if(contrasenaRepetida == null || contrasenaRepetida.isEmpty()) {
				contrasenaRepetida = clienteMod.getContrasena();;
			}
			
			int provinciaId = Integer.parseInt(provincia);
			int localidadId = Integer.parseInt(localidad);
			
			Date fechaNac = Date.valueOf(fechaNacimiento);
			
			Clientes cliente = new Clientes(
					clienteMod.getId(),
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
			
			
			
			if(!contrasena.equals(contrasenaRepetida)) {
				mensaje = "Las contraseñas no coinciden";
			}else {
				registrado = clienteNegocio.ActualizarCliente(cliente);
				if(registrado) {
					mensaje = "Cliente modificado correctamente";
				}else {
					mensaje = "Ocurrio un error al modificar cliente";
				}
			}
			
			request.setAttribute("Mensaje", mensaje);
			request.setAttribute("Registrado", registrado);
			
			ProvinciaService provinciaNegocio = new ProvinciaServiceImpl();
			LocalidadService localidadNegocio = new LocalidadServiceImpl();
			
			List<Provincia> provincias = provinciaNegocio.ListarProvincias();
			List<Localidad> localidades = localidadNegocio.ListarLocalidad();
			
			request.setAttribute("listProvincias", provincias);
			request.setAttribute("listLocalidades", localidades);
			request.setAttribute("FinModificacion", true);
						
			RequestDispatcher rd = request.getRequestDispatcher("/ModUsuario.jsp");
			rd.forward(request, response);
			
			
			
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
			
			RequestDispatcher rd = request.getRequestDispatcher("/ModUsuario.jsp");
			rd.forward(request, response);
		}
		
		
		

	}

}
