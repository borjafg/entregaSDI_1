<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ include file="jsp_util/comprobarNavegacion.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Task Manager - Listado de Tareas</title>
</head>
<body>
	<div class="container" style="margin-top: 50px">
	<h1 class="text-center">Lista de tareas</h1>
	<br/>
	
	<%@ include file="jsp_util/mensaje_advertencia.jsp"%>
	<%@ include file="jsp_util/mensaje_exito.jsp"%>
	
	
	<br/>
	<br/>
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
					<td> - </td>
				</c:when>
				<c:otherwise>
					<td>${tarea.category.name}</td>
				</c:otherwise>	
			</c:choose>
			<td>${tarea.created}</td>
			<c:choose>
				<c:when test="${empty tarea.planned}">
					<td> - </td>
				</c:when>
				<c:otherwise>
					<td>${tarea.category.planned}</td>
				</c:otherwise>	
			</c:choose>
			<td class="danger"><a onclick="return confirm('Finalizar tarea')" href="finalizarTarea?id=${tarea.id}"></a></td>			
		</tr>	
	</c:forEach>
	</table>
</div>

</body>
</html>