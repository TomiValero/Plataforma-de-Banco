<%@page import="entidad.Cuentas"%>
<%@page import="java.util.List"%>
<%@page  import= "entidad.Clientes" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transferir</title>
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
 	<nav class="navbar navbar-expand-lg bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="HomeServlet">Banco</a>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="TransferirServlet">Transferir</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="PedirPrestamoServlet">Pedir Préstamo</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="PagoServlet">Mis Prestamos</a>
	        </li>
	        <li class="nav-item">
	            <a class="nav-link" href="ListaMisCuentas">Mis Cuentas</a>
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
	List<Cuentas> misCuentas = null;
	if(request.getAttribute("listMisCuentas")!=null)
	{
		misCuentas = (List<Cuentas>)request.getAttribute("listMisCuentas");
	}
	
%>	
	
	<div class="container-md align-items-center">
	
							<% 
							    Boolean exito = (Boolean) request.getAttribute("Exito"); 
								String mensaje = (String) request.getAttribute("Mensaje"); 
							    if (exito != null && exito) { 
							    	if(mensaje != null && mensaje != ""){
							%>					
							<div class="alert alert-success" role="alert">
							  <%=mensaje%>
							</div>
							<% 
							    	}
						    	} else if(exito != null && !exito) { 
						    		if(mensaje != null && mensaje != ""){
							%>
							<div class="alert alert-danger" role="alert">
							  <%=mensaje%>
							</div>
							<%		}
					    		} %>
	
	
	
		<div class="col" style="padding-top:10px">
			<div style="padding: 20px; max-width:; border: 1px solid #ddd; border-radius: 10px; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); margin: 20px auto;">
			    <h1 class="text-center" style="margin-bottom:20px">Transferir</h1>
			    <form action="TransferirServlet" method="post">
			    	<div class="row">
			    		<div class="col">			        
					        <label for="montoATransferir" class="form-label">Monto a Transferir:</label>
					        <input type="number" id="montoATransferir" name="montoATransferir" required class="form-control mb-2" min=1;>
					        
					        <label for="cuenta" class="form-label">Cuenta a Debitar:</label>
					        <select id="cuenta" name="cuenta" required class="form-control mb-2">
								<% if(misCuentas != null){
										for(Cuentas cuenta :misCuentas) {
											if(!cuenta.isBaja()){%>
										<option value="<%=cuenta.getId()%>"><%=cuenta.toString()%></option>
									<%}}
									}%>
							</select>
					        
					        <label for="numCBU" class="form-label">CBU a Depositar:</label>
					        <input type="number" id="numCBU" name="numCBU" required class="form-control mb-2">
					        
				        </div>
				        <div class="d-flex justify-content-center">
					        <input type="submit" value="Transferir" class="btn btn-primary mx-2">
				        </div>
			        </div>
			    </form>
		    </div>
	    </div>
	    <div class="d-flex justify-content-center mt-4">
			<a href="HomeServlet" class="btn btn-outline-dark btn-lg">Volver a Home</a>
		</div>
		</div>
</body>
</html>