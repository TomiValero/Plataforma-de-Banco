<%@ page import="java.util.List" %>
<%@ page import="entidad.Cuota" %>
<%@ page import="entidad.Cuentas" %>
<%@page  import= "entidad.Clientes" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Menú de Pagos</title>
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
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="TransferirServlet">Transferir</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="PedirPrestamoServlet">Pedir Préstamo</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="PagoServlet">Mis Préstamos</a>
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
	List<Cuota> cuotas = null;
	if(request.getAttribute("listaCuotas")!=null)
	{
		cuotas = (List<Cuota>)request.getAttribute("listaCuotas");
	}
	
	String error = null;
	if(request.getAttribute("Error")!=null)
	{
		error = (String)request.getAttribute("Error");
	}
	
	
	List<Cuentas> misCuentas = null;
	if(request.getAttribute("listMisCuentas")!=null)
	{
		misCuentas = (List<Cuentas>)request.getAttribute("listMisCuentas");
	}
	%>

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
	
    
        <h1 class="text-center mb-4">Menu de Pago</h1>

        <% if (error != null) { %>
            <div class="alert alert-danger text-center"><%= error %></div>
        <% } %>

        <table class="table table-hover table-bordered">
            <thead class="bg-primary text-white text-center">
                <tr>
                    <th>N° Cuota</th>
                    <th>Fecha de pago</th>
                    <th>Monto</th>
                    <th>Estado</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <% 
                if (cuotas != null && !cuotas.isEmpty()) {
                    for (Cuota cuota : cuotas) {
                %>
                <tr class="text-center">
                    <td><%= cuota.getNumCuota() %></td>
                    <td><%= cuota.getPago() ? cuota.getFechaPago() : "Pendiente" %></td>
                    <td>$<%= cuota.getMonto() %></td>
                    <td><%= cuota.getPago() ? "Pagada" : "Pendiente" %></td>
                    <td>
                    <%if(!cuota.getPago()){ %>
                        <form action="MenuPagoServlet" method="post" class="">
                            <input type="hidden" name="idCuota" value="<%= cuota.getId()%>">
                            <div class="input-group" style="width:auto;">
						        <select id="cuenta" name="cuenta" required class="form-control" >
									<% if(misCuentas != null){
											for(Cuentas cuenta :misCuentas) {
												if(!cuenta.isBaja()){%>
											<option value="<%=cuenta.getId()%>"><%=cuenta.toString()%></option>
										<%}}
										}%>
								</select>
	                            <button type="submit" class="btn btn-success btn-sm">Pagar</button>
                            </div>
                        </form>
                        <%} else { %>
                        	<p>--</p>
                        <%} %>
                    </td>
                </tr>
                <% 
                    }
                } else { 
                %>
                <tr>
                    <td colspan="4" class="text-center">No hay cuotas pendientes.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <div class="d-flex justify-content-end mt-4">
            <a href="PagoServlet" class="btn btn-primary">Volver a Mis Préstamos</a>
        </div>
    </div>
</body>
</html>
