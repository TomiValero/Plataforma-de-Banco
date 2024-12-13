<%@page import="entidad.Clientes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous">
</script><title>Home</title>
</head>
<body>
<%
	Clientes usuario = null;
	if(request.getAttribute("Usuario")!=null)
	{
		usuario = (Clientes)request.getAttribute("Usuario");
	}
	if(usuario != null){
%>

	<nav class="navbar navbar-expand-lg bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="HomeServlet">Banco</a>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		  <%if(usuario.getTipoUsuario() == 1){ %>
	        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	          <li class="nav-item">
	            <a class="nav-link active" aria-current="page" href="TransferirServlet">Transferir</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="PedirPrestamoServlet">Pedir Prestamo</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="PagoServlet">Mis Prestamos</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="ListaMisCuentas">Mis Cuentas</a>
	          </li>
	         </ul>
	        <div class="d-flex align-items-center">
			  <p class="mb-0"><%=usuario.nombreApellido() %></p>
			  <a class="btn btn-outline-dark ms-3" href="PerfilServlet">Mi Perfil</a>
			</div>
		  <%} else if(usuario.getTipoUsuario() == 2){ %>
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
			  <p class="mb-0"><%=usuario.nombreApellido() %></p>
			  <a class="btn btn-outline-dark ms-3" href="PerfilServlet">Mi Perfil</a>
			</div>
		  <%} %>
	    </div>
	  </div>
	</nav>
	<div class="container-md align-items-center">
    <div class="col" style="padding-top:10px">
        <div style="padding: 20px; max-width: 700px; border: 1px solid #ddd; border-radius: 10px; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); margin: 20px auto;">
            <h1 class="text-center" style="margin-bottom:20px">Bienvenido a Banco</h1>
            <p style="text-align: center; font-size: 1.2em; color: #555;">
                Nuestro compromiso es brindarte soluciones financieras innovadoras y un servicio personalizado para ayudarte a alcanzar tus metas. Explora nuestros productos y servicios, pensados para ofrecerte la seguridad y el respaldo que necesitas en cada paso de tu camino.
            </p>
            <p style="text-align: center; font-size: 1.1em; color: #777; margin-top: 20px;">
                <strong>Estamos para apoyarte!</strong>
            </p>
        </div>
    </div>
</div>
<%} else { %>
	<div class="container-md align-items-center">
    <div class="col" style="padding-top:10px; display: flex; justify-content: center; align-items: center;">
        <div style="padding: 20px; max-width: 700px; border: 1px solid #ddd; border-radius: 10px; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); margin: 20px auto; text-align: center;">
            <h1 class="text-center" style="margin-bottom:20px;">Logueate para poder navegar</h1>
            <p style="font-size: 1.1em; color: #777; margin-top: 20px;">
                <strong>Estamos para apoyarte!</strong>
            </p>
            <a class="btn btn-primary" href="Principal.jsp" style="display: inline-block; margin: 20px auto;">Loguearse</a>
        </div>
    </div>
</div>

		
<%} %>
</body>
</html>