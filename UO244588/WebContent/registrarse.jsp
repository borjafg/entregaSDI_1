<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>TaskManager - registrase</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="jquery/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- =================== -->
	<!-- Barra de navegación -->
	<!-- =================== -->

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">

			<%@ include file="jsp_util/titulo_barra_navegacion.jsp"%>

			<div id="barraNavegacion" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a id="inicioSesion" href="login.jsp">iniciar sesión</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- =================== -->
	<!-- Formulario de login -->
	<!-- =================== -->

	<div class="container" style="margin-top: 50px">
		<h1 class="col-sm-offset-3">Registrese</h1>
		<br />

		<form action="registrarse" method="post" name="registrarse_form"
			class="form-horizontal">

			<div class="form-group">
				<label class="control-label col-xs-3" for="campoLogin">Identificador
					de usuario:</label>

				<div class="col-xs-5">
					<input type="text" class="form-control" id="campoLogin"
						placeholder="Escriba su identificador" name="login" required />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3" for="campoEmail">Email:</label>

				<div class="col-xs-5">
					<input type="email" class="form-control" id="campoEmail"
						placeholder="Escriba su email" name="email" required />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3" for="campoPassword1">Contraseña:</label>

				<div class="col-xs-5">
					<input type="password" class="form-control" id="campoPassword1"
						placeholder="Escriba su contraseña" name="password1" required />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3" for="campoPassword2">Repita
					la contraseña:</label>

				<div class="col-xs-5">
					<input type="password" class="form-control" id="campoPassword2"
						placeholder="Repita la contraseña" name="password2" required />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-3 col-xs-3">
					<input type="submit" value="Enviar" class="btn btn-primary" />
				</div>
			</div>
		</form>

		<!-- ========================================== -->
		<!-- Pie de página (Posibles mensajes de error) -->
		<!-- ========================================== -->

		<br />

		<div>
			<%@ include file="jsp_util/mensaje_advertencia.jsp"%>
		</div>
	</div>

</body>
</html>