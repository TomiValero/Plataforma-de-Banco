<%@page import="entidad.Cuentas"%>
<%@page import="java.util.List"%>
<%@page  import= "entidad.Clientes" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Solicitar Préstamo</title>
    <script>
    </script>
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

<div class="container mt-5">
 	
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


<%
	List<Cuentas> misCuentas = null;
	if(request.getAttribute("listMisCuentas")!=null)
	{
		misCuentas = (List<Cuentas>)request.getAttribute("listMisCuentas");
	}
	
%>	


    <h2 class="text-center">Solicitar Préstamo</h2>
    <form action="PedirPrestamoServlet" method="post" class="mt-4" onsubmit="return validateForm();">
        <div class="mb-3">
	        <label for="cuenta" class="form-label">Seleccione una cuenta</label>
	        <select id="cuenta" name="cuenta" required class="form-control mb-2">
				<% if(misCuentas != null){
						for(Cuentas cuenta :misCuentas) {
							if(!cuenta.isBaja()){%>
						<option value="<%=cuenta.getId()%>"><%=cuenta.toString()%></option>
					<%}}
					}%>
			</select>
        
        </div>
        <div class="mb-3">
            <label for="importePedido" class="form-label">Importe Solicitado</label>
            <input type="number" id="importePedido" name="importePedido" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="cantCuotas" class="form-label">Cantidad de Cuotas</label>
            <input type="number" id="cantCuotas" name="cantCuotas" class="form-control" required>
            <span id="errorCuotas" class="text-danger"></span>
        </div>
        <input type="hidden" name="action" value="agregar">
        <button type="submit" class="btn btn-primary">Solicitar Préstamo</button>
    </form>
    <%-- 
    <div class="mt-3">
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
    </div>
    --%>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>