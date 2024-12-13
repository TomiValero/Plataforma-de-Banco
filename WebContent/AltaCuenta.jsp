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
<title>Alta de Cuenta</title>
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
	<% 
	List<TipoDeCuenta> listaTipoCuentas=null;
	if(request.getAttribute("TipoCuentas")!=null)
    {
 	   listaTipoCuentas=(List <TipoDeCuenta> )request.getAttribute("TipoCuentas");
    } 
	Cuentas nuevaCuenta = null;
	if(request.getAttribute("NuevaCuenta")!=null)
    {
		nuevaCuenta=(Cuentas)request.getAttribute("NuevaCuenta");
    }
	
    %>
	
		<div class="container-md align-items-center">
		<div class="col" style="padding-top:10px">
			<div style="padding: 20px; max-width:; border: 1px solid #ddd; border-radius: 10px; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); margin: 20px auto;">
			    <h1 class="text-center" style="margin-bottom:20px">Alta de Cuenta</h1>
			    <%if(nuevaCuenta == null){ %>
			    <form action="AltaCuentaServlet" method="post">
			    	<div class="row">
			    		<div class="col">			        
					        <label for="txtTipoCuenta" class="form-label">Tipo de cuenta:</label>
					        <select id="txtTipoCuenta" name="txtTipoCuenta" required class="form-control mb-2">
								<%for(TipoDeCuenta tipo :listaTipoCuentas) {%>
										<option value="<%=tipo.getID()%>"><%=tipo.getDescripcion()%></option>
									<%} %>							
							</select>
				        </div>
				        <div class="d-flex justify-content-center">
					        <input type="submit" value="Dar de Alta" class="btn btn-primary mx-2">
				        </div>
			        </div>
			    </form>
			    <%} else {%>
			    <h2 class="text-center" style="margin-bottom:10px">Cuenta Creada</h2>
			    <table  id="table_id" class="display table">
		            <thead class="table-dark">
		                <tr>
				            <th scope="col">Fecha creacion</th>
				            <th scope="col">Tipo de cuenta</th>
				            <th scope="col">Numero de cuenta</th>
				            <th scope="col">CBU</th>
				            <th scope="col">Saldo</th>
				            <th scope="col">Activo</th>
			          	</tr>
		            </thead>
					<tbody>
					<% 
					String descripcionTipoCuenta = "";
					if(listaTipoCuentas!=null)
		            {
			           	 for(TipoDeCuenta tipoCuenta: listaTipoCuentas)
			            	{
			           		 if(tipoCuenta.getID()==nuevaCuenta.getTipoCuenta())descripcionTipoCuenta=tipoCuenta.getDescripcion();
			            	}
		           	}
					%>
						<tr>
							<td><%=nuevaCuenta.getFechaAlta() %></td>
							<td><%=descripcionTipoCuenta%></td>
							<td><%=nuevaCuenta.getNumCuenta() %></td>
							<td><%=nuevaCuenta.getCbu() %></td>
							<td><%=nuevaCuenta.getSaldo() %></td>
							<%if(!nuevaCuenta.isBaja()){ %>
							<td>Activo</td>							
							<%} 
				            else{%>
							<td>No Activo</td>					
							<%  }%>
					</tr>
				</tbody>
			</table>
		    <%} %>			    
		    </div>
	    </div>
	    <div class="d-flex justify-content-center mt-4">
			<a href="HomeServlet" class="btn btn-outline-dark btn-lg">Volver a Home</a>
		</div>
</body>
</html>