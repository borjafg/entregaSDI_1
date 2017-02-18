<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="jsp_util/comprobarNavegacion.jsp"%>
<html>
<head>
<title>TaskManager - Administración de usuarios</title>
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
					<li><a href="cerrarSesion">cerrar sesión</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- =================== -->
	<!-- Tabla de usuarios -->
	<!-- =================== -->

	<div class="container" style="margin-top: 50px">


		<!-- =========================================================== -->
		<!-- Pie de página (Posibles mensajes de error o de información) -->
		<!-- =========================================================== -->

		<div class="col-sm-offset-3">
			<%@ include file="jsp_util/mensaje_advertencia.jsp"%>
		</div>

		<br />

		<div class="col-sm-offset-3">
			<%@ include file="jsp_util/mensaje_exito.jsp"%>
		</div>
	</div>

</body>
</html>