<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="jsp_util/comprobarNavegacion.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>TaskManager - Página principal del usuario</title>
</head>
<body>
	<i>Iniciaste sesión el <fmt:formatDate
			pattern="dd-MM-yyyy' a las 'HH:mm"
			value="${sessionScope.fechaInicioSesion}" /> (usuario número
		${contador})
	</i>
	<br />
	<br />
	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	<table>
		<tr>
			<td>Id:</td>
			<td id="id"><jsp:getProperty property="id" name="user" /></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td id="email"><form action="modificarDatos" method="POST">
					<input type="text" name="email" size="15"
						value="<jsp:getProperty property="email" name="user"/>"> <input
						type="submit" value="Modificar">
				</form></td>
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
		<div class="panel-heading">Categorias del sistema	</div>
		<div class="panel-body">
			<ul>
				<li> <a href="listarTareas?CategoriaSistema=SI&nombreCategoria=Inbox">Inbox</a></li>
				<li> <a href="listarTareas?CategoriaSistema=SI&nombreCategoria=Hoy">Hoy</a></li>
				<li>  <a href="listarTareas?CategoriaSistema=SI&nombreCategoria=Semana">Semana</a></li>
			</ul>
		</div>
	
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">Categorias del usuario</div>
		<div class="panel-body">
			<c:forEach  var="categoria" items="${listaCategorias}" >
				<li> <a href="listarTareas?CategoriaSistema=NO$Id=${categoria.Id}"> ${categoria.name}</a> </li>
			</c:forEach>
		</div>
	</div>
	
	
	
	<a id="cerrarSesion_link_id" href="cerrarSesion">Cerrar sesión</a>
	
	<div>
		<%@ include file="jsp_util/mensaje_advertencia.jsp"%>
	</div>
</body>
</html>