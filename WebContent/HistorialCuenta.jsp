<%@page  import= "entidad.Movimientos" %>
<%@page  import= "entidad.TipoDeMovimiento" %>
<%@page  import= "java.util.List" %>
<%@page  import= "java.util.ArrayList" %>
<%@page import="entidad.Clientes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>Historial de Cuenta</title>
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
	          <a class="nav-link" href="PedirPrestamoServlet">Pedir Prestamo</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="PagoServlet">Mis Prestamos</a>
	        </li>
	        <li class="nav-item">
	            <a class="nav-link" href="ListaDeMisCuentas.jsp">Mis Cuentas</a>
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
List<Movimientos> movimientosCuenta = new ArrayList<Movimientos>();

if(request.getAttribute("historialCuenta")!= null)
{
	movimientosCuenta=(List<Movimientos>)request.getAttribute("historialCuenta");
}

 List<TipoDeMovimiento> listaTipoMovimientos=null;
  
  if(request.getAttribute("TiposMovimientos")!=null)
  {
	  listaTipoMovimientos=(List <TipoDeMovimiento> )request.getAttribute("TiposMovimientos");
  } 
  %>	

<div class="container-md">
        <h1 class="text-center my-4">Historial de Cuenta</h1>
<div class="d-flex justify-content-center" style="margin-bottom:10px">

     <form id="formFiltro" action="HistorialCuentaServlet" method="get" class="d-flex flex-wrap justify-content-center">
        <div class="d-flex flex-column me-3">
            <label for="tipoSelect" class="form-label">Estado:</label>
            <select id="tipoSelect" name="tipoSelect" class="form-control">
            <option value="-1" <%= -1 == (int)request.getAttribute("Tipo") ? "selected" : "" %>>Todos</option>
            <%for (TipoDeMovimiento tipo : listaTipoMovimientos) { %>
                <option value="<%=tipo.getID() %>" <%= tipo.getID() == (int)request.getAttribute("Tipo") ? "selected" : "" %>><%=tipo.getDescripcion() %></option>
            <%}%>
            </select>
        </div>

        <div class="d-flex flex-column me-3">
            <label for="txtMinImporte" class="form-label">Monto Min:</label>
            <input type="number" id="txtMinImporte" name="txtMinImporte" class="form-control" placeholder="Importe Minimo" min="0"
            value="<%= request.getAttribute("MinImporte") != null ? request.getAttribute("MinImporte") : "" %>">
        </div>

        <div class="d-flex flex-column me-3">
            <label for="txtMaxImporte" class="form-label">Moto Max:</label>
            <input type="number" id="txtMaxImporte" name="txtMaxImporte" class="form-control" placeholder="Importe Maximo" min="0"
            value="<%= request.getAttribute("MaxImporte") != null ? request.getAttribute("MaxImporte") : "" %>">
        </div>

        <div class="d-flex flex-column me-3">
            <label for="txtFechaMin" class="form-label">Fecha Min:</label>
            <input type="date" id="txtFechaMin" name="txtFechaMin" class="form-control" placeholder="Fecha minima"
            value="<%= request.getAttribute("FechaMinima") != null ? request.getAttribute("FechaMinima") : "" %>">
        </div>

        <div class="d-flex flex-column me-3">
            <label for="txtFechaMax" class="form-label">Fecha Max:</label>
            <input type="date" id="txtFechaMax" name="txtFechaMax" class="form-control" placeholder="Fecha maxima"
            value="<%= request.getAttribute("FechaMaxima") != null ? request.getAttribute("FechaMaxima") : "" %>">
        </div>

        <div class="d-flex align-items-end">
            <input type="submit" name="btnFiltros" class="btn btn-outline-secondary" value="Aplicar">
            <a href="HistorialCuentaServlet" class="btn btn-outline-dark" style="margin-left:5px">Limpiar</a>
        </div>
        
	</form>
</div>
        <table  id="table_id" class="display table">
            <thead class="table-dark">
                <tr>
		            <th scope="col">Tipo de movimiento</th>
		            <th scope="col">Monto</th>
		            <th scope="col">Fecha</th>
	          	</tr>
            </thead>
            <tbody>         
				<% 		              
					for(Movimientos movimiento : movimientosCuenta)
					{
						String descripcionTipoMov = "";
						for (TipoDeMovimiento tipo : listaTipoMovimientos) {
		  		            if (tipo.getID() == movimiento.getTipoMovimiento()) {
		  		            	descripcionTipoMov = tipo.getDescripcion();
		  		                break; // Salir del bucle una vez que se encuentra el tipo de cuenta
		  		            }
						}
				%>     
                    <tr>
			            <td><%=descripcionTipoMov%></td>
			            <td><%=movimiento.getMonto()%></td>
			            <td><%=movimiento.getFecha()%></td>
                    </tr> 
				<%
				}%>
            </tbody>
        </table>
		<div class="d-flex justify-content-center mt-4">
			<a href="ListaMisCuentas" class="btn btn-outline-dark btn-lg">Volver a Cuentas</a>
		</div>
    </div>
</body>
</html>