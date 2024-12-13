<%@page  import= "entidad.Cuentas" %>
<%@page  import= "entidad.TipoDeCuenta" %>
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
<title>Lista de Clientes</title>
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

 <div class="container-md">
 <%
	 						Boolean mostrarModal = false; 
							if(request.getAttribute("MostrarModal") != null){
								mostrarModal = (Boolean) request.getAttribute("MostrarModal"); 
							}
							int idBaja = 0; 
							if(request.getAttribute("idCuentaBajaModal") != null){
								idBaja = (int) request.getAttribute("idCuentaBajaModal"); 
							}
							
							if(mostrarModal){
 						%>
	 						<div class="alert alert-dark text-center" role="alert" style="margin: 5px;">
							  <p class="mb-3">¿Está seguro que desea dar de baja la Cuenta?</p>
							  <div class="d-flex justify-content-center gap-2">
							    <a href="ListaMisCuentas?idCuentaBaja=<%=idBaja%>&Modal=false" class="btn btn-outline-dark">Aceptar</a>
							    <a href="ListaMisCuentas" class="btn btn-danger">Cancelar</a>
							  </div>
							</div>

						<%} %>	
  						<% 
							    Boolean exito = (Boolean) request.getAttribute("Exito"); 
								String mensaje = (String) request.getAttribute("Mensaje"); 
							    if (exito != null && exito) { 
							    	if(mensaje != null && mensaje != ""){
							%>					
							<div class="alert alert-success" role="alert" style="margin:5px">
							  <%=mensaje%>
							</div>
							<% 
							    	}
						    	} else if(exito != null && !exito) { 
						    		if(mensaje != null && mensaje != ""){
							%>
							<div class="alert alert-danger" role="alert" style="margin:5px">
							  <%=mensaje%>
							</div>
							<%		}
					    		} %>
	</div>


<div class="container-md">
        <h1 class="text-center my-4">Lista Cuentas</h1>
        
            <div class="d-flex justify-content-center" style="margin-bottom:10px">
     <form id="formFiltro" action="ListaMisCuentas" method="post" class="d-flex flex-wrap justify-content-center">
    

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
            <a href="ListaMisCuentas" class="btn btn-outline-dark" style="margin-left:5px">Limpiar</a>
        </div>
        
	</form>
   </div>

	 	 	
        <table  id="table_id" class="display table">
            <thead class="table-dark">
                <tr>
		            <th scope="col">Fecha creacion</th>
		            <th scope="col">Tipo de cuenta</th>
		            <th scope="col">Numero de cuenta</th>
		            <th scope="col">CBU</th>
		            <th scope="col">Saldo</th>
		            <th scope="col">Activo</th>
		            <th scope="col">Baja</th>
		            <th scope="col">Historial</th>	            
	          	</tr>
            </thead>
            <tbody>              
              <%
              ArrayList<Cuentas> cuentasCliente=new ArrayList<Cuentas>();
              String descripcionTipoCuenta = "N/A";
             
              List<TipoDeCuenta> listaTipoCuentas=null;
              
              if(request.getAttribute("TipoCuentas")!=null)
              {
           	   listaTipoCuentas=(List <TipoDeCuenta> )request.getAttribute("TipoCuentas");
              }
              if(request.getAttribute("cuentasCliente")!= null)
              {
            	  cuentasCliente=(ArrayList<Cuentas>)request.getAttribute("cuentasCliente");
            	  
              }
              if(request.getAttribute("listaFiltrada")!=null)
              {
            	  cuentasCliente=(ArrayList<Cuentas>)request.getAttribute("listaFiltrada");

              }
              for(Cuentas cuenta : cuentasCliente)
              {
            	  for (TipoDeCuenta tipoCuenta : listaTipoCuentas) {
  		            if (tipoCuenta.getID() == cuenta.getTipoCuenta()) {
  		                descripcionTipoCuenta = tipoCuenta.getDescripcion();
  		                break; // Salir del bucle una vez que se encuentra el tipo de cuenta
  		            }
  		        }
            	  
        %>    	  
            	  <tr>    	  
		            <td><%=cuenta.getFechaAlta() %></td>
		            <td><%=descripcionTipoCuenta %></td>
		            <td><%=cuenta.getNumCuenta() %></td>
		            <td><%=cuenta.getCbu() %></td>
		            <td><%=cuenta.getSaldo() %></td>
		            <%if(!cuenta.isBaja()){ %>
		            <td>Activo </td>
		            <td class="text-center">
		            	<a href="ListaMisCuentas?idCuentaBaja=<%=cuenta.getId()%>"  class="btn btn-outline-dark">Baja</a>
		            </td>
		            <%} 
		            else{%>
		            <td>No Activo </td>
		            <td>-</td>
		            <%  }%>
		            <td class="text-center">
		            <a href="ListaMisCuentas?idCuentaHistorial=<%=cuenta.getId()%>"  class="btn btn-outline-dark">Ver</a>
		            </td>		           	
             
              </tr>            
      	  
            	  
      <%      	  
              }
              
              %>
                            
            </tbody>

        </table>


		<div class="d-flex justify-content-center mt-4">
			<a href="HomeServlet" class="btn btn-outline-dark btn-lg">Volver a Home</a>
		</div>
		
    </div>
</body>
</html>