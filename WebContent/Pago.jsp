<%@page import="entidad.Prestamos"%>
<%@page import="entidad.Cuentas"%>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>Lista de Mis Prestamos</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id').DataTable();
	});
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

<%
	List<Prestamos> prestamos = null;
	if(request.getAttribute("ListaPrestamos") != null)
	{
		prestamos = (List<Prestamos>)request.getAttribute("ListaPrestamos");
	}
	int paginas = 0;
	if(request.getAttribute("Paginas") != null)
	{
		paginas = (int)request.getAttribute("Paginas");
	}
	
	
	if(prestamos != null || prestamos.size() <= 0)
	{
			
		List<Cuentas> misCuentas = null;
		if(request.getAttribute("listMisCuentas")!=null)
		{
			misCuentas = (List<Cuentas>)request.getAttribute("listMisCuentas");
		}
		
%>

    <div class="container-md">
  
        <h1 class="text-center my-4">Mis Prestamos</h1>  
              
<div class="d-flex justify-content-center" style="margin-bottom:10px">
    <form id="formFiltro" action="PagoServlet" method="get" class="d-flex flex-wrap justify-content-center">
        <%-- Filtro por estado --%>
        <div class="d-flex flex-column me-3">
            <label for="estadoSelect" class="form-label">Estado:</label>
            <select id="estadoSelect" name="estadoSelect" class="form-control">
                <option value="Todos" <%= "Todos".equals(request.getAttribute("Estado")) ? "selected" : "" %>>Todos</option>
                <option value="Pendiente" <%= "Pendiente".equals(request.getAttribute("Estado")) ? "selected" : "" %>>Pendiente</option>
                <option value="Rechazado" <%= "Rechazado".equals(request.getAttribute("Estado")) ? "selected" : "" %>>Rechazado</option>
                <option value="Aceptado" <%= "Aceptado".equals(request.getAttribute("Estado")) ? "selected" : "" %>>Aceptado</option>
                <option value="Pago" <%= "Pago".equals(request.getAttribute("Estado")) ? "selected" : "" %>>Pago</option>
            </select>
        </div>

        <%-- Filtro por MinImporte --%>
        <div class="d-flex flex-column me-3">
            <label for="txtMinImporte" class="form-label">Importe Pedido Min:</label>
            <input type="number" id="txtMinImporte" name="txtMinImporte" class="form-control" placeholder="Importe Minimo" min="0"
            value="<%= request.getAttribute("MinImporte") != null ? request.getAttribute("MinImporte") : "" %>">
        </div>

        <%-- Filtro por MaxImporte --%>
        <div class="d-flex flex-column me-3">
            <label for="txtMaxImporte" class="form-label">Importe Pedido Max:</label>
            <input type="number" id="txtMaxImporte" name="txtMaxImporte" class="form-control" placeholder="Importe Maximo" min="0"
            value="<%= request.getAttribute("MaxImporte") != null ? request.getAttribute("MaxImporte") : "" %>">
        </div>

        <%-- Filtro por FechaMin --%>
        <div class="d-flex flex-column me-3">
            <label for="txtFechaMin" class="form-label">Fecha Min:</label>
            <input type="date" id="txtFechaMin" name="txtFechaMin" class="form-control" placeholder="Fecha minima"
            value="<%= request.getAttribute("FechaMinima") != null ? request.getAttribute("FechaMinima") : "" %>">
        </div>

        <%-- Filtro por FechaMax --%>
        <div class="d-flex flex-column me-3">
            <label for="txtFechaMax" class="form-label">Fecha Max:</label>
            <input type="date" id="txtFechaMax" name="txtFechaMax" class="form-control" placeholder="Fecha maxima"
            value="<%= request.getAttribute("FechaMaxima") != null ? request.getAttribute("FechaMaxima") : "" %>">
        </div>

        <%-- Botón de enviar filtros --%>
        <div class="d-flex align-items-end">
            <input type="submit" name="btnFiltros" class="btn btn-outline-secondary" value="Aplicar">
            <a href="PagoServlet" class="btn btn-outline-dark" style="margin-left:5px">Limpiar</a>
        </div>
    </form>
</div>
  
       <table id="table_id" class="display table">
		    <thead class="table-dark">          
		        <tr>
		            <th scope="col">Cuenta</th>
		            <th scope="col">Fecha de Alta</th>
		            <th scope="col">Importe Pedido</th>
		            <th scope="col">Cantidad de Cuotas</th>
		            <th scope="col">Importe por Mes</th>
		            <th scope="col">Estado</th> 
		            <th scope="col">Cuotas</th>                                
		        </tr>
		    </thead>
		    <tbody>      
		        <% for (Prestamos prestamo : prestamos) {
		            String cbu = "00";
		        	for (Cuentas cuenta : misCuentas) { 
		            	if(prestamo.getCuenta() == cuenta.getId())
		            	{
		            		cbu = String.valueOf(cuenta.getCbu());
		            	}
	            	}%>        
		        <tr>
		            <td><%= cbu %></td>
		            <td><%= prestamo.getFechaAlta() %></td>
		            <td><%= prestamo.getImportePedido() %></td>
		            <td><%= prestamo.getCantCuotas() %></td>
		            <td><%= prestamo.getImportePorMes() %></td>
		            <td><%= prestamo.getEstado() %></td>
		            <%if(prestamo.getEstado().equalsIgnoreCase("Aceptado") || prestamo.getEstado().equalsIgnoreCase("Pago")){ %>
		            	<td><a href="PagoServlet?PrestId=<%=prestamo.getId()%>"  class="btn btn-primary">Ver Cuotas</a></td>
		            <%} else {%>
		            	<td>-</td>
	            	<%}%>
		        </tr>   
		        <% } %>         
		    </tbody>
		</table>

	<% } else {
	%>
	<h1 class="text-center my-4">No tienes prestamos</h1>
	<%}%>


        <div class="d-flex justify-content-center mt-4">
			<a href="HomeServlet" class="btn btn-outline-dark btn-lg">Volver a Home</a>
		</div>
    </div>
</body>
</html>