<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="jsp_util/comprobarNavegacion.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>TaskManager - Página principal del usuario</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="jquery/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">

			<%@ include file="jsp_util/titulo_barra_navegacion.jsp"%>

			<div id="barraNavegacion" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="cerrarSesion.jsp">cerrar sesión</a></li>
				</ul>
			</div>
		</div>
	</nav>


	<div class="container" style="margin-top: 50px">

		<h1>¡Bienvenido al Task-Manager!</h1>

		<i>Iniciaste sesión el <fmt:formatDate
				pattern="dd-MM-yyyy' a las 'HH:mm"
				value="${sessionScope.fechaInicioSesion}" /> (usuario número
			${contador})
		</i> <br /> <br />
		<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
		<table>
			<tr>
				<td>Email:</td>
				<td id="email"><jsp:getProperty property="email" name="user" /></td>
			</tr>
			<tr>
				<td>Login:</td>
				<td id="login"><jsp:getProperty property="login" name="user" /></td>
			</tr>
			<tr>
				<td>Estado:</td>
				<td id="status"><jsp:getProperty property="status" name="user" /></td>
			</tr>
		</table>
		<br />

		<div class="panel panel-default">
			<div class="panel-heading">Categorias del sistema</div>
			<div class="panel-body">
				<ul>
					<li><a
						href="listarTareas?CategoriaSistema=SI&nombreCategoria=Inbox">Inbox</a></li>
					<li><a
						href="listarTareas?CategoriaSistema=SI&nombreCategoria=Hoy">Hoy</a></li>
					<li><a
						href="listarTareas?CategoriaSistema=SI&nombreCategoria=Semana">Semana</a></li>
				</ul>
			</div>

		</div>

		<div class="panel panel-default">
			<div class="panel-heading">Categorias del usuario</div>
			<div class="panel-body">

				<table class="table table-striped table-bordered table-hover">
					<tr>
						<th>Nombre categoria</th>
						<th>Listar tareas</th>
						<th>Eliminar categoria</th>
					</tr>
					<c:forEach var="categoria" items="${listaCategorias}">
						<tr>
							<td>
								<form action="renombrarCategoria" method="post"
									name="renombrar_form" class="form-horizontal">
									<div class="col-xs-5">

										<input type="text" class="form-control"
											id="Categoria${categoria.id}" name="nombreCategoria"
											value="${categoria.name}" required />

												<input type="submit" value="Renombrar"
													class="btn btn-primary" />
									</div>

								</form>
							</td>
							<td><a
								href="listarTareas?CategoriaSistema=NO&Id=${categoria.id}">
									tareas</a></td>
							<td class="danger">
								<a onclick="return confirm('Eliminar categoria')" href="eliminarCategoria?id=${categoria.id}">eliminar</a>
							</td>
						
						
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>

		<div>
			<%@ include file="jsp_util/mensaje_advertencia.jsp"%>
		</div>
	</div>
</body>
</html>