<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>TaskManager - Inicie sesión</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="custom_css/css_centrar.css" rel="stylesheet">
</head>
<body>
	<!-- =================== -->
	<!-- Barra de navegación -->
	<!-- =================== -->

	<nav class="navbar navbar-default ">
		<div class="container-fluid">

			<!-- Nombre de la aplicación -->
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Task Manager</a>
			</div>

			<div>
				<ul class="nav navbar-nav">
					<li><a href="registrarse">registrarse</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- =================== -->
	<!-- Formulario de login -->
	<!-- =================== -->

	<h1 class="col-sm-offset-2">Inicie sesión</h1>
	<br />

	<form action="validarse" method="post" name="validarse_form"
		class="form-horizontal">

		<div class="form-group">
			<label class="control-label col-xs-2" for="campoLogin">Identificador
				de usuario:</label>

			<div class="col-xs-4">
				<input type="text" class="form-control" id="campoLogin"
					placeholder="Escriba su identificador" name="login" required />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-xs-2" for="campoPassword">Contraseña:</label>

			<div class="col-xs-4">
				<input type="password" class="form-control" id="campoPassword"
					placeholder="Escriba su contraseña" name="password" required />
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-xs-2">
				<input type="submit" value="Enviar" class="btn btn-primary" />
			</div>
		</div>
	</form>

	<!-- ========================================== -->
	<!-- Pie de página (Posibles mensajes de error) -->
	<!-- ========================================== -->

	<%@ include file="pieDePagina.jsp"%>

</body>
</html>