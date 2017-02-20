<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="jsp_util/comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>TaskManager - Administración de usuarios</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	TYPE="text/css">
<script type="text/javascript" src="jquery/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<%-- ===================
		 Barra de navegación
		 =================== --%>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">

			<%@ include file="jsp_util/titulo_barra_navegacion.jsp"%>

			<div id="barraNavegacion" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a id="cerrarSesion" href="cerrarSesion">cerrar sesión</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<%-- ===================
		 Tabla de usuarios
		 =================== --%>

	<div class="container" style="margin-top: 50px">
		<h1 class="text-center">Lista de usuarios del sistema</h1>
		<br />

		<%@ include file="jsp_util/mensaje_advertencia.jsp"%>
		<%@ include file="jsp_util/mensaje_exito.jsp"%>

		<table class="table table-striped table-bordered table-hover">
			<tr>
				<th>Login</th>
				<th>Email</th>
				<th>Estado</th>
				<th>Cambiar estado</th>
				<th>Eliminar del sistema</th>
			</tr>
			<c:forEach var="usuario" items="${listaUsuarios}">
				<tr>
					<td>${usuario.login}</td>
					<td>${usuario.email}</td>
					<td>${usuario.status.name().toLowerCase()}</td>
					<c:choose>
						<c:when test="${usuario.status == 'DISABLED'}">
							<td class="warning"><a id="habilitar${usuario.login}"
								href="cambiarEstadoUsuario?opcion=habilitar&id=${usuario.id}">habilitar</a></td>
						</c:when>
						<c:otherwise>
							<td class="warning"><a id="deshabilitar${usuario.login}"
								href="cambiarEstadoUsuario?opcion=deshabilitar&id=${usuario.id}">deshabilitar</a></td>
						</c:otherwise>
					</c:choose>
					<td class="danger"><a id="eliminar_${usuario.login}"
						onclick="return confirm('Eliminar usuario')"
						href="borrarUsuario?id=${usuario.id}">borrar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>