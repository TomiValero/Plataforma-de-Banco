<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script><title>Iniciar Sesión</title>
</head>
<body>
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


	<div class="container-md align-items-center">
		<div class="row" style="padding-top:100px;">
			<div class="col-md-6">
			    <div style="max-width:400px; max-hieght:300px; padding: 20px;">
			    	<h1 style="font-size: 80px">Bienvenido a Banco Nacion</h1>
			    </div>	
		    </div>
		
			<div class="col-md-6">
				<div style="padding: 20px; max-width: 400px; border: 1px solid #ddd; border-radius: 10px; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); margin: 20px auto;">
				    <form action="LoginServlet" method="post">
				        <label for="usuario" class="form-label">Ingrese su usuario:</label>
				        <input type="text" id="usuario" name="txtusuario" required class="form-control"><br><br>
				        
				        <label for="contrasena" class="form-label" style="margin-bottom:5px">Ingrese su contraseña:</label>
				        <input type="password" id="contrasena" name="txtcontrasena" required class="form-control"><br><br>
			        		
				        <input type="submit" value="Iniciar Sesión" class="btn btn-primary">
				    </form>
			    </div>
		    </div>
		    
	    </div>
    </div>
</body>
</html>
