<%@page import="java.util.List" %>
<%@page import="entidad.Clientes" %>

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

 <div class="container-md">
 
 						<%
	 						Boolean mostrarModal = false; 
							if(request.getAttribute("MostrarModal") != null){
								mostrarModal = (Boolean) request.getAttribute("MostrarModal"); 
							}
							int idBaja = 0; 
							if(request.getAttribute("IdClienteBajaModal") != null){
								idBaja = (int) request.getAttribute("IdClienteBajaModal"); 
							}
							
							if(mostrarModal){
 						%>
	 						<div class="alert alert-dark text-center" role="alert" style="margin: 5px;">
							  <p class="mb-3">¿Está seguro que desea dar de baja el cliente?</p>
							  <div class="d-flex justify-content-center gap-2">
							    <a href="ListaClienteServlet?IdClienteBaja=<%=idBaja%>&Modal=false" class="btn btn-outline-dark">Aceptar</a>
							    <a href="ListaClienteServlet" class="btn btn-danger">Cancelar</a>
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
        <h1 class="text-center my-4">Lista Clientes</h1>
        
  <div class="d-flex justify-content-center" style="margin-bottom:10px">
     <form id="formFiltro" action="ListaClienteServlet" method="get" class="d-flex flex-wrap justify-content-center">
    

        <div class="d-flex flex-column me-3">
            <label for="txtDNI" class="form-label">DNI:</label>
            <input type="number" id="txtDNI" name="txtDNI" class="form-control" placeholder="buscar DNI.."
            value="<%= request.getAttribute("DNI") != null ? request.getAttribute("DNI") : "" %>">
        </div>

        <div class="d-flex flex-column me-3">
            <label for="txtApellido" class="form-label">Apellido:</label>
            <input type="text" id="txtApellido" name="txtApellido" class="form-control" placeholder="buscar Apellido.."
            value="<%= request.getAttribute("Apellido") != null ? request.getAttribute("Apellido") : "" %>">
        </div>

        <div class="d-flex align-items-end">
            <input type="submit" name="btnFiltros" class="btn btn-outline-secondary" value="Aplicar">
            <a href="ListaClienteServlet" class="btn btn-outline-dark" style="margin-left:5px">Limpiar</a>
        </div>
        
	</form>
   </div>
	 	<div class="d-flex justify-content-center container">
    <table id="table_id" class="display table table-responsive">

            <thead class="table-dark">
                <tr>
		            <th scope="col">#</th>
		            <th scope="col">Nombre</th>
		            <th scope="col">Apellido</th>
		            		            <th scope="col">DNI</th>
		            		            <th scope="col">CUIL</th>
		            		            <th scope="col">Telefono</th>
		            		            <th scope="col">Direccion</th>
		            		            <th scope="col">Sexo</th>
		            		            <th scope="col">Nacionalidad</th>
		            		            <th scope="col">Localidad</th>
		            		            <th scope="col">Provincia</th>
		            		            <th scope="col">Fecha Nacimiento</th>
		            		            <th scope="col">Correo</th>		            
		            					<th scope="col">Cuentas</th>\
		            					<th scope="col">Estado</th>
		            					<th scope="col">Baja/Alta</th>
		            					<th scope="col">Modificar</th>
	          	</tr>
            </thead>
            <tbody>  
            <%
            List<Clientes> listClientes=null;
            if(request.getAttribute("ListaClientes")!=null)
            {
            	listClientes=(List<Clientes>)request.getAttribute("ListaClientes");
             	for(Clientes cliente: listClientes)
            	{
            		
            		  %>
            		   
                    <tr>
                  
                        <th scope="row"><%=cliente.getId() %></th>
			            <td><%=cliente.getNombre() %></td>
			            <td><%=cliente.getApellido() %></td>
			            <td><%=cliente.getDni() %></td>
			            <td><%=cliente.getCuil() %></td>
			            <td><%=cliente.getTelefono() %></td>
			            <td><%=cliente.getDireccion() %></td>
			            <td><%=cliente.getSexo() %></td>
			            <td><%=cliente.getNacionalidad() %></td>
			            <td><%=cliente.getLocalidad() %></td>
			            <td><%=cliente.getProvincia() %></td>
			            <td><%=cliente.getFechaNac() %></td>
			            <td><%=cliente.getCorreo() %></td>       
						<td>
							<a href="ListaCuentasServlet?IdCliente=<%=cliente.getId()%>" class="btn btn-outline-dark">Ver Cuentas</a>
						</td>
						
			            <%if(!cliente.isBaja()){ %>
				            <td>Activo </td>
				            <td class="text-center">
				            	<a href="ListaClienteServlet?IdClienteBaja=<%=cliente.getId()%>" class="btn btn-outline-dark">Baja</a>
				            </td>
			            <%} else{%>
				            <td>No Activo </td>
				            <td class="text-center">
				            	<a href="ListaClienteServlet?IdClienteAlta=<%=cliente.getId()%>" class="btn btn-outline-dark">Alta</a>
				            </td>
			            <% }%>
	         				<td class="text-center">
				            	<a href="ModUsuarioServlet?idCliente=<%=cliente.getId()%>" class="btn btn-outline-dark">Modificar</a>
				            </td>
	                     </tr> 
                    <%}
            }
            %>                  
               <%
       
            if(request.getAttribute("ListaFiltroXDNI")!=null)
            {
            	listClientes=(List<Clientes>)request.getAttribute("ListaFiltroXDNI");
            	for(Clientes cliente: listClientes)
            	{
            		
            		  %>
            		   
                    <tr>
                  
                        <th scope="row"><%=cliente.getId() %></th>
			            <td><%=cliente.getNombre() %></td>
			            <td><%=cliente.getApellido() %></td>
			            <td><%=cliente.getDni() %></td>
			            <td><%=cliente.getCuil() %></td>
			            <td><%=cliente.getTelefono() %></td>
			            <td><%=cliente.getDireccion() %></td>
			            <td><%=cliente.getSexo() %></td>
			            <td><%=cliente.getNacionalidad() %></td>
			            <td><%=cliente.getLocalidad() %></td>
			            <td><%=cliente.getProvincia() %></td>
			            <td><%=cliente.getFechaNac() %></td>
			            <td><%=cliente.getCorreo() %></td>      
						<td>
							<a href="ListaCuentasServlet?IdCliente=<%=cliente.getId()%>" class="btn btn-outline-dark">Ver Cuentas</a>
						</td>
						
			            <%if(!cliente.isBaja()){ %>
				            <td>Activo </td>
				            <td class="text-center">
				            	<a href="ListaClienteServlet?IdClienteBaja=<%=cliente.getId()%>" class="btn btn-outline-dark">Baja</a>
				            </td>
			            <%} else{%>
				            <td>No Activo </td>
				            <td class="text-center">
				            	<a href="ListaClienteServlet?IdClienteAlta=<%=cliente.getId()%>" class="btn btn-outline-dark">Alta</a>
				            </td>
			            <% }%>
	         				<td class="text-center">
				            	<a href="ModUsuarioServlet?idCliente=<%=cliente.getId()%>" class="btn btn-outline-dark">Modificar</a>
				            </td>
	                     </tr> 
                    <%}
            }
            %>                
             <%
      
            if(request.getAttribute("ListaFiltroXApellido")!=null)
            {
            	listClientes=(List<Clientes>)request.getAttribute("ListaFiltroXApellido");
            	for(Clientes cliente: listClientes)
            	{
            		
            		  %>
            		   
                    <tr>
                  
                          <th scope="row"><%=cliente.getId() %></th>
			            <td><%=cliente.getNombre() %></td>
			            <td><%=cliente.getApellido() %></td>
			            <td><%=cliente.getDni() %></td>
			            <td><%=cliente.getCuil() %></td>
			            <td><%=cliente.getTelefono() %></td>
			            <td><%=cliente.getDireccion() %></td>
			            <td><%=cliente.getSexo() %></td>
			            <td><%=cliente.getNacionalidad() %></td>
			            <td><%=cliente.getLocalidad() %></td>
			            <td><%=cliente.getProvincia() %></td>
			            <td><%=cliente.getFechaNac() %></td>
			            <td><%=cliente.getCorreo() %></td>      
						<td>
							<a href="ListaCuentasServlet?IdCliente=<%=cliente.getId()%>" class="btn btn-outline-dark">Ver Cuentas</a>
						</td>
						
			            <%if(!cliente.isBaja()){ %>
				            <td>Activo </td>
				            <td class="text-center">
				            	<a href="ListaClienteServlet?IdClienteBaja=<%=cliente.getId()%>" class="btn btn-outline-dark">Baja</a>
				            </td>
			            <%} else{%>
				            <td>No Activo </td>
				            <td class="text-center">
				            	<a href="ListaClienteServlet?IdClienteAlta=<%=cliente.getId()%>" class="btn btn-outline-dark">Alta</a>
				            </td>
			            <% }%>
	         				<td class="text-center">
				            	<a href="ModUsuarioServlet?idCliente=<%=cliente.getId()%>" class="btn btn-outline-dark">Modificar</a>
				            </td>
	                     </tr> 
                    <%}
            }
            %>
            </tbody>

        </table>
        </div>
        
		<div class="d-flex justify-content-center mt-4">
			<a href="HomeServlet" style="margin:5px" class="btn btn-outline-dark btn-lg">Volver a Home</a>
			<a href="RegisterServlet" style="margin:5px" class="btn btn-outline-primary btn-lg">Nuevo Cliente</a>
		</div>
    </div>

</body>
</html>