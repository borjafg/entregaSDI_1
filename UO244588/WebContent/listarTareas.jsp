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
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<th>Nombre tarea</th>
				<th>Categoria</th>
				<th>Fecha de creación</th>
				<th>Fecha planeada</th>
				<th>Finalizar tarea</th>
			</tr>
			<c:forEach var="tarea" items="${listaTareas}">
				<tr>
					<td><a href="TareaPrincipal?id=${tarea.id}">${tarea.title}</a></td>
					<c:choose>
						<c:when test="${empty tarea.category.name}">
							<td>------</td>
						</c:when>
						<c:otherwise>
							<td>${tarea.category.name}</td>
						</c:otherwise>
					</c:choose>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${tarea.created}"/></td>
					<c:choose>
						<c:when test="${empty tarea.planned}">
							<td>------</td>
						</c:when>
						<c:otherwise>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${tarea.planned}"/></td>
						</c:otherwise>
					</c:choose>
					<td class="danger"><a
						onclick="return confirm('Finalizar tarea')"
						href="finalizarTarea?id=${tarea.id}">terminar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>