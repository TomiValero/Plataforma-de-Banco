<%@page  import= "entidad.Cuentas" %>
<%@page  import= "entidad.TipoDeCuenta" %>
<%@page  import= "entidad.Clientes" %>
<%@page  import= "java.util.ArrayList" %>
<%@page  import= "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>Lista de Cuentas</title>

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
							if(request.getAttribute("idCuentaBajaModal") != null){
								idBaja = (int) request.getAttribute("idCuentaBajaModal"); 
							}
							
							if(mostrarModal){
 						%>
	 						<div class="alert alert-dark text-center" role="alert" style="margin: 5px;">
							  <p class="mb-3">¿Está seguro que desea dar de baja la Cuenta?</p>
							  <div class="d-flex justify-content-center gap-2">
							    <a href="ListaCuentasServlet?idCuentaBaja=<%=idBaja%>&Modal=false" class="btn btn-outline-dark">Aceptar</a>
							    <a href="ListaCuentasServlet" class="btn btn-danger">Cancelar</a>
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
 <%if(request.getAttribute("UsuarioCuentas")!=null){
        	Clientes cliente = (Clientes)request.getAttribute("UsuarioCuentas");
        	%>
        	<h1 class="text-center my-4">Cuentas de <%=cliente.nombreApellido() %></h1>
       	<%} else {%>
        	<h1 class="text-center my-4">Todas las Cuentas</h1>
        <%}%>
       <div class="d-flex justify-content-center" style="margin-bottom:10px">
     <form id="formFiltro" action="ListaCuentasServlet" method="post" class="d-flex flex-wrap justify-content-center">
    

        <div class="d-flex flex-column me-3">
            <label for="txtNumeroCuenta" class="form-label">Numero de Cuenta:</label>
            <input type="number" id="txtNumeroCuenta" name="txtNumeroCuenta" class="form-control" 
            value="<%= request.getAttribute("CuentaNumero") != null ? request.getAttribute("CuentaNumero") : 0 %>">
        </div>

        <div class="d-flex flex-column me-3">
            <label for="txtCBU" class="form-label">CBU:</label>
            <input type="number" id="txtCBU" name="txtCBU" class="form-control" 
            value="<%= request.getAttribute("CuentaCBU") != null ? request.getAttribute("CuentaCBU") : 0 %>">
        </div>

        <div class="d-flex align-items-end">
            <input type="submit" name="btnFiltros" class="btn btn-outline-secondary" value="Aplicar">
            <a href="ListaCuentasServlet" class="btn btn-outline-dark" style="margin-left:5px">Limpiar</a>
        </div>
        
	</form>
   </div>

	 	 	
        <table  id="table_id" class="display table">
            <thead class="table-dark">
                <tr>
		            <th scope="col">#</th>
		            <th scope="col">Fecha creacion</th>
		            <th scope="col">Tipo de cuenta</th>
		            <th scope="col">Numero de cuenta</th>
		            <th scope="col">CBU</th>
		            <th scope="col">Saldo</th>
		            <th scope="col">Activo</th>
		            <th scope="col">Baja/Alta</th>
		            <th scope="col">Informe</th>
	          	</tr>
            </thead>
			<tbody>
				<% 
            /////
            ArrayList <Cuentas> cuentasFiltrada=null;
            List<Cuentas> cuentas=null;
           Cuentas cuentaAux = new Cuentas();
           Cuentas cuentaXNumero= new Cuentas();
           Cuentas cuentaXCbu = new Cuentas(); 
           
           List<TipoDeCuenta> listaTipoCuentas=null;
           
           String descripcionTipoCuenta = "N/A";
         
           ////
           if(request.getAttribute("TipoCuentas")!=null)
           {
        	   listaTipoCuentas=(List <TipoDeCuenta> )request.getAttribute("TipoCuentas");
           }
           if(request.getAttribute("cuentasFiltrada")!=null)
            {
            	cuentasFiltrada=(ArrayList)request.getAttribute("cuentasFiltrada");
            }
           ///
           if(request.getAttribute("NumeroCuenta")!=null)
           {
           cuentaXNumero=(Cuentas)request.getAttribute("NumeroCuenta");
         if(listaTipoCuentas!=null)
         {
        	 for(TipoDeCuenta tipoCuenta:listaTipoCuentas)
         {
        		 if(tipoCuenta.getID()==cuentaXNumero.getTipoCuenta())descripcionTipoCuenta=tipoCuenta.getDescripcion();
         }
         }
           %>
				<tr>

					<th scope="row"><%=cuentaXNumero.getId() %></th>
					<td><%=cuentaXNumero.getFechaAlta() %></td>
					<td><%=descripcionTipoCuenta%></td>
					<td><%=cuentaXNumero.getNumCuenta() %></td>
					<td><%=cuentaXNumero.getCbu() %></td>
					<td><%=cuentaXNumero.getSaldo() %></td>
					<%if(!cuentaXNumero.isBaja()){ %>
					<td>Activo</td>
					<td class="text-center">
		            	<a href="ListaCuentasServlet?idCuentaBaja=<%=cuentaXNumero.getId()%>"  class="btn btn-outline-dark">Baja</a>
		            </td>
					<%} 
		            else{%>
					<td>No Activo</td>
					<td class="text-center">
		            	<a href="ListaCuentasServlet?idCuentaAlta=<%=cuentaXNumero.getId()%>"  class="btn btn-outline-dark">Alta</a>
		            </td>
					<%  }%>
					<td>
						<a href="InformeServlet?idCuenta=<%=cuentaXNumero.getId()%>"  class="btn btn-outline-dark">Pedir Informe</a>			
					</td>

				</tr>
				<%
           
           }
           if(request.getAttribute("CBU")!=null )
           {
           	cuentaXCbu=(Cuentas)request.getAttribute("CBU");
           	if(listaTipoCuentas!=null)
            {
           	 for(TipoDeCuenta tipoCuenta:listaTipoCuentas)
            {
           		 if(tipoCuenta.getID()==cuentaXNumero.getTipoCuenta())descripcionTipoCuenta=tipoCuenta.getDescripcion();
            }
           	 }
         
           	%>
				<tr>

					<th scope="row"><%=cuentaXCbu.getId() %></th>
					<td><%=cuentaXCbu.getFechaAlta() %></td>
					<td><%=descripcionTipoCuenta%></td>
					<td><%=cuentaXCbu.getNumCuenta() %></td>
					<td><%=cuentaXCbu.getCbu() %></td>
					<td><%=cuentaXCbu.getSaldo() %></td>
					<%if(!cuentaXCbu.isBaja()){ %>
					<td>Activo</td>
					<td class="text-center">
		            	<a href="ListaCuentasServlet?idCuentaBaja=<%=cuentaXCbu.getId()%>"  class="btn btn-outline-dark">Baja</a>
		            </td>
					<%} 
		            else{%>
					<td>No Activo</td>
					<td class="text-center">
		            	<a href="ListaCuentasServlet?idCuentaAlta=<%=cuentaXCbu.getId()%>"  class="btn btn-outline-dark">Alta</a>
		            </td>
					<%  }%>
					<td>
						<a href="InformeServlet?idCuenta=<%=cuentaXCbu.getId()%>"  class="btn btn-outline-dark">Pedir Informe</a>			
					</td>

				</tr>

				<%
            }        
         
        	   if (cuentasFiltrada != null) {
        		    for (Cuentas c : cuentasFiltrada) {
        		        for (TipoDeCuenta tipoCuenta : listaTipoCuentas) {
        		            if (tipoCuenta.getID() == c.getTipoCuenta()) {
        		                descripcionTipoCuenta = tipoCuenta.getDescripcion();
        		                break; // Salir del bucle una vez que se encuentra el tipo de cuenta
        		            }
        		        }
        		        %>
        		        <tr>
        		            <th scope="row"><%=c.getId()%></th>
        		            <td><%=c.getFechaAlta()%></td>
        		            <td><%=descripcionTipoCuenta%></td>
        		            <td><%=c.getNumCuenta()%></td>
        		            <td><%=c.getCbu()%></td>
        		            <td><%=c.getSaldo()%></td>
        		            <% if (!c.isBaja()) { %>
        		            <td>Activo</td>
							<td class="text-center">
				            	<a href="ListaCuentasServlet?idCuentaBaja=<%=c.getId()%>"  class="btn btn-outline-dark">Baja</a>
				            </td>
							<%} 
				            else{%>
							<td>No Activo</td>
							<td class="text-center">
				            	<a href="ListaCuentasServlet?idCuentaAlta=<%=c.getId()%>"  class="btn btn-outline-dark">Alta</a>
				            </td>
							<%  }%>
        		            <td>
								<a href="InformeServlet?idCuenta=<%=c.getId()%>"  class="btn btn-outline-dark">Pedir Informe</a>			
							</td>
        		        </tr>
        		        <%
        		    }
        		} 
        	   if (request.getAttribute("Cuentas") != null) {
        		    cuentas = (List<Cuentas>) request.getAttribute("Cuentas");
        		    for (Cuentas cuenta : cuentas) {
        		        for (TipoDeCuenta tipoCuenta : listaTipoCuentas) {
        		            if (tipoCuenta.getID() == cuenta.getTipoCuenta()) {
        		                descripcionTipoCuenta = tipoCuenta.getDescripcion();
        		                break; // Salir del bucle una vez que se encuentra el tipo de cuenta
        		            }
        		        }
        		        %>
        		        <tr>
        		            <th scope="row"><%=cuenta.getId() %></th>
        		            <td><%=cuenta.getFechaAlta() %></td>
        		            <td><%=descripcionTipoCuenta%></td>
        		            <td><%=cuenta.getNumCuenta() %></td>
        		            <td><%=cuenta.getCbu() %></td>
        		            <td><%=cuenta.getSaldo() %></td>
        		            <% if (!cuenta.isBaja()) { %>
        		            <td>Activo</td>
							<td class="text-center">
				            	<a href="ListaCuentasServlet?idCuentaBaja=<%=cuenta.getId()%>"  class="btn btn-outline-dark">Baja</a>
				            </td>
							<%} 
				            else{%>
							<td>No Activo</td>
							<td class="text-center">
				            	<a href="ListaCuentasServlet?idCuentaAlta=<%=cuenta.getId()%>"  class="btn btn-outline-dark">Alta</a>
				            </td>
							<%  }%>
        		             <td>
								<a href="InformeServlet?idCuenta=<%=cuenta.getId()%>"  class="btn btn-outline-dark">Pedir Informe</a>			
							</td>
        		        </tr>
        		        <%
        		    }
        		}
        		%> </tbody>

        </table>
        </div>
        
        <%if(request.getAttribute("UsuarioCuentas")!=null){
        	Clientes cliente = (Clientes)request.getAttribute("UsuarioCuentas");
			int idUsuario = cliente.getId();
			%>
			<div class="d-flex justify-content-center mt-4">
				<a href="ListaCuentasServlet?idClienteNuevaCuenta=<%=idUsuario%>" class="btn btn-outline-dark btn-lg">Nueva Cuenta</a>
			</div>
			<%}%>
        
		<div class="d-flex justify-content-center mt-4">
			<a href="HomeServlet" class="btn btn-outline-dark btn-lg">Volver a Home</a>
		</div>
		
				
</body>
</html>