<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <title>Perfil</title>
</head>
<body>


<%
	Provincia provincia = null;
	if(request.getAttribute("Provincia") != null) {
		provincia = (Provincia) request.getAttribute("Provincia");
	}
	
	Localidad localidad = null;
	if(request.getAttribute("Localidad") != null) {
		localidad = (Localidad) request.getAttribute("Localidad");
	}
%>

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
            <div class="card shadow-sm border-light">
                <div class="card-body">
                    <h1 class="text-center mb-4">Perfil</h1>
                    <form>
                        <div class="row mb-3">
                            <div class="col-md-4 col-sm-12">
                                <label for="dni" class="form-label fw-bold">DNI:</label>
                            </div>
                            <div class="col-md-8 col-sm-12">
                                <label id="dni" class="form-control-plaintext">${Cliente.getDni()}</label>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4 col-sm-12">
                                <label for="cuil" class="form-label fw-bold">CUIL:</label>
                            </div>
                            <div class="col-md-8 col-sm-12">
                                <label id="cuil" class="form-control-plaintext">${Cliente.getCuil()}</label>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4 col-sm-12">
                                <label for="nombre" class="form-label fw-bold">Nombre:</label>
                            </div>
                            <div class="col-md-8 col-sm-12">
                                <label id="nombre" class="form-control-plaintext">${Cliente.getNombre()}</label>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4 col-sm-12">
                                <label for="apellido" class="form-label fw-bold">Apellido:</label>
                            </div>
                            <div class="col-md-8 col-sm-12">
                                <label id="apellido" class="form-control-plaintext">${Cliente.getApellido()}</label>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4 col-sm-12">
                                <label for="ddlSexo" class="form-label fw-bold">Sexo:</label>
                            </div>
                            <div class="col-md-8 col-sm-12">
                                <label id="ddlSexo" class="form-control-plaintext">${Cliente.getSexo()}</label>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4 col-sm-12">
                                <label for="fechaNacimiento" class="form-label fw-bold">Fecha de Nacimiento:</label>
                            </div>
                            <div class="col-md-8 col-sm-12">
                                <label id="fechaNacimiento" class="form-control-plaintext">${Cliente.getFechaNac()}</label>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4 col-sm-12">
                                <label for="ddlProvincia" class="form-label fw-bold">Provincia:</label>
                            </div>
                            <div class="col-md-8 col-sm-12">
                                <label id="ddlProvincia" class="form-control-plaintext"><%= provincia.getDescripcion() %></label>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4 col-sm-12">
                                <label for="ddlLocalidad" class="form-label fw-bold">Localidad:</label>
                            </div>
                            <div class="col-md-8 col-sm-12">
                                <label id="ddlLocalidad" class="form-control-plaintext"><%= localidad.getDescripcion() %></label>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4 col-sm-12">
                                <label for="correo" class="form-label fw-bold">Correo:</label>
                            </div>
                            <div class="col-md-8 col-sm-12">
                                <label id="correo" class="form-control-plaintext">${Cliente.getCorreo()}</label>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4 col-sm-12">
                                <label for="telefono" class="form-label fw-bold">Teléfono:</label>
                            </div>
                            <div class="col-md-8 col-sm-12">
                                <label id="telefono" class="form-control-plaintext">${Cliente.getTelefono()}</label>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4 col-sm-12">
                                <label for="usuario" class="form-label fw-bold">Usuario:</label>
                            </div>
                            <div class="col-md-8 col-sm-12">
                                <label id="usuario" class="form-control-plaintext">${Cliente.getUsuario()}</label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="d-flex justify-content-center mt-4">
        <a href="HomeServlet" class="btn btn-outline-dark btn-lg" style="display: inline-block; margin: 20px auto;">Volver a Home</a>
        <a class="btn btn-primary" href="Principal.jsp" style="display: inline-block; margin: 20px auto;">Cerrar sesion</a>
    </div>
</div>
</body>
</html>
