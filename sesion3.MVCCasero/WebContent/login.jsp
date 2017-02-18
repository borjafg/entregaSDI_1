<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>TaskManager - Inicie sesión</title>
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
					<li><a href="registrarse.jsp">registrarse</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- =================== -->
	<!-- Formulario de login -->
	<!-- =================== -->

	<div class="container" style="margin-top: 50px">
		<h1 class="col-sm-offset-3">Inicie sesión</h1>
		<br />

		<form action="validarse" method="post" name="validarse_form"
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
				<label class="control-label col-xs-3" for="campoPassword">Contraseña:</label>

				<div class="col-xs-5">
					<input type="password" class="form-control" id="campoPassword"
						placeholder="Escriba su contraseña" name="password" required />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-3 col-xs-3">
					<input type="submit" value="Enviar" class="btn btn-primary" />
				</div>
			</div>
		</form>

		<!-- =========================================================== -->
		<!-- Pie de página (Posibles mensajes de error o de información) -->
		<!-- =========================================================== -->

		<div class="col-sm-offset-3">
			<%@ include file="jsp_util/mensaje_advertencia.jsp"%>
		</div>

		<div class="col-sm-offset-3">
			<%@ include file="jsp_util/mensaje_exito.jsp"%>
		</div>
	</div>

</body>
</html>