<%@page import="entidad.Cuentas"%>
<%@page import="entidad.Clientes"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informe</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

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
<%
    Cuentas miCuenta = null;
    if (request.getAttribute("MiCuenta") != null) {
    	miCuenta = (Cuentas) request.getAttribute("MiCuenta");
    }

    Clientes clienteInforme = null;
    if (request.getAttribute("ClienteInforme") != null) {
        clienteInforme = (Clientes) request.getAttribute("ClienteInforme");
    }

    boolean mostrarInforme = false;
    if (request.getAttribute("MostrarInforme") != null) {
        mostrarInforme = (boolean) request.getAttribute("MostrarInforme");
    }

    double[] extracionesMes = null;
    if (request.getAttribute("ExtraidoMes") != null) {
        extracionesMes = (double[]) request.getAttribute("ExtraidoMes");
    }

    double[] entranteMes = null;
    if (request.getAttribute("EntranteMes") != null) {
        entranteMes = (double[]) request.getAttribute("EntranteMes");
    }

    double extraidoTotal = 0;
    if (request.getAttribute("ExtraidoTotal") != null) {
        extraidoTotal = (double) request.getAttribute("ExtraidoTotal");
    }

    double entranteTotal = 0;
    if (request.getAttribute("EntranteTotal") != null) {
        entranteTotal = (double) request.getAttribute("EntranteTotal");
    }

    int[] cantExtracionesMes = null;
    if (request.getAttribute("CantExtraidoMes") != null) {
        cantExtracionesMes = (int[]) request.getAttribute("CantExtraidoMes");
    }

    int[] cantEntranteMes = null;
    if (request.getAttribute("CantEntranteMes") != null) {
        cantEntranteMes = (int[]) request.getAttribute("CantEntranteMes");
    }

    int cantExtraido = 0;
    if (request.getAttribute("CantExtraidoTotal") != null) {
        cantExtraido = (int) request.getAttribute("CantExtraidoTotal");
    }

    int cantEntrante = 0;
    if (request.getAttribute("CantEntranteTotal") != null) {
        cantEntrante = (int) request.getAttribute("CantEntranteTotal");
    }
    
    int mesInicio = 0;
    if (request.getAttribute("MesInicioSelect") != null) {
    	mesInicio = (int) request.getAttribute("MesInicioSelect");
    }
    
    int mesFin = 12;
    if (request.getAttribute("MesFinSelect") != null) {
    	mesFin = (int) request.getAttribute("MesFinSelect");
    }
%>



<div class="container-md align-items-center">
		<div class="col" style="padding-top:10px">
			<div style="padding: 20px; max-width:; border: 1px solid #ddd; border-radius: 10px; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); margin: 20px auto;">
			    <h1 class="text-center" style="margin-bottom:20px">Pedir informe</h1>
			    <form action="InformeServlet" method="post">
			    	<div class="row">
			    		<div class="col">		
			    			<label for="txtCliente" class="form-label">Cliente:</label>
					        <input type="text" id="txtCliente" name="txtCliente" required class="form-control mb-2" value="<%=clienteInforme.nombreApellido()%>" disabled>
					        
					        <label for="ddlCuenta" class="form-label">Cuenta:</label>
					        <input type="text" id="ddlCuenta" name="ddlCuenta" required class="form-control mb-2" value="<%=miCuenta.toString()%>" disabled>
					        
					       <%--  <select id="ddlCuenta" name="ddlCuenta" required class="form-control mb-2">
			                       <% if(misCuentas != null){
										for(Cuentas cuenta :misCuentas) {
											if(!cuenta.isBaja()){%>
										<option value="<%=cuenta.getId()%>"><%=cuenta.toString()%></option>
									<%}}
									}%>	                     
			                </select>
			                --%>
					        	      
					        <label for="txtAnio" class="form-label">Año:</label>
			                    <input type="number" id="txtAnio" name="txtAnio" required class="form-control mb-2">
					        	        
					        <label for="mesInicio" class="form-label">Mes de Inicio:</label>
								<select id="mesInicio" name="mesInicio" class="form-control mb-2" required>
								    <option value="" selected>Seleccionar Mes</option>
								    <option value="0">Enero</option>
								    <option value="1">Febrero</option>
								    <option value="2">Marzo</option>
								    <option value="3">Abril</option>
								    <option value="4">Mayo</option>
								    <option value="5">Junio</option>
								    <option value="6">Julio</option>
								    <option value="7">Agosto</option>
								    <option value="8">Septiembre</option>
								    <option value="9">Octubre</option>
								    <option value="10">Noviembre</option>
								    <option value="11">Diciembre</option>
								</select>
								
							<label for="mesFin" class="form-label">Mes de Fin:</label>
								<select id="mesFin" name="mesFin" class="form-control mb-2" required>
								    <option value="" selected>Seleccionar Mes</option>
								    <option value="0">Enero</option>
								    <option value="1">Febrero</option>
								    <option value="2">Marzo</option>
								    <option value="3">Abril</option>
								    <option value="4">Mayo</option>
								    <option value="5">Junio</option>
								    <option value="6">Julio</option>
								    <option value="7">Agosto</option>
								    <option value="8">Septiembre</option>
								    <option value="9">Octubre</option>
								    <option value="10">Noviembre</option>
								    <option value="11">Diciembre</option>
								</select>

					        
				        </div>
				        <div class="d-flex justify-content-center">
					        <input type="submit" value="Pedir Informe" class="btn btn-primary mx-2">
				        </div>
			        </div>
			    </form>
		    </div>
		    
		    
		    <%if(mostrarInforme){ %>
		    <div style="padding: 20px; max-width: 600px; border: 1px solid #e0e0e0; border-radius: 15px; box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.1); margin: 30px auto;">
			    <h1 class="text-center" style="margin-bottom: 25px;">Informe</h1>
		        <table id="table_id" class="display table">
    				<thead class="table-dark">
					    <tr>
					      <th scope="col">Mes</th>
					      <th scope="col">Dinero Entrante</th>
					      <th scope="col">Cantidad de Entrantes</th>
					      <th scope="col">Dinero Extraído</th>
					      <th scope="col">Cantidad de Extraciones</th>
					      <th scope="col">Balance del mes</th>
					    </tr>
					  </thead>
					  <tbody>
					    <% 
                String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		                for (int i = mesInicio; i <= mesFin; i++) { 
		            %>
					    <tr>
					      <th scope="row"><%= meses[i] %></th>
					      <td class="text-success"><%=entranteMes[i]%></td>
					      <td><%=cantEntranteMes[i] %></td>
					      <td class="text-danger"><%=extracionesMes[i] %></td>
					      <td><%=cantExtracionesMes[i] %></td>
					      <% double balance = entranteMes[i] - extracionesMes[i];
					      if(balance > 0) { %>
					      	<td class="text-success"><%=balance %></td>
					      <%} else if(balance < 0) { %>
					      	<td class="text-danger"><%=balance %></td>
					      <%} else { %>
					     	<td><%=balance %></td>
					      <%} %>
					    </tr>
				    <%} %>
				    	<tr class="table-secondary">
					      <th scope="row">Total</th>
					      <td class="text-success" ><%=entranteTotal%></td>
					      <td><%=cantEntrante %></td>
					      <td class="text-danger"><%=extraidoTotal %></td>
					      <td><%=cantExtraido %></td>
					      <% double balance = entranteTotal - extraidoTotal;
					      if(balance > 0) { %>
					      	<td class="text-success"><%=balance %></td>
					      <%} else if(balance < 0) { %>
					      	<td class="text-danger"><%=balance %></td>
					      <%} else { %>
					     	<td><%=balance %></td>
					      <%} %>
					    </tr>
					  </tbody>
					</table>
			</div>

		    <%} %>
		    
	    </div>
	    <div class="d-flex justify-content-center mt-4">
			<a href="HomeServlet" class="btn btn-outline-dark btn-lg">Volver a Home</a>
		</div>
</div>

</body>
</html>