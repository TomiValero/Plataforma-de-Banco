<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="java.util.List"%>
<%@page  import= "entidad.Clientes" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script><title>Registrarse</title>
</head>
<body>
<%
Clientes usuarioLog = null;
	if( request.getSession().getAttribute("Cliente") !=null)
    {
		usuarioLog=(Clientes)request.getSession().getAttribute("Cliente");;
    }
%>
<script type="text/javascript">

	function eventoSeleccionarProvincia() {
		var x = document.getElementById("ddlProvincia").value;
		 document.getElementById("formAltaCliente").submit();
	  }
	  
</script>

<%
	String provSeleccionada="0";
	if(request.getAttribute("pSelecionada")!=null)
	{
		provSeleccionada=request.getAttribute("pSelecionada").toString();
	}
%>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="HomeServlet">Banco</a>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		            <li class="nav-item">
		              <a class="nav-link" href="PrestamoServlet">Listado de Prestamos</a>
		            </li>
		            <li class="nav-item">
		              <a class="nav-link" href="ListaCuentasServlet">Lista de Cuentas</a>
		            </li>
		            <li class="nav-item">
		              <a class="nav-link" href="ListaClienteServlet">Lista de Clientes</a>
		            </li>

			</ul>
			 <div class="d-flex align-items-center">
			  <p class="mb-0"><%=usuarioLog.nombreApellido() %></p>
			  <a class="btn btn-outline-dark ms-3" href="PerfilServlet">Mi Perfil</a>
			</div>
	    </div>
	  </div>
</nav>




<%
	List<Provincia> provincias = null;
	if(request.getAttribute("listProvincias")!=null)
	{
		provincias = (List<Provincia>)request.getAttribute("listProvincias");
	}
	
	List<Localidad> localidades = null;
	if(request.getAttribute("listLocalidades")!=null)
	{
		localidades = (List<Localidad>)request.getAttribute("listLocalidades");
	}
%>

	<div class="container-md align-items-center">
							<% 
							    Boolean registro = (Boolean) request.getAttribute("Registrado"); 
								String mensaje = (String) request.getAttribute("Mensaje"); 
							    if (registro != null && registro) { 
							    	if(mensaje != null && mensaje != ""){
							%>					
							<div class="alert alert-success" role="alert">
							  <%=mensaje%>
							</div>
							<% 
							    	}
						    	} else if(registro != null && !registro) { 
						    		if(mensaje != null && mensaje != ""){
							%>
							<div class="alert alert-danger" role="alert">
							  <%=mensaje%>
							</div>
							<%		}
					    		} %>
							
			<div class="col" style="padding-top:10px">
				<div style="padding: 20px; max-width:; border: 1px solid #ddd; border-radius: 10px; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); margin: 20px auto;">
				    <h1 class="text-center" style="margin-bottom:20px">Alta Cliente</h1>
				    <form id="formAltaCliente" action="RegisterServlet" method="post">
				    	<div class="row">
				    		<div class="col">
			                    <label for="dni" class="form-label">DNI:</label>
			                    <input type="number" id="txtdni" name="txtdni" required class="form-control mb-2" 
			                        value="<%= request.getAttribute("dni") != null ? request.getAttribute("dni") : "" %>">
			                    
			                    <label for="cuil" class="form-label">CUIL:</label>
			                    <input type="number" id="txtcuil" name="txtcuil" required class="form-control mb-2" 
			                        value="<%= request.getAttribute("cuil") != null ? request.getAttribute("cuil") : "" %>">
			                    
			                    <label for="nombre" class="form-label">Nombre:</label>
			                    <input type="text" id="txtnombre" name="txtnombre" required class="form-control mb-2" 
			                        value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>">
			                    
			                    <label for="apellido" class="form-label">Apellido:</label>
			                    <input type="text" id="txtapellido" name="txtapellido" required class="form-control mb-2" 
			                        value="<%= request.getAttribute("apellido") != null ? request.getAttribute("apellido") : "" %>">
			                    
			                    <label for="ddlSexo" class="form-label">Sexo:</label>
			                    <select id="ddlSexo" name="ddlSexo" required class="form-control mb-2">
			                        <option value="Nulo" <%= "Indeterminado".equals(request.getAttribute("sexo")) ? "selected" : "" %>>Prefiero no decirlo</option>
			                        <option value="Masculino" <%= "Masculino".equals(request.getAttribute("sexo")) ? "selected" : "" %>>Masculino</option>
			                        <option value="Femenino" <%= "Femenino".equals(request.getAttribute("sexo")) ? "selected" : "" %>>Femenino</option>
			                    </select>
			                    
			                    <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento:</label>
			                    <input type="date" id="txtfechaNacimiento" name="txtfechaNacimiento" required class="form-control mb-2" 
			                        value="<%= request.getAttribute("fechaNacimiento") != null ? request.getAttribute("fechaNacimiento") : "" %>">
			                    
			                    <label for="nacionalidad" class="form-label">Nacionalidad:</label>
			                    <input type="text" id="txtnacionalidad" name="txtnacionalidad" required class="form-control mb-2" 
			                        value="<%= request.getAttribute("nacionalidad") != null ? request.getAttribute("nacionalidad") : "" %>">
			               
					       	</div>
					       	<div class="col"  style="padding-top:">
						        <label for="ddlProvincia" class="form-label" >Provincia:</label>
						        <select id="ddlProvincia" name="ddlProvincia" required class="form-control mb-2" onchange="eventoSeleccionarProvincia()">
									<option value="0">Seleccione una Provincia</option>
									<%for(Provincia provincia :provincias) {%>
										<option value="<%=provincia.getID()%>"<%if(provSeleccionada.equals(provincia.getID()+"")==true){ %> selected="true"<%}%>><%=provincia.getDescripcion()%></option>
									<%} %>
								</select>
						        	        
						        <label for="ddlLocalidad" class="form-label">Localidad:</label>
						        <select id="ddlLocalidad" name="ddlLocalidad" required class="form-control mb-2">
									<%for(Localidad localidad :localidades) {
										if((localidad.getProvincia()).getID() == Integer.parseInt(provSeleccionada)){%>
										<option value="<%=localidad.getID()%>"><%=localidad.getDescripcion()%></option>
									<%}
										} %>
								</select>
								
			                    <label for="direccion" class="form-label">Direccion:</label>
			                    <input type="text" id="txtdireccion" name="txtdireccion" required class="form-control mb-2" 
			                        value="<%= request.getAttribute("direccion") != null ? request.getAttribute("direccion") : "" %>">
			                    
			                    <label for="correo" class="form-label">Correo:</label>
			                    <input type="email" id="txtcorreo" name="txtcorreo" required class="form-control mb-2" 
			                        value="<%= request.getAttribute("correo") != null ? request.getAttribute("correo") : "" %>">
			                    
			                    <label for="telefono" class="form-label">Telefono:</label>
			                    <input type="tel" id="txttelefono" name="txttelefono" required class="form-control mb-2" 
			                        value="<%= request.getAttribute("telefono") != null ? request.getAttribute("telefono") : "" %>">
			                    
			                    <label for="usuario" class="form-label">Usuario:</label>
			                    <input type="text" id="txtusuario" name="txtusuario" required class="form-control mb-2" 
			                        value="<%= request.getAttribute("usuario") != null ? request.getAttribute("usuario") : "" %>">
			                    
			                    <label for="contrasena" class="form-label">Contraseña:</label>
			                    <input type="password" id="txtcontrasena" name="txtcontrasena" required class="form-control mb-2" 
			                        value="<%= request.getAttribute("contrasena") != null ? request.getAttribute("contrasena") : "" %>">
			               
			               		<label for="contrasenaRepetida" class="form-label">Repetir Contraseña:</label>
			                    <input type="password" id="txtcontrasenaRepetida" name="txtcontrasenaRepetida" required class="form-control mb-2" 
			                        value="<%= request.getAttribute("contrasenaRepetida") != null ? request.getAttribute("contrasenaRepetida") : "" %>">
			               
					        </div>
					        <div class="d-flex justify-content-center">
						        <input type="submit" value="Registrar Cliente" name="btnAceptar" class="btn btn-primary mx-2">	
						    </div>
				        </div>
				    </form>
				    <div class="d-flex justify-content-center mt-4">
				        <a href="HomeServlet" class="btn btn-outline-dark btn-lg" style="display: inline-block; margin: 20px auto;">Volver a Home</a>
				    </div>
			    </div>
		    </div>
		    
	    </div>
</body>
</html>