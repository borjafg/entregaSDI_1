<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="jsp_util/comprobarNavegacion.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Task Manager - Listado de Tareas</title>
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
					<li><a id="registrarse" href="registrarse.jsp">registrarse</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- =================== -->
	<!-- Formulario de login -->
	<!-- =================== -->

	<div class="container" style="margin-top: 50px">
		<h1 class="col-sm-offset-3">Datos de la tarea</h1>
		<br />

		<form action="modificarDatosTarea" method="post"
			name="modificar_tarea_form" class="form-horizontal">

			<%-- ------------------------------------------------------ --%>
			<%-- Información necesaria para volver a la lista de tareas --%>
			<%-- ------------------------------------------------------ --%>

			<c:choose>
				<c:when test="${CategoriaSistema == 'NO' }">
					<input type="hidden" name="CategoriaSistema"
						value="${CategoriaSistema}" />
					<input type="hidden" name="idCategoria" value="${idCategoria}" />
				</c:when>

				<c:otherwise>
					<input type="hidden" name="CategoriaSistema"
						value="${CategoriaSistema}" />
					<input type="hidden" name="nombreCategoria"
						value="${nombreCategoria}" />
				</c:otherwise>
			</c:choose>

			<%-- Información de la tarea que se quiere modificar --%>
			<input type="hidden" name="idTarea" value="${tarea.id}" />

			<%-- ----------------------------------------- --%>
			<%-- Datos que se pueden modificar de la tarea --%>
			<%-- ----------------------------------------- --%>

			<div class="form-group">
				<label id="labelNombre" class="control-label col-xs-3"
					for="campoNombre">Nombre de la tarea: </label>

				<div class="col-xs-5">
					<input type="text" class="form-control" id="campoNombre"
						placeholder="Escriba el nombre de la tarea" name="nombre"
						value="${tarea.title}" required />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3" id="labelComentario"
					for="campoComentario">Comentario: </label>

				<div class="col-xs-5">
					<input type="text" class="form-control" id="campoComentario"
						placeholder="Escriba un comentario sobre la tarea"
						name="comentario" value="${tarea.comments}" required />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3" id="labelFechaPlaneada"
					for="campoFechaPlaneada">Fecha planeada (formato
					dia/mes/año Ej: 21/12/2017): </label>

				<div class="col-xs-5">
					<input type="date" class="form-control" id="campoFechaPlaneada"
						placeholder="Escriba la fecha de planificación de la tarea"
						name="fechaPlaneada" value="${tarea.planned}" required />
				</div>
			</div>

			<br/>
			<select name="idCategTarea" required>
					<c:forEach var="categ" items="${categoriasUsuario}">
						<option value="${categ.id}">${categ.name}</option>
					</c:forEach>
				</select>

			<%-- ---------------------------------------- --%>
			<%-- Botón para cambiar los datos de la tarea --%>
			<%-- ---------------------------------------- --%>

			<div class="form-group">
				<div class="col-sm-offset-3 col-xs-3">
					<input type="submit" value="Cambiar datos" class="btn btn-primary" />
				</div>
			</div>
		</form>

		<!-- =========================================================== -->
		<!-- Pie de página (Posibles mensajes de error o de información) -->
		<!-- =========================================================== -->

		<br />

		<div>
			<%@ include file="jsp_util/mensaje_advertencia.jsp"%>
		</div>
	</div>
</body>