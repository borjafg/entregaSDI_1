<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="jsp_util/comprobarNavegacion.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.uniovi.es/sdi/fecha_anterior_tlib"
	prefix="fech"%>
<!DOCTYPE html>
<html>
<head>
<title>Task Manager - Listado de Tareas</title>
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
					<li><a href="listarCategorias">listar categorias</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container" style="margin-top: 50px">
		<h1 class="text-center">Lista de tareas</h1>
		<br />

		<%@ include file="jsp_util/mensaje_advertencia.jsp"%>
		<%@ include file="jsp_util/mensaje_exito.jsp"%>

		<br />

		<div class="panel panel-default">
			<div class="panel-heading">Añadir una nueva tarea</div>
			<div class="panel-body">
				<c:choose>
					<c:when test="${CategoriaSistema == 'NO' }">
						<form action="nuevaTarea">

							<input type="hidden" name="CategoriaSistema"
								value="${CategoriaSistema}" /> <input type="hidden"
								name="idCategoria" value="${idCategoria}" />

							<div class="form-group">
								<label for="nombreTarea">Nombre de la tarea</label> <input
									type="text" class="form-control" id="nombreTarea"
									name="nombreTarea" placeholder="Escriba el nombre de la tarea">
							</div>

							<input type="submit" value="Crear nueva tarea"
								class="btn btn-primary" />
						</form>
					</c:when>
					<c:otherwise>
						<%-- Categoria del sistema == 'SI' --%>
						<form action="nuevaTarea">

							<input type="hidden" name="CategoriaSistema"
								value="${CategoriaSistema}" /> <input type="hidden"
								name="nombreCategoria" value="${nombreCategoria}" />

							<div class="form-group">
								<label for="nombreTarea">Nombre de la tarea</label> <input
									type="text" class="form-control" id="nombreTarea"
									name="nombreTarea" placeholder="Escriba el nombre de la tarea">
							</div>

							<input type="submit" value="Crear nueva tarea"
								class="btn btn-primary" />
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<br />
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<th>Nombre tarea</th>
				<th>Categoria</th>
				<th>Comentarios</th>
				<th>Fecha de creación</th>
				<th>Fecha planeada</th>
				<th>Fecha finalizada</th>
				<th>Modificar tarea</th>
				<th>Finalizar tarea</th>
			</tr>

			<c:forEach var="tarea" items="${listaTareas}">
				<tr>
					<td><a href="TareaPrincipal?id=${tarea.id}">${tarea.title}</a></td>
					<c:choose>
						<c:when test="${empty tarea.category.name}">
							<c:choose>
								<c:when
									test="${(CategoriaSistema == 'SI' && nombreCategoria == 'Semana')}">
									<td><fech:categ_rojo_tareas_retrasadas_semana
											fecha_evaluada="${tarea.planned}" nombreCategoria="------" /></td>
								</c:when>
								<c:otherwise>
									<td>------</td>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<%-- Con categoria asignada --%>
							<c:choose>
								<c:when
									test="${CategoriaSistema == 'SI' && nombreCategoria == 'Semana'}">
									<td><fech:categ_rojo_tareas_retrasadas_semana
											fecha_evaluada="${tarea.planned}"
											nombreCategoria="${tarea.category.name}" /></td>
								</c:when>
								<c:otherwise>
									<td>${tarea.category.name}</td>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>

					<td>${tarea.comments}</td>

					<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${tarea.created}" /></td>

					<c:choose>
						<c:when test="${empty tarea.planned}">
							<td>------</td>
						</c:when>
						<c:otherwise>
							<%-- Tarea fecha planeada --%>
							<c:choose>
								<c:when
									test="${CategoriaSistema == 'SI' && nombreCategoria == 'Semana'}">
									<td><fmt:formatDate pattern="dd/MM/yyyy"
											value="${tarea.planned}" /></td>
								</c:when>
								<c:otherwise>
									<td><fech:fecha_rojo_tareas_retrasadas
											fecha_evaluada="${tarea.planned}"
											fecha_finalizacion="${tarea.finished}" /></td>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${empty tarea.finished}">
							<td>------</td>
						</c:when>
						<c:otherwise>
							<td><fmt:formatDate pattern="dd/MM/yyyy"
									value="${tarea.finished}" /></td>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${not empty tarea.finished}">
							<td class="default">------</td>
						</c:when>
						<c:otherwise>
							<td><c:choose>
									<c:when test="${CategoriaSistema == 'NO' }">
										<a href="modificarTarea?idTarea=${tarea.id}&CategoriaSistema=${CategoriaSistema}&idCategoria=${idCategoria}">Cambiar datos</a>
									</c:when>
									<c:otherwise>
										<a href="modificarTarea?idTarea=${tarea.id}&CategoriaSistema=${CategoriaSistema}&nombreCategoria=${nombreCategoria}">Cambiar datos</a>
									</c:otherwise>
								</c:choose></td>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${not empty tarea.finished}">
							<td class="default">------</td>
						</c:when>
						<c:otherwise>
							<td class="danger"><c:choose>
									<c:when test="${CategoriaSistema == 'NO' }">
										<a onclick="return confirm('Finalizar tarea')"
											href="finalizarTarea?id=${tarea.id}&CategoriaSistema=${CategoriaSistema}&idCategoria=${idCategoria}">finalizar</a>
									</c:when>
									<c:otherwise>
										<a onclick="return confirm('Finalizar tarea')"
											href="finalizarTarea?id=${tarea.id}&CategoriaSistema=${CategoriaSistema}&nombreCategoria=${nombreCategoria}">finalizar</a>
									</c:otherwise>
								</c:choose></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>